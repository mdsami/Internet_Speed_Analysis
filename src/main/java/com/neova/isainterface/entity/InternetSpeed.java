package com.neova.isainterface.entity;

public class InternetSpeed {

	String timestamp;
	double speed;

	public InternetSpeed(String timestamp, double speed) {
		this.timestamp = timestamp;
		this.speed = speed;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	@Override
	public String toString() {
		return "InternetSpeed [timestamp=" + timestamp + ", speed=" + speed + "]";
	}
}//End of InternetSpeed Class.
