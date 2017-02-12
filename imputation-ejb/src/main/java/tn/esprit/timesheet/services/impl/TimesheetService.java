package tn.esprit.timesheet.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.timesheet.entities.Contrat;
import tn.esprit.timesheet.entities.Departement;
import tn.esprit.timesheet.entities.Employe;
import tn.esprit.timesheet.entities.Entreprise;
import tn.esprit.timesheet.entities.Mission;
import tn.esprit.timesheet.services.interfaces.TimesheetServiceRemote;

@Stateless
@LocalBean
public class TimesheetService implements TimesheetServiceRemote {

	@PersistenceContext(unitName = "imputation-ejb")
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

	@Override
	public int ajouterEntreprise(Entreprise entreprise) {
		em.persist(entreprise);
		return entreprise.getId();
	}

	@Override
	public int ajouterDepartement(Departement dep) {
		em.persist(dep);
		return dep.getId();
	}
	
	@Override
	public void affecterDepartementAEntreprise(Departement dep, Entreprise entreprise) {
		dep.setEntreprise(entreprise);
		em.merge(dep);
//		List<Departement> deps = new ArrayList<>();
//		deps.add(dep);
//		entreprise.setDepartements(deps);
//		em.merge(entreprise);
	}
	
	@Override
	public List<Departement> getAllDepartementsByEntreprise(Entreprise entreprise) {
		Entreprise entrepriseManagedEntity = em.find(Entreprise.class, entreprise.getId());
		return new ArrayList<>(entrepriseManagedEntity.getDepartements());
	}


	@Override
	public void affecterEmployeADepartement(Employe employe, Departement dep) {
		List<Employe> employes = new ArrayList<>();
		employes.add(em.merge(employe));
		dep.setEmployes(employes);
		em.merge(dep);
	}

	@Override
	public void affecterContratAEmploye(Contrat contrat, Employe employe) {
		employe.setContrat(em.merge(contrat));
		em.merge(employe);
	}

	@Override
	public void affecterMissionADepartement(Mission mission, Departement dep) {
		List<Mission> missions = new ArrayList<>();
		missions.add(em.merge(mission));
		dep.setMissions(missions);
	}

	@Override
	public void ajouterTimesheet(Mission mission, Employe employe, Date dateDebut, Date dateFin) {
		// TODO Auto-generated method stub		
	}



}
