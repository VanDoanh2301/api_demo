package com.example.warehouses.model.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Content {
    private Integer index;
    private String userId;
    private Integer score;
    private LocalDateTime scoreDate;
    private String userName;
    private String avatar;
}
