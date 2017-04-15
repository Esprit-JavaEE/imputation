package tn.esprit.managedBeans;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import tn.esprit.timesheet.entities.Role;

@ManagedBean
@ApplicationScoped
public class Data {

	public Role[] getRoles(){
		return Role.values();
	}
}

