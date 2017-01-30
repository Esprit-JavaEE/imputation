package tn.esprit.imputation.services.interfaces;

import javax.ejb.Remote;

import tn.esprit.imputation.entity.Utilisateur;

@Remote
public interface UtilisateurServiceRemote {
	
	void ajouterUtilisateur(Utilisateur user);

}
