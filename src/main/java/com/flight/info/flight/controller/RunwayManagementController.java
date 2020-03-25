package com.flight.info.flight.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flight.info.flight.dto.LandingInfoRequestDto;
import com.flight.info.flight.dto.ResponseDto;
import com.flight.info.flight.serviceImpl.RunwayServiceImpl;

@RestController
@RequestMapping("/admin")
public class RunwayManagementController {

	@Autowired
	RunwayServiceImpl runwayService;
	
	@PostMapping("/flights/{flightId}/airports/{airportId}")
	public ResponseEntity<Optional<ResponseDto>> runwayUpdate(@PathVariable("flightId") Long flightId, @PathVariable("airportId") Long airportId, @RequestBody LandingInfoRequestDto landingInfoRequestDto){
		
		Optional<ResponseDto> responseDto = runwayService.saveflightRunwayInfo(flightId, airportId, landingInfoRequestDto);
		return new ResponseEntity<>(responseDto, HttpStatus.OK);
	}
}
