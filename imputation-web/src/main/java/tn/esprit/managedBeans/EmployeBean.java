package tn.esprit.managedBeans;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
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
	private Role selectedRole;
	private List<Employe> employes;
	private Integer employeIdToBeUpdated; // @viewScoped est necessaire, 
										//sinon la valeur sera perdu
	// injection de dépendances
	@EJB
	EmployeService employeService;
	
	// injection de dépendances
	@ManagedProperty(value = "#{loginBean}")
	LoginBean loginBean;
	
	public void addEmploye(){
		employeService.ajouterEmploye(new Employe(nom, prenom, 
				email, password, isActif, selectedRole));
	}
	
//	if(loginBean == null || !loginBean.isLoggedIn()){
//		return "/login?faces-redirect=true";
//	}
	//FacesContext.getCurrentInstance().addMessage("welcome:ajoutEmp", new FacesMessage("Erreur !"));

	//Ceci ne fonctionne qu'avec @ViewScoped sinon les valeurs employeId, role ... seront perdu, 
	//parce qu'il sont sauvegardé dans l'ancienne requete : appel a la methode modifier()
	public void mettreAjourEmploye(){
		employeService.updateEmploye(new Employe(nom, prenom, 
				email, password, isActif, selectedRole, employeIdToBeUpdated));
	}
	
	public void supprimer(Integer employeID){
		employeService.deleteEmployeById(employeID);
	}
	
	public void modifier(Employe employe){
		this.setEmail(employe.getEmail());
		this.setPassword(employe.getPassword());
		this.setActif(employe.isActif());
		this.setPrenom(employe.getPrenom());
		this.setNom(employe.getNom());
		this.setRole(employe.getRole());
		this.setEmployeIdToBeUpdated(employe.getId());
	}

	
	public LoginBean getLoginBean() {
		return loginBean;
	}

	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}

	public Integer getEmployeIdToBeUpdated() {
		return employeIdToBeUpdated;
	}

	public void setEmployeIdToBeUpdated(Integer employeIdToBeUpdated) {
		this.employeIdToBeUpdated = employeIdToBeUpdated;
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
		return selectedRole;
	}

	public void setRole(Role role) {
		this.selectedRole = role;
	}
	
}
