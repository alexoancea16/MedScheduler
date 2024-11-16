package com.database.MedScheduler.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @CreationTimestamp // Populează automat cu data și ora curentă
    @Column(name = "created_at", updatable = false) // Nu permite actualizarea după creare
    private LocalDateTime createdAt;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "users_roles", // Numele tabelei intermediare
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), // Legătura cu User
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id") // Legătura cu Role
    )
    private List<Role> roles = new ArrayList<>();
}
