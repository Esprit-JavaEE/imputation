package tn.esprit.timesheet.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Employe implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String prenom;
	
	private String nom;
	
	private String email;
	
	private boolean isActif;
	
	Role role;
	
	
	
}
