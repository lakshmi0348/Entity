package com.example.Entityproject.repository;
import com.example.Entityproject.model.EntityTable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface EntityRepository extends JpaRepository<EntityTable,Integer>
{
    Optional<EntityTable> findByName(String name);

}
