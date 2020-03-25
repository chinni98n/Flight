package com.flight.info.flight.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flight.info.flight.entity.Flight;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long>{

	Optional<List<Flight>> findBySourceOrDestination(String source, String destination);

	Optional<Flight> findByFlightCode(String flightCode);
	
	Optional<List<Flight>> findAllBySourceAndDestinationAndStartTimeBetween(String source, String destination, LocalDateTime startDate, LocalDateTime endDate);
}
