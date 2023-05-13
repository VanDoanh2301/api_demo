package com.example.warehouses.model.repository;

import com.example.warehouses.model.domain.Score;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ScoreRepository extends JpaRepository<Score, Integer> {
    Page<Score> findByDateLike(String day, Pageable pageable);

    List<Score> findDistinctByDateLikeOrderByScoreDesc(String day);

    List<Score> findByDateLikeOrderByScoreDesc(String month);

    List<Score> findByDateBetween(String start, String end);
}
