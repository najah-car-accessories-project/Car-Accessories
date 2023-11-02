package carAccessories;

public class Customer extends Users {
	public Customer(String email, String password, String role) {
		super(email, password, role);
	}
	
	public Customer(String email, String password, String role, String contactNumber) {
		super(email, password, role);
		this.setContactNumber(contactNumber);
	}
}
