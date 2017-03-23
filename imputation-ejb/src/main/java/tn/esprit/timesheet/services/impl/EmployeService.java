package tn.esprit.timesheet.services.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

import tn.esprit.timesheet.entities.Contrat;
import tn.esprit.timesheet.entities.Departement;
import tn.esprit.timesheet.entities.Employe;
import tn.esprit.timesheet.entities.Entreprise;
import tn.esprit.timesheet.entities.Mission;
import tn.esprit.timesheet.entities.Timesheet;
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

		//em.merge(depManagedEntity);
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


//	@Override
//	public void deleteEmployeById(int employeId) {
//		em.remove(em.find(Employe.class, employeId));
//	}
	
	@Override
	public void deleteEmployeById(int employeId) {
		Employe employe = em.find(Employe.class, employeId);
		
		//Desaffecter l'employe de tous les departements
		//c'est le bout master qui permet de mettre a jour
		//la table d'association
		for(Departement dep : employe.getDepartements()){
			dep.getEmployes().remove(employe);
		}
		
		em.remove(employe);
	}


	@Override
	public void desaffecterEmployeDuDepartement(int employeId, int depId) {
		Departement dep = em.find(Departement.class, depId);
		
		int employeNb = dep.getEmployes().size();
		for(int index = 0; index < employeNb; index++){
			if(dep.getEmployes().get(index).getId() == employeId){
				dep.getEmployes().remove(index);
				break;//a revoir
			}
		}
		
		//em.merge(dep);
	}


	@Override
	public void deleteContratById(int contratId) {
		em.remove(em.find(Contrat.class, contratId));
	}


	@Override
	public void mettreAjourEmailByEmployeId(String email, int employeId) {
		Employe employe = em.find(Employe.class, employeId);
		employe.setEmail(email);
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
	  TypedQuery<Float> query = em.createQuery(
	  "select c.salaire from Contrat c join c.employe e where e.id=:employeId", 
	  Float.class);
	  query.setParameter("employeId", employeId);
	  return query.getSingleResult();
	}

    
	@Override
	public List<Employe> getAllEmployeByEntreprise(Entreprise entreprise) {
		TypedQuery<Employe> query = em.createQuery("Select "
				+ "DISTINCT emp from Employe emp "
				+ "join emp.departements dps "
				+ "join dps.entreprise entrep "
				+ "where entrep=:entreprise", Employe.class);
		
		query.setParameter("entreprise", entreprise);
		return query.getResultList();
	}

    
	@Override
	public Double getSalaireMoyenByDepartementId(int departementId) {
		TypedQuery<Double> query = em.createQuery("Select "
				+ "DISTINCT AVG(cont.salaire) from Contrat cont "
				+ "join cont.employe emp "
				+ "join emp.departements deps "
				+ "where deps.id=:depId", Double.class);
		
		query.setParameter("depId", departementId);		
		return query.getSingleResult();
	}
	
	@Override
	public List<Timesheet> getTimesheetsByMissionAndDate(Employe employe, 
			Mission mission, Date dateDebut, Date dateFin){
		
		TypedQuery<Timesheet> query = em.createQuery(
				"Select t from Timesheet t "
				+ "where t.mission=:mis and "
				+ "t.employe=:emp and "
				+ "t.timesheetPK.dateDebut>=:dateD and "
				+ "t.timesheetPK.dateFin<=:dateF", Timesheet.class);
		
		query.setParameter("mis", mission);
		query.setParameter("emp", employe);	
		query.setParameter("dateD", dateDebut , TemporalType.DATE);	
		query.setParameter("dateF", dateFin, TemporalType.DATE);	

		return query.getResultList();
	}
	
//		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");  
//	for(Timesheet timesheet : timesheets){
//		System.out.println("DateDebut : "+ 
//					dateFormat.format((timesheet.getTimesheetPK().getDateDebut())));
//		System.out.println("DateFin : "+ 
//				dateFormat.format((timesheet.getTimesheetPK().getDateFin())));
//		System.out.println("Mission ID : " + timesheet.getMission().getId());
//		System.out.println("Employe ID : " + timesheet.getEmploye().getId());
//	}
	
	
}