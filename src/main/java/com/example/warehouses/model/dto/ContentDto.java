package com.example.warehouses.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContentDto {
    private Integer score;
    private LocalDateTime scoreDate;
    private String userName;
    private String avatar;
}
