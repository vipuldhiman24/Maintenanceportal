package com.maintenanceportal.worker_service.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.http.ResponseEntity;

import java.util.*;
@Service
public class WorkerService {

    private final RestClient restClient;
    private static final String USER_SERVICE_URL = "http://user-service:8080/users/";
    private static final String REQUEST_SERVICE_URL = "http://maintenance-request-service:8081/requests";
    public WorkerService(RestClient restClient) {
        this.restClient = restClient;
    }

    public List<Map<String, Object>> getRequestsWithUsers() {
        // Fetch paginated response
        ResponseEntity<Map> requestsResponse = restClient.get()
                .uri(REQUEST_SERVICE_URL)
                .retrieve()
                .toEntity(Map.class);

        Map<String, Object> pageResponse = requestsResponse.getBody();
        List<Map<String, Object>> combinedList = new ArrayList<>();

        if (pageResponse != null && pageResponse.containsKey("content")) {
            List<Map<String, Object>> requests = (List<Map<String, Object>>) pageResponse.get("content");

            for (Map<String, Object> req : requests) {
                Long userId = Long.valueOf(req.get("userId").toString());

                ResponseEntity<Map> userResponse = restClient.get()
                        .uri(USER_SERVICE_URL + userId)
                        .retrieve()
                        .toEntity(Map.class);

                Map<String, Object> user = userResponse.getBody();

                Map<String, Object> combinedMap = new HashMap<>();
                combinedMap.put("request", req);
                combinedMap.put("user", user);
                combinedList.add(combinedMap);
            }
        }

        return combinedList;
    }
}
