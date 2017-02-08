package tn.esprit.timesheet.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@Entity
public class Employe implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String prenom;
	
	private String nom;
	
	//@Column(unique=true)
	private String email;
	
	private boolean isActif;
	
	@Enumerated(EnumType.STRING)
	//@NotNull
	private Role role;
	
	@ManyToMany(mappedBy="employes")
	//@NotNull
	private List<Departement> departement;
	
	@OneToOne(mappedBy="employe")
	private Contrat contrat;
	
	@OneToMany(mappedBy="employe")
	private List<Timesheet> timesheets;
}
