package com.example.warehouses.model.service;

import com.example.warehouses.model.domain.Score;
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
public  interface   ScoreService {
    void flush();

    <S extends Score> S saveAndFlush(S entity);

    <S extends Score> List<S> saveAllAndFlush(Iterable<S> entities);

    @Deprecated
    void deleteInBatch(Iterable<Score> entities);

    void deleteAllInBatch(Iterable<Score> entities);

    void deleteAllByIdInBatch(Iterable<Integer> integers);

    void deleteAllInBatch();

    @Deprecated
    Score getOne(Integer integer);

    @Deprecated
    Score getById(Integer integer);

    Score getReferenceById(Integer integer);

    <S extends Score> List<S> findAll(Example<S> example);

    <S extends Score> List<S> findAll(Example<S> example, Sort sort);

    <S extends Score> List<S> saveAll(Iterable<S> entities);

    List<Score> findAll();

    List<Score> findAllById(Iterable<Integer> integers);

    <S extends Score> S save(S entity);

    Optional<Score> findById(Integer integer);

    boolean existsById(Integer integer);

    long count();

    void deleteById(Integer integer);

    void delete(Score entity);

    void deleteAllById(Iterable<? extends Integer> integers);

    void deleteAll(Iterable<? extends Score> entities);

    void deleteAll();

    List<Score> findAll(Sort sort);

    Page<Score> findAll(Pageable pageable);

    <S extends Score> Optional<S> findOne(Example<S> example);

    <S extends Score> Page<S> findAll(Example<S> example, Pageable pageable);

    <S extends Score> long count(Example<S> example);

    <S extends Score> boolean exists(Example<S> example);

    <S extends Score, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction);
}
