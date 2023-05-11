package com.example.warehouses.controller;

import com.example.warehouses.model.domain.WareHouses;
import com.example.warehouses.model.dto.WareHouseDto;
import com.example.warehouses.model.service.WareHouseService;
import org.apache.coyote.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("")
public class WareHouseController {
    @Autowired
    private WareHouseService wareHouseService;


    //Insert surface
    @PostMapping("/surfaces")
    public ResponseEntity<?> insertSurface(@RequestParam(name = "surface") String surface) {
        if (wareHouseService.existsBySurface(surface)) {
            return ResponseEntity.badRequest().body("Surface is already exist");
        }
        WareHouses wareHouses = new WareHouses();
        wareHouses.setSurface(surface);
        wareHouseService.save(wareHouses);
        return ResponseEntity.ok("Save success");
    }


    //Get all list warehouse
    @GetMapping("/surfaces")
    public ResponseEntity<?>  getAllSurface() {
        List<WareHouses> wareHouses = wareHouseService.findAll();
        if(wareHouses == null) {
            return ResponseEntity.badRequest().body("WareHouse is null");
        }

        List<WareHouseDto> wareHouseDtos = new ArrayList<>();
        for(WareHouses w : wareHouses) {
            WareHouseDto dto = new WareHouseDto();
            dto.setId(w.get_id());
            dto.setSurface(w.getSurface());
            wareHouseDtos.add(dto);
        }
        return ResponseEntity.ok(wareHouseDtos);
    }

    //update favorite
    @PostMapping("/favorites/{id}")
    public ResponseEntity<?> updateFavorite (@PathVariable(name = "id") Integer id
            ,@RequestParam(name = "favorite", required = false) Integer favorite) {
        WareHouses wareHouses = wareHouseService.findWareHousesById(id);
        if(wareHouses == null) {
            return ResponseEntity.badRequest().body("WareHouse is null");
        }
        WareHouseDto dto = new WareHouseDto();
        dto.setId(wareHouses.get_id());
        dto.setSurface(wareHouses.getSurface());
        if(favorite == null) {
            return ResponseEntity.ok(dto);
        }
        wareHouses.setFavorite(favorite);
        wareHouseService.save(wareHouses);
        return ResponseEntity.ok("Update favorite: " + favorite);
    }
}
