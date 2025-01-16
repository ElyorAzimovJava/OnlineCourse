package org.example.onlinecourse.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.onlinecourse.entities.baseEntity.BaseEntity;
import org.example.onlinecourse.enums.Status;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Device extends BaseEntity {
    @Column(nullable = false, unique = true)
    private String ipAddress;
    @Column(nullable = false)
    private String deviceName;
    private String browserName;
    private String operatingSystemName;
    private LocalDateTime loginTime;
}
