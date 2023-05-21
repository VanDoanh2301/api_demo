package com.example.warehouses.controller;

import com.example.warehouses.model.domain.Delete;
import com.example.warehouses.model.domain.Favorites;
import com.example.warehouses.model.repository.FavoritesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("")
public class FavoritesController {


    @Autowired
    private FavoritesRepository favoritesRepo;


    @PostMapping("/insertFavorites")
    public ResponseEntity<?> deleteUsers(@RequestBody Map<String, String> request) {
        List<Integer> ids = new ArrayList<>();
        List<Integer> existingIds = new ArrayList<>();

        String idsString = request.get("ids");
        String[] idsArray = idsString.split(",");
        for (String id : idsArray) {
            ids.add(Integer.parseInt(id));
        }

        for (Integer id : ids) {
            if (favoritesRepo.existsFavoritesByIdWord(id)) {
                existingIds.add(id);
            } else {
                Favorites favorites = new Favorites();
                favorites.setIdWord(id);
                favoritesRepo.save(favorites);
            }
        }

        if (existingIds.isEmpty()) {
            return ResponseEntity.ok("Insert success");
        } else {
            String message = "The following ids already exist: " + existingIds.toString();
            return ResponseEntity.badRequest().body(message);
        }
    }
}
