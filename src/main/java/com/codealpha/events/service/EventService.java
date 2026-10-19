package com.codealpha.events.service;

import com.codealpha.events.dto.*;
import com.codealpha.events.model.*;
import com.codealpha.events.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EventService {
    private final EventRepository events;
    private final RegistrationRepository registrations;

    public EventService(EventRepository events, RegistrationRepository registrations) {
        this.events = events; this.registrations = registrations;
    }

    public EventResponse createEvent(CreateEventRequest r) {
        Event e = events.save(new Event(r.title().trim(), r.description().trim(), r.venue().trim(), r.startsAt(), r.capacity()));
        return toEventResponse(e);
    }

    public List<EventResponse> listEvents() {
        return events.findAll().stream().map(this::toEventResponse).toList();
    }

    public EventResponse getEvent(Long id) {
        return toEventResponse(getEventEntity(id));
    }

    @Transactional
    public RegistrationResponse register(Long eventId, RegisterRequest r) {
        Event event = getEventEntity(eventId);
        String email = r.email().trim().toLowerCase();
        if (registrations.existsByEventIdAndEmailAndStatus(eventId, email, RegistrationStatus.ACTIVE)) {
            throw new ConflictException("This email is already registered for this event");
        }
        long taken = registrations.countByEventIdAndStatus(eventId, RegistrationStatus.ACTIVE);
        if (taken >= event.getCapacity()) throw new ConflictException("This event is full");
        Registration saved = registrations.save(new Registration(r.name().trim(), email, event));
        return toRegistrationResponse(saved);
    }

    public List<RegistrationResponse> registrationsForEmail(String email) {
        return registrations.findByEmailIgnoreCase(email.trim()).stream().map(this::toRegistrationResponse).toList();
    }

    @Transactional
    public RegistrationResponse cancel(Long registrationId) {
        Registration registration = registrations.findById(registrationId)
                .orElseThrow(() -> new ResourceNotFoundException("Registration not found: " + registrationId));
        registration.cancel();
        return toRegistrationResponse(registration);
    }

    private Event getEventEntity(Long id) {
        return events.findById(id).orElseThrow(() -> new ResourceNotFoundException("Event not found: " + id));
    }

    private EventResponse toEventResponse(Event e) {
        long taken = registrations.countByEventIdAndStatus(e.getId(), RegistrationStatus.ACTIVE);
        return new EventResponse(e.getId(), e.getTitle(), e.getDescription(), e.getVenue(), e.getStartsAt(), e.getCapacity(), taken);
    }

    private RegistrationResponse toRegistrationResponse(Registration r) {
        return new RegistrationResponse(r.getId(), r.getEvent().getId(), r.getEvent().getTitle(),
                r.getName(), r.getEmail(), r.getRegisteredAt(), r.getStatus());
    }
}
