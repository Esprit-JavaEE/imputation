package tn.esprit.timesheet.services.interfaces;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import tn.esprit.timesheet.entities.Mission;

@Remote
public interface TimesheetServiceRemote {
	public int ajouterMission(Mission mission);
	public void affecterMissionADepartement(int missionId, int depId);
	public void ajouterTimesheet(int missionId, int employeId, Date dateDebut, Date dateFin);
	public void validerTimesheet(int missionId, int employeId, Date dateDebut, Date dateFin, int validateurId);
	public List<String> findAllMissionByEmployeJPQL(int employeId);
}
