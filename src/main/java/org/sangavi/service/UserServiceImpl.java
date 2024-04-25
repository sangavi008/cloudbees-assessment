package org.sangavi.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.sangavi.model.Booking;
import org.sangavi.model.Journey;
import org.sangavi.model.Seat;
import org.sangavi.model.User;
import org.sangavi.model.dto.PurchaseDto;
import org.sangavi.repository.BookingRepository;
import org.sangavi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl {
  private final UserRepository userRepository;
  private final BookingRepository bookingRepository;

  public User addUser(User user) {
    return userRepository.save(user);
  }

  public User getUserById(Long id) {
    return userRepository.findById(id).orElse(null);
  }


}
