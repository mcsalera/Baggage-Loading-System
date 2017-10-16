package com.uplift.baggageloadingsystem.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.uplift.baggageloadingsystem.domain.Baggage;
import com.uplift.baggageloadingsystem.domain.BaggageCounter;
import com.uplift.baggageloadingsystem.domain.LoadingBay;
import com.uplift.baggageloadingsystem.domain.Passenger;
import com.uplift.baggageloadingsystem.forms.PassengerForm;
import com.uplift.baggageloadingsystem.repository.BaggageCounterRepository;
import com.uplift.baggageloadingsystem.repository.BaggageRepository;
import com.uplift.baggageloadingsystem.repository.LoadingBayRepository;
import com.uplift.baggageloadingsystem.repository.PassengerRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class PassengerService {

    private PassengerRepository passengerRepository;
    private BaggageRepository baggageRepository;
    private BaggageCounterRepository baggageCounterRepository;
    private LoadingBayRepository loadingBayRepository;

    @Value("${qrcode.baggage}")
    private String baggageQrCodePath;
    @Value("${qrcode.passenger}")
    private String passengerQrCodePath;
    @Value("${qrcode.file.server}")
    private String fileServer;

    PassengerService(PassengerRepository passengerRepository, BaggageRepository baggageRepository,
                     BaggageCounterRepository baggageCounterRepository, LoadingBayRepository loadingBayRepository) {
        this.passengerRepository = passengerRepository;
        this.baggageRepository = baggageRepository;
        this.baggageCounterRepository = baggageCounterRepository;
        this.loadingBayRepository = loadingBayRepository;
    }

    public PassengerForm createPassenger(PassengerForm form) {
        form.setFee(new BigDecimal(20.0 * form.getBaggageWeight()));
        HashMap<String, String> passengerQrCode = generateQrCode(false);
        form.setPassengerQrCodeUrl(passengerQrCode.get("url"));
        form.setCode(passengerQrCode.get("code"));
        LoadingBay loadingBay = this.loadingBayRepository.findOne(form.getLoadingBayId());
        Passenger passengerForm = new Passenger(form);
        passengerForm.setLoadingBay(loadingBay);
        Passenger passenger = passengerRepository.save(passengerForm);
        form.setId(passenger.getId());
        List<Map<String, String>> qrCodes =  IntStream.range(0, form.getBaggageCount())
                                                        .mapToObj( e -> generateQrCode(true))
                                                        .collect(Collectors.toList());
        qrCodes.forEach(e -> {
            Baggage baggage = new Baggage();
            baggage.setQrCodeUrl(e.get("url"));
            baggage.setCode(e.get("code"));
            baggage.setPassenger(passenger);
            this.baggageRepository.save(baggage);
        });

        return form;
    }

    public Collection<Baggage> getPassengerBaggage(String code) {
        Passenger passenger = this.passengerRepository.findByCode(code);
        return passenger.getBaggages();
    }

    private HashMap<String, String> generateQrCode(boolean isBaggage) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH_mm_ss_SSS'Z'");
        df.setTimeZone(TimeZone.getTimeZone("UTC"));
        String code = UUID.randomUUID().toString(),
            savePath = isBaggage? this.baggageQrCodePath : this.passengerQrCodePath,
            fileServer = this.fileServer + "upload/",
            fileType = "png",
            nowAsISO = df.format(new Date()),
            fileName = nowAsISO + ".png",
            filePath = savePath + "/" + fileName;

        fileServer += isBaggage? "baggage/" : "passenger/";
        fileServer += fileName;

        HashMap<String, String> result = new HashMap<>();
        result.put("code", code);
        result.put("url", fileServer);
        File saveDir = new File(savePath);

        if(!saveDir.exists())
            saveDir.mkdirs();

        final int SIZE = 250;
        File myFile = new File(filePath);

        try {

            Map<EncodeHintType, Object> hintMap = new EnumMap<EncodeHintType, Object>(EncodeHintType.class);
            hintMap.put(EncodeHintType.CHARACTER_SET, "UTF-8");

            // Now with zxing version 3.2.1 you could change border size (white border size to just 1)
            hintMap.put(EncodeHintType.MARGIN, 1); /* default = 4 */
            hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix byteMatrix = qrCodeWriter.encode(code, BarcodeFormat.QR_CODE, SIZE,
                    SIZE, hintMap);

            int width = byteMatrix.getWidth();
            BufferedImage image = new BufferedImage(width, width,
                    BufferedImage.TYPE_INT_RGB);
            image.createGraphics();

            Graphics2D graphics = (Graphics2D) image.getGraphics();
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, width, width);
            graphics.setColor(Color.BLACK);

            for (int i = 0; i < width; i++) {
                for (int j = 0; j < width; j++) {
                    if (byteMatrix.get(i, j)) {
                        graphics.fillRect(i, j, 1, 1);
                    }
                }
            }
            ImageIO.write(image, fileType, myFile);
        } catch (WriterException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
