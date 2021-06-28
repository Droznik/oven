package com.example.oven.program;

import com.example.oven.util.OvenMode;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("baking")
public class Baking extends Process {

    public Baking() {
    }

    public Baking(Long duration, Integer temperature, Enum<OvenMode> mode) {
        super(duration, temperature, mode);
    }

    @Override
    public String toString() {
        return "Baking " + super.toString();
    }
}
