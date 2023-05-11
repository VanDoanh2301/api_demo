package com.example.warehouses.model.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
public class WareHouseDto {

    private Integer id;

    private String surface;

    private String mean;


    private Integer favorite;


    private String timeAccess;


    private Integer count;

    private String active;


    private String timePlay;

    public Integer getId() {
        return id;
    }

    public String getSurface() {
        return surface;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setSurface(String surface) {
        this.surface = surface;
    }

    public void setMean(String mean) {
        this.mean = mean;
    }

    public void setFavorite(Integer favorite) {
        this.favorite = favorite;
    }

    public void setTimeAccess(String timeAccess) {
        this.timeAccess = timeAccess;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public void setTimePlay(String timePlay) {
        this.timePlay = timePlay;
    }
}
