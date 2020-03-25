package com.flight.info.flight.dto;

import java.time.LocalDateTime;

public class AirportFlightResponseDto {

	private String flightName;
	private String type;
	private LocalDateTime time;
	public String getFlightName() {
		return flightName;
	}
	public void setFlightName(String flightName) {
		this.flightName = flightName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public LocalDateTime getTime() {
		return time;
	}
	public void setTime(LocalDateTime time) {
		this.time = time;
	}
	
}
