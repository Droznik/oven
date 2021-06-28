package com.example.oven.service;

import com.example.oven.program.WarmUp;
import com.example.oven.repository.WarmUpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WarmUpServiceImplementation implements WarmUpService {
    private final WarmUpRepository warmUpRepository;

    @Autowired
    public WarmUpServiceImplementation(WarmUpRepository warmUpRepository) {
        this.warmUpRepository = warmUpRepository;
    }

    @Override
    public WarmUp save(WarmUp warmup) {
        return warmUpRepository.save(warmup);
    }
}
