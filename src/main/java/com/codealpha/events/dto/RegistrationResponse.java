package com.codealpha.events.dto;

import com.codealpha.events.model.RegistrationStatus;
import java.time.LocalDateTime;

public record RegistrationResponse(Long id, Long eventId, String eventTitle, String name,
                                   String email, LocalDateTime registeredAt,
                                   RegistrationStatus status) {}
