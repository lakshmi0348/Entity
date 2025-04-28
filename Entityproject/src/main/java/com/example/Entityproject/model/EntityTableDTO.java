package com.example.Entityproject.model;
import jakarta.persistence.EntityListeners;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class EntityTableDTO {

    private Integer entity_id;
    private String name;
    private String createdBy;
    @CreatedDate
    private LocalDateTime createdOn;
    private String modifiedBy;
    @LastModifiedDate
    private LocalDateTime modifiedOn;
}
