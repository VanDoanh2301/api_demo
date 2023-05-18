package com.example.warehouses.controller;

import com.example.warehouses.model.domain.WareHouses;
import com.example.warehouses.model.dto.SurfaceDto;
import com.example.warehouses.model.dto.WareHouseDto;
import com.example.warehouses.model.repository.WareHousesRepository;
import com.example.warehouses.model.service.WareHouseService;
import org.apache.coyote.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("")
public class WareHouseController {
    @Autowired
    private WareHouseService wareHouseService;

    @Autowired
    private WareHousesRepository wareHousesRepository;


    //Insert surface
    @PostMapping("/insertSurface")
    public ResponseEntity<?> insertSurface(@RequestBody SurfaceDto surface) {
        if (wareHouseService.existsBySurface(surface.getSurface())) {
            return ResponseEntity.ok("Surface is already exist");
        }
        if(surface == null) {
            return ResponseEntity.ok("Please input surface");
        }
        WareHouses wareHouses = new WareHouses();
        wareHouses.setSurface(surface.getSurface());
        wareHouses.setDelete(1);
        wareHouseService.save(wareHouses);
        return ResponseEntity.ok("Save success");
    }


    //Get all list warehouse
    @GetMapping("/getSurfaces")
    public ResponseEntity<?>  getAllSurface() {
        List<WareHouses> wareHouses = wareHouseService.findAll();
        if(wareHouses == null) {
            return ResponseEntity.ok("Surface is null");
        }

        List<WareHouseDto> wareHouseDtos = new ArrayList<>();
        for(WareHouses w : wareHouses) {
            if(w.getDelete() != null) {
            if(w.getDelete().equals(1)) {
            WareHouseDto dto = new WareHouseDto();
            dto.setId(w.get_id());
            dto.setSurface(w.getSurface());
            wareHouseDtos.add(dto);} }
        }
        return ResponseEntity.ok(wareHouseDtos);
    }

    //update favorite
    @PostMapping("/updateSurface")
    public ResponseEntity<?> updateFavorite (@RequestParam(name = "surface_id") Integer id
            ,@RequestParam(name = "favorite", required = false) Integer favorite) {
        WareHouses wareHouses = wareHouseService.findWareHousesById(id);
        if(wareHouses == null) {
            return ResponseEntity.ok("Surface is null");
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


    @DeleteMapping("/deleteSurface")
    public ResponseEntity<?> deleteSurfaceById(@RequestParam(name = "surface_id") Integer id) {
        WareHouses wareHouses = wareHouseService.findWareHousesById(id);
        if(wareHouses == null) {
            return ResponseEntity.ok("Surface is null");
        }
        wareHouses.setDelete(0);
        wareHouseService.save(wareHouses);
        return ResponseEntity.ok("Delete surface: " + wareHouses.getSurface());
    }

    @PostMapping("/deleteListSurface")
    public ResponseEntity<?> deleteUsers(@RequestBody Map<String, String> request) {
        List<Integer> ids = new ArrayList<>();
        String idsString = request.get("ids");
        String[] idsArray = idsString.split(",");
        for (String id : idsArray) {
            ids.add(Integer.parseInt(id));
        }

        for (Integer id : ids) {
            WareHouses wareHouses = wareHouseService.findWareHousesById(id);
            if (wareHouses != null) {
                wareHouses.setDelete(0);
                wareHouseService.save(wareHouses);
            }
        }

        return ResponseEntity.ok("Delete success");
    }

}

