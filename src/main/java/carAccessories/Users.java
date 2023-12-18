package carAccessories;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.Popup;

public class Users {
	private String name;
	public String email;
	public String password;
	public String role;
	public String contactNumber;
	public boolean active ;
	protected boolean isSignedIn;
	
	public Users(String email, String password, String role) {
		this.email = email;
		this.password = password;
		this.role = role;
		this.isSignedIn = false;
		this.active = true;
		this.contactNumber = "Empty";
		this.name = "Empty";

	}
	
	public Users(String newEmail, String newContactNumber, String newPassword, String newRole) {
		this.email = newEmail;
		this.contactNumber = newContactNumber;
		this.password = newPassword;
		this.role = newRole;
		this.isSignedIn = false;
		this.active = true;	
	}

	public void print() {
		System.out.println("================================================================================");
		System.out.println("\t\t\t\tUser Profile");
		System.out.println("Email: " + this.email);
		System.out.println("Role: " + this.role);
		System.out.println("Contact Number: " + this.contactNumber);
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	boolean isActive() {
		return active;
	}
	public void setActive(boolean activate) {
		this.active = activate;
	}
	public boolean createAccount(String email, String password, String contactNumber) {
		return isValidEmail(email) && isPasswordValid(password) && isValidContactNumber(contactNumber);
	}
	
	public boolean checkEmail(String email) {
		return this.email.equals(email);
	}


	public boolean checkPassword(String password) {
		return this.password.equals(password);		
	}


	public boolean signIn(String email, String password) {
		this.isSignedIn = checkEmail(email) && checkPassword(password);
		return this.isSignedIn;
	}
	
	public boolean checkRole(String role) {
		return this.role.equals(role);		
	}
	

	public boolean signOut() {
		this.isSignedIn = false;
		return this.isSignedIn;
	}
	
	public boolean isSignedIn() {
		return this.isSignedIn;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	
	public boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public boolean isPasswordValid(String password) {
        return password.length() >= 6 && password.matches(".*[a-zA-Z].*") && password.matches(".*\\d.*");
    }
    
    public boolean isValidContactNumber(String phoneNumber) {
        return phoneNumber.matches("^[0-9]{10}$");
    }

	@Override
    public boolean equals(Object o) {
        if  (!(o instanceof Users))
            return false;
        Users other = (Users) o;
        return (this.email.equals(other.email) && this.password.equals(other.password) && this.role.equals(other.role));
    }
	@Override
	  public int hashCode() {
	   return 0;
	    }




}
