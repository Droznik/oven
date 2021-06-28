package com.example.oven.repository;

import com.example.oven.program.WarmUp;
import org.springframework.data.repository.CrudRepository;

public interface WarmUpRepository extends CrudRepository<WarmUp, Long> {
    WarmUp save(WarmUp warmUp);
}
