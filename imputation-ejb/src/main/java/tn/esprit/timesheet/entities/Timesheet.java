package tn.esprit.timesheet.entities;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Timesheet implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3876346912862238239L;

	@EmbeddedId
	private TimesheetPK timesheetPK;
	
	@ManyToOne
    @JoinColumn(name = "idMission", referencedColumnName = "id", insertable=false, updatable=false)
	private Mission mission;
	
	@ManyToOne
    @JoinColumn(name = "idEmploye", referencedColumnName = "id", insertable=false, updatable=false)
	private Employe employe;

	public TimesheetPK getTimesheetPK() {
		return timesheetPK;
	}

	public void setTimesheetPK(TimesheetPK timesheetPK) {
		this.timesheetPK = timesheetPK;
	}

	public Mission getMission() {
		return mission;
	}

	public void setMission(Mission mission) {
		this.mission = mission;
	}

	public Employe getEmploye() {
		return employe;
	}

	public void setEmploye(Employe employe) {
		this.employe = employe;
	}

	
	
}
