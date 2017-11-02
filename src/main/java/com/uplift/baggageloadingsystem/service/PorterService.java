package com.uplift.baggageloadingsystem.service;

import com.uplift.baggageloadingsystem.domain.LoadingBay;
import com.uplift.baggageloadingsystem.domain.Porter;
import com.uplift.baggageloadingsystem.domain.PorterLog;
import com.uplift.baggageloadingsystem.forms.PorterForm;
import com.uplift.baggageloadingsystem.forms.PorterLogForm;
import com.uplift.baggageloadingsystem.repository.LoadingBayRepository;
import com.uplift.baggageloadingsystem.repository.PorterLogRepository;
import com.uplift.baggageloadingsystem.repository.PorterRepository;
import com.uplift.baggageloadingsystem.utils.Utility;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PorterService {
    private PorterRepository porterRepository;
    private PorterLogRepository porterLogRepository;
    private LoadingBayRepository loadingBayRepository;

    PorterService(PorterRepository porterRepository, PorterLogRepository porterLogRepository,
                    LoadingBayRepository loadingBayRepository){
        this.porterRepository = porterRepository;
        this.porterLogRepository = porterLogRepository;
        this.loadingBayRepository = loadingBayRepository;
    }

    @Transactional
    public PorterForm createPorter(PorterForm form){
        List<LoadingBay> loadingBays = form.getLoadingBayIds()
                                            .stream()
                                            .map(id -> loadingBayRepository.findOne(id))
                                            .collect(Collectors.toList());
        form.setStatus("OFFLINE");
        Porter porter = form.toPorter();
        porter.setLoadingBays(loadingBays);
        porter = porterRepository.save(porter);
        form.setId(porter.getId());
        return form;
    }

    @Transactional
    public Porter updatePorter(PorterForm form){
        Porter porter = porterRepository.findOne(form.getId());
        Utility.copyProperties(form, porter);
        return porterRepository.save(porter);
    }

    @Transactional
    public PorterLogForm createPorterLog(PorterLogForm form){
        Porter porter = porterRepository.findOne(form.getPorterId());
        PorterLog log = porterLogRepository.findTopByPorterOrderByIdDesc(porter);
        log = (log != null && log.getLoginTime().toLocalDate().equals(LocalDate.now()))? log: new PorterLog();
        String action;
        if(form.isLogin()){
            log.setPorter(porter);
            porter.setStatus("ONLINE");
            porterLogRepository.save(log);
            action = "LOGIN";
        } else {
            log.setLogoutTime(LocalDateTime.now());
            porter.setStatus("OFFLINE");
            porterLogRepository.save(log);
            action = "LOGOUT";
        }
        return PorterLogForm
                .builder()
                .action(action)
                .timestamp(LocalDateTime.now())
                .porterName(String.format("%s %s", porter.getFirstName(), porter.getLastName()))
                .status(porter.getStatus())
                .build();
    }
}