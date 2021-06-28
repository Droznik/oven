package com.example.oven.service;

import com.example.oven.program.Baking;
import com.example.oven.repository.BakingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BakingServiceImplementation implements BakingService {
    private final BakingRepository bakingRepository;

    @Autowired
    public BakingServiceImplementation(BakingRepository bakingRepository) {
        this.bakingRepository = bakingRepository;
    }

    @Override
    public Baking save(Baking baking) {
        return bakingRepository.save(baking);
    }
}
