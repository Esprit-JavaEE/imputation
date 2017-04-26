package tn.esprit.managedBeans;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import tn.esprit.timesheet.entities.Role;

@ManagedBean
@ApplicationScoped
public class Data {
	
	public final String htmlSpace = "&nbsp";
	public final static String formatDate = "dd-MM-yyyy";

	public Role[] getRoles(){
		return Role.values();
	}
	

	public String getHtmlSpace() {
		return htmlSpace;
	}


	public String getFormatDate() {
		return formatDate;
	}

}

