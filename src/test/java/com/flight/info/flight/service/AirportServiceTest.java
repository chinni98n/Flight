package com.flight.info.flight.service;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.flight.info.flight.entity.Airport;
import com.flight.info.flight.entity.Flight;
import com.flight.info.flight.exception.RecordNotFoundException;
import com.flight.info.flight.repository.AirportRepository;
import com.flight.info.flight.repository.FlightRepository;
import com.flight.info.flight.serviceImpl.AirportServiceImpl;


@RunWith(MockitoJUnitRunner.Silent.class)
public class AirportServiceTest {


	@InjectMocks
	AirportServiceImpl airportService;

	@Mock
	AirportRepository airportRepository;
	
	@Mock
	FlightRepository flightRepository;

	@Before
	public void before() {

	}
	@Test
	public void TestgetAirportDetails() {
		Airport airport = new Airport();
		airport.setLocation("BLR");
		Mockito.when(airportRepository.findByAirportCode("1")).thenReturn(Optional.of(airport));
		String airport_location = airport.getLocation();
		List<Flight> list = new ArrayList<>();
		Mockito.when(flightRepository.findBySourceOrDestination(airport_location, airport_location)).thenReturn(Optional.of(list));
		assertNotNull(airportService.getAirportDetails("1"));
	}
	@Test(expected = RecordNotFoundException.class)
	public void TestgetAirportDetailsNegative() {
		Mockito.when(airportRepository.findByAirportCode("1")).thenReturn(Optional.ofNullable(null));
		String airport_location = "BLR";
		List<Flight> list = new ArrayList<>();
		Mockito.when(flightRepository.findBySourceOrDestination(airport_location, airport_location)).thenReturn(Optional.of(list));
		assertNotNull(airportService.getAirportDetails("1"));
	}
	@Test(expected = RecordNotFoundException.class)
	public void TestgetAirportDetailsNegative2() {
		Airport airport = new Airport();
		Mockito.when(airportRepository.findByAirportCode("1")).thenReturn(Optional.of(airport));
		String airport_location = "BLR";
		Mockito.when(flightRepository.findBySourceOrDestination(airport_location, airport_location)).thenReturn(Optional.ofNullable(null));
		assertNotNull(airportService.getAirportDetails("1"));
	}
}
