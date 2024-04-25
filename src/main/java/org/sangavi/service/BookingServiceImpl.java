package org.sangavi.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.sangavi.model.Booking;
import org.sangavi.model.Journey;
import org.sangavi.model.Seat;
import org.sangavi.model.User;
import org.sangavi.model.dto.PurchaseDto;
import org.sangavi.model.enums.BookingStatus;
import org.sangavi.repository.BookingRepository;
import org.sangavi.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BookingServiceImpl {
  private final BookingRepository bookingRepository;
  private final SeatRepository seatRepository;

  public Booking purchaseTicket(PurchaseDto purchaseDto) throws Exception {
    User user = new User();
    user.setId(purchaseDto.getUserId());

    Journey journey = new Journey();
    journey.setId(purchaseDto.getJourneyId());

    Seat seat = new Seat(purchaseDto.getJourneyId(), purchaseDto.getSeat().getSection(), purchaseDto.getSeat().getNumber());
    if (!isSeatAvailable(seat)) {
      throw new Exception("Request seat is already booked");
    }

    seat.setStatus(BookingStatus.BOOKED);

    Booking booking = new Booking();
    booking.setUser(user);
    booking.setJourney(journey);
    booking.setSeat(seat);
    booking.setDateOfBooking(new Date());
    try {


      seatRepository.save(seat);
      return bookingRepository.save(booking);
    } catch (Exception e) {
      throw new Exception("Error while booking ticket {} " + e.getMessage());
    }
  }

  public Booking getBookingDetails(Long id) {
    return bookingRepository.findById(id).orElse(null);
  }

  public List<Booking> getAllTickets(Long userId) {
    User user = new User();
    user.setId(userId);
    return bookingRepository.findAllByUser(user);
  }

  public Booking getUserTicketById(Long ticketId, Long userId) {
    User user = new User();
    user.setId(userId);
    return bookingRepository.findByIdAndUser(ticketId, user);
  }

  public boolean cancelTicket(Long ticketId, Long userId) throws Exception {
    Booking ticket = getUserTicketById(ticketId, userId);
    Seat seat = ticket.getSeat();
    seat.setStatus(BookingStatus.AVAILABLE);
    try {
      seatRepository.save(seat);
      bookingRepository.delete(ticket);
      return true;
    } catch (Exception e) {
      throw new Exception("Error while cancelling ticket - {}", e);
    }
  }

  public Booking modifyUserSeat(Long ticketId, Long userId, Seat seat) throws Exception {
    Booking booking = getUserTicketById(ticketId, userId);

    if (!isSeatAvailable(seat)) {
      log.error("Requested seat (for modification) is already booked - {} ", seat);
      throw new Exception("Requested seat is already booked");
    }

    try {
      Seat oldSeat = booking.getSeat();
      oldSeat.setStatus(BookingStatus.AVAILABLE);
      seatRepository.save(oldSeat);

      seat.setStatus(BookingStatus.BOOKED);
      seat.setId(seat);
      seat = seatRepository.save(seat);

      booking.setSeat(seat);
      return bookingRepository.save(booking);
    } catch (Exception e) {
      throw new Exception("Error while modifying ticket - {}", e);
    }
  }

  public boolean isSeatAvailable(Seat seat) {
    seat = seatRepository.findById(seat.getId(seat));
    if (seat.getStatus() != BookingStatus.AVAILABLE) {
      return false;
    }
    return true;
  }
}
