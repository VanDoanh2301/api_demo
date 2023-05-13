package com.example.warehouses.controller;

import com.example.warehouses.model.domain.Score;
import com.example.warehouses.model.domain.User;
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
    @GetMapping("getScoreByDay")
    public ResponseEntity<List<Score>> getTopScoresByDayLike(@RequestParam String day) {
        List<Score> scores = scoreService.findDistinctByDateLikeOrderByScoreDesc("%" + day);

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
        return new ResponseEntity<>(topScores, HttpStatus.OK);
    }
    @GetMapping("getIndexUser")
    public ResponseEntity<?> getScoreIndexForUser( @RequestParam String day,@RequestParam String userId) {
        List<Score> scores = scoreService.findDistinctByDateLikeOrderByScoreDesc("%" + day);
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

        Score score = topScores.stream()
                .filter(s -> s.getUserId().equals(userId))
                .findFirst()
                .orElse(null);
        if (score == null) {
            return ResponseEntity.ok("User is null");
        }
        User user = userService.findUserByUserId(score.getUserId());
        return ResponseEntity.ok("Index user: " + user.getId());
    }
    @GetMapping("/getScoreByMonth")
    public ResponseEntity<List<Score>> getScoresByMonth(@RequestParam String month) {
        List<Score> scores = scoreRepository.findByDateLikeOrderByScoreDesc("%" + month + "-%");

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
        return new ResponseEntity<>(topScores, HttpStatus.OK);
    }
    @GetMapping("/getUserByMonth")
    public ResponseEntity<?> getUserByMonth(@RequestParam String month, @RequestParam String userId) {
        List<Score> scores = scoreRepository.findByDateLikeOrderByScoreDesc("%" + month + "-%");

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
        Score score = topScores.stream()
                .filter(s -> s.getUserId().equals(userId))
                .findFirst()
                .orElse(null);
        if (score == null) {
            return ResponseEntity.ok("User is null");
        }
        User user = userService.findUserByUserId(score.getUserId());
        return ResponseEntity.ok("Index user: " + user.getId());

    }
    @GetMapping("getScoreByWeek")
    public ResponseEntity<List<Score>> getScoresByWeek(
            @RequestParam int year,
            @RequestParam int month,
            @RequestParam int week) {

        LocalDate startDateOfWeek = LocalDate.of(year, month, 1)
                .with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY))
                .plusWeeks(week - 1);
        LocalDate endDateOfWeek = startDateOfWeek.plusDays(6);

        // Query the scores for the week
        List<Score> scores = scoreRepository.findByDateBetween(startDateOfWeek.toString(), endDateOfWeek.toString());

        // Filter the scores by selecting the ones with highest scores for each user
        Map<String, Score> maxScoresByUserId = new HashMap<>();
        for (Score score : scores) {
            String userId = score.getUserId();
            if (!maxScoresByUserId.containsKey(userId) || score.getScore() > maxScoresByUserId.get(userId).getScore()) {
                maxScoresByUserId.put(userId, score);
            }
        }

        // Sort and limit the top scores
        List<Score> topScores = maxScoresByUserId.values().stream()
                .sorted(Comparator.comparing(Score::getScore).reversed())
                .limit(50)
                .collect(Collectors.toList());

        return new ResponseEntity<>(topScores, HttpStatus.OK);
    }

    @GetMapping("getUserByWeek")
    public ResponseEntity<?> getUserByWeek(
            @RequestParam int year,
            @RequestParam int month,
            @RequestParam int week,
            @RequestParam String userId

    ) {

        LocalDate startDateOfWeek = LocalDate.of(year, month, 1)
                .with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY))
                .plusWeeks(week - 1);
        LocalDate endDateOfWeek = startDateOfWeek.plusDays(6);

        List<Score> scores = scoreRepository.findByDateBetween(
                startDateOfWeek.toString(), endDateOfWeek.toString());
        Map<String, Integer> maxScoreByUserId = new HashMap<>();
        List<Score> filteredScores = new ArrayList<>();
        for (Score score : scores) {
            if (!maxScoreByUserId.containsKey(score.getUserId())
                    || score.getScore() > maxScoreByUserId.get(score.getUserId())) {
                maxScoreByUserId.put(score.getUserId(), score.getScore());
                filteredScores.add(score);
            }
        }

        List<Score> topScores = filteredScores.stream()
                .sorted(Comparator.comparing(Score::getScore).reversed())
                .limit(50)
                .collect(Collectors.toList());

        Score score = topScores.stream()
                .filter(s -> s.getUserId().equals(userId))
                .findFirst()
                .orElse(null);
        if (score == null) {
            return ResponseEntity.ok("User is null");
        }

        User user = userService.findUserByUserId(score.getUserId());
        return ResponseEntity.ok("Index user: " + user.getId());
    }





    }
