package com.example.oven.program;


import com.example.oven.util.OvenMode;

import javax.persistence.*;

@Entity
@Table(name = "PROCESS")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
public abstract class Process extends JPA {
    private Long duration;
    private Integer temperature;
    private Enum<OvenMode> mode;

    public Process() {
    }

    public Process(Long duration, Integer temperature, Enum<OvenMode> mode) {
        this.duration = duration;
        this.temperature = temperature;
        this.mode = mode;
    }

    @Basic(optional = false)
    @Column(name = "duration")
    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    @Basic(optional = false)
    @Column(name = "temperature")
    public Integer getTemperature() {
        return temperature;
    }

    public void setTemperature(Integer temperature) {
        this.temperature = temperature;
    }

    @Basic(optional = false)
    @Column(name = "mode")
    public Enum<OvenMode> getMode() {
        return mode;
    }

    public void setMode(Enum<OvenMode> mode) {
        this.mode = mode;
    }

    @Override
    public String toString() {
        return "Process{" +
                "duration=" + duration +
                ", temperature=" + temperature +
                ", mode=" + mode +
                '}';
    }
}
