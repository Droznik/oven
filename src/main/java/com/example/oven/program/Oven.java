package com.example.oven.program;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "APPLIANCE")
public class Oven extends JPA implements Programmable,Appliance {
    private String model;
    private String serial;
    private List<Program> programs;

    @Override
    @Basic(optional = false)
    @Column(name = "model")
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    @Basic(optional = false)
    @Column(name = "serial")
    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    @Override
    @Fetch(FetchMode.SUBSELECT)
    @OneToMany(fetch = FetchType.EAGER)
    public List<Program> getPrograms() {
        return programs;
    }

    public void setPrograms(List<Program> programs) {
        this.programs = programs;
    }
}
