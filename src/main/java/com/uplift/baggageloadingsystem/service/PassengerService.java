package com.uplift.baggageloadingsystem.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.uplift.baggageloadingsystem.domain.Bus;
import com.uplift.baggageloadingsystem.domain.LoadingBay;
import com.uplift.baggageloadingsystem.domain.Passenger;
import com.uplift.baggageloadingsystem.forms.PassengerForm;
import com.uplift.baggageloadingsystem.repository.BusRepository;
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

@Service
public class PassengerService {

    private PassengerRepository passengerRepository;
    private BusRepository busRepository;

    @Value("${qrcode.path}")
    private String qrcodePath;

    PassengerService(PassengerRepository passengerRepository, BusRepository busRepository) {
        this.passengerRepository = passengerRepository;
        this.busRepository = busRepository;
    }

    public PassengerForm createPassenger(PassengerForm form) {
        Bus bus = busRepository.findOne(form.getBusId());
        String guid = UUID.randomUUID().toString();
        form.setFee(new BigDecimal(20.0 * form.getBaggageWeight()));
        form.setCode(guid);
        form.setQrCodeUrl(generateQrCode(guid));
        Passenger passenger = new Passenger(form);
        passenger.setBus(bus);
        passenger = passengerRepository.save(passenger);
        form.setId(passenger.getId());
        return form;
    }

    private String generateQrCode(String code) {
        File saveDir = new File(qrcodePath);
        System.out.println(qrcodePath);
        if(!saveDir.exists())
            saveDir.mkdirs();
        String fileType = "png";
        TimeZone tz = TimeZone.getTimeZone("UTC");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH_mm_ss_SSS'Z'");
        df.setTimeZone(tz);
        String nowAsISO = df.format(new Date());
        String fileName = nowAsISO + ".png";
        String filePath = qrcodePath + "/" + fileName;
        int size = 250;
        File myFile = new File(filePath);
        try {

            Map<EncodeHintType, Object> hintMap = new EnumMap<EncodeHintType, Object>(EncodeHintType.class);
            hintMap.put(EncodeHintType.CHARACTER_SET, "UTF-8");

            // Now with zxing version 3.2.1 you could change border size (white border size to just 1)
            hintMap.put(EncodeHintType.MARGIN, 1); /* default = 4 */
            hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix byteMatrix = qrCodeWriter.encode(code, BarcodeFormat.QR_CODE, size,
                    size, hintMap);
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

        return "http://localhost:8084/upload/" + fileName;
    }
}
