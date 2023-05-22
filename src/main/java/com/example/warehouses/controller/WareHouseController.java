package com.example.warehouses.controller;

import ch.qos.logback.core.util.DelayStrategy;
import com.example.warehouses.model.domain.Delete;
import com.example.warehouses.model.domain.Favorites;
import com.example.warehouses.model.domain.Score;
import com.example.warehouses.model.domain.WareHouses;
import com.example.warehouses.model.dto.*;
import com.example.warehouses.model.repository.DeleteRepository;
import com.example.warehouses.model.repository.FavoritesRepository;
import com.example.warehouses.model.repository.WareHousesRepository;
import com.example.warehouses.model.service.WareHouseService;
import jakarta.annotation.PostConstruct;
import org.apache.coyote.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("")
public class WareHouseController {
    @Autowired
    private WareHouseService wareHouseService;

    @Autowired
    private DeleteRepository deleteRepo;

    @Autowired
    private FavoritesRepository favoritesRepo;

    @Autowired
    private WareHousesRepository wareHousesRepository;
    private int previousTime = -1;
    private List<DeleteDto> deleteDtos;
    private List<FavoritesDto> favoritesDtos;
    private List<WareHouseDto> wareHouseDtos;
    private boolean isLoading = false;


    @GetMapping("/getDataNew")
    public ResponseEntity<?> getDataNew() {
        LocalDateTime now = LocalDateTime.now();
        int currentHour = now.getHour();

        if (previousTime != currentHour || !isLoading) {
            deleteDtos = null;
            favoritesDtos = null;
            wareHouseDtos = null;
            getDataFromStorage();
            previousTime = currentHour;
        }

        NewData newdata = new NewData();
        if (wareHouseDtos == null) {
            return ResponseEntity.ok("Surface is null");
        }
        isLoading = false;
        newdata.setFavorites(favoritesDtos);
        newdata.setNews(wareHouseDtos);
        newdata.setRemoves(deleteDtos);

        NewDataDto newDataDto = new NewDataDto();
        newDataDto.setNewData(newdata);

        return ResponseEntity.ok(newDataDto);
    }

    private void getDataFromStorage() {
        isLoading = true;
        List<Delete> deletes = deleteRepo.findAll();
        deletes.sort(Comparator.comparingInt(Delete::getId));
        deleteDtos = new ArrayList<>();
        for (Delete d : deletes) {
            DeleteDto dto = new DeleteDto();
            dto.setId(d.getIdWord());
            deleteDtos.add(dto);
        }

        List<Favorites> favorites = favoritesRepo.findAll();
        favorites.sort(Comparator.comparingInt(Favorites::getId));
        favoritesDtos = new ArrayList<>();
        for (Favorites f : favorites) {
            FavoritesDto dto = new FavoritesDto();
            dto.setId(f.getIdWord());
            favoritesDtos.add(dto);
        }

        List<WareHouses> wareHouses = wareHouseService.findAll();
        wareHouseDtos = new ArrayList<>();
        for (WareHouses w : wareHouses) {
            if (w.getDelete() != null && w.getDelete().equals(2)) {
                WareHouseDto dto = new WareHouseDto();
                dto.setId(w.get_id());
                dto.setSurface(w.getSurface());
                wareHouseDtos.add(dto);
            }
        }
        isLoading = false;
    }

        List<WareHouses> wareHouses = wareHouseService.findAll();
        wareHouseDtos = new ArrayList<>();
        for (WareHouses w : wareHouses) {
            if (w.getDelete() != null && w.getDelete().equals(2)) {
                WareHouseDto dto = new WareHouseDto();
                dto.setId(w.get_id());
                dto.setSurface(w.getSurface());
                wareHouseDtos.add(dto);
            }
        }
    }

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
    public ResponseEntity<?>  getAllSurface(@RequestParam(value = "delete", required = false) Integer delete) {
        List<WareHouses> wareHouses = wareHouseService.findAll();
        if(wareHouses == null) {
            return ResponseEntity.ok("Surface is null");
        }

        List<WareHouseDto> wareHouseDtos = new ArrayList<>();
        List<WareHouseDto> w1 = new ArrayList<>();
        List<WareHouseDto> w2 = new ArrayList<>();
        for(WareHouses w : wareHouses) {
            if(w.getDelete() != null) {
            if(w.getDelete().equals(1)) {
            WareHouseDto dto = new WareHouseDto();
            dto.setId(w.get_id());
            dto.setSurface(w.getSurface());
            wareHouseDtos.add(dto);
            w1.add(dto);
            } }
        }
        for(WareHouses w : wareHouses) {
            if(w.getDelete() != null) {
                if(w.getDelete().equals(2)) {
                    WareHouseDto dto = new WareHouseDto();
                    dto.setId(w.get_id());
                    dto.setSurface(w.getSurface());
                    wareHouseDtos.add(dto);
                    w2.add(dto);
                    } }
        }
        if (delete != null) {
            if (delete == 1) {
                return ResponseEntity.ok(w1);
            }
            if(delete == 2) {
                return ResponseEntity.ok(w2);
            }
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
                wareHouses.setDelete(2);
                wareHouseService.save(wareHouses);
            }
        }

        return ResponseEntity.ok("Delete success");
    }


}

