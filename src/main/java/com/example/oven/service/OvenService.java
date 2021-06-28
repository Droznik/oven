package com.example.oven.service;

import com.example.oven.program.Oven;

public interface OvenService {
    Oven getFirst();
    Oven save (Oven oven);
}
