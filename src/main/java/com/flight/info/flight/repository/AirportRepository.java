package com.flight.info.flight.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flight.info.flight.entity.Airport;

@Repository
public interface AirportRepository extends JpaRepository<Airport, Long>{

	Optional<Airport> findByAirportCode(String airportCode);

}
