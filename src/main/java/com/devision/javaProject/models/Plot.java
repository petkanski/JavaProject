package com.devision.javaProject.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity(name = "plots")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Plot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private double coordinates;

    private String crops;

    public Plot() {
    }

    public Plot(String name, double coordinates, String crops){
        this.name = name;
        this.coordinates = coordinates;
        this.crops = crops;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(double coordinates) {
        this.coordinates = coordinates;
    }

    public String getCrops() {
        return crops;
    }

    public void setCrops(String crops) {
        this.crops = crops;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Plot plot = (Plot) o;
        return Double.compare(plot.coordinates, coordinates) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(coordinates);
    }
}
