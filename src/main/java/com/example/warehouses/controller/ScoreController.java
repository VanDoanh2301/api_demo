package com.example.warehouses.controller;

import com.example.warehouses.model.domain.Score;
import com.example.warehouses.model.domain.User;
import com.example.warehouses.model.dto.ScoreDto;
import com.example.warehouses.model.service.ScoreService;
import com.example.warehouses.model.service.UserService;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("")
public class ScoreController {
    @Autowired
    private ScoreService scoreService;

    @Autowired
    private UserService userService;



    @PostMapping("insertScore")
    private ResponseEntity<?> insertScore(@RequestBody ScoreDto scoreDto) {

        if(StringUtils.isBlank(String.valueOf(scoreDto.getDate()))) {
            return ResponseEntity.ok("Date is required");
        }
        if(StringUtils.isBlank(String.valueOf(scoreDto.getUserId()))) {
            return ResponseEntity.ok("User Id is required");
        }

        Score score = new Score();
        score.setScore(scoreDto.getScore());
        score.setDate(scoreDto.getDate());
        score.setUserId(scoreDto.getUserId());
        scoreService.save(score);
        return ResponseEntity.ok("Save score");

    }
    @GetMapping
    public ResponseEntity<?> getScoresByDay(@RequestParam String day,
                                                      @RequestParam(defaultValue = "0") int page,
                                                      @RequestParam(defaultValue = "50") int size
                                                  ) {
        Pageable paging = PageRequest.of(page, size, Sort.by("score").descending());
        Page<Score> pageScores = scoreService.findByDateLike("%-" + day, paging);

        if (pageScores.isEmpty()) {
            return ResponseEntity.ok("Page Score is null");
        }

        Score score = pageScores.stream()
                .filter(s -> s.getUserId().equals(userId))
                .findFirst()
                .orElse(null);

        if (score == null) {
            return ResponseEntity.ok("Score is null");
        }

        return ResponseEntity.ok("oke");
    }


}
