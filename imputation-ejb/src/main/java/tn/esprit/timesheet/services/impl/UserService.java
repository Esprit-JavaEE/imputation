//package tn.esprit.timesheet.services.impl;
//
//import javax.ejb.Stateless;
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//
//import tn.esprit.timesheet.entities.Employee;
//import tn.esprit.timesheet.services.interfaces.UserServiceLocal;
//import tn.esprit.timesheet.services.interfaces.UserServiceRemote;
//
//@Stateless
//public class UserService implements UserServiceRemote, UserServiceLocal {
//
//	@PersistenceContext(unitName="imputation-ejb")
//	EntityManager em;
//	
//	@Override
//	public void ajouterUtilisateur(Employee user) {
//				em.persist(user);
//	}
//
//}
