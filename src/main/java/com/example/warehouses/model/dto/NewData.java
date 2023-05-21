package com.example.warehouses.model.dto;

import com.example.warehouses.model.domain.Delete;
import com.example.warehouses.model.domain.Favorites;
import com.example.warehouses.model.domain.WareHouses;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewData {
    List<FavoritesDto> favorites;
    List<WareHouseDto> news;
    List<DeleteDto> removes;
}
