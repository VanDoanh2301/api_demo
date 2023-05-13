package com.example.warehouses.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContentDto {
    private String userId;
    private Integer score;
    private LocalDate scoreDate;
    private String userName;
    private String avatar;
}
