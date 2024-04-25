package org.sangavi.service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.sangavi.model.Booking;
import org.sangavi.model.Journey;
import org.sangavi.model.Seat;
import org.sangavi.model.User;
import org.sangavi.model.dto.PurchaseDto;
import org.sangavi.model.enums.BookingStatus;
import org.sangavi.model.enums.SeatSection;
import org.sangavi.repository.BookingRepository;
import org.sangavi.repository.SeatRepository;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class BookingServiceImplTest {
  @Mock
  private BookingServiceImpl bookingService;

  @InjectMocks
  BookingRepository bookingRepository;

  @InjectMocks
  SeatRepository seatRepository;

  @Test
  public void testGetBookingDetails() {
    Booking booking = mockDataForBooking();

    when(bookingRepository.findById(any())).thenReturn(Optional.of(booking));

    Booking actualValue = bookingService.getBookingDetails(1L);
    assertEquals(booking.getSeat().getStatus(), actualValue.getSeat().getStatus());
  }


  @Test
  void testPurchaseTicket() throws Exception{
    Booking booking = mockDataForBooking();
    when(bookingRepository.save(any())).thenReturn(booking);
    when(seatRepository.save(any())).thenReturn("true");

    PurchaseDto purchaseDto = new PurchaseDto();
    purchaseDto.setJourneyId(1L);
    purchaseDto.setUserId(1L);
    purchaseDto.setJourneyId(1L);
    purchaseDto.setSeat(new Seat(1L, SeatSection.A, 1));

    Booking actualValue = bookingService.purchaseTicket(purchaseDto);
    assertEquals(booking.getSeat().getStatus(), actualValue.getSeat().getStatus());
  }

  public Booking mockDataForBooking() {
    User user = new User();
    user.setId(1L);
    user.setFirstName("unit");
    user.setLastName("tests");
    user.setEmail("unit.tests@uts.com");

    Journey journey = new Journey();
    journey.setId(1L);
    journey.setDateOfJourney(new Date());
    journey.setFromLocation("London");
    journey.setToLocation("Paris");
    journey.setPrice(5.0);

    Seat seat = new Seat(1L, SeatSection.A, 1);
    seat.setStatus(BookingStatus.AVAILABLE);

    Booking booking = new Booking();
    booking.setDateOfBooking(new Date());
    booking.setUser(user);
    booking.setJourney(journey);
    booking.setSeat(seat);
    booking.setId(1L);

    return  booking;
  }
}