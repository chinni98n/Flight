package com.flight.info.flight.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.flight.info.flight.dto.AirportResponseDto;
import com.flight.info.flight.dto.FlightResponseDto;
import com.flight.info.flight.serviceImpl.AirportServiceImpl;
import com.flight.info.flight.serviceImpl.FlightServiceImpl;

@RestController("/user")
public class UserController {

	@Autowired
	FlightServiceImpl flightService;
	
	@Autowired
	AirportServiceImpl airportService;
	
	@GetMapping("/flights")
	public ResponseEntity<Optional<FlightResponseDto>> getFlightDetails(@RequestParam("flightCode") String flightCode){
		Optional<FlightResponseDto> flight = flightService.getFlightDetails(flightCode);
		return new ResponseEntity<>(flight,HttpStatus.OK);		
	}
	
	@GetMapping("/airports")
	public ResponseEntity<Optional<AirportResponseDto>> getAirportDetails(@RequestParam("airportCode") String airportCode){
		Optional<AirportResponseDto> airport = airportService.getAirportDetails(airportCode);
		return new ResponseEntity<>(airport,HttpStatus.OK);		
	}
	
	@GetMapping("/flights/search")
	public ResponseEntity<Optional<List<FlightResponseDto>>> searchFlights(@RequestParam("source") String source, @RequestParam("destination") String destination, @RequestParam("date") String date){
		Optional<List<FlightResponseDto>> flights = flightService.searchFlights(source, destination, date);
		return new ResponseEntity<>(flights,HttpStatus.OK);		
	}
}
