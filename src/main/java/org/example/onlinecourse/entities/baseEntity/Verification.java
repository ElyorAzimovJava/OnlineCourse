package org.example.onlinecourse.entities.baseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Verification extends BaseEntity {
    @Column(unique=true, nullable = false)
    private String email;
    @Column( nullable = false)
    private String message;
}
