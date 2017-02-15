package tn.esprit.timesheet.services.interfaces;


import javax.ejb.Remote;

import tn.esprit.timesheet.entities.Contrat;
import tn.esprit.timesheet.entities.Employe;


@Remote
public interface EmployeServiceRemote {
	
	public int ajouterEmploye(Employe employe);
	public void mettreAjourEmailByEmployeId(String email, int employeId);
	public void affecterEmployeADepartement(int employeId, int depId);
	public void desaffecterEmployeDuDepartement(int employeId, int depId);
	public int ajouterContrat(Contrat contrat);
	void affecterContratAEmploye(int contratId, int employeId);
	String getEmployePrenomById(int employeId);
	void deleteEmployeById(int employeId);
	void deleteContratById(int contratId);

}
