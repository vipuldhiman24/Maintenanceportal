package com.maintenanceportal.worker_service.dto;

import lombok.Data;

@Data
public class RequestDto {
    private Long id;
    private Long userId;
    private String title;
    private String description;
    private String status;
}