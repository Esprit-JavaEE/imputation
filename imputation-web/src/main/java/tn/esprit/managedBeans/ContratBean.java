package tn.esprit.managedBeans;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import tn.esprit.timesheet.entities.Employe;
import tn.esprit.timesheet.services.impl.EmployeService;

@ManagedBean
//defaut scope is request
public class ContratBean {

	private Date date;
	
	private String typeContrat;
	
	private Float salaire;
	
	private List<Employe> employes;
	
	private Integer selectedEmployeId;
	
	public final String htmlSpace = "&nbsp";
	
	@EJB
	EmployeService employeService;
	
//	SimpleDateFormat simpleFormat = new SimpleDateFormat("dd-MM-yyyy");
//	date = simpleFormat.parse("28-12-2015");
//	System.out.println("*******" + simpleFormat.format(new Date()));
	@PostConstruct
	public void init(){
		date = new Date();   //default value
		typeContrat = "CDI"; //default value
		employes = employeService.getAllEmployes();
	}
	
	public void ajouterContrat(Integer employeId) throws ParseException{
		SimpleDateFormat simpleFormat = new SimpleDateFormat("dd-MM-yyyy");
		System.out.println("*******" + simpleFormat.format(date));
		System.out.println("*******" + selectedEmployeId);
	}
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getTypeContrat() {
		return typeContrat;
	}

	public void setTypeContrat(String typeContrat) {
		this.typeContrat = typeContrat;
	}


	public Float getSalaire() {
		return salaire;
	}


	public void setSalaire(Float salaire) {
		this.salaire = salaire;
	}


	public List<Employe> getEmployes() {
		return employes;
	}


	public void setEmployes(List<Employe> employes) {
		this.employes = employes;
	}


	public Integer getSelectedEmployeId() {
		return selectedEmployeId;
	}


	public void setSelectedEmployeId(Integer selectedEmployeId) {
		this.selectedEmployeId = selectedEmployeId;
	}

	public String getHtmlSpace() {
		return htmlSpace;
	}
	
	
}