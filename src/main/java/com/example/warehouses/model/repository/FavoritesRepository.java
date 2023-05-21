package com.example.warehouses.model.repository;

import com.example.warehouses.model.domain.Favorites;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoritesRepository extends JpaRepository<Favorites, Integer> {
    Boolean existsFavoritesByIdWord(Integer idWord);
}
