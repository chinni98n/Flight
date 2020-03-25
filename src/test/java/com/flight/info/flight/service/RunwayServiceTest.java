package com.flight.info.flight.service;

import static org.junit.Assert.assertEquals;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.flight.info.flight.constants.ApplicationConstants;
import com.flight.info.flight.dto.LandingInfoRequestDto;
import com.flight.info.flight.dto.ResponseDto;
import com.flight.info.flight.entity.FlightSchedule;
import com.flight.info.flight.entity.RunWay;
import com.flight.info.flight.exception.RecordNotFoundException;
import com.flight.info.flight.repository.RunwayRepository;
import com.flight.info.flight.repository.ScheduleRepository;
import com.flight.info.flight.serviceImpl.RunwayServiceImpl;

@RunWith(MockitoJUnitRunner.Silent.class)
public class RunwayServiceTest {
	
	@Mock
	RunwayRepository runwayRepository;
	@Mock
	ScheduleRepository scheduleRepository;
	@InjectMocks
	RunwayServiceImpl runwayService;
	Optional<ResponseDto> responseDto;
	
	LandingInfoRequestDto landingInfoRequestDto;
	@Before
	public void before() {
		landingInfoRequestDto = new LandingInfoRequestDto();
		landingInfoRequestDto.setRunwayBlock("B");
		landingInfoRequestDto.setType("landing");
	}
	@Test
	public void TestsaveflightRunwayInfo() {
		RunWay runway = new RunWay();
		runway.setStatus("clear");
		Mockito.when(runwayRepository
				.findByRunWayBlockAndAirportId(landingInfoRequestDto.getRunwayBlock(), 1L)).thenReturn(Optional.of(runway));
		responseDto = runwayService.saveflightRunwayInfo(1L, 1L, landingInfoRequestDto);
		assertEquals(responseDto.get().getStatusCode(),ApplicationConstants.SAVE_SUCCESS_CODE);
	}
	
	@Test
	public void TestsaveflightRunwayInfoTakeOff() {
		landingInfoRequestDto.setType("takeoff");
		RunWay runway = new RunWay();
		FlightSchedule fs = new FlightSchedule();
		Mockito.when(runwayRepository
				.findByRunWayBlockAndAirportId(landingInfoRequestDto.getRunwayBlock(), 1L)).thenReturn(Optional.of(runway));
		Mockito.when(scheduleRepository.save(fs)).thenReturn(fs);
		responseDto = runwayService.saveflightRunwayInfoTakeOff(1L, 1L, landingInfoRequestDto);
		assertEquals(responseDto.get().getStatusCode(),ApplicationConstants.SAVE_SUCCESS_CODE);
	}
	
	@Test(expected = RecordNotFoundException.class)
	public void TestsaveflightRunwayInfoTakeOffNegative() {
		landingInfoRequestDto.setType("takeoff");
		FlightSchedule fs = new FlightSchedule();
		Mockito.when(runwayRepository
				.findByRunWayBlockAndAirportId(landingInfoRequestDto.getRunwayBlock(), 1L)).thenReturn(Optional.ofNullable(null));
		Mockito.when(scheduleRepository.save(fs)).thenReturn(fs);
		responseDto = runwayService.saveflightRunwayInfoTakeOff(1L, 1L, landingInfoRequestDto);
		assertEquals(responseDto.get().getStatusCode(),ApplicationConstants.SAVE_SUCCESS_CODE);
	}
	
	@Test
	public void TestsaveflightRunwayInfoLanding() {
		landingInfoRequestDto.setType("landing");
		RunWay runway = new RunWay();
		FlightSchedule fs = new FlightSchedule();
		Mockito.when(runwayRepository
				.findByRunWayBlockAndAirportId(landingInfoRequestDto.getRunwayBlock(), 1L)).thenReturn(Optional.of(runway));
		Mockito.when(scheduleRepository.save(fs)).thenReturn(fs);
		responseDto = runwayService.saveflightRunwayInfoTakeOff(1L, 1L, landingInfoRequestDto);
		assertEquals(responseDto.get().getStatusCode(),ApplicationConstants.SAVE_SUCCESS_CODE);
	}
	
	@Test(expected = RecordNotFoundException.class)
	public void TestsaveflightRunwayInfoLandingNegative() {
		landingInfoRequestDto.setType("landing");
		FlightSchedule fs = new FlightSchedule();
		Mockito.when(runwayRepository
				.findByRunWayBlockAndAirportId(landingInfoRequestDto.getRunwayBlock(), 1L)).thenReturn(Optional.ofNullable(null));
		Mockito.when(scheduleRepository.save(fs)).thenReturn(fs);
		responseDto = runwayService.saveflightRunwayInfoTakeOff(1L, 1L, landingInfoRequestDto);
		assertEquals(responseDto.get().getStatusCode(),ApplicationConstants.SAVE_SUCCESS_CODE);
	}
	
}
