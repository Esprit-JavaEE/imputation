package tn.esprit.timesheet.entities;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Contrat implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int reference;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dateDebut;
	
	private String typeContrat;
	
	private float salaire;
	
	@OneToOne
	private Employe employe;
	
	
}
