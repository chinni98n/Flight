package com.flight.info.flight.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "FlightSchedule")
public class FlightSchedule {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long flightRunWayId;
	private Long flightId;
	private Long airportId;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_runwayId")
	private RunWay runway;
	private String comment;
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public RunWay getRunway() {
		return runway;
	}

	public void setRunway(RunWay runway) {
		this.runway = runway;
	}

	private String type;

	public Long getFlightRunWayId() {
		return flightRunWayId;
	}

	public void setFlightRunWayId(Long flightRunWayId) {
		this.flightRunWayId = flightRunWayId;
	}

	public Long getFlightId() {
		return flightId;
	}

	public void setFlightId(Long flightId) {
		this.flightId = flightId;
	}

	public Long getAirportId() {
		return airportId;
	}

	public void setAirportId(Long airportId) {
		this.airportId = airportId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


}
