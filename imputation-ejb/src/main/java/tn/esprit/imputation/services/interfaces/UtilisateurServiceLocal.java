package tn.esprit.imputation.services.interfaces;

import javax.ejb.Local;
import javax.ejb.Remote;

import tn.esprit.imputation.entity.Utilisateur;

@Local
public interface UtilisateurServiceLocal {
	
	void ajouterUtilisateur(Utilisateur user);

}
