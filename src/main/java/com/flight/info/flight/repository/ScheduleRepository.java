package com.flight.info.flight.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flight.info.flight.entity.FlightSchedule;

@Repository
public interface ScheduleRepository extends JpaRepository<FlightSchedule, Long>{

}
