package carAccessories;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import io.cucumber.java.en.*;

public class NotificationsTest {
	private InstallationRequest installationRequest;
	private List<Product> productList;
	private Customer customer;
	private Installer installer;

	public NotificationsTest() {
		installer = new Installer("ahmad@gmail.com", "ahmad2000", "Installer");

		Product product1 = new Product("JPL", "Car Audio, 3000 wat", new ArrayList<String>(), 1000, true);
		Product product2 = new Product("JBL", "Car Audio, 2000 wat", new ArrayList<String>(), 500, true);

		productList = new ArrayList<Product>();
		productList.add(product1);
		productList.add(product2);

		customer = new Customer("haya@gmail.com", "drhaya9999", "Customer");
		installationRequest = new InstallationRequest("10-12-2023 02:00", customer, productList, "Toyota Corolla");

	}

	@Given("a Customer has an pinding order")
	public void a_customer_has_an_pinding_order() {
		assertEquals("Pending", installationRequest.getStats());
	}

	@When("there is a status update on their order")
	public void there_is_a_status_update_on_their_order() {
		installationRequest.setState("Accepted", installationRequest);
	}

	@Then("the Customer receives an update notification via email")
	public void the_customer_receives_an_update_notification_via_email() {
		assertEquals("Accepted", installationRequest.getStats());
	}

	@Given("an installer is  in the system")
	public void an_installer_is_in_the_system() {
		installer.signIn("ahmad@gmail.com", "ahmad2000");
		assertTrue(installer.isSignedIn());
	}

	@When("a new installation request is created")
	public void a_new_installation_request_is_created() {
		installationRequest = new InstallationRequest("10-12-2023 02:00", customer, productList, "Toyota Corolla");
	}

	@Then("a notification is sent to the installer email informing about the new request")
	public void a_notification_is_sent_to_the_installer_email_informing_about_the_new_request() {


		assertEquals("Pending", installationRequest.getStats());
	}

}
