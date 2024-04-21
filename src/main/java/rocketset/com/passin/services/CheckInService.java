package rocketset.com.passin.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rocketset.com.passin.domain.attendee.Attendee;
import rocketset.com.passin.domain.checkin.CheckIn;
import rocketset.com.passin.domain.checkin.exceptions.CheckInAlreadyExistsException;
import rocketset.com.passin.repositories.CheckInRepository;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CheckInService {
    private final CheckInRepository checkInRepository;

    public void registerCheckIn(Attendee attendee) {
        this.verifyCheckInExists(attendee.getId());

        CheckIn newCheckIn = new CheckIn();
        newCheckIn.setAttendee(attendee);
        newCheckIn.setCreatedAt(LocalDateTime.now());

        this.checkInRepository.save(newCheckIn);
    }

    private void verifyCheckInExists(String attendeeId) {
        Optional<CheckIn> isCheckedIn = this.checkInRepository.findByAttendeeId(attendeeId);

        if (isCheckedIn.isPresent()) {
            throw new CheckInAlreadyExistsException("Attendee already checked in");
        }
    }

    public Optional<CheckIn> getCheckIn(String attendeeId) {
        return this.checkInRepository.findByAttendeeId(attendeeId);
    }
}
