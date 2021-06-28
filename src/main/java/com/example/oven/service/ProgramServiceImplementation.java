package com.example.oven.service;

import com.example.oven.program.Program;
import com.example.oven.repository.ProgramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProgramServiceImplementation implements ProgramService {
    private final ProgramRepository programRepository;

    @Autowired
    public ProgramServiceImplementation(ProgramRepository programRepository) {
        this.programRepository = programRepository;
    }

    @Override
    public Program save(Program program) {
        return programRepository.save(program);
    }
}
