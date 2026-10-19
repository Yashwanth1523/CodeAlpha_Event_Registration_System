
package com.codealpha.events.repository;

import com.codealpha.events.model.Registration;
import com.codealpha.events.model.RegistrationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {

    boolean existsByEventIdAndEmailAndStatus(
            Long eventId, String email, RegistrationStatus status);

    long countByEventIdAndStatus(
            Long eventId, RegistrationStatus status);

    @Query("SELECT r FROM Registration r JOIN FETCH r.event WHERE UPPER(r.email) = UPPER(:email)")
    List<Registration> findByEmailIgnoreCase(@Param("email") String email);

    List<Registration> findByEventId(Long eventId);
}
