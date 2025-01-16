package org.example.onlinecourse.repository;

import org.example.onlinecourse.entities.Device;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DeviceRepository extends JpaRepository<Device, UUID> {
    Boolean existsByIpAddress(String ipAddress);
}
