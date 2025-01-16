package org.example.onlinecourse.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.onlinecourse.entities.baseEntity.BaseEntity;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(uniqueConstraints = {
        @UniqueConstraint(
                name = "name_courseId unique constraint",
                columnNames = {
                        "name","course_id"
                }
        ),
        @UniqueConstraint(
                name = "number_course_id unique constraint",
                columnNames = {
                        "number","course_id"
                }
        )
})
public class Lesson extends BaseEntity {
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false, unique = true)
    private String videoFilePath;
    @Column(nullable = false)
    private Integer number;
    @ManyToOne
    private Course course;
}
