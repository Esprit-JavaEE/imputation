package tn.esprit.timesheet.services.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.timesheet.entities.Contrat;
import tn.esprit.timesheet.entities.Employe;
import tn.esprit.timesheet.entities.Mission;
import tn.esprit.timesheet.services.interfaces.UserServiceRemote;

@Stateless
public class EmployeService implements UserServiceRemote {

	@PersistenceContext(unitName="imputation-ejb")
	EntityManager em;
	
	@Override
	public void ajouterEmploye(Employe employe) {
		em.persist(employe);
	}

	@Override
	public void ajouterMission(Mission mission) {
		em.persist(mission);
	}

	@Override
	public void ajouterContrat(Contrat contrat) {
		em.persist(contrat);
	}

	

	
	
}
