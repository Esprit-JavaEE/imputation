package tn.esprit.timesheet.services.interfaces;

import javax.ejb.Local;
import javax.ejb.Remote;

import tn.esprit.timesheet.entities.User;

@Local
public interface UserServiceLocal {
	
	void ajouterUtilisateur(User user);

}
