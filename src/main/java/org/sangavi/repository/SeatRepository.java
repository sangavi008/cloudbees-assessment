package org.sangavi.repository;

import org.sangavi.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {
  public List<Seat> findByJourneyId(Long journeyId);

  public Seat findById(String id);
}