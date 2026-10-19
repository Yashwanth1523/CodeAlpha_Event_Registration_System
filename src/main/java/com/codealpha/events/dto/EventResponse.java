package com.codealpha.events.dto;

import java.time.LocalDateTime;

public record EventResponse(Long id, String title, String description, String venue,
                            LocalDateTime startsAt, Integer capacity, long seatsTaken) {}
