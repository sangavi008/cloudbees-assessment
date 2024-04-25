package org.sangavi.utils;

import org.sangavi.model.Journey;
import org.sangavi.model.Seat;
import org.sangavi.model.dto.JourneyDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JourneyMapper {
  public Journey mapJourneyDtoToJourney(JourneyDto journeyDto) {
    Journey journey = new Journey();
    journey.setFromLocation(journeyDto.getFromLocation());
    journey.setToLocation(journeyDto.getToLocation());
    journey.setPrice(journeyDto.getPrice());
    journey.setDateOfJourney(journeyDto.getDateOfJourney());
    return journey;
  }

  public JourneyDto mapJourneyToJourneyDto(Journey journey, List<Seat> seats) {
    JourneyDto journeyDto = new JourneyDto();
    journeyDto.setId(journey.getId());
    journeyDto.setFromLocation(journey.getFromLocation());
    journeyDto.setToLocation(journey.getToLocation());
    journeyDto.setPrice(journey.getPrice());
    journeyDto.setDateOfJourney(journey.getDateOfJourney());
    journeyDto.setAvailableSeats(seats);
    return journeyDto;
  }
}
