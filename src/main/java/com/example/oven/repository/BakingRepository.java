package com.example.oven.repository;

import com.example.oven.program.Baking;
import org.springframework.data.repository.CrudRepository;

public interface BakingRepository extends CrudRepository<Baking,Long> {
       Baking save(Baking process);
}
