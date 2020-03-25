package com.flight.info.flight.service;

import java.util.Optional;

import com.flight.info.flight.dto.AirportResponseDto;

public interface AirportService {

	public Optional<AirportResponseDto> getAirportDetails(String airportCode);
}
