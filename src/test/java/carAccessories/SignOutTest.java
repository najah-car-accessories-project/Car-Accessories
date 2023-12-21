package carAccessories;

import io.cucumber.java.en.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;

public class SignOutTest {
	ArrayList<Users> usersList = new ArrayList<Users>();

	public SignOutTest() {
		usersList.add(new Customer("majdbasem6@gmail.com", "majd123", "Customer"));
	}

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

		for (int i = 0; i < usersList.size(); i++)
			if (usersList.get(i).checkRole(string))
				assertEquals(false, usersList.get(i).isSignedIn());
	}

}
