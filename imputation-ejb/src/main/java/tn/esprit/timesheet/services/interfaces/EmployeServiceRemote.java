package tn.esprit.timesheet.services.interfaces;


import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import tn.esprit.timesheet.entities.Contrat;
import tn.esprit.timesheet.entities.Employe;
import tn.esprit.timesheet.entities.Entreprise;
import tn.esprit.timesheet.entities.Mission;
import tn.esprit.timesheet.entities.Timesheet;


@Remote
public interface EmployeServiceRemote {
	
	public int ajouterEmploye(Employe employe);
	public void mettreAjourEmailByEmployeId(String email, int employeId);
	public void affecterEmployeADepartement(int employeId, int depId);
	public void desaffecterEmployeDuDepartement(int employeId, int depId);
	public int ajouterContrat(Contrat contrat);
	public void affecterContratAEmploye(int contratId, int employeId);
	public String getEmployePrenomById(int employeId);
	public void deleteEmployeById(int employeId);
	public void deleteContratById(int contratId);
	public long getNombreEmployeJPQL();
	public List<String> getAllEmployeNamesJPQL();
	public List<Employe> getAllEmployeByEntreprise(Entreprise entreprise);
	public void mettreAjourEmailByEmployeIdJPQL(String email, int employeId);
	public void deleteAllContratJPQL();
	public float getSalaireByEmployeIdJPQL(int employeId);
	public Double getSalaireMoyenByDepartementId(int departementId);
	public List<Timesheet> getTimesheetsByMissionAndDate(Employe employe, Mission mission, Date dateDebut, Date dateFin);

}
