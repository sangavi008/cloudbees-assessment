package org.sangavi.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.sangavi.model.Booking;
import org.sangavi.service.BookingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/booking")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@ApiOperation("Operations pertaining to booking")
public class BookingController
{
  private final BookingServiceImpl bookingService;

  @ApiOperation("Get booking details by id")
  @GetMapping("/{id}")
  public Booking getBookingDetail(@PathVariable("id") Long bookingId) {
    return bookingService.getBookingDetails(bookingId);
  }
}
