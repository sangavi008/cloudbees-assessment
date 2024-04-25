package org.sangavi.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.sangavi.model.enums.BookingStatus;
import org.sangavi.model.enums.SeatSection;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "seat")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Seat {
  @Id
  private String id;

  private Long journeyId;

  @Enumerated(EnumType.STRING)
  private SeatSection section;

  private int number;

  @Enumerated(EnumType.STRING)
  private BookingStatus status;

  public Seat(Long journeyId, SeatSection section, int number) {
    this.journeyId = journeyId;
    this.section = section;
    this.number = number;
    this.id = journeyId + "-" + section + "-" + number;
  }

  public void setId(Seat seat) {
    this.id = journeyId + "-" + section + "-" + number;
  }

  public String getId(Seat seat) {
    setId(seat);
    return this.id;
  }
}
