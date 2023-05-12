package com.example.warehouses.controller;

import com.example.warehouses.model.dto.ScoreDto;
import com.example.warehouses.model.service.ScoreService;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class ScoreController {
    @Autowired
    private ScoreService scoreService;

    @PostMapping("insertScore")
    private ResponseEntity<?> insertScore(@RequestBody ScoreDto scoreDto) {
        if(StringUtils.isBlank(String.valueOf(scoreDto.getScore()))) {
            return ResponseEntity.ok("Score is required");
        }
        if(StringUtils.isBlank(String.valueOf(scoreDto.getDate()))) {
            return ResponseEntity.ok("Date is required");
        }
        if(StringUtils.isBlank(String.valueOf(scoreDto.getUser().getUserId()))) {
            return ResponseEntity.ok("User Id is required");
        }


    }
}
