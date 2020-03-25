package com.flight.info.flight.serviceImpl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flight.info.flight.constants.ApplicationConstants;
import com.flight.info.flight.dto.AirportFlightResponseDto;
import com.flight.info.flight.dto.AirportResponseDto;
import com.flight.info.flight.entity.Airport;
import com.flight.info.flight.entity.Flight;
import com.flight.info.flight.exception.RecordNotFoundException;
import com.flight.info.flight.repository.AirportRepository;
import com.flight.info.flight.repository.FlightRepository;
import com.flight.info.flight.service.AirportService;

@Service
public class AirportServiceImpl implements AirportService {

	@Autowired
	AirportRepository airportRepository;

	@Autowired
	FlightRepository flightRepository;

	@Override
	public Optional<AirportResponseDto> getAirportDetails(String airportCode) {
		Optional<Airport> airport_opt = airportRepository.findByAirportCode(airportCode);
		if (!airport_opt.isPresent())
			throw new RecordNotFoundException(ApplicationConstants.NO_RECORD_FOUND);
		Airport airport = airport_opt.get();
		String airport_location = airport.getLocation();
		Optional<List<Flight>> flightList_opt = flightRepository.findBySourceOrDestination(airport_location, airport_location);
		if (!flightList_opt.isPresent())
			throw new RecordNotFoundException(ApplicationConstants.NO_RECORD_FOUND);
		AirportResponseDto airportResponseDto = new AirportResponseDto();
		List<Flight> flightList = flightList_opt.get();
		List<AirportFlightResponseDto> new_flightList = new ArrayList<>();
		for (Flight flightObj : flightList) {
			AirportFlightResponseDto flightResponseDto = new AirportFlightResponseDto();
			flightResponseDto.setFlightName(flightObj.getFlightName());
			
			if (flightObj.getSource().equals(airport_location)) {
				flightResponseDto.setType("takeOff");
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				String datetimeString = flightObj.getStartTime().format(formatter);
				LocalDateTime datetime = LocalDateTime.parse(datetimeString,formatter);
				flightResponseDto.setTime(datetime);
			}
			else {
				flightResponseDto.setType("landing");
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				String datetimeString = flightObj.getLandingTime().format(formatter);
				LocalDateTime datetime = LocalDateTime.parse(datetimeString,formatter);
				flightResponseDto.setTime(datetime);
			}
			new_flightList.add(flightResponseDto);
			
		}
		airportResponseDto.setAirportName(airport.getAirportName());
		airportResponseDto.setLocation(airport.getLocation());
		airportResponseDto.setNoOfRunways(airport.getNoOfrunways());
		airportResponseDto.setFlightList(new_flightList);
		return Optional.of(airportResponseDto);
		
	}

}
