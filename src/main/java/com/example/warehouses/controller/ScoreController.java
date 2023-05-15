package com.example.warehouses.controller;

import com.example.warehouses.model.domain.Content;
import com.example.warehouses.model.domain.Score;
import com.example.warehouses.model.domain.User;
import com.example.warehouses.model.dto.*;
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
import java.time.LocalDateTime;
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
        score.setDate(LocalDateTime.now());
        score.setUserId(scoreDto.getUserId());
        scoreService.save(score);
        return ResponseEntity.ok("Save score");

    }
    @GetMapping("getScoreByDay")
    public ResponseEntity<?> getTopScoresByDayLike(@RequestParam(name = "userId", required = false) String userId) {
        LocalDate today = LocalDate.now();
        int currentDay = today.getDayOfMonth();
        List<Score> scores = scoreRepository.findByDay(currentDay);
        Map<String, Score> maxScoreByUserId = new HashMap<>();
        for (Score score : scores) {
            if (!maxScoreByUserId.containsKey(score.getUserId())
                    || score.getScore() > maxScoreByUserId.get(score.getUserId()).getScore()) {
                maxScoreByUserId.put(score.getUserId(), score);
            }
        }

        List<ScoreWrapper> scoreWrappers = new ArrayList<>();
        int i = 0;
        for (Score score : maxScoreByUserId.values()) {
            scoreWrappers.add(new ScoreWrapper(++i, score.getScore(), score.getUserId(), score.getDate()));
        }
        Collections.sort(scoreWrappers, Comparator.comparingInt(ScoreWrapper::getScore).reversed());

        List<ScoreWrapper> topScores = scoreWrappers.subList(0, Math.min(scoreWrappers.size(), 50));

        if (topScores.isEmpty()) {
            List<ContentDto> c = new ArrayList<>();
            UserWrapper userList = new UserWrapper(0, c);
            return ResponseEntity.ok(userList);
        }

        List<ContentDto> contentDtos = new ArrayList<>();
        for(ScoreWrapper s : topScores) {
            User user = userService.findUserByUserId(s.getUserId());
            if(user == null) {
                continue;
            }
            ContentDto contentDto = new ContentDto();
            contentDto.setScore(s.getScore());
            contentDto.setScoreDate(s.getDate());
            contentDto.setUserName(user.getName());
            contentDto.setAvatar(user.getAvatar());
            contentDtos.add(contentDto);
        }
        UserWrapper scoreView = new UserWrapper(-1, contentDtos);
        if(userId != null) {
            ScoreWrapper score = topScores.stream()
                    .filter(s -> s.getUserId().equals(userId))
                    .findFirst()
                    .orElse(null);
            if (score == null) {
                UserWrapper user = new UserWrapper(-1, contentDtos);
                return ResponseEntity.ok(user);
            }
            List<Score> scoreList = scoreRepository.findByUserId(score.getUserId());
            List<ContentDto> con = new ArrayList<>();
            for(ScoreWrapper s : topScores) {
                User user = userService.findUserByUserId(s.getUserId());
                if(user == null) {
                    continue;
                }
                ContentDto contentDto = new ContentDto();
                contentDto.setScore(s.getScore());
                contentDto.setScoreDate(s.getDate());
                contentDto.setUserName(user.getName());
                contentDto.setAvatar(user.getAvatar());
                con.add(contentDto);
            }
            List<ContentDto> hight = con.subList(0, Math.min(con.size(), 50));
            UserWrapper userWrapper = new UserWrapper(score.getIndex_user(), hight);
            return ResponseEntity.ok(userWrapper);
        }
        return ResponseEntity.ok(scoreView);
    }

    @GetMapping("/getScoreByMonth")
    public ResponseEntity<?> getScoresByMonth(@RequestParam(name = "userId", required = false) String userId) {
        LocalDate today = LocalDate.now();
        int currentMonth= today.getMonthValue();
        List<Score> scores = scoreRepository.findByMonth(currentMonth);
        Map<String, Score> maxScoreByUserId = new HashMap<>();
        for (Score score : scores) {
            if (!maxScoreByUserId.containsKey(score.getUserId())
                    || score.getScore() > maxScoreByUserId.get(score.getUserId()).getScore()) {
                maxScoreByUserId.put(score.getUserId(), score);
            }
        }

        List<ScoreWrapper> scoreWrappers = new ArrayList<>();
        int i = 0;
        for (Score score : maxScoreByUserId.values()) {
            scoreWrappers.add(new ScoreWrapper(++i, score.getScore(), score.getUserId(), score.getDate()));
        }
        Collections.sort(scoreWrappers, Comparator.comparingInt(ScoreWrapper::getScore).reversed());

        List<ScoreWrapper> topScores = scoreWrappers.subList(0, Math.min(scoreWrappers.size(), 50));


        if (topScores.isEmpty()) {
            List<ContentDto> c = new ArrayList<>();
            UserWrapper userList = new UserWrapper(0, c);
            return ResponseEntity.ok(userList);
        }

        List<ContentDto> contentDtos = new ArrayList<>();
        for(ScoreWrapper s : topScores) {
            User user = userService.findUserByUserId(s.getUserId());
            if(user == null) {
                continue;
            }
            ContentDto contentDto = new ContentDto();
            contentDto.setScore(s.getScore());
            contentDto.setScoreDate(s.getDate());
            contentDto.setUserName(user.getName());
            contentDto.setAvatar(user.getAvatar());
            contentDtos.add(contentDto);
        }
        UserWrapper scoreView = new UserWrapper(-1, contentDtos);
        if(userId != null) {
            ScoreWrapper score = topScores.stream()
                    .filter(s -> s.getUserId().equals(userId))
                    .findFirst()
                    .orElse(null);
            if (score == null) {
                UserWrapper user = new UserWrapper(-1, contentDtos);
                return ResponseEntity.ok(user);
            }
            List<Score> scoreList = scoreRepository.findByUserId(score.getUserId());
            List<ContentDto> con = new ArrayList<>();
            for(ScoreWrapper s : topScores) {
                User user = userService.findUserByUserId(s.getUserId());
                if(user == null) {
                    continue;
                }
                ContentDto contentDto = new ContentDto();
                contentDto.setScore(s.getScore());
                contentDto.setScoreDate(s.getDate());
                contentDto.setUserName(user.getName());
                contentDto.setAvatar(user.getAvatar());
                con.add(contentDto);
            }
            List<ContentDto> hight = con.subList(0, Math.min(con.size(), 50));
            UserWrapper userWrapper = new UserWrapper(score.getIndex_user(), hight);
            return ResponseEntity.ok(userWrapper);
        }
        return ResponseEntity.ok(scoreView);
    }

    @GetMapping("/getScoreByWeek")
    public ResponseEntity<?> getScoresByWeek(@RequestParam(name = "userId", required = false) String userId) {
        LocalDate today = LocalDate.now();
        int currentYear = today.getYear();
        int currentWeek = today.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR);
        List<Score> scores = scoreRepository.findByWeekNumber(currentWeek, currentYear);
        Map<String, Score> maxScoreByUserId = new HashMap<>();
        for (Score score : scores) {
            if (!maxScoreByUserId.containsKey(score.getUserId())
                    || score.getScore() > maxScoreByUserId.get(score.getUserId()).getScore()) {
                maxScoreByUserId.put(score.getUserId(), score);
            }
        }

        List<ScoreWrapper> scoreWrappers = new ArrayList<>();
        int i = 0;
        for (Score score : maxScoreByUserId.values()) {
            scoreWrappers.add(new ScoreWrapper(++i, score.getScore(), score.getUserId(), score.getDate()));
        }
        Collections.sort(scoreWrappers, Comparator.comparingInt(ScoreWrapper::getScore).reversed());

        List<ScoreWrapper> topScores = scoreWrappers.subList(0, Math.min(scoreWrappers.size(), 50));

        if (topScores.isEmpty()) {
            List<ContentDto> c = new ArrayList<>();
            UserWrapper userList = new UserWrapper(0, c);
            return ResponseEntity.ok(userList);
        }

        List<ContentDto> contentDtos = new ArrayList<>();
        for(ScoreWrapper s : topScores) {
            User user = userService.findUserByUserId(s.getUserId());
            if(user == null) {
                continue;
            }
            ContentDto contentDto = new ContentDto();
            contentDto.setScore(s.getScore());
            contentDto.setScoreDate(s.getDate());
            contentDto.setUserName(user.getName());
            contentDto.setAvatar(user.getAvatar());
            contentDtos.add(contentDto);
        }
        UserWrapper scoreView = new UserWrapper(-1, contentDtos);
        if(userId != null) {
            ScoreWrapper score = topScores.stream()
                    .filter(s -> s.getUserId().equals(userId))
                    .findFirst()
                    .orElse(null);
            if (score == null) {
                UserWrapper user = new UserWrapper(-1, contentDtos);
                return ResponseEntity.ok(user);
            }
            List<Score> scoreList = scoreRepository.findByUserId(score.getUserId());
            List<ContentDto> con = new ArrayList<>();
            for(ScoreWrapper s : topScores) {
                User user = userService.findUserByUserId(s.getUserId());
                if(user == null) {
                    continue;
                }
                ContentDto contentDto = new ContentDto();
                contentDto.setScore(s.getScore());
                contentDto.setScoreDate(s.getDate());
                contentDto.setUserName(user.getName());
                contentDto.setAvatar(user.getAvatar());
                con.add(contentDto);
            }
            List<ContentDto> hight = con.subList(0, Math.min(con.size(), 50));
            UserWrapper userWrapper = new UserWrapper(score.getIndex_user(), hight);
            return ResponseEntity.ok(userWrapper);
        }
        return ResponseEntity.ok(scoreView);
    }
    }
