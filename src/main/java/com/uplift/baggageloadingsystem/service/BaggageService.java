package com.uplift.baggageloadingsystem.service;

import com.uplift.baggageloadingsystem.api.exceptions.ResourceNotFoundException;
import com.uplift.baggageloadingsystem.domain.Baggage;
import com.uplift.baggageloadingsystem.domain.LoadingBay;
import com.uplift.baggageloadingsystem.repository.BaggageRepository;
import org.springframework.stereotype.Service;

@Service
public class BaggageService {

    private BaggageRepository baggageRepository;
    private MessagingService messagingService;

    public BaggageService(BaggageRepository baggageRepository, MessagingService messagingService) {
        this.baggageRepository = baggageRepository;
        this.messagingService = messagingService;
    }

    public Baggage loadBaggage(String code){
        Baggage baggage = baggageRepository.findByCode(code);
        if(baggage == null)
            throw new ResourceNotFoundException("Baggage does not exists");
        baggage.setStatus("LOADED");
        baggage = this.baggageRepository.save(baggage);
        LoadingBay loadingBay = baggage.getPassenger().getLoadingBay();
        String message = String.format("Your baggage has been loaded to %s at %s", loadingBay.getBusCompany(), loadingBay.getLoadingBayName());
        String number = baggage.getPassenger().getContactNumber();
        messagingService.sendMessage(String.format("+63%s",number.substring(1)), message);
        return baggage;
    }

}
