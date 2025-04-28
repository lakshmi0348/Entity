package com.example.Entityproject.service;
import com.example.Entityproject.model.EntityTableDTO;
import com.example.Entityproject.model.EntityTable;
import com.example.Entityproject.repository.EntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EntityService {

    @Autowired
    private EntityRepository entityRepository;
    public List<EntityTableDTO> getAllEntities() {
        List<EntityTable> entities = entityRepository.findAll();
        List<EntityTableDTO> entityTableDTO ;
        entityTableDTO= entities.stream()
                .map(entity -> new EntityTableDTO(
                        entity.getEntity_id(),
                        entity.getName(),
                        entity.getCreatedBy(),
                        entity.getCreatedOn(),
                        entity.getModifiedBy(),
                        entity.getModifiedOn()
                ))
                .collect(Collectors.toList());

        return entityTableDTO;
    }
    public EntityTableDTO getEntitiesById(Integer entityId) {
        EntityTable entityTable = entityRepository.findById(entityId)
                .orElseThrow(() -> new RuntimeException("Entity Id does not exist"));
        return new EntityTableDTO(
                entityTable.getEntity_id(),
                entityTable.getName(),
                entityTable.getCreatedBy(),
                entityTable.getCreatedOn(),
                entityTable.getModifiedBy(),
                entityTable.getModifiedOn()
        );
    }
    public void deleteByEntityId(Integer Entity_id)
    {
        if(!entityRepository.existsById(Entity_id))
            throw new RuntimeException("Entity Id is not exists");
        entityRepository.deleteById(Entity_id);
    }

    public EntityTable updateEntityById(Integer entityId, EntityTable newEntityData) {
        EntityTable existingEntity = entityRepository.findById(entityId)
                .orElseThrow(() -> new RuntimeException("Entity not found"));
        existingEntity.setName(newEntityData.getName());
        existingEntity.setEmail(newEntityData.getEmail());
        existingEntity.setPassword(newEntityData.getPassword());
        existingEntity.setModifiedBy(newEntityData.getName());
        existingEntity.setModifiedOn(LocalDateTime.now());
        return entityRepository.save(existingEntity);

    }
    public EntityTableDTO update(Integer Entity_id,EntityTable entityTable)
    {
        EntityTable updateEntity = updateEntityById(Entity_id, entityTable);
        EntityTableDTO dto = new EntityTableDTO(
                updateEntity.getEntity_id(),
                updateEntity.getName(),
                updateEntity.getCreatedBy(),
                updateEntity.getCreatedOn(),
                updateEntity.getModifiedBy(),
                updateEntity.getModifiedOn()
        );
        return dto;
    }

    public EntityTableDTO retrieve(EntityTable entityTable)
    {
        EntityTable savedEntity = SaveEntity(entityTable);
        EntityTableDTO dto = new EntityTableDTO(
                savedEntity.getEntity_id(),
                savedEntity.getName(),
                savedEntity.getCreatedBy(),
                savedEntity.getCreatedOn(),
                savedEntity.getModifiedBy(),
                savedEntity.getModifiedOn()
        );
        return dto;
    }
    public EntityTable SaveEntity(EntityTable entityTable)
    {

        if (entityTable.getName() == null || entityTable.getName().isEmpty()) {
            throw new RuntimeException("Name cannot be null");
        }
        if (entityRepository.findByName(entityTable.getName()).isPresent()) {
            throw new RuntimeException("Name already exists");
        }
        entityTable.setEntity_id(entityTable.getEntity_id());
        entityTable.setName(entityTable.getName());
        entityTable.setPassword(entityTable.getPassword());
        entityTable.setCreatedOn(LocalDateTime.now());
        entityTable.setModifiedBy(entityTable.getName());
        entityTable.setCreatedBy(entityTable.getName());
        entityTable.setModifiedOn(LocalDateTime.now());
        return entityRepository.save(entityTable);

    }

}
