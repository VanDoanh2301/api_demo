package com.example.warehouses.model.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

// insert user

@Entity
@Table(name = "user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id", length = 125, nullable = false)
    private String userId;

    @Column(name = "name", length = 125, nullable =false)
    private String name;

    @Column(name = "email", length = 125, nullable = false)
    private String email;

    @Column(name = "avatar", length = 125, nullable = false)
    private String avatar;




}
