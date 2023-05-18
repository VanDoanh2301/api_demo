package com.example.warehouses.model.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private String userId;

    private String name;


    private String avatar;




    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }


    public String getAvatar() {
        return avatar;
    }


    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }


}
