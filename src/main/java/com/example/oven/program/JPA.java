package com.example.oven.program;


import javax.persistence.*;
import java.util.Objects;

@MappedSuperclass
public abstract class JPA {
    private long id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JPA jpa = (JPA) o;
        return id == jpa.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column( name = "id")
    public long getId() {
        return id;
    }

    public void setId(Long id){
    this.id = id;
    }
}
