package com.example.oven.service;

import com.example.oven.program.Oven;
import com.example.oven.repository.OvenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OvenServiceImplementation implements OvenService{
    private final OvenRepository ovenRepository;

    @Autowired
    public OvenServiceImplementation(OvenRepository ovenRepository) {
        this.ovenRepository = ovenRepository;
    }

    @Override
    public Oven getFirst() {
        List<Oven> all = ovenRepository.findAll();
        return all.size() == 0 ? null : all.get(0);
    }

    @Override
    public Oven save(Oven oven) {
        return null;
    }
}
