package com.maintenanceportal.worker_service.controller;

import com.maintenanceportal.worker_service.service.WorkerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/worker")
public class WorkerController {

    private final WorkerService workerService;

    public WorkerController(WorkerService workerService) {
        this.workerService = workerService;
    }

    @GetMapping("/requests")
    public List<Map<String, Object>> getRequestsWithUsers() {
        return workerService.getRequestsWithUsers();
    }
}