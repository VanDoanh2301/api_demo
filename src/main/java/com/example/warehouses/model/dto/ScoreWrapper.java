package com.example.warehouses.model.dto;

import com.example.warehouses.model.domain.Score;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScoreWrapper {
    private int index_user;
    private int score;
    private  String userId;
    private LocalDateTime date;


}
