package com.example.oven.program;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PROGRAM")
public class Program extends JPA {
    private WarmUp warmUp;
    private Baking baking;

    public Program() {
    }

    public Program(WarmUp warmUp, Baking baking) {
        this.warmUp = warmUp;
        this.baking = baking;
    }

    @OneToOne(optional = false)
    @JoinColumn(name = "warmup")
    public WarmUp getWarmUp() {
        return warmUp;
    }
    public void setWarmUp(WarmUp warmUp) {
        this.warmUp = warmUp;
    }

    @OneToOne(optional = false)
    @JoinColumn(name = "baking")
    public Baking getBaking() {
        return baking;
    }
    public void setBaking(Baking baking) {
        this.baking = baking;
    }

    @Override
    public String toString() {
        return "Program{" +
                "warmUp=" + warmUp.toString() +
                ", baking=" + baking.toString() +
                '}';
    }
}
