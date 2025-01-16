package org.example.onlinecourse.entities;

import jakarta.persistence.*;
import lombok.*;
import org.example.onlinecourse.entities.baseEntity.BaseEntity;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Teacher extends BaseEntity {
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(unique = true, nullable = false)
    private String phoneNumber;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "teacher")
    private List<Course> course;

}
