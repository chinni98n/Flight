package com.flight.info.flight.service;

import java.util.List;
import java.util.Optional;

import com.flight.info.flight.dto.FlightResponseDto;

public interface FlightService {

	public Optional<FlightResponseDto> getFlightDetails(String flightCode);
	public Optional<List<FlightResponseDto>> searchFlights(String source, String destination, String date);
}
