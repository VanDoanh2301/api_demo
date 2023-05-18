package com.example.warehouses.model.repository;

import com.example.warehouses.model.domain.Score;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ScoreRepository extends JpaRepository<Score, Integer> {
    Page<Score> findByDateLike(String day, Pageable pageable);

    List<Score> findDistinctByDateLikeOrderByScoreDesc(String day);

    List<Score> findByDateLikeOrderByScoreDesc(String month);

    List<Score> findByDateBetween(String start, String end);

    List<Score> findByUserId(String userId);

    @Query("SELECT e FROM Score e WHERE DAY(e.date) = :dd ORDER BY e.score DESC ")
    List<Score> findByDay(@Param("dd") int dd);
    @Query("SELECT e FROM Score e WHERE MONTH(e.date) = :mm ORDER BY  e.score DESC")
    List<Score> findByMonth(@Param("mm") int mm);

    @Query("SELECT e FROM Score e WHERE WEEK(e.date) = :weekNumber AND YEAR(e.date) = :yearNumber ORDER BY  e.score DESC")
    List<Score> findByWeekNumber(@Param("weekNumber") int weekNumber, @Param("yearNumber") int yearNumber);

}
