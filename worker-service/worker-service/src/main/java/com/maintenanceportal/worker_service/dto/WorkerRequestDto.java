package com.maintenanceportal.worker_service.dto;


import lombok.Data;

@Data
public class WorkerRequestDto {
    private RequestDto request;
    private UserDto user;
}