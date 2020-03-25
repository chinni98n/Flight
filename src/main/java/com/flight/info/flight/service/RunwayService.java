package com.flight.info.flight.service;

import java.util.Optional;

import com.flight.info.flight.dto.LandingInfoRequestDto;
import com.flight.info.flight.dto.ResponseDto;

public interface RunwayService {

	public Optional<ResponseDto> saveflightRunwayInfo(Long flightId, Long airportId, LandingInfoRequestDto landingInfoRequestDto);
	public Optional<ResponseDto> saveflightRunwayInfoTakeOff(Long flightId, Long airportId,
			LandingInfoRequestDto landingInfoRequestDto);
	public Optional<ResponseDto> saveflightRunwayInfoLanding(Long flightId, Long airportId,
			LandingInfoRequestDto landingInfoRequestDto);
}