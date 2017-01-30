package tn.esprit.imputation.services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.imputation.entity.Utilisateur;
import tn.esprit.imputation.services.interfaces.UtilisateurServiceLocal;
import tn.esprit.imputation.services.interfaces.UtilisateurServiceRemote;

@Stateless
public class UtilisateurService implements UtilisateurServiceRemote, UtilisateurServiceLocal {

	@PersistenceContext(unitName="imputation-ejb")
	EntityManager em;
	
	@Override
	public void ajouterUtilisateur(Utilisateur user) {
				em.persist(user);
	}

}
