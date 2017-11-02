package com.uplift.baggageloadingsystem.api;

import com.uplift.baggageloadingsystem.domain.LoadingBay;
import com.uplift.baggageloadingsystem.domain.Porter;
import com.uplift.baggageloadingsystem.domain.PorterLog;
import com.uplift.baggageloadingsystem.forms.PorterForm;
import com.uplift.baggageloadingsystem.forms.PorterLogForm;
import com.uplift.baggageloadingsystem.repository.LoadingBayRepository;
import com.uplift.baggageloadingsystem.repository.PorterRepository;
import com.uplift.baggageloadingsystem.service.PorterService;
import com.uplift.baggageloadingsystem.validators.PorterValidator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/porter")
public class PorterController {

    private PorterRepository porterRepository;
    private LoadingBayRepository loadingBayRepository;
    private PorterService porterService;
    private PorterValidator porterValidator;

    PorterController(PorterRepository porterRepository, LoadingBayRepository loadingBayRepository,
                     PorterService porterService, PorterValidator porterValidator){
        this.porterRepository = porterRepository;
        this.loadingBayRepository = loadingBayRepository;
        this.porterService = porterService;
        this.porterValidator = porterValidator;
    }

    @InitBinder("porterForm")
    public void setupBinder(WebDataBinder binder) {
        binder.addValidators(porterValidator);
    }

//    @PostMapping("/log")
//    public PorterLogForm createPorterLog(@RequestBody PorterLogForm form) {
//        return porterService.createPorterLog(form);
//    }

    @GetMapping("/{id}")
    public Porter getPorterById(@PathVariable("id") Integer id){
        return porterRepository.findOne(id);
    }

//    @GetMapping("/{id}/loading-bay")
//    public Collection<LoadingBay> getLoadingBay(@PathVariable("id") Integer id) {
//        Porter porter = porterRepository.findOne(id);
//        return porter.getLoadingBays();
//    }
//
//    @PostMapping("/{id}/loading-bay")
//    public Collection<LoadingBay> updateLoadingBay(@PathVariable("id") Integer id,
//                                                         @RequestBody List<Integer> loadingBays) {
//        Collection <LoadingBay> loadingBaysIns = loadingBays.stream()
//                                                    .map(e -> loadingBayRepository.findOne(e))
//                                                    .collect(Collectors.toList());
//
//        Porter porter = porterRepository.findOne(id);
//        porter.getLoadingBays().addAll(loadingBaysIns);
//        porter.setLoadingBays(porter.getLoadingBays());
//        porterRepository.save(porter);
//        return porter.getLoadingBays();
//    }


    @GetMapping
    public Collection<Porter> getPorter() {
        return porterRepository.findAll();
    }

    @PostMapping
    public PorterForm createPorter(@Validated(PorterForm.Create.class) @RequestBody PorterForm porterForm){
        return porterService.createPorter(porterForm);
    }

    @PutMapping
    public Porter updatePorter(@RequestBody Porter form){
        return porterRepository.save(form);
    }
}
