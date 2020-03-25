package com.flight.info.flight.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import com.flight.info.flight.entity.Flight;
import com.flight.info.flight.exception.RecordNotFoundException;
import com.flight.info.flight.repository.FlightRepository;
import com.flight.info.flight.serviceImpl.FlightServiceImpl;

import static org.junit.Assert.assertNotNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.Silent.class)
public class FlightServiceTest {

	@InjectMocks
	FlightServiceImpl flightService;

	@Mock
	FlightRepository flightRepository;

	@Before
	public void before() {

	}
	
	@Test
	public void TestgetFlightDetails(){
		Flight flight = new Flight();
		Mockito.when(flightRepository.findByFlightCode("1")).thenReturn(Optional.of(flight));
		assertNotNull(flightService.getFlightDetails("1"));
	}
	
	@Test(expected = RecordNotFoundException.class)
	public void TestgetFlightDetailsNegative(){
		Mockito.when(flightRepository.findByFlightCode("1")).thenReturn(Optional.ofNullable(null));
		assertNotNull(flightService.getFlightDetails("1"));
	}
	
	@Test
	public void TestsearchFlights() {
		String source = "BLR";
		String destination = "HYD";
		String date = "2020-03-26";
		List<Flight> list = new ArrayList<>();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime startDate = LocalDateTime.parse(date + " 00:00:01", formatter);
		LocalDateTime endDate = LocalDateTime.parse(date + " 23:59:59", formatter);
		Mockito.when(flightRepository.findAllBySourceAndDestinationAndStartTimeBetween(source, destination, startDate, endDate)).thenReturn(Optional.of(list));
		assertNotNull(flightService.searchFlights(source, destination, date));
	}
	
	@Test(expected = RecordNotFoundException.class)
	public void TestsearchFlightsNegative() {
		String source = "BLR";
		String destination = "HYD";
		String date = "2020-03-26";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime startDate = LocalDateTime.parse(date + " 00:00:01", formatter);
		LocalDateTime endDate = LocalDateTime.parse(date + " 23:59:59", formatter);
		Mockito.when(flightRepository.findAllBySourceAndDestinationAndStartTimeBetween(source, destination, startDate, endDate)).thenReturn(Optional.ofNullable(null));
		assertNotNull(flightService.searchFlights(source, destination, date));
	}
}