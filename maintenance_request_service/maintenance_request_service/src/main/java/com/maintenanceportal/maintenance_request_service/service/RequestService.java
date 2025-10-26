package com.maintenanceportal.maintenance_request_service.service;


import com.maintenanceportal.maintenance_request_service.repository.RequestRepository;

import org.springframework.stereotype.Service;
import com.maintenanceportal.maintenance_request_service.model.Request;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

import java.util.Optional;

@Service
public class RequestService {
    private final RequestRepository requestRepository;
    private final RestClient restClient;

    public RequestService(RequestRepository requestRepository, RestClient restClient) {
        this.requestRepository = requestRepository;
        this.restClient = restClient;
    }



    private boolean validateUserExists(Long userId) {
        try {
            restClient.get()
                    .uri("/{id}", userId)
                    .retrieve()
                    .toBodilessEntity(); // ✅ Just checks if the user exists (no body)
            return true;
        } catch (HttpClientErrorException.NotFound e) {
            return false; // user not found
        } catch (Exception e) {
            return false; // other errors (like service down)
        }
    }

    public Request createRequest(Request request) {
        if (!validateUserExists(request.getUserId())) {
            throw new RuntimeException("User not found, cannot create request");
        }
        return requestRepository.save(request);
    }


    public Page<Request> getAllRequests(Pageable pageable) {
        return requestRepository.findAll(pageable);
    }

    public Optional<Request> getRequestById(Long id) {
        return requestRepository.findById(id);
    }

    public Request updateRequest(Long id, Request updatedRequest) {
        return requestRepository.findById(id)
                .map(request -> {
                    request.setTitle(updatedRequest.getTitle());
                    request.setDescription(updatedRequest.getDescription());
                    request.setStatus(updatedRequest.getStatus());
                    return requestRepository.save(request);
                })
                .orElseThrow(() -> new RuntimeException("Request not found with id " + id));
    }

    public boolean deleteRequest(Long id) {
        if (!requestRepository.existsById(id)) {
            return false;
        }
        requestRepository.deleteById(id);
        return true;
    }

}
