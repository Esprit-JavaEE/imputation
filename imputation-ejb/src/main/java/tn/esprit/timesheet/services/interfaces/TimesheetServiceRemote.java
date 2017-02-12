package tn.esprit.timesheet.services.interfaces;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import tn.esprit.timesheet.entities.Contrat;
import tn.esprit.timesheet.entities.Departement;
import tn.esprit.timesheet.entities.Employe;
import tn.esprit.timesheet.entities.Entreprise;
import tn.esprit.timesheet.entities.Mission;


@Remote
public interface TimesheetServiceRemote {
	
	public int ajouterEntreprise(Entreprise entreprise);
	public int ajouterDepartement(Departement dep);
	public void affecterDepartementAEntreprise(Departement dep, Entreprise entreprise);
	public List<Departement> getAllDepartementsByEntreprise(Entreprise entreprise);
	public void ajouterEmploye(Employe employe);
	public void affecterEmployeADepartement(Employe employe, Departement dep);
	public void ajouterContrat(Contrat contrat);
	public void affecterContratAEmploye(Contrat contrat, Employe employe);
	public void ajouterMission(Mission mission);
	public void affecterMissionADepartement(Mission mission, Departement dep);
	public void ajouterTimesheet(Mission mission, Employe employe, Date dateDebut, Date dateFin);

}
