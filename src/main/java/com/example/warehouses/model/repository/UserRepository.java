package com.example.warehouses.model.repository;

import com.example.warehouses.model.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    public boolean existsByEmail(String email);
    public boolean existsByUserId(String userId);

    @Query(value = "SELECT * FROM user where id = ?1", nativeQuery = true)
    public User findUserId(Integer id);


    public  User findUserByUserId(String userId);
}
