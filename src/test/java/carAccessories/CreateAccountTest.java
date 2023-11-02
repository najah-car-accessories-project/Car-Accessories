package carAccessories;

import io.cucumber.java.en.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

public class CreateAccountTest {
	String email;
	String password;
	String role;
	String contactNumber;
	ArrayList<Users> usersList = new ArrayList<Users>();
	Users user;
	
	@Given("the user is on the registration page")
	public void the_user_is_on_the_registration_page() {
	}

	@When("the user enters their personal information \\({string}, {string}, {string} and {string})")
	public void the_user_enters_their_personal_information_and(String email, String password, String role, String contactNumber) {
	    this.email = email;
	    this.password = password;
	    this.role = role;
	    this.contactNumber = contactNumber;
	    if (role.equals("Customer"))
	    	user = new Customer(email, password, role, contactNumber);
	    else
	    	user = new Installer(email, password, role, contactNumber);
	}
	
	@Then("if the details are valid, the user's account will be created successfully")
	public void if_the_details_are_valid_the_user_s_account_will_be_created_successfully() {
	    assertTrue(user.createAccount(email, password, contactNumber));
	    usersList.add(user);
	}

	@Then("the user's account is created successfully")
	public void the_user_s_account_is_created_successfully() {

	}
	
	@Then("if the details are invalid, the user's account creation fails")
	public void if_the_details_are_invalid_the_user_s_account_creation_fails() {
		assertFalse(user.createAccount(email, password, contactNumber));
	}

}
