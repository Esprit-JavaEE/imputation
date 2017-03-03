package tn.esprit.timesheet.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.timesheet.entities.Departement;
import tn.esprit.timesheet.entities.Entreprise;
import tn.esprit.timesheet.services.interfaces.EntrepriseServiceRemote;

@Stateless
public class EntrepriseService implements EntrepriseServiceRemote {

	@PersistenceContext(unitName = "imputation-ejb")
	EntityManager em;

	@Override
	public int ajouterEntreprise(Entreprise entreprise) {
		//entreprise.setName("autre entreprise"); avec ça une autre requete d'update 
		//va s'exécuter a la fin de la methode pour mettre
		//a jour la base de données avec le nouveau nom
		em.persist(entreprise);
		return entreprise.getId();
	}
	


	@Override
	public int ajouterDepartement(Departement dep) {
		em.persist(dep);
		return dep.getId();
	}
	
	@Override
	public void affecterDepartementAEntreprise(int depId, int entrepriseId) {
		//Le bout Master de cette relation N:1 est departement  
		//donc il faut rajouter l'entreprise a departement 
		// ==> c'est l'objet departement(le master) qui va mettre a jour l'association
		//Rappel : la classe qui contient mappedBy represente le bout Slave
		//Rappel : Dans une relation oneToMany le mappedBy doit etre du cote one.
		Entreprise entrepriseManagedEntity = em.find(Entreprise.class, entrepriseId);
		Departement depManagedEntity = em.find(Departement.class, depId);

		depManagedEntity.setEntreprise(entrepriseManagedEntity);
		//inutile de faire : em.merge(depManagedEntity);
	}
	
	
//	@Override
//	public void affecterDepartementAEntreprise(int depId, int entrepriseId) {
//		Entreprise entrepriseManagedEntity = em.find(Entreprise.class, entrepriseId);
//		Departement depManagedEntity = em.find(Departement.class, depId);
//
//		entrepriseManagedEntity.getDepartements().add(depManagedEntity);
//		//ceci ne met pas a jour la relation !
//	}
	
	@Override
	public List<String> getAllDepartementsNamesByEntreprise(int entrepriseId) {
		Entreprise entrepriseManagedEntity = em.find(Entreprise.class, entrepriseId);
		List<String> depNames = new ArrayList<>();
		for(Departement dep : entrepriseManagedEntity.getDepartements()){
			depNames.add(dep.getName());
		}
		
		return depNames;
	}
	
	@Override
	public void deleteEntrepriseById(int entrepriseId){
		em.remove(em.find(Entreprise.class, entrepriseId));
	}
	
	@Override
	public void deleteDepartementById(int depId){
		em.remove(em.find(Departement.class, depId));
	}



	@Override
	public Entreprise getEntrepriseById(int entrepriseId) {
		return em.find(Entreprise.class, entrepriseId);
	}



	
	
}
