package org.sangavi.repository;

import org.sangavi.model.Booking;
import org.sangavi.model.Journey;
import org.sangavi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findAllByUser(User user);

    Booking findByIdAndUser(Long id, User user);
}
