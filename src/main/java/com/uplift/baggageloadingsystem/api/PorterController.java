package com.uplift.baggageloadingsystem.api;

import com.uplift.baggageloadingsystem.domain.LoadingBay;
import com.uplift.baggageloadingsystem.domain.Porter;
import com.uplift.baggageloadingsystem.repository.LoadingBayRepository;
import com.uplift.baggageloadingsystem.repository.PorterRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/porter")
public class PorterController {

    private PorterRepository porterRepository;
    private LoadingBayRepository loadingBayRepository;

    PorterController(PorterRepository porterRepository){
        this.porterRepository = porterRepository;
    }

    @GetMapping
    public Collection<Porter> getPorter() {
        return porterRepository.findAll();
    }

    @GetMapping("/{id}")
    public Porter getPorterById(@PathVariable("id") Integer id){
        return porterRepository.findOne(id);
    }

    @PostMapping
    public Porter createPorter(@RequestBody Porter form){
        return porterRepository.save(form);
    }

    @PutMapping
    public Porter updatePorter(@RequestBody Porter form){
        return porterRepository.save(form);
    }

    @GetMapping("/{id}/loading-bay")
    public Collection<LoadingBay> getLoadingBay(@PathVariable("id") Integer id) {
        Porter porter = porterRepository.findOne(id);
        return porter.getLoadingBays();
    }

    @PostMapping("/{id}/loading-bay")
    public Collection<LoadingBay> updateLoadingBay(@PathVariable("id") Integer id,
                                                         @RequestBody List<Integer> loadingBays) {
        Collection <LoadingBay> loadingBaysIns = loadingBays.stream()
                                                    .map(e -> loadingBayRepository.findOne(e))
                                                    .collect(Collectors.toList());

        Porter porter = porterRepository.findOne(id);
        porter.getLoadingBays().addAll(loadingBaysIns);
        porter.setLoadingBays(porter.getLoadingBays());
        porterRepository.save(porter);
        return porter.getLoadingBays();
    }
}
