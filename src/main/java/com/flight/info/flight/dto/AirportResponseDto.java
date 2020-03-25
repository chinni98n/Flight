package com.flight.info.flight.dto;

import java.util.List;

public class AirportResponseDto {

	private String airportName;
	private String location;
	private int noOfRunways;
	private List<AirportFlightResponseDto> flightList;
	public String getAirportName() {
		return airportName;
	}
	public void setAirportName(String airportName) {
		this.airportName = airportName;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public int getNoOfRunways() {
		return noOfRunways;
	}
	public void setNoOfRunways(int noOfRunways) {
		this.noOfRunways = noOfRunways;
	}
	public List<AirportFlightResponseDto> getFlightList() {
		return flightList;
	}
	public void setFlightList(List<AirportFlightResponseDto> flightList) {
		this.flightList = flightList;
	}
	
}
