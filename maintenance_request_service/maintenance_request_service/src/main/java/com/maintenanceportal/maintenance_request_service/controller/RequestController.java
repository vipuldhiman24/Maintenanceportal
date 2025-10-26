package com.maintenanceportal.maintenance_request_service.controller;


import com.maintenanceportal.maintenance_request_service.service.RequestService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.maintenanceportal.maintenance_request_service.model.Request;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

import java.util.Optional;

@RestController
@RequestMapping("/requests")
public class RequestController {

    private final RequestService requestService;
    private static final String USER_SERVICE_URL = "http://user-service:8080/users/";


    public RequestController(RequestService requestService) {
        this.requestService = requestService;

    }

    @PostMapping
    public ResponseEntity<Request> createRequest(@RequestBody Request request) {
        Request savedRequest = requestService.createRequest(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedRequest);
    }



    @GetMapping
    public Page<Request> getAllRequests(@RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return requestService.getAllRequests(pageable);
    }

    @GetMapping("/{id}")
    public Optional<Request> getRequestById(@PathVariable Long id) {
        return requestService.getRequestById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Request> updateRequest(@PathVariable Long id, @RequestBody Request updatedRequest) {
        Request request = requestService.updateRequest(id, updatedRequest);
        return ResponseEntity.ok(request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRequest(@PathVariable Long id) {
        return requestService.deleteRequest(id)
                ? ResponseEntity.ok("Request deleted successfully")
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Request not found");
    }

}
