package com.flight.info.flight.serviceImpl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flight.info.flight.constants.ApplicationConstants;
import com.flight.info.flight.dto.FlightResponseDto;
import com.flight.info.flight.entity.Flight;
import com.flight.info.flight.exception.RecordNotFoundException;
import com.flight.info.flight.repository.FlightRepository;
import com.flight.info.flight.service.FlightService;

@Service
public class FlightServiceImpl implements FlightService{

	ModelMapper mapper = new ModelMapper();
	@Autowired
	FlightRepository flightRepository;
	@Override
	public Optional<FlightResponseDto> getFlightDetails(String flightCode) {
		// TODO Auto-generated method stub
		Optional<Flight> flight_opt = flightRepository.findByFlightCode(flightCode);
		if(!flight_opt.isPresent())
			throw new RecordNotFoundException(ApplicationConstants.NO_RECORD_FOUND);
		Flight flight = flight_opt.get();
		FlightResponseDto flightResponseDto = mapper.map(flight,FlightResponseDto.class);
		return Optional.of(flightResponseDto);
	}
	@Override
	public Optional<List<FlightResponseDto>> searchFlights(String source, String destination, String date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime startDate = LocalDateTime.parse(date + " 00:00:01", formatter);
		LocalDateTime endDate = LocalDateTime.parse(date + " 23:59:59", formatter);
		Optional<List<Flight>> flightList_opt = flightRepository.findAllBySourceAndDestinationAndStartTimeBetween(source, destination, startDate, endDate);
		List<FlightResponseDto> flightList_final = new ArrayList<>();
		if(!flightList_opt.isPresent()) 
			throw new RecordNotFoundException(ApplicationConstants.NO_RECORD_FOUND);
		List<Flight> flightList = flightList_opt.get();
		for(Flight flight : flightList) {
			FlightResponseDto flightResponseDto = mapper.map(flight, FlightResponseDto.class);
			flightList_final.add(flightResponseDto);
		}
		return Optional.of(flightList_final);
	}

}
