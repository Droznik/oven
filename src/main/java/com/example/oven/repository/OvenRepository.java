package com.example.oven.repository;

import com.example.oven.program.Oven;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OvenRepository extends CrudRepository<Oven, Long> {
    List<Oven> findAll();
    Oven save(Oven appliance);
}
