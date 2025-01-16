package org.example.onlinecourse.service;

import eu.bitwalker.useragentutils.UserAgent;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.example.onlinecourse.entities.Device;
import org.example.onlinecourse.repository.DeviceRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class DeviceServiceImpl implements DeviceService {
    private final DeviceRepository deviceRepository;
    @Override
    public Device createDevice(HttpServletRequest request) {
        String ipAddress = extractClientIpAddress(request);
        String userAgentString = request.getHeader("User-Agent");

        UserAgent userAgent = UserAgent.parseUserAgentString(userAgentString);
        System.out.println("User-Agent String: " + userAgentString);
        Device device = new Device();
        device.setIpAddress(ipAddress);
        device.setDeviceName(userAgent.getOperatingSystem().getDeviceType().getName());
        device.setBrowserName(userAgent.getBrowser().getName());
        device.setOperatingSystemName(userAgent.getOperatingSystem().getName());
        device.setLoginTime(LocalDateTime.now());
        return device;
    }

    @Override
    public Boolean isIPExist(HttpServletRequest request) {
       return deviceRepository.existsByIpAddress(extractClientIpAddress(request));
    }

    public String extractClientIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For"); // In case of proxies
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
