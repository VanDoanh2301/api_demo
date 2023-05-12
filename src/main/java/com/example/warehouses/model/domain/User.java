package com.example.warehouses.model.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

// insert user

@Entity
@Table(name = "user")
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

    @OneToMany(mappedBy = "user")
    private Collection<Score> scores;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setScores(Collection<Score> scores) {
        this.scores = scores;
    }
}
