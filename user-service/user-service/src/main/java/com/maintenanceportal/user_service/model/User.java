package com.maintenanceportal.user_service.model;


import lombok.*;
import jakarta.persistence.*;
import org.springframework.boot.autoconfigure.web.WebProperties;

import java.time.LocalDateTime;


@Data // gen getter setter
@AllArgsConstructor
@NoArgsConstructor
@Builder // alows optional building
@Table(name = "users")
@Entity


public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false) // the table cant be null
    private String name;

    @Column(unique = true) // only unique values are allowed
    private String email;

    private String phone;

    @Column(name = "flat_number") // the table name in DB is flat_number
    private String flatNumber;

    private String role;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @PrePersist //runs before JPA sends the insert statement
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
