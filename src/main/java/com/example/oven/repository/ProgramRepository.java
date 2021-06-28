package com.example.oven.repository;

import com.example.oven.program.Program;
import org.springframework.data.repository.CrudRepository;

public interface ProgramRepository extends CrudRepository<Program, Long> {
    Program save(Program program);
}
