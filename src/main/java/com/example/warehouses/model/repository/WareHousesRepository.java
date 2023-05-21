package com.example.warehouses.model.repository;

import com.example.warehouses.model.domain.WareHouses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WareHousesRepository extends JpaRepository<WareHouses, Integer> {
    public Boolean existsBySurface(String surface);
    @Query(value = "SELECT * FROM warehouse where _id = ?1", nativeQuery = true)
    public  WareHouses findWareHousesById(Integer id);

    @Query(value = "SELECT u._id AS id, u.surface, u.`delete` FROM warehouse u", nativeQuery = true)
    public List<WareHouseProjection> findByAllList();

    public interface WareHouseProjection {
        Integer getId();
        String getSurface();
        Integer getDelete();
    }



}
