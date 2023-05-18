package com.example.warehouses.model.dto;

import com.example.warehouses.model.domain.Score;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserWrapper {
    private int index;
    private int score;
    private String time;
    private List<ContentDto> hight_score;
}
