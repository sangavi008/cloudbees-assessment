package org.sangavi.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.sangavi.model.Journey;
import org.sangavi.model.Seat;
import org.sangavi.model.dto.JourneyDto;
import org.sangavi.model.enums.BookingStatus;
import org.sangavi.repository.JourneyRepository;
import org.sangavi.repository.SeatRepository;
import org.sangavi.utils.JourneyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class JourneyServiceImpl {
  private final JourneyRepository journeyRepository;
  private final SeatRepository seatRepository;
  private final JourneyMapper journeyMapper;

  public Journey addJourney(JourneyDto journeyDto) {
    Journey journey = journeyRepository.save(journeyMapper.mapJourneyDtoToJourney(journeyDto));

    List<Seat> seats = journeyDto.getAvailableSeats();
    seats.forEach(seat -> {
      seat.setJourneyId(journey.getId());
      seat.setId(seat);
      seat.setStatus(BookingStatus.AVAILABLE);
    });
    seats = seatRepository.saveAll(seats);

    return journey;
  }


  public JourneyDto getJourneyById(Long id) {
    Journey journey = journeyRepository.findById(id).orElse(null);
    List<Seat> seats = seatRepository.findByJourneyId(id);
    return journeyMapper.mapJourneyToJourneyDto(journey, seats);
  }
}
