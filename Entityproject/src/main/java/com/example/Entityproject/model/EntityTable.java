package com.example.Entityproject.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor

public class EntityTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Entity_id;
    @NotNull()
    private String name;
    private String email;
    private String password;
    @CreatedBy
    private String createdBy;
    @CreatedDate
    private LocalDateTime createdOn;
    @LastModifiedBy
    private String ModifiedBy;
    @LastModifiedDate
    private LocalDateTime ModifiedOn;


}
