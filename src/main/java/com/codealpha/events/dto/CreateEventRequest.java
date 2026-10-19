package com.codealpha.events.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

public record CreateEventRequest(
    @NotBlank @Size(max = 120) String title,
    @NotBlank @Size(max = 2000) String description,
    @NotBlank @Size(max = 160) String venue,
    @NotNull @Future LocalDateTime startsAt,
    @NotNull @Min(1) @Max(100000) Integer capacity
) {}
