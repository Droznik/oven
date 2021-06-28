package com.example.oven.program;


import com.example.oven.util.OvenMode;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("warmup")
public class WarmUp extends Process {
    public WarmUp() {
    }

    public WarmUp(Long duration, Integer temperature, Enum<OvenMode> mode) {
        super(duration, temperature, mode);
    }

    @Override
    public String toString() {
        return "Warming Up " + super.toString();
    }
}

