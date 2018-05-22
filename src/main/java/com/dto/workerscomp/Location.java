package com.dto.workerscomp;

public class Location {
	
	private int locationNumber;
	private String highestFloor;
	private Address address;

	public int getLocationNumber() {
		return locationNumber;
	}

	public void setLocationNumber(int locationNumber) {
		this.locationNumber = locationNumber;
	}

	public String getHighestFloor() {
		return highestFloor;
	}

	public void setHighestFloor(String highestFloor) {
		this.highestFloor = highestFloor;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

}
