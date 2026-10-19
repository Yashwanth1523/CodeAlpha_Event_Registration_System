package com.codealpha.events.controller;

import com.codealpha.events.dto.*;
import com.codealpha.events.service.EventService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class EventController {
    private final EventService service;
    public EventController(EventService service) { this.service = service; }

    @PostMapping("/events")
    @ResponseStatus(HttpStatus.CREATED)
    public EventResponse create(@Valid @RequestBody CreateEventRequest request) {
        return service.createEvent(request);
    }

    @GetMapping("/events")
    public List<EventResponse> list() { return service.listEvents(); }

    @GetMapping("/events/{id}")
    public EventResponse get(@PathVariable Long id) { return service.getEvent(id); }

    @PostMapping("/events/{eventId}/registrations")
    @ResponseStatus(HttpStatus.CREATED)
    public RegistrationResponse register(@PathVariable Long eventId, @Valid @RequestBody RegisterRequest request) {
        return service.register(eventId, request);
    }

    @GetMapping("/registrations")
    public List<RegistrationResponse> myRegistrations(@RequestParam String email) {
        return service.registrationsForEmail(email);
    }

    @PatchMapping("/registrations/{registrationId}/cancel")
    public RegistrationResponse cancel(@PathVariable Long registrationId) {
        return service.cancel(registrationId);
    }
}
