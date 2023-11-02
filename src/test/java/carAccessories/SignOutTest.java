package carAccessories;

import io.cucumber.java.en.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

public class SignOutTest {
	String role;
	ArrayList<Users> usersList = new ArrayList<Users>();

	@Given("that the {string} is signed in")
	public void that_the_is_signed_in(String string) {
		for (int i = 0; i < usersList.size(); i++)
			if (usersList.get(i).checkRole(string))

	assertTrue(usersList.get(i).signIn(usersList.get(i).email, usersList.get(i).password));
	}

	@When("the {string} signs out")
	public void the_signs_out(String string) {
		for (int i = 0; i < usersList.size(); i++)
			if (usersList.get(i).checkRole(string))
				usersList.get(i).signOut();
	}
	
	@Then("the {string} is not signed ins")
	public void the_is_not_signed_ins(String string) {
		for (int i = 0; i < usersList.size(); i++)
			if (usersList.get(i).checkRole(string))
			assertEquals(false,usersList.get(i).isSignedIn());
	}

}
