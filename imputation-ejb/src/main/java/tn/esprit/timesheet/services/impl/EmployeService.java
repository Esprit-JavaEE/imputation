package tn.esprit.timesheet.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.timesheet.entities.Contrat;
import tn.esprit.timesheet.entities.Departement;
import tn.esprit.timesheet.entities.Employe;
import tn.esprit.timesheet.services.interfaces.EmployeServiceRemote;

@Stateless
public class EmployeService implements EmployeServiceRemote {

	@PersistenceContext(unitName = "imputation-ejb")
	EntityManager em;

	@Override
	public int ajouterEmploye(Employe employe) {
		em.persist(employe);
		return employe.getId();
	}
	

	@Override
	public void affecterEmployeADepartement(int employeId, int depId) {
		Departement depManagedEntity = em.find(Departement.class, depId);
		Employe employeManagedEntity = em.find(Employe.class, employeId);

		if(depManagedEntity.getEmployes() == null){
			List<Employe> employes = new ArrayList<>();
			employes.add(employeManagedEntity);
			depManagedEntity.setEmployes(employes);
		}else{
			depManagedEntity.getEmployes().add(employeManagedEntity);
		}

		em.merge(depManagedEntity);
	}
	
	
	@Override
	public int ajouterContrat(Contrat contrat) {
		em.persist(contrat);
		return contrat.getReference();
	}
	

	@Override
	public void affecterContratAEmploye(int contratId, int employeId) {
		Contrat contratManagedEntity = em.find(Contrat.class, contratId);
		Employe employeManagedEntity = em.find(Employe.class, employeId);
		
		contratManagedEntity.setEmploye(employeManagedEntity);
		
		em.merge(contratManagedEntity);
	}
	
	
	@Override
	public String getEmployePrenomById(int employeId) {
		Employe employe = em.find(Employe.class, employeId);
		return employe.getPrenom();
	}


	@Override
	public void deleteEmployeById(int employeId) {
		Employe employe = em.find(Employe.class, employeId);
		em.remove(employe);
	}


	@Override
	public void desaffecterEmployeDuDepartement(int employeId, int depId) {
		Departement dep = em.find(Departement.class, depId);
		
		int employeNb = dep.getEmployes().size();
		for(int index = 0; index < employeNb; index++){
			if(dep.getEmployes().get(index).getId() == employeId){
				dep.getEmployes().remove(index);
			}
		}
		
		em.merge(dep);
	}


	@Override
	public void deleteContratById(int contratId) {
		em.remove(em.find(Contrat.class, contratId));
	}


	@Override
	public void mettreAjourEmailByEmployeId(String email, int employeId) {
		Employe employe = em.find(Employe.class, employeId);
		employe.setEmail(email);
		em.merge(employe);
	}



}
