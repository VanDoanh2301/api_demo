package com.example.warehouses.model.service;

import com.example.warehouses.model.domain.User;
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
public interface UserService {
    void flush();

    <S extends User> S saveAndFlush(S entity);

    @Deprecated
    void deleteInBatch(Iterable<User> entities);

    void deleteAllInBatch(Iterable<User> entities);

    void deleteAllByIdInBatch(Iterable<Integer> integers);

    void deleteAllInBatch();

    @Deprecated
    User getOne(Integer integer);

    @Deprecated
    User getById(Integer integer);

    User getReferenceById(Integer integer);

    <S extends User> List<S> findAll(Example<S> example);

    <S extends User> List<S> findAll(Example<S> example, Sort sort);

    <S extends User> List<S> saveAll(Iterable<S> entities);

    List<User> findAll();

    List<User> findAllById(Iterable<Integer> integers);

    <S extends User> S save(S entity);

    Optional<User> findById(Integer integer);

    boolean existsById(Integer integer);

    long count();

    void deleteById(Integer integer);

    void delete(User entity);

    void deleteAllById(Iterable<? extends Integer> integers);

    void deleteAll(Iterable<? extends User> entities);

    void deleteAll();

    List<User> findAll(Sort sort);

    Page<User> findAll(Pageable pageable);

    <S extends User> Optional<S> findOne(Example<S> example);

    <S extends User> Page<S> findAll(Example<S> example, Pageable pageable);

    <S extends User> long count(Example<S> example);

    <S extends User> boolean exists(Example<S> example);

    <S extends User, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction);

    boolean existsByEmail(String email);

    boolean existsByUserId(String userId);
}
