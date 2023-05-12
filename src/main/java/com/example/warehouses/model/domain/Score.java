package com.example.warehouses.model.domain;
//insert score, getscorebyday, getscoreBymonth,getscoreWeek

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "score")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Score {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "score", length = 125, nullable =false)
    private Integer score;

    @Column(name = "date", length = 125, nullable =false)
    private String date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"score", "handler","hibernateLazyInitializer"}, allowSetters = true)
    @JoinColumn(name = "userId")
    private User user;





}
