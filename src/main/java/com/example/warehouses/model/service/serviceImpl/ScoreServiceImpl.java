package com.example.warehouses.model.service.serviceImpl;

import com.example.warehouses.model.domain.Score;
import com.example.warehouses.model.repository.ScoreRepository;
import com.example.warehouses.model.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
public class ScoreServiceImpl implements ScoreService {
    @Autowired
    private ScoreRepository scoreRepository;

    @Override
    public void flush() {
        scoreRepository.flush();
    }

    @Override
    public <S extends Score> S saveAndFlush(S entity) {
        return scoreRepository.saveAndFlush(entity);
    }

    @Override
    public <S extends Score> List<S> saveAllAndFlush(Iterable<S> entities) {
        return scoreRepository.saveAllAndFlush(entities);
    }


    @Override
    @Deprecated
    public void deleteInBatch(Iterable<Score> entities) {
        scoreRepository.deleteInBatch(entities);
    }

    @Override
    public void deleteAllInBatch(Iterable<Score> entities) {
        scoreRepository.deleteAllInBatch(entities);
    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Integer> integers) {
        scoreRepository.deleteAllByIdInBatch(integers);
    }

    @Override
    public void deleteAllInBatch() {
        scoreRepository.deleteAllInBatch();
    }

    @Override
    @Deprecated
    public Score getOne(Integer integer) {
        return scoreRepository.getOne(integer);
    }

    @Override
    @Deprecated
    public Score getById(Integer integer) {
        return scoreRepository.getById(integer);
    }

    @Override
    public Score getReferenceById(Integer integer) {
        return scoreRepository.getReferenceById(integer);
    }

    @Override
    public <S extends Score> List<S> findAll(Example<S> example) {
        return scoreRepository.findAll(example);
    }

    @Override
    public <S extends Score> List<S> findAll(Example<S> example, Sort sort) {
        return scoreRepository.findAll(example, sort);
    }

    @Override
    public <S extends Score> List<S> saveAll(Iterable<S> entities) {
        return scoreRepository.saveAll(entities);
    }

    @Override
    public List<Score> findAll() {
        return scoreRepository.findAll();
    }

    @Override
    public List<Score> findAllById(Iterable<Integer> integers) {
        return scoreRepository.findAllById(integers);
    }

    @Override
    public <S extends Score> S save(S entity) {
        return scoreRepository.save(entity);
    }

    @Override
    public Optional<Score> findById(Integer integer) {
        return scoreRepository.findById(integer);
    }

    @Override
    public boolean existsById(Integer integer) {
        return scoreRepository.existsById(integer);
    }

    @Override
    public long count() {
        return scoreRepository.count();
    }

    @Override
    public void deleteById(Integer integer) {
        scoreRepository.deleteById(integer);
    }

    @Override
    public void delete(Score entity) {
        scoreRepository.delete(entity);
    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {
        scoreRepository.deleteAllById(integers);
    }

    @Override
    public void deleteAll(Iterable<? extends Score> entities) {
        scoreRepository.deleteAll(entities);
    }

    @Override
    public void deleteAll() {
        scoreRepository.deleteAll();
    }

    @Override
    public List<Score> findAll(Sort sort) {
        return scoreRepository.findAll(sort);
    }

    @Override
    public Page<Score> findAll(Pageable pageable) {
        return scoreRepository.findAll(pageable);
    }

    @Override
    public <S extends Score> Optional<S> findOne(Example<S> example) {
        return scoreRepository.findOne(example);
    }

    @Override
    public <S extends Score> Page<S> findAll(Example<S> example, Pageable pageable) {
        return scoreRepository.findAll(example, pageable);
    }

    @Override
    public <S extends Score> long count(Example<S> example) {
        return scoreRepository.count(example);
    }

    @Override
    public <S extends Score> boolean exists(Example<S> example) {
        return scoreRepository.exists(example);
    }

    @Override
    public <S extends Score, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return scoreRepository.findBy(example, queryFunction);
    }

    @Override
    public Page<Score> findByDateLike(String day, Pageable pageable) {
        return scoreRepository.findByDateLike(day, pageable);
    }

    @Override
    public List<Score> findDistinctByDateLikeOrderByScoreDesc(String day) {
        return scoreRepository.findDistinctByDateLikeOrderByScoreDesc(day);
    }
}
