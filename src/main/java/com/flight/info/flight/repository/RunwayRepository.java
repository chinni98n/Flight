package com.flight.info.flight.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flight.info.flight.entity.RunWay;

@Repository
public interface RunwayRepository extends JpaRepository<RunWay, Long>{

	Optional<RunWay> findByRunWayBlockAndAirportId(String runwayBlock, Long airportId);

	Optional<List<RunWay>> findByAirportIdAndStatus(Long airportId, String string);

}
