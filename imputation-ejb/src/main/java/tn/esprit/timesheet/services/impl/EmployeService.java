package tn.esprit.timesheet.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

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


	@Override
	public long getNombreEmployeJPQL() {
		TypedQuery<Long> query = em.createQuery("select count(e) from Employe e", Long.class);
		return query.getSingleResult();
	}


	@Override
	public List<String> getAllEmployeNamesJPQL() {
		List<Employe> employes = em.createQuery("select e from Employe e", Employe.class).getResultList();
		List<String> employeNames = new ArrayList<>();
		for(Employe employe : employes){
			employeNames.add(employe.getNom());
		}
		
		return employeNames;
	}
	
	@Override
	public void mettreAjourEmailByEmployeIdJPQL(String email, int employeId){
		Query query = em.createQuery("update Employe e set email=:email where e.id=:employeId");
		query.setParameter("email", email);
		query.setParameter("employeId", employeId);
		int modified = query.executeUpdate();
		if(modified == 1){
			System.out.println("successfully updated");
		}else{
			System.out.println("failed to update");
		}
	}


	@Override
	public void deleteAllContratJPQL() {
		int modified = em.createQuery("delete from Contrat").executeUpdate();
		if(modified > 1){
			System.out.println("successfully deleted");
		}else{
			System.out.println("failed to delete");
		}
	}


	@Override
	public float getSalaireByEmployeIdJPQL(int employeId) {
		TypedQuery<Float> query = em.createQuery("select c.salaire from Contrat c join c.employe e where e.id=:employeId", Float.class);
		query.setParameter("employeId", employeId);	
		return query.getSingleResult();
	}
	
	
	
	
}