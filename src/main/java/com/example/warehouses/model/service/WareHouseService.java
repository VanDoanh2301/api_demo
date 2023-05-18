package com.example.warehouses.model.service;

import com.example.warehouses.model.domain.WareHouses;
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
public interface WareHouseService {
    void flush();

    <S extends WareHouses> S saveAndFlush(S entity);

    <S extends WareHouses> List<S> saveAllAndFlush(Iterable<S> entities);

    @Deprecated
    void deleteInBatch(Iterable<WareHouses> entities);

    void deleteAllInBatch(Iterable<WareHouses> entities);

    void deleteAllByIdInBatch(Iterable<Integer> integers);

    void deleteAllInBatch();

    @Deprecated
    WareHouses getOne(Integer integer);

    @Deprecated
    WareHouses getById(Integer integer);

    WareHouses getReferenceById(Integer integer);

    <S extends WareHouses> List<S> findAll(Example<S> example);

    <S extends WareHouses> List<S> findAll(Example<S> example, Sort sort);

    <S extends WareHouses> List<S> saveAll(Iterable<S> entities);

    List<WareHouses> findAll();

    List<WareHouses> findAllById(Iterable<Integer> integers);

    <S extends WareHouses> S save(S entity);

    Optional<WareHouses> findById(Integer integer);

    boolean existsById(Integer integer);

    long count();

    void deleteById(Integer integer);

    void delete(WareHouses entity);

    void deleteAllById(Iterable<? extends Integer> integers);

    void deleteAll(Iterable<? extends WareHouses> entities);

    void deleteAll();

    List<WareHouses> findAll(Sort sort);

    Page<WareHouses> findAll(Pageable pageable);

    <S extends WareHouses> Optional<S> findOne(Example<S> example);

    <S extends WareHouses> Page<S> findAll(Example<S> example, Pageable pageable);

    <S extends WareHouses> long count(Example<S> example);

    <S extends WareHouses> boolean exists(Example<S> example);

    <S extends WareHouses, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction);

    Boolean existsBySurface(String surface);

    WareHouses findWareHousesById(Integer id);

}
