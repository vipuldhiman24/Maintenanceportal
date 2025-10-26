package com.maintenanceportal.maintenance_request_service.repository;


import com.maintenanceportal.maintenance_request_service.model.Request;
import org.springframework.boot.actuate.web.exchanges.HttpExchange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestRepository  extends JpaRepository<Request,Long>{
}
