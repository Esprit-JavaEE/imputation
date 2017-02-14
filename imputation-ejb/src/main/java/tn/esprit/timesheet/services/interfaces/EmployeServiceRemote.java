package tn.esprit.timesheet.services.interfaces;

import java.util.Date;

import javax.ejb.Remote;

import tn.esprit.timesheet.entities.Contrat;
import tn.esprit.timesheet.entities.Departement;
import tn.esprit.timesheet.entities.Employe;
import tn.esprit.timesheet.entities.Mission;


@Remote
public interface EmployeServiceRemote {
	
	public int ajouterEmploye(Employe employe);
	public void affecterEmployeADepartement(int employeId, int depId);
	public int ajouterContrat(Contrat contrat);
	void affecterContratAEmploye(int contratId, int employeId);
	public void ajouterMission(Mission mission);
	public void affecterMissionADepartement(Mission mission, Departement dep);
	public void ajouterTimesheet(Mission mission, Employe employe, Date dateDebut, Date dateFin);

}
