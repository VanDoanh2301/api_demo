package com.example.warehouses.controller;

import com.example.warehouses.model.domain.Score;
import com.example.warehouses.model.domain.User;
import com.example.warehouses.model.dto.ContentDto;
import com.example.warehouses.model.dto.ScoreDto;
import com.example.warehouses.model.repository.ScoreRepository;
import com.example.warehouses.model.service.ScoreService;
import com.example.warehouses.model.service.UserService;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.IsoFields;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("")
public class ScoreController {
    @Autowired
    private ScoreService scoreService;

    @Autowired
    private UserService userService;

    @Autowired
    private ScoreRepository scoreRepository;



    @PostMapping("insertScore")
    private ResponseEntity<?> insertScore(@RequestBody ScoreDto scoreDto) {
        if(StringUtils.isBlank(String.valueOf(scoreDto.getUserId()))) {
            return ResponseEntity.ok("User Id is required");
        }
        Score score = new Score();
        score.setScore(scoreDto.getScore());
        score.setDate(LocalDate.now());
        score.setUserId(scoreDto.getUserId());
        scoreService.save(score);
        return ResponseEntity.ok("Save score");

    }
    @GetMapping("getScoreByDay")
    public ResponseEntity<?> getTopScoresByDayLike(@RequestParam(name = "userId", required = false) String userId) {
        LocalDate today = LocalDate.now();
        int currentDay = today.getDayOfMonth();
        List<Score> scores = scoreRepository.findByDay(currentDay);
        Map<String, Integer> maxScoreByUserId = new HashMap<>();
        List<Score> filteredScores = new ArrayList<>();
        for (Score score : scores) {
            if (!maxScoreByUserId.containsKey(score.getUserId())
                    || score.getScore() > maxScoreByUserId.get(score.getUserId())) {
                maxScoreByUserId.put(score.getUserId(), score.getScore());
                filteredScores.add(score);
            }
        }

        filteredScores.sort(Comparator.comparing(Score::getScore).reversed());
        List<Score> topScores = filteredScores.stream()
                .limit(50)
                .collect(Collectors.toList());
        if (topScores.isEmpty()) {
            return ResponseEntity.ok("Scores is null");
        }
        List<ContentDto> contentDtos = new ArrayList<>();
        for(Score s : topScores) {
            User user = userService.findUserByUserId(s.getUserId());
            if(user == null) {
                continue;
            }
            ContentDto contentDto = new ContentDto();
            contentDto.setUserId(s.getUserId());
            contentDto.setScore(s.getScore());
            contentDto.setScoreDate(s.getDate());
            contentDto.setUserName(user.getName());
            contentDto.setAvatar(user.getAvatar());
            contentDtos.add(contentDto);
        }
        if(userId != null) {
            Score score = topScores.stream()
                    .filter(s -> s.getUserId().equals(userId))
                    .findFirst()
                    .orElse(null);
            if (score == null) {
                return ResponseEntity.ok("User is null");
            }
            User user = userService.findUserByUserId(score.getUserId());
            return ResponseEntity.ok("Index user: "+ user.getId());
        }
        return ResponseEntity.ok(contentDtos);
    }

    @GetMapping("/getScoreByMonth")
    public ResponseEntity<?> getScoresByMonth(@RequestParam(name = "userId", required = false) String userId) {
        LocalDate today = LocalDate.now();
        int currentMonth= today.getMonthValue();
        List<Score> scores = scoreRepository.findByMonth(currentMonth);
        Map<String, Integer> maxScoreByUserId = new HashMap<>();
        List<Score> filteredScores = new ArrayList<>();
        for (Score score : scores) {
            if (!maxScoreByUserId.containsKey(score.getUserId())
                    || score.getScore() > maxScoreByUserId.get(score.getUserId())) {
                maxScoreByUserId.put(score.getUserId(), score.getScore());
                filteredScores.add(score);
            }
        }

        filteredScores.sort(Comparator.comparing(Score::getScore).reversed());
        List<Score> topScores = filteredScores.stream()
                .limit(50)
                .collect(Collectors.toList());
        if (topScores.isEmpty()) {
            return ResponseEntity.ok("Scores is null");
        }
        List<ContentDto> contentDtos = new ArrayList<>();
        for(Score s : topScores) {
            User user = userService.findUserByUserId(s.getUserId());
            ContentDto contentDto = new ContentDto();
            contentDto.setUserId(s.getUserId());
            contentDto.setScore(s.getScore());
            contentDto.setScoreDate(s.getDate());
            contentDto.setUserName(user.getName());
            contentDto.setAvatar(user.getAvatar());
            contentDtos.add(contentDto);
        }
        if(userId != null) {
            Score score = topScores.stream()
                    .filter(s -> s.getUserId().equals(userId))
                    .findFirst()
                    .orElse(null);
            if (score == null) {
                return ResponseEntity.ok("User is null");
            }
            User user = userService.findUserByUserId(score.getUserId());
            return ResponseEntity.ok("Index user: "+ user.getId());
        }

        return ResponseEntity.ok(contentDtos);
    }

    @GetMapping("/getScoreByWeek")
    public ResponseEntity<?> getScoresByWeek(@RequestParam(name = "userId", required = false) String userId) {
        LocalDate today = LocalDate.now();
        int currentYear = today.getYear();
        int currentWeek = today.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR);
        List<Score> scores = scoreRepository.findByWeekNumber(currentWeek, currentYear);
        Map<String, Integer> maxScoreByUserId = new HashMap<>();
        List<Score> filteredScores = new ArrayList<>();
        for (Score score : scores) {
            if (!maxScoreByUserId.containsKey(score.getUserId())
                    || score.getScore() > maxScoreByUserId.get(score.getUserId())) {
                maxScoreByUserId.put(score.getUserId(), score.getScore());
                filteredScores.add(score);
            }
        }

        filteredScores.sort(Comparator.comparing(Score::getScore).reversed());
        List<Score> topScores = filteredScores.stream()
                .limit(50)
                .collect(Collectors.toList());
        if (topScores.isEmpty()) {
            return ResponseEntity.ok("Scores is null");
        }
        List<ContentDto> contentDtos = new ArrayList<>();
        for(Score s : topScores) {
            User user = userService.findUserByUserId(s.getUserId());
            ContentDto contentDto = new ContentDto();
            contentDto.setUserId(s.getUserId());
            contentDto.setScore(s.getScore());
            contentDto.setScoreDate(s.getDate());
            contentDto.setUserName(user.getName());
            contentDto.setAvatar(user.getAvatar());
            contentDtos.add(contentDto);
        }
        if(userId != null) {
            Score score = topScores.stream()
                    .filter(s -> s.getUserId().equals(userId))
                    .findFirst()
                    .orElse(null);
            if (score == null) {
                return ResponseEntity.ok("User is null");
            }
            User user = userService.findUserByUserId(score.getUserId());
            return ResponseEntity.ok("Index user: "+ user.getId());
        }

        return ResponseEntity.ok(contentDtos);
    }

    }
