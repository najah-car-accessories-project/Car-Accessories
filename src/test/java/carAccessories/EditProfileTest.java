package carAccessories;

import io.cucumber.java.en.*;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;

public class EditProfileTest {
	Customer customer;
	String email;
	String password;
	String role;
	String contactNumber;

	public EditProfileTest() {
		customer = new Customer("majdbasem6@gmail.com", "majd0567", "Customer", "0599364789");
		customer.signIn("majdbasem6@gmail.com", "majd0567");
	}

	@Given("the Customer is logged in")
	public void the_customer_is_logged_in() {
		assertTrue(customer.isSignedIn());
	}

	@Then("{string} message will appear")
	public void message_will_appear(String string) {
		assertNotNull(string);
	}

	@When("the Customer select edit profile option")
	public void the_customer_select_edit_profile_option() {

	}

	@When("the Customer wants to change email and enters valid {string}")
	public void the_customer_wants_to_change_email_and_enters_valid(String newEmail) {
		assertTrue(customer.isValidEmail(newEmail));
		this.email = newEmail;
	}

	@When("the Customer enters {string} for confirmation")
	public void the_customer_enters_for_confirmation(String password) {
		assertTrue(customer.checkPassword(password));
	}

	@Then("the Customer's email will be updated")
	public void the_customer_s_email_will_be_updated() {
		customer.setEmail(email);
	}

	@When("the Customer enters {string}")
	public void the_customer_enters(String oldPassword) {
		assertTrue(customer.checkPassword(oldPassword));
	}

	@When("the Customer enters valid {string}")
	public void the_customer_enters_valid(String newPassword) {
		assertTrue(customer.isPasswordValid(newPassword));
		this.password = newPassword;
	}

	@Then("the Customer's password will be updated")
	public void the_customer_s_password_will_be_updated() {
		customer.setPassword(password);
	}

	@When("the Customer wants to change contact number and enters valid {string}")
	public void the_customer_wants_to_change_contact_number_and_enters_valid(String newContactNumber) {
		assertTrue(customer.isValidContactNumber(newContactNumber));
		this.contactNumber = newContactNumber;
	}

	@Then("the Customer's contact number will be updated")
	public void the_customer_s_contact_number_will_be_updated() {
		customer.setContactNumber(contactNumber);
	}
}
