package tn.esprit.timesheet.entities;

import javax.persistence.Entity;

@Entity
public class Employee extends User  {

	private double salary;

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}
	
}
