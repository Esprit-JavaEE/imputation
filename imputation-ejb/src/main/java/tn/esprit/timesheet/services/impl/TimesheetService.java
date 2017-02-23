	package tn.esprit.timesheet.services.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import tn.esprit.timesheet.entities.Departement;
import tn.esprit.timesheet.entities.Employe;
import tn.esprit.timesheet.entities.Mission;
import tn.esprit.timesheet.entities.Role;
import tn.esprit.timesheet.entities.Timesheet;
import tn.esprit.timesheet.entities.TimesheetPK;
import tn.esprit.timesheet.services.interfaces.TimesheetServiceRemote;

@Stateless
public class TimesheetService implements TimesheetServiceRemote{
	
	@PersistenceContext
	EntityManager entityManager;

	@Override
	public int ajouterMission(Mission mission) {
		entityManager.persist(mission);
		return mission.getId();
	}

	@Override
	public void affecterMissionADepartement(int missionId, int depId) {
		Mission mission = entityManager.find(Mission.class, missionId);
		Departement dep = entityManager.find(Departement.class, depId);
		mission.setDepartement(dep);
		entityManager.merge(mission);
	}

	@Override
	public void ajouterTimesheet(int missionId, int employeId, Date dateDebut, Date dateFin) {
		Mission mission = entityManager.find(Mission.class, missionId);
		Employe employe = entityManager.find(Employe.class, employeId);
		
		Timesheet timesheet = new Timesheet();
		TimesheetPK timesheetPK = new TimesheetPK();
		timesheetPK.setDateDebut(dateDebut);
		timesheetPK.setDateFin(dateFin);
		timesheetPK.setIdEmploye(employe.getId());
		timesheetPK.setIdMission(mission.getId());
		
		timesheet.setTimesheetPK(timesheetPK);
		timesheet.setValide(false); //par defaut non valide
		entityManager.persist(timesheet);
		
	}

	@Override
	public void validerTimesheet(int missionId, int employeId, Date dateDebut, Date dateFin, int validateurId) {
		Employe validateur = entityManager.find(Employe.class, validateurId);
		Mission mission = entityManager.find(Mission.class, missionId);
		
		//verifier s'il est un chef de departement
		if(!validateur.getRole().equals(Role.CHEF_DEPARTEMENT)){
			System.out.println("l'employe doit etre chef de departement pour valider une feuille de temps !");
			return;
		}
		//verifier s'il est le chef de departement de la mission en question
		boolean chefDeLaMission = false;
		for(Departement dep : validateur.getDepartements()){
			if(dep.getId() == mission.getDepartement().getId()){
				chefDeLaMission = true;
				break;
			}
		}
		
		if(!chefDeLaMission){
			System.out.println("l'employe doit etre chef de departement de la mission en question");
			return;
		}

		TimesheetPK timesheetPK = new TimesheetPK(missionId, employeId, dateDebut, dateFin);
		Timesheet timesheet = entityManager.find(Timesheet.class, timesheetPK);
		timesheet.setValide(true);
		entityManager.merge(timesheet);
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		System.out.println("dateDebut : " + dateFormat.format(timesheet.getTimesheetPK().getDateDebut()));

	}

	@Override
	public List<String> findAllMissionByEmployeJPQL(int employeId) {
		//Employe employe = entityManager.find(Employe.class, employeId);
	
		TypedQuery<Mission> query= entityManager.createQuery(
				"select DISTINCT m from Mission m join m.timesheets t join t.employe e where e.id=:employeId", Mission.class);
		query.setParameter("employeId", employeId);
		List<Mission> missions =  query.getResultList();
		
		List<String> missionNames = new ArrayList<>();
		
		for(Mission mission : missions){
			missionNames.add(mission.getName());
		}
		
		return missionNames;
	}

}
