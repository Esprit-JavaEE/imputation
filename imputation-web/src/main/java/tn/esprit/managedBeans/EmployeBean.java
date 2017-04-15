package tn.esprit.managedBeans;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

import tn.esprit.timesheet.entities.Employe;
import tn.esprit.timesheet.entities.Role;
import tn.esprit.timesheet.services.impl.EmployeService;

@ManagedBean
@ViewScoped
public class EmployeBean {

	private String prenom;
	private String nom;
	private String password;
	private String email;
	private boolean isActif;
	private Role role;
	private List<Employe> employes;
	private HtmlDataTable employesTable;
	private Integer employeId;

	@EJB
	EmployeService employeService;
	
	@Inject
	LoginBean loginBean;
	
	public void addEmploye(){
		//if(loginBean != null && loginBean.isLoggedIn()){
			employeService.ajouterEmploye(new Employe(nom, prenom, email, password, isActif, role));
		//}else{
			//FacesContext.getCurrentInstance().addMessage("welcome:ajoutEmp", new FacesMessage("Erreur !"));
		//} 
	}
	
	public void modifierEmploye(){
		//if(loginBean != null && loginBean.isLoggedIn()){
			employeService.updateEmploye(new Employe(nom, prenom, email, password, isActif, role, employeId));
		//}else{
			//FacesContext.getCurrentInstance().addMessage("welcome:ajoutEmp", new FacesMessage("Erreur !"));
		//}
	}
	
	public void supprimer(ActionEvent event){
		String employeId = FacesContext.getCurrentInstance()
			.getExternalContext().getRequestParameterMap().get("employeId");
		
		employeService.deleteEmployeById(Integer.valueOf(employeId));
	}
	
	public void modifier(){
		Employe employe = (Employe)employesTable.getRowData();
		this.setEmail(employe.getEmail());
		this.setPassword(employe.getPassword());
		this.setActif(employe.isActif());
		this.setPrenom(employe.getPrenom());
		this.setNom(employe.getNom());
		this.setRole(employe.getRole());
		this.setEmployeId(employe.getId());
	}

	
	
	public int getEmployeId() {
		return employeId;
	}

	public void setEmployeId(int employeId) {
		this.employeId = employeId;
	}

	public HtmlDataTable getEmployesTable() {
		return employesTable;
	}

	public void setEmployesTable(HtmlDataTable employesTable) {
		this.employesTable = employesTable;
	}

	public List<Employe> getEmployes() {
		employes = employeService.getAllEmployes();
		return employes;
	}


	public void setEmployes(List<Employe> employes) {
		this.employes = employes;
	}


	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isActif() {
		return isActif;
	}

	public void setActif(boolean isActif) {
		this.isActif = isActif;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
}
