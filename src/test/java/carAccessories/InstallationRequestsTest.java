package carAccessories;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import io.cucumber.java.en.*;

public class InstallationRequestsTest {

	private List<Product> productList;
	private Customer customer;
	private Admin admin;
	private Installer installer;
	private InstallationRequest installationRequest;
	private List<InstallationRequest> installationRequestList;

	public InstallationRequestsTest() {
		
		installer = new Installer("ahmad@gmail.com", "ahmad2000", "Installer");
		customer = new Customer("haya@gmail.com", "drhaya9999", "Customer");
		customer.signIn("haya@gmail.com", "drhaya9999");
		
		
		installationRequest = new InstallationRequest("10-12-2023 02:00", customer, null, "Toyota Corolla");
		installationRequestList = new ArrayList<InstallationRequest>();
		installationRequestList.add(installationRequest);
		installationRequestList.add(new InstallationRequest("11-12-2023 02:00", customer, null, "BMW"));

		admin = new Admin("mohammadbadawi@gmail.com", "mohammadbadawi2001", "Admin");

		Product product1 = new Product("JPL", "Car Audio, 3000 wat", new ArrayList<String>(), 1000, true);
		Product product2 = new Product("JBL", "Car Audio, 2000 wat", new ArrayList<String>(), 500, true);

		productList = new ArrayList<Product>();
		productList.add(product1);
		productList.add(product2);
	}

	@Given("a Customer is logged in")
	public void a_customer_is_logged_in() {
		assertTrue(customer.isSignedIn());
	}

	@When("they select products and specify installation details like date, time, and car details")
	public void they_select_products_and_specify_installation_details_like_date_time_and_car_details() {
		installationRequest = new InstallationRequest("2020-05-20 02:00", customer, productList, "Toyota Corolla");
	}

	@Then("an installation request is created and added to the system")
	public void an_installation_request_is_created_and_added_to_the_system() {
		assertEquals(2, installationRequestList.size());
	}

	@Given("an Admin is logged in")
	public void an_admin_is_logged_in() {
		admin.signIn("mohammadbadawi@gmail.com", "mohammadbadawi2001");
		assertTrue(admin.isSignedIn());
	}

	@Then("they see a list of all installation requests")
	public void they_see_a_list_of_all_installation_requests() {
		assertEquals(2, installationRequestList.size());
	}

	@Given("an Admin is viewing installation requests")
	public void an_admin_is_viewing_installation_requests() {
		assertNotNull(installationRequestList);
	}

	@When("they choose to edit or delete a request")
	public void they_choose_to_edit_or_delete_a_request() {
		installationRequestList.get(0).setState("Accepted", installationRequestList.get(0));
		assertEquals("Accepted", installationRequestList.get(0).getStats());
		installationRequestList.remove(0);
		assertEquals(1, installationRequestList.size());
	}

	@Then("the selected request is edited or deleted from the system")
	public void the_selected_request_is_edited_or_deleted_from_the_system() {
		assertEquals(1, installationRequestList.size());

	}

	@Given("an Installer is logged in")
	public void an_installer_is_logged_in() {
		installer.signIn("ahmad@gmail.com", "ahmad2000");
		assertTrue(installer.isSignedIn());
	}

	@When("they update the status of a request")
	public void they_update_the_status_of_a_request() {
		installationRequestList.get(0).setState("Accepted", installationRequestList.get(0));
		assertEquals("Accepted", installationRequestList.get(0).getStats());
	}

	@Then("the updated status is reflected in the system")
	public void the_updated_status_is_reflected_in_the_system() {
		assertEquals("Accepted", installationRequestList.get(0).getStats());
	}
}