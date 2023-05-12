package com.example.warehouses.model.service.serviceImpl;

import com.example.warehouses.model.domain.User;
import com.example.warehouses.model.repository.UserRepository;
import com.example.warehouses.model.service.UserService;
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
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepo;

    @Override
    public void flush() {
        userRepo.flush();
    }

    @Override
    public <S extends User> S saveAndFlush(S entity) {
        return userRepo.saveAndFlush(entity);
    }

    public <S extends User> List<S> saveAllAndFlush(Iterable<S> entities) {
        return userRepo.saveAllAndFlush(entities);
    }

    @Override
    @Deprecated
    public void deleteInBatch(Iterable<User> entities) {
        userRepo.deleteInBatch(entities);
    }

    @Override
    public void deleteAllInBatch(Iterable<User> entities) {
        userRepo.deleteAllInBatch(entities);
    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Integer> integers) {
        userRepo.deleteAllByIdInBatch(integers);
    }

    @Override
    public void deleteAllInBatch() {
        userRepo.deleteAllInBatch();
    }
    @Override
    @Deprecated
    public User getOne(Integer integer) {
        return userRepo.getOne(integer);
    }

    @Override
    @Deprecated
    public User getById(Integer integer) {
        return userRepo.getById(integer);
    }

    @Override
    public User getReferenceById(Integer integer) {
        return userRepo.getReferenceById(integer);
    }

    @Override
    public <S extends User> List<S> findAll(Example<S> example) {
        return userRepo.findAll(example);
    }

    @Override
    public <S extends User> List<S> findAll(Example<S> example, Sort sort) {
        return userRepo.findAll(example, sort);
    }

    @Override
    public <S extends User> List<S> saveAll(Iterable<S> entities) {
        return userRepo.saveAll(entities);
    }

    @Override
    public List<User> findAll() {
        return userRepo.findAll();
    }

    @Override
    public List<User> findAllById(Iterable<Integer> integers) {
        return userRepo.findAllById(integers);
    }

    @Override
    public <S extends User> S save(S entity) {
        return userRepo.save(entity);
    }

    @Override
    public Optional<User> findById(Integer integer) {
        return userRepo.findById(integer);
    }

    @Override
    public boolean existsById(Integer integer) {
        return userRepo.existsById(integer);
    }

    @Override
    public long count() {
        return userRepo.count();
    }

    @Override
    public void deleteById(Integer integer) {
        userRepo.deleteById(integer);
    }

    @Override
    public void delete(User entity) {
        userRepo.delete(entity);
    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {
        userRepo.deleteAllById(integers);
    }

    @Override
    public void deleteAll(Iterable<? extends User> entities) {
        userRepo.deleteAll(entities);
    }

    @Override
    public void deleteAll() {
        userRepo.deleteAll();
    }

    @Override
    public List<User> findAll(Sort sort) {
        return userRepo.findAll(sort);
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return userRepo.findAll(pageable);
    }

    @Override
    public <S extends User> Optional<S> findOne(Example<S> example) {
        return userRepo.findOne(example);
    }

    @Override
    public <S extends User> Page<S> findAll(Example<S> example, Pageable pageable) {
        return userRepo.findAll(example, pageable);
    }

    @Override
    public <S extends User> long count(Example<S> example) {
        return userRepo.count(example);
    }

    @Override
    public <S extends User> boolean exists(Example<S> example) {
        return userRepo.exists(example);
    }

    @Override
    public <S extends User, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return userRepo.findBy(example, queryFunction);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepo.existsByEmail(email);
    }

    @Override
    public boolean existsByUserId(String userId) {
        return userRepo.existsByUserId(userId);
    }


    @Override
    public User findUserId(Integer id) {
        return userRepo.findUserId(id);
    }

    @Override
    public User findUserByUserId(String userId) {
        return userRepo.findUserByUserId(userId);
    }
}
