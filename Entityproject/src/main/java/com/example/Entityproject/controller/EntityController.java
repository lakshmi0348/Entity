package com.example.Entityproject.controller;

import com.example.Entityproject.model.EntityTableDTO;
import com.example.Entityproject.model.ApiResponse;
import com.example.Entityproject.model.EntityTable;
import com.example.Entityproject.service.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class EntityController
{
    @Autowired
    private EntityService entityService;
    @GetMapping("/allEntities")
    public ResponseEntity<List<EntityTableDTO>> getAllEntities() {
        List<EntityTableDTO> entityTableDTOs = entityService.getAllEntities();
        return ResponseEntity.ok(entityTableDTOs);
    }
    @GetMapping("/Entity/{Entity_id}")
    public EntityTableDTO getEntityById(@PathVariable Integer Entity_id)
    {
        return entityService.getEntitiesById(Entity_id);
    }
    @PutMapping("/Put/{Entity_id}")
    public ResponseEntity<Object> getEntityUpdate(@PathVariable Integer Entity_id, @RequestBody EntityTable entityTable)
    {
        EntityTableDTO dto =entityService.update(Entity_id,entityTable);
        return ResponseEntity.ok(new ApiResponse("Updated successfully", dto));
    }

    @DeleteMapping("/del/{Entity_id}")
    public ResponseEntity<Void> deleteMapping(@PathVariable Integer Entity_id)
    {
        entityService.deleteByEntityId(Entity_id);
        System.out.println("Record deleted Successfully " +Entity_id);
        return  ResponseEntity.noContent().build();
    }
    @PostMapping("/saveEntity")
    public ResponseEntity<EntityTableDTO> saveDataEntity(@RequestBody EntityTable entityTable)
    {
      EntityTableDTO dto =entityService.retrieve(entityTable);
      return ResponseEntity.ok(dto);
    }
}
