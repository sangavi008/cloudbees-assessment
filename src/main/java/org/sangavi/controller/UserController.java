package org.sangavi.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.sangavi.model.Booking;
import org.sangavi.model.Seat;
import org.sangavi.model.User;
import org.sangavi.model.dto.PurchaseDto;
import org.sangavi.service.BookingServiceImpl;
import org.sangavi.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@ApiOperation("Operations pertaining to user")
public class UserController {
  private final UserServiceImpl userService;
  private final BookingServiceImpl bookingService;

  @ApiOperation("Add a new user")
  @PostMapping
  public User addUser(@RequestBody User user) {
    return userService.addUser(user);
  }

  @ApiOperation("Get user by id")
  @GetMapping("/{id}")
  public User getUser(@PathVariable Long id) {
    return userService.getUserById(id);
  }

  @ApiOperation("Purchase ticket for a user")
  @PostMapping("/{id}/purchase-ticket")
  public Booking purchaseTicket(@PathVariable("id") Long userId, @RequestBody PurchaseDto purchaseDto) throws Exception {
    purchaseDto.setUserId(userId);
    purchaseDto.setJourneyId(purchaseDto.getSeat().getJourneyId());
    return bookingService.purchaseTicket(purchaseDto);
  }

  @ApiOperation("Get all tickets for a user")
  @GetMapping("/{id}/tickets")
  public List<Booking> getAllTickets(@PathVariable("id") Long userId) {
    return bookingService.getAllTickets(userId);
  }

  @ApiOperation("Get ticket by id for a user")
  @GetMapping("/{id}/tickets/{ticketId}")
  public Booking getTicket(@PathVariable("id") Long userId, @PathVariable("ticketId") Long ticketId) {
    return bookingService.getUserTicketById(ticketId, userId);
  }

  @ApiOperation("Cancel ticket for a user")
  @PutMapping("/{id}/tickets/{ticketId}/cancel")
  public boolean cancelTicket(@PathVariable("id") Long userId, @PathVariable("ticketId") Long ticketId) throws Exception {
    return bookingService.cancelTicket(ticketId, userId);
  }

  @ApiOperation("Modify seat for a user")
  @PutMapping("/{id}/tickets/{ticketId}/modify")
  public Booking modifyUserSeat(@PathVariable("id") Long userId, @PathVariable("ticketId") Long ticketId, @RequestBody Seat seat) throws Exception{
    return bookingService.modifyUserSeat(ticketId, userId, seat);
  }
}

