package rocketset.com.passin.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rocketset.com.passin.domain.checkin.CheckIn;

import java.util.Optional;

public interface CheckInRepository extends JpaRepository<CheckIn, Integer> {
    Optional<CheckIn> findByAttendeeId(String attendeeId);
}
