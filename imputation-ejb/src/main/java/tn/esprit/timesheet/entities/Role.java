package tn.esprit.timesheet.entities;

public enum Role{
	CHEF_DEPARTEMENT("Chef departement"), 
	ADMIN ("Administrateur"), 
	INGENIEUR("Ingénieur"), 
	TECHNICIEN("Technicien");
	
    private String label;
	
	private Role(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}
	
}
