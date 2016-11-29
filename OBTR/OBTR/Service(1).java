package com.svecw.obtr.domain;

public class Service {
	private int serviceId;
	private int sourceId;
	private int destinationId;
	private int noOfSeats;
	private int fare;
	private int distance;
	private String journeyDate;
	private String arrivalTime;
	private String departureTime;
	private String status;
	private int sourceStationId;
	private int destinationStationId;
	public int getServiceId() {
		return serviceId;
	}
	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}
	public int getSourceStationId() {
		return sourceStationId;
	}
	public void setSourceStationId(int sourceStationId) {
		this.sourceStationId = sourceStationId;
	}
	public int getDestinationStationId() {
		return destinationStationId;
	}
	public void setDestinationStationId(int destinationStationId) {
		this.destinationStationId = destinationStationId;
	}
	public int getSourceId() {
		return sourceId;
	}
	public void setSourceId(int sourceId) {
		this.sourceId = sourceId;
	}
	public int getDestinationId() {
		return destinationId;
	}
	public void setDestinationId(int destinationId) {
		this.destinationId = destinationId;
	}
	public int getNoOfSeats() {
		return noOfSeats;
	}
	public void setNoOfSeats(int noOfSeats) {
		this.noOfSeats = noOfSeats;
	}
	public int getFare() {
		return fare;
	}
	public void setFare(int fare) {
		this.fare = fare;
	}
	public int getDistance() {
		return distance;
	}
	public void setDistance(int distance) {
		this.distance = distance;
	}
	public String getJourneyDate() {
		return journeyDate;
	}
	public void setJourneyDate(String journeyDate) {
		this.journeyDate = journeyDate;
	}
	public String getArrivalTime() {
		return arrivalTime;
	}
	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	public String getDepartureTime() {
		return departureTime;
	}
	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

}
