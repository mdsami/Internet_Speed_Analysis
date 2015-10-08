package com.neova.isainterface.entity;

public class ResponseData {

	double maxSpeed;
	double minSpeed;
	double avgSpeed;

	// Parameterized Constructor.
	public ResponseData() {
		maxSpeed = 0;
		minSpeed = 0;
		avgSpeed = 0;
	}

	// Parameterized Constructor.
	public ResponseData(double maxSpeed, double minSpeed, double avgSpeed) {

		this.maxSpeed = maxSpeed;
		this.minSpeed = minSpeed;
		this.avgSpeed = avgSpeed;
	}

	public double getMaxSpeed() {
		return maxSpeed;
	}

	public void setMaxSpeed(double maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	public double getMinSpeed() {
		return minSpeed;
	}

	public void setMinSpeed(double minSpeed) {
		this.minSpeed = minSpeed;
	}

	public double getAvgSpeed() {
		return avgSpeed;
	}

	public void setAvgSpeed(double avgSpeed) {
		this.avgSpeed = avgSpeed;
	}

	@Override
	public String toString() {
		return "ResponseData [maxSpeed=" + maxSpeed + ", minSpeed=" + minSpeed + ", avgSpeed=" + avgSpeed + "]";
	}
}
