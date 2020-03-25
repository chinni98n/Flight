package com.flight.info.flight.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "RunWay")
public class RunWay {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long runWayId;
	private String runWayBlock;
	private long airportId;
	private String status;

	public String getRunWayBlock() {
		return runWayBlock;
	}

	public void setRunWayBlock(String runWayBlock) {
		this.runWayBlock = runWayBlock;
	}

	public long getAirportId() {
		return airportId;
	}

	public void setAirportId(long airportId) {
		this.airportId = airportId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	

}
