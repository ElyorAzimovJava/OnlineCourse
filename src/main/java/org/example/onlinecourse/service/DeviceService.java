package org.example.onlinecourse.service;

import jakarta.servlet.http.HttpServletRequest;
import org.example.onlinecourse.entities.Device;

public interface DeviceService {
    Device createDevice(HttpServletRequest request);
    Boolean isIPExist(HttpServletRequest request);
}
