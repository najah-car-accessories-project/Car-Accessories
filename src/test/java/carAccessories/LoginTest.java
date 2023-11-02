package carAccessories;

import io.cucumber.java.en.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;


public class LoginTest {
	String email;
	String password;
	String role;
	ArrayList<Users> usersList = new ArrayList<Users>();
	Users user;

	@Given("the users credentials")
	public void theUserCredentials(
			io.cucumber.datatable.DataTable dataTable) {
		List<List<String>> rows = dataTable.asLists(String.class);

		for (int i = 0; i < rows.size(); i++) {
			Users addUser;
			if (rows.get(i).get(2).equals("Admin"))
				addUser = new Admin(rows.get(i).get(0), rows.get(i).get(1), rows.get(i).get(2));
			else if (rows.get(i).get(2).equals("Customer"))
				addUser = new Customer(rows.get(i).get(0), rows.get(i).get(1), rows.get(i).get(2));
			else
				addUser = new Installer(rows.get(i).get(0), rows.get(i).get(1), rows.get(i).get(2));
			usersList.add(addUser);
		}
	}

	@Given("That the {string} is not signed in")
	public void thatTheIsNotSignedIn(String role) {

		for (int i = 0; i < usersList.size(); i++)
		assertFalse(usersList.get(0).isSignedIn());
	}

	@Given("the {string} email is {string}")
	public void theEmailIs(String role, String userName) {
		this.email = userName;
		this.role = role;

	}

	@Given("the {string} password is {string}")
	public void thePasswordIs(String role, String password) {
		this.password = password;

	}

	@Then("the {string} sign in succeeds")
	public void theSignInSucceeds(String role) {

		for (int i = 0; i < usersList.size(); i++)
			if (usersList.get(i).checkRole(role))
			assertTrue(usersList.get(i).signIn(email, password));

	}

	@Then("the {string} is signed in")
	public void theIsSignedIn(String string) {
				
		for (int i = 0; i < usersList.size(); i++)
			if (usersList.get(i).checkRole(role)) {
				assertTrue(usersList.get(i).isSignedIn());
				if (role.equals("Admin")) {
					user = new Admin(email, password, role);
					assertEquals(true, usersList.get(i).equals(user));
				}
				else if (role.equals("Doctor")) {
					user = new Customer(email, password, role);
					assertEquals(true, usersList.get(i).equals(user));
				}
				else if (role.equals("Secretary")) {
					user = new Installer(email, password, role);
					assertEquals(true, usersList.get(i).equals(user));
				}
			}
	}

	@Then("go to {string} page")
	public void goToPage(String string) {

	}

	@Then("the {string} sign in not succeeds")
	public void theSignInNotSucceeds(String string) {
		for (int i = 0; i < usersList.size(); i++)
			if (usersList.get(i).checkRole(role))
				assertFalse(usersList.get(i).signIn(email, password));
	}
	@Then("the {string} is not signed in")
	public void theIsNotSignedIn(String string) {
		for (int i = 0; i < usersList.size(); i++)
			if (usersList.get(i).checkRole(role)) {
				assertFalse(usersList.get(i).isSignedIn());
				if (role.equals("Admin")) {
					user = new Admin(email, password, role);
					assertEquals(false, usersList.get(i).equals(user));
				}
				else if (role.equals("Doctor")) {
					user = new Customer(email, password, role);
					assertEquals(false, usersList.get(i).equals(user));
				}
				else if (role.equals("Secretary")) {
					user = new Installer(email, password, role);
					assertEquals(false, usersList.get(i).equals(user));
				}
			}
	}
}
