package com.uplift.baggageloadingsystem.api;

import com.uplift.baggageloadingsystem.domain.LoadingBay;
import com.uplift.baggageloadingsystem.domain.Porter;
import com.uplift.baggageloadingsystem.repository.LoadingBayRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping(value = "/api/loading-bay")
public class LoadingBayController {

    private LoadingBayRepository loadingBayRepository;

    LoadingBayController(LoadingBayRepository loadingBayRepository) {
        this.loadingBayRepository = loadingBayRepository;
    }

    @GetMapping
    public Collection<LoadingBay> getLoadingBay() {
        return this.loadingBayRepository.findAll();
    }

    @GetMapping("/{id}")
    public LoadingBay getLoadingBayById(@PathVariable("id") Integer id){
        return loadingBayRepository.findOne(id);
    }

    @GetMapping("/{id}/porter")
    public Collection<Porter> getLoadingBayPorter(@PathVariable("id") Integer id){
        return loadingBayRepository.findOne(id).getPorters();
    }
}
