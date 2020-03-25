package com.flight.info.flight.dto;

import java.time.LocalDateTime;

public class FlightResponseDto {
	private String flightCode;
	private String flightName;
	private String source;
	private String destination;
	private LocalDateTime startTime;
	private LocalDateTime landingTime;
	
	public String getFlightCode() {
		return flightCode;
	}
	public void setFlightCode(String flightCode) {
		this.flightCode = flightCode;
	}
	public String getFlightName() {
		return flightName;
	}
	public void setFlightName(String flightName) {
		this.flightName = flightName;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public LocalDateTime getStartTime() {
		return startTime;
	}
	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}
	public LocalDateTime getLandingTime() {
		return landingTime;
	}
	public void setLandingTime(LocalDateTime landingTime) {
		this.landingTime = landingTime;
	}
	
}
