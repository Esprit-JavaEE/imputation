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
import tn.esprit.timesheet.services.interfaces.EntrepriseServiceRemote;
import tn.esprit.timesheet.services.interfaces.EmployeServiceRemote;

@Stateless
@LocalBean
public class EntrepriseService implements EntrepriseServiceRemote {

	@PersistenceContext(unitName = "imputation-ejb")
	EntityManager em;

	@Override
	public int ajouterEntreprise(Entreprise entreprise) {
		em.persist(entreprise);
		//entreprise.setName("autre entreprise"); avec ça une autre requete d'update 
		//va s'exécuter a la fin de la methode pour mettre
		//a jour la base de données avec le nouveau nom
		return entreprise.getId();
	}

	@Override
	public int ajouterDepartement(Departement dep) {
		em.persist(dep);
		return dep.getId();
	}
	
	@Override
	public void affecterDepartementAEntreprise(int depId, int entrepriseId) {
		//Le bout Master de cette relation N:N est departement donc 
		//il faut rajouter l'entreprise a departement puis faire le merge de
		//l'entite departement
		
		Entreprise entrepriseManagedEntity = em.find(Entreprise.class, entrepriseId);
		Departement depManagedEntity = em.find(Departement.class, depId);

		depManagedEntity.setEntreprise(entrepriseManagedEntity);
		em.merge(depManagedEntity);
	}
	
	@Override
	public List<Departement> getAllDepartementsByEntreprise(int entrepriseId) {
		Entreprise entrepriseManagedEntity = em.find(Entreprise.class, entrepriseId);
		return new ArrayList<>(entrepriseManagedEntity.getDepartements());
	}

	@Override
	public void deleteEntreprise(Entreprise entreprise){
		em.remove(em.merge(entreprise));
	}
	
	@Override
	public void deleteDepartement(Departement dep){
		em.remove(em.merge(dep));
	}


}
