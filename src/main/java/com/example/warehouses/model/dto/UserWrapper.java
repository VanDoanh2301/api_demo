package com.example.warehouses.model.dto;

import com.example.warehouses.model.domain.Score;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserWrapper {
    private int index;
    private List<ContentDto> hight_score;
}
