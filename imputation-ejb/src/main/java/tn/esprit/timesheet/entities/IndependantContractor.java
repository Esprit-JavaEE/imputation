package tn.esprit.timesheet.entities;

import javax.persistence.Entity;

@Entity
public class IndependantContractor extends User{

	private double ratePerHour;

	public double getRatePerHour() {
		return ratePerHour;
	}

	public void setRatePerHour(double ratePerHour) {
		this.ratePerHour = ratePerHour;
	}
	
}
