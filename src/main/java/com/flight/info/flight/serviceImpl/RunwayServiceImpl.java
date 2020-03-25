package com.flight.info.flight.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flight.info.flight.constants.ApplicationConstants;
import com.flight.info.flight.dto.LandingInfoRequestDto;
import com.flight.info.flight.dto.ResponseDto;
import com.flight.info.flight.entity.FlightSchedule;
import com.flight.info.flight.entity.RunWay;
import com.flight.info.flight.exception.RecordNotFoundException;
import com.flight.info.flight.exception.RunwaysAreBusyException;
import com.flight.info.flight.repository.RunwayRepository;
import com.flight.info.flight.repository.ScheduleRepository;
import com.flight.info.flight.service.RunwayService;

@Service
public class RunwayServiceImpl implements RunwayService {
	@Autowired
	RunwayRepository runwayRepository;
	@Autowired
	ScheduleRepository scheduleRepository;

	@Override
	public Optional<ResponseDto> saveflightRunwayInfo(Long flightId, Long airportId,
			LandingInfoRequestDto landingInfoRequestDto) {
		Optional<ResponseDto> responseDto = Optional.empty();
		String s = landingInfoRequestDto.getType();
		String landing = "landing";
		if (landing.equalsIgnoreCase(s)) {
			responseDto = saveflightRunwayInfoLanding(flightId, airportId, landingInfoRequestDto);
		}
		responseDto = saveflightRunwayInfoTakeOff(flightId, airportId, landingInfoRequestDto);

		return responseDto;
	}

	@Override
	public Optional<ResponseDto> saveflightRunwayInfoTakeOff(Long flightId, Long airportId,
			LandingInfoRequestDto takeOffInfoRequestDto) {
		Optional<RunWay> runway_opt = runwayRepository
				.findByRunWayBlockAndAirportId(takeOffInfoRequestDto.getRunwayBlock(), airportId);
		if (!runway_opt.isPresent()) {
			throw new RecordNotFoundException(ApplicationConstants.NO_RECORD_FOUND);
		}
		RunWay runway = runway_opt.get();

		FlightSchedule fs = new FlightSchedule();

		fs.setAirportId(airportId);
		fs.setFlightId(flightId);
		runway.setStatus("clear");
		fs.setRunway(runway);
		fs.setType(takeOffInfoRequestDto.getType());
		fs.setComment("takeoff from runway " + runway.getRunWayBlock() + " is success");
		scheduleRepository.save(fs);

		ResponseDto responseDto = new ResponseDto();
		responseDto.setStatusCode(ApplicationConstants.SAVE_SUCCESS_CODE);
		responseDto.setMessage(ApplicationConstants.SAVE_SUCCESS);
		return Optional.of(responseDto);
	}

	@Override
	public Optional<ResponseDto> saveflightRunwayInfoLanding(Long flightId, Long airportId,
			LandingInfoRequestDto landingInfoRequestDto) {
		Optional<RunWay> runway_opt = runwayRepository
				.findByRunWayBlockAndAirportId(landingInfoRequestDto.getRunwayBlock(), airportId);
		if (!runway_opt.isPresent()) {
			throw new RecordNotFoundException(ApplicationConstants.NO_RECORD_FOUND);
		}
		RunWay runway = runway_opt.get();
		String status = runway.getStatus();
		FlightSchedule fs = new FlightSchedule();
		if (status.equalsIgnoreCase("clear")) {

			fs.setAirportId(airportId);
			fs.setFlightId(flightId);
			runway.setStatus("busy");
			fs.setRunway(runway);
			fs.setType(landingInfoRequestDto.getType());
			fs.setComment("landing on " + runway.getRunWayBlock() + " is success");
			scheduleRepository.save(fs);
		} else {
			Optional<List<RunWay>> runwayList_opt = runwayRepository.findByAirportIdAndStatus(airportId, "clear");
			if (!runwayList_opt.isPresent()) {
				throw new RunwaysAreBusyException(ApplicationConstants.RUNWAY_BUSY);
			}
			runway = runwayList_opt.get().get(0);
			fs.setAirportId(airportId);
			fs.setComment("landed on " + runway.getRunWayBlock() + " as " + landingInfoRequestDto.getRunwayBlock()
					+ " is busy");
			fs.setFlightId(flightId);
			runway.setStatus("busy");
			fs.setRunway(runway);
			fs.setType(landingInfoRequestDto.getType());
			scheduleRepository.save(fs);
		}
		ResponseDto responseDto = new ResponseDto();
		responseDto.setStatusCode(ApplicationConstants.SAVE_SUCCESS_CODE);
		responseDto.setMessage(ApplicationConstants.SAVE_SUCCESS);
		return Optional.of(responseDto);
	}

}
