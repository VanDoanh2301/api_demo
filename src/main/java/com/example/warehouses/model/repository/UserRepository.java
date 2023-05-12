package com.example.warehouses.model.repository;

import com.example.warehouses.model.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    public boolean existsByEmail(String email);
    public boolean existsByUserId(String userId);

    public User findByUserId(Integer id);
}
