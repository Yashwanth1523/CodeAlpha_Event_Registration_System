package com.codealpha.events.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "events")
public class Event {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank @Size(max = 120)
    @Column(nullable = false, length = 120)
    private String title;

    @NotBlank @Size(max = 2000)
    @Column(nullable = false, length = 2000)
    private String description;

    @NotBlank @Size(max = 160)
    @Column(nullable = false, length = 160)
    private String venue;

    @NotNull
    @Future
    @Column(name = "starts_at", nullable = false)
    private LocalDateTime startsAt;

    @NotNull @Min(1) @Max(100000)
    @Column(name = "capacity", nullable = false)
    private Integer capacity;

    protected Event() {}

    public Event(String title, String description, String venue, LocalDateTime startsAt, Integer capacity) {
        this.title = title; this.description = description; this.venue = venue;
        this.startsAt = startsAt; this.capacity = capacity;
    }
    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getVenue() { return venue; }
    public LocalDateTime getStartsAt() { return startsAt; }
    public Integer getCapacity() { return capacity; }
    public void setTitle(String title) { this.title = title; }
    public void setDescription(String description) { this.description = description; }
    public void setVenue(String venue) { this.venue = venue; }
    public void setStartsAt(LocalDateTime startsAt) { this.startsAt = startsAt; }
    public void setCapacity(Integer capacity) { this.capacity = capacity; }
}
