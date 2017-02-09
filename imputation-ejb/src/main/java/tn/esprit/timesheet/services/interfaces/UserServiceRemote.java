package tn.esprit.timesheet.services.interfaces;

import javax.ejb.Remote;

import tn.esprit.timesheet.entities.Contrat;
import tn.esprit.timesheet.entities.Employe;
import tn.esprit.timesheet.entities.Mission;


@Remote
public interface UserServiceRemote {
	
	public void ajouterEmploye(Employe user);
	
	public void ajouterMission(Mission mission);
	
	public void ajouterContrat(Contrat contrat);


}
