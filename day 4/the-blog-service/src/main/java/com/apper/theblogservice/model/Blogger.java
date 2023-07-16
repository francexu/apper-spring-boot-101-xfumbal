package com.apper.theblogservice.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Entity // para alam ni JPA na ito yung gagawing table sa database
@Table(name = "BLOGGER") // naming convention: all caps
@Data // para sa getter and setter
public class Blogger {
    @Id // ito yung id na column (primary key)
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.UUID) // magaauto generate ng uuid kada save
    private String id;

    @Column(name = "COMPLETE_NAME")
    private String name;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

    @Column(name = "LAST_UPDATE")
    private LocalDateTime lastUpdate;

    @PrePersist // before i-persist, magseset ng value kay createdAt
    public void setCreatedAt() {
        createdAt = LocalDateTime.now();
        lastUpdate = LocalDateTime.now();
    }

    @PreUpdate // every update
    public void setLastUpdate() {
        lastUpdate = LocalDateTime.now();
    }
}
