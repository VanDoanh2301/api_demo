package com.example.warehouses.model.repository;

import com.example.warehouses.model.domain.Delete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeleteRepository extends JpaRepository<Delete, Integer> {
    Boolean existsDeleteByIdWord(Integer idWord);
}
