package com.company.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Airplane {
    private static double MAX_FUEL = 5;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne
    @NotNull
    private Airport location;
    private double fuelLevel;

    public long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private String type;

    public void fillTank(){
        fuelLevel = MAX_FUEL;
    }

    public Airport getLocation() {
        return location;
    }

    public void setLocation(Airport location) {
        this.location = location;
    }

    public double getFuelLevel() {
        return fuelLevel;
    }

    public void setFuelLevel(double fuelLevel) {
        this.fuelLevel = fuelLevel;
    }
}
