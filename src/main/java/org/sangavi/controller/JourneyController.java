package org.sangavi.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.sangavi.model.Journey;
import org.sangavi.model.dto.JourneyDto;
import org.sangavi.service.JourneyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/journey")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@ApiOperation("Operations pertaining to journey")
public class JourneyController {
  private final JourneyServiceImpl journeyService;

  @ApiOperation("Add a new journey")
  @PostMapping("/add")
  public Journey addJourney(@RequestBody JourneyDto journeyDto) {
    return journeyService.addJourney(journeyDto);
  }

  @ApiOperation("Get journey by id")
  @GetMapping("/{id}")
  public JourneyDto getJourney(@PathVariable("id") Long id) {
    return journeyService.getJourneyById(id);
  }

}
