package carAccessories;

public class Installer extends Users {
	public boolean isInstallerAvailable = true;
    
	public Installer(String email, String password, String role) {
		super(email, password, role);
	}
	
	public Installer(String email, String password, String role, String contactNumber) {
		super(email, password, role);
		this.setContactNumber(contactNumber);
	}

//	public void setAvailability(boolean b) {
//		isInstallerAvailable = b;
//	}

	public boolean getAvailability() {
		return isInstallerAvailable;
	}

	
}
