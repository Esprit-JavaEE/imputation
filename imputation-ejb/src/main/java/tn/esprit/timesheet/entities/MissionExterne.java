package tn.esprit.timesheet.entities;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class MissionExterne extends Mission {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	
	private String emailFacturation;
	
	
	private float tauxJournalierMoyen;

}
