package com.example.warehouses.controller;

import com.example.warehouses.model.domain.Delete;
import com.example.warehouses.model.domain.WareHouses;
import com.example.warehouses.model.repository.DeleteRepository;
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
public class DeleteController {

    @Autowired
    private DeleteRepository deleteRepo;

    @PostMapping("/insertDelete")
    public ResponseEntity<?> deleteUsers(@RequestBody Map<String, String> request) {
        List<Integer> ids = new ArrayList<>();
        List<Integer> existingIds = new ArrayList<>();

        String idsString = request.get("ids");
        String[] idsArray = idsString.split(",");
        for (String id : idsArray) {
            ids.add(Integer.parseInt(id));
        }

        for (Integer id : ids) {
            if (deleteRepo.existsDeleteByIdWord(id)) {
                existingIds.add(id);
            } else {
                Delete delete = new Delete();
                delete.setIdWord(id);
                deleteRepo.save(delete);
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
