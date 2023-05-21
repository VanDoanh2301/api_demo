package com.example.warehouses.model.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//CREATE TABLE "warehouses" (
//        "_id"  INTEGER NOT NULL,
//        "surface"  TEXT,
//        "mean"  TEXT,
//        "favorite"  INTEGER,
//        "timeaccess"  TEXT,
//        "count"  INTEGER,
//        "active"  TEXT,
//        "time_play"  TEXT,
//        PRIMARY KEY("_id" AUTOINCREMENT)
//        );


@Entity
@Data
@Table(name = "warehouse")
@NoArgsConstructor
public class WareHouses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer _id;

    @Column(name = "surface", length = 125, nullable = false, unique = true)
    private String surface;

    @Column(name = "mean", length = 125, nullable = true)
    private String mean;

    @Column(name = "favorite", length = 125, nullable = true)
    private Integer favorite;

    @Column(name = "time_access", length = 125, nullable = true)
    private String timeAccess;

    @Column(name = "count", length = 125, nullable = true)
    private Integer count;

    @Column(name = "active", length = 125, nullable = true)
    private String active;

    @Column(name = "time_play", length = 125, nullable = true)
    private String timePlay;

    @Column(name = "delete" , length = 10, nullable = true)
    private Integer delete;

    public WareHouses(Integer _id, String surface, Integer delete) {
        this._id = _id;
        this.surface = surface;
        this.delete = delete;
    }
}
