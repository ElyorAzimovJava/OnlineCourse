package org.example.onlinecourse.entities;

import jakarta.persistence.*;
import lombok.*;
import org.example.onlinecourse.entities.baseEntity.BaseEntity;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(uniqueConstraints = @UniqueConstraint(
        name = "name_category_unique_constraint",
        columnNames = {"name","category"}
))
public class Course extends BaseEntity {
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String description;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(nullable = false, name = "category")
    private CourseCategory category;
    private BigDecimal price;
    @Column(nullable = false, unique = true)
    private String imageUrl;
    @ManyToOne(fetch = FetchType.EAGER)
    private Teacher teacher;
    @ManyToMany(mappedBy = "courses")
    private List<User> user;
    private Long userCount;

}