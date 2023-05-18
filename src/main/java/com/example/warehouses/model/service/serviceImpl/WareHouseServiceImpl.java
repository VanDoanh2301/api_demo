package com.example.warehouses.model.service.serviceImpl;

import com.example.warehouses.model.domain.WareHouses;
import com.example.warehouses.model.repository.WareHousesRepository;
import com.example.warehouses.model.service.WareHouseService;
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
import java.util.stream.Collectors;

@Service
public class WareHouseServiceImpl implements WareHouseService {
    @Autowired
    private WareHousesRepository wareHousesRepository;

    @Override
    public void flush() {
        wareHousesRepository.flush();
    }

    @Override
    public <S extends WareHouses> S saveAndFlush(S entity) {
        return wareHousesRepository.saveAndFlush(entity);
    }

    @Override
    public <S extends WareHouses> List<S> saveAllAndFlush(Iterable<S> entities) {
        return wareHousesRepository.saveAllAndFlush(entities);
    }

    @Override
    @Deprecated
    public void deleteInBatch(Iterable<WareHouses> entities) {
        wareHousesRepository.deleteInBatch(entities);
    }

    @Override
    public void deleteAllInBatch(Iterable<WareHouses> entities) {
        wareHousesRepository.deleteAllInBatch(entities);
    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Integer> integers) {
        wareHousesRepository.deleteAllByIdInBatch(integers);
    }

    @Override
    public void deleteAllInBatch() {
        wareHousesRepository.deleteAllInBatch();
    }

    @Override
    @Deprecated
    public WareHouses getOne(Integer integer) {
        return wareHousesRepository.getOne(integer);
    }

    @Override
    @Deprecated
    public WareHouses getById(Integer integer) {
        return wareHousesRepository.getById(integer);
    }

    @Override
    public WareHouses getReferenceById(Integer integer) {
        return wareHousesRepository.getReferenceById(integer);
    }

    @Override
    public <S extends WareHouses> List<S> findAll(Example<S> example) {
        return wareHousesRepository.findAll(example);
    }

    @Override
    public <S extends WareHouses> List<S> findAll(Example<S> example, Sort sort) {
        return wareHousesRepository.findAll(example, sort);
    }

    @Override
    public <S extends WareHouses> List<S> saveAll(Iterable<S> entities) {
        return wareHousesRepository.saveAll(entities);
    }

    @Override
    public List<WareHouses> findAll() {
        return wareHousesRepository.findAll();
    }

    @Override
    public List<WareHouses> findAllById(Iterable<Integer> integers) {
        return wareHousesRepository.findAllById(integers);
    }

    @Override
    public <S extends WareHouses> S save(S entity) {
        return wareHousesRepository.save(entity);
    }

    @Override
    public Optional<WareHouses> findById(Integer integer) {
        return wareHousesRepository.findById(integer);
    }

    @Override
    public boolean existsById(Integer integer) {
        return wareHousesRepository.existsById(integer);
    }

    @Override
    public long count() {
        return wareHousesRepository.count();
    }

    @Override
    public void deleteById(Integer integer) {
        wareHousesRepository.deleteById(integer);
    }

    @Override
    public void delete(WareHouses entity) {
        wareHousesRepository.delete(entity);
    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {
        wareHousesRepository.deleteAllById(integers);
    }

    @Override
    public void deleteAll(Iterable<? extends WareHouses> entities) {
        wareHousesRepository.deleteAll(entities);
    }

    @Override
    public void deleteAll() {
        wareHousesRepository.deleteAll();
    }

    @Override
    public List<WareHouses> findAll(Sort sort) {
        return wareHousesRepository.findAll(sort);
    }

    @Override
    public Page<WareHouses> findAll(Pageable pageable) {
        return wareHousesRepository.findAll(pageable);
    }

    @Override
    public <S extends WareHouses> Optional<S> findOne(Example<S> example) {
        return wareHousesRepository.findOne(example);
    }

    @Override
    public <S extends WareHouses> Page<S> findAll(Example<S> example, Pageable pageable) {
        return wareHousesRepository.findAll(example, pageable);
    }

    @Override
    public <S extends WareHouses> long count(Example<S> example) {
        return wareHousesRepository.count(example);
    }

    @Override
    public <S extends WareHouses> boolean exists(Example<S> example) {
        return wareHousesRepository.exists(example);
    }

    @Override
    public <S extends WareHouses, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return wareHousesRepository.findBy(example, queryFunction);
    }
    //----------------------------------------------------//

    @Override
    public Boolean existsBySurface(String surface) {
        return wareHousesRepository.existsBySurface(surface);
    }

    @Override
    public WareHouses findWareHousesById(Integer id) {
        return wareHousesRepository.findWareHousesById(id);
    }


}
