package org.example.onlinecourse.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.onlinecourse.entities.baseEntity.BaseEntity;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseCategory extends BaseEntity {
    @Column(nullable = false, unique = true)
    private String name;
}
