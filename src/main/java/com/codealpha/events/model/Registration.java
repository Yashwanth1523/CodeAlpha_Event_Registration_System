package com.codealpha.events.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Table(name = "registrations",
       uniqueConstraints = @UniqueConstraint(name = "uk_event_email", columnNames = {"event_id", "email"}))
public class Registration {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank @Size(max = 100)
    @Column(nullable = false, length = 100)
    private String name;

    @NotBlank @Email @Size(max = 180)
    @Column(nullable = false, length = 180)
    private String email;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @Column(name = "registered_at", nullable = false)
    private LocalDateTime registeredAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private RegistrationStatus status;

    protected Registration() {}

    public Registration(String name, String email, Event event) {
        this.name = name; this.email = email; this.event = event;
        this.registeredAt = LocalDateTime.now(); this.status = RegistrationStatus.ACTIVE;
    }
    public Long getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public Event getEvent() { return event; }
    public LocalDateTime getRegisteredAt() { return registeredAt; }
    public RegistrationStatus getStatus() { return status; }
    public void cancel() { this.status = RegistrationStatus.CANCELLED; }
}
