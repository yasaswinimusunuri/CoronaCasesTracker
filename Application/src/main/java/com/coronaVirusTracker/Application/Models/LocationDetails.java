package com.coronaVirusTracker.Application.Models;

public class LocationDetails {

	private String Country;
	private String State;
	private int NewCases;
	public String getCountry() {
		return Country;
	}
	public void setCountry(String country) {
		Country = country;
	}
	public String getState() {
		return State;
	}
	public void setState(String state) {
		State = state;
	}
	public int getNewCases() {
		return NewCases;
	}
	public void setNewCases(int latestCases) {
		NewCases = latestCases;
	}
	@Override
	public String toString() {
		return "LocationDetails [Country=" + Country + ", State=" + State + ", NewCases=" + NewCases + "]";
	}
	
	
}
