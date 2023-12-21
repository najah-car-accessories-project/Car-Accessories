package carAccessories;

import static org.junit.Assert.*;
import java.util.ArrayList;

import io.cucumber.java.en.*;

public class AdminDashboardTest {

	AdminDashboard adminDashboard;
	ProductCatalog productCatalog;
	ProductCategory productCategory;
	Admin admin;

	public AdminDashboardTest() {

		adminDashboard = new AdminDashboard();
		admin = new Admin("haya@gmail.com", "drhaya9999", "Admin");
		adminDashboard.addUser(new Customer("mohammadbadawi01@gmail.com", "mohammadbadawi2001", "Customer"));
		adminDashboard.addUser(admin);
		adminDashboard.addUser(new Installer("majdbasem6@gmail.com", "majd0567", "Installer"));
		productCatalog = new ProductCatalog();

		ProductCategory carAudio = new ProductCategory("Car Audio");
		ProductCategory carSecurity = new ProductCategory("Car Security");

		Product product1 = new Product("JPL", "Car Audio, 3000 wat", new ArrayList<String>(), 1000, true);
		Product product2 = new Product("JBL", "Car Audio, 2000 wat", new ArrayList<String>(), 500, true);
		carAudio.addProduct(product1);
		carAudio.addProduct(product2);
		productCatalog.addCategory(carAudio);

		Product product3 = new Product("Car Alarm", "Car Security", new ArrayList<String>(), 1000, true);
		Product product4 = new Product("Car Camera", "Car Security", new ArrayList<String>(), 500, true);
		carSecurity.addProduct(product3);
		carSecurity.addProduct(product4);
		productCatalog.addCategory(carSecurity);

		InstallationRequest installationRequest1 = new InstallationRequest("10-12-2023",
				new Installer("majdbasem6@gmail.com", "majd0567", "Installer"),
				new Customer("mohammadbadawi01@gmail.com", "mohammadbadawi2001", "Customer"), carAudio.getAllProducts(),
				"M8 twin terbo stage 3");

		adminDashboard.addInstallationRequest(installationRequest1);

		adminDashboard.addProductCatalog(productCatalog);
	}

	@Given("the Admin is signed in")
	public void the_admin_is_signed_in() {
		int index = adminDashboard.authenticateUser("mohammadbadawi01@gmail.com", "mohammadbadawi2001");
		assertNotEquals(-1, index);
	}

	@Given("they navigate to the {string} section")
	public void they_navigate_to_the_section(String string) {
		assertNotNull(adminDashboard);
	}

	@Then("they have options to Add a product category")
	public void they_have_options_to_add_a_product_category() {
		adminDashboard.getProductCatalogs().get(0).addCategory(new ProductCategory("Car Accessories"));
	}

	@Then("they can view all product categories")
	public void they_can_view_all_product_categories() {
		assertEquals(2, adminDashboard.getProductCatalogs().get(0).getAllCategories().size());
	}

	@Then("they have options to Edit a product category")
	public void they_have_options_to_edit_a_product_category() {

		adminDashboard.getProductCatalogs().get(0).getAllCategories().get(0).setName("Car Sounds");
		assertEquals("Car Sounds", adminDashboard.getProductCatalogs().get(0).getAllCategories().get(0).getName());
	}

	@Then("they have options to Delete a product category")
	public void they_have_options_to_delete_a_product_category() {
		adminDashboard.getProductCatalogs().get(0).getAllCategories().remove(0);
		assertEquals(1, adminDashboard.getProductCatalogs().get(0).getAllCategories().size());
	}

	@Then("they can view all products")
	public void they_can_view_all_products() {
		assertEquals(1,adminDashboard.searchProduct("JPL").size());
		assertEquals(2, adminDashboard.getProductCatalogs().get(0).getAllCategories().get(0).getAllProducts().size());
	}

	@Then("they have options to Add a product")
	public void they_have_options_to_add_a_product() {

		adminDashboard.getProductCatalogs().get(0).getAllCategories().get(0)
				.addProduct(new Product("Car Audio", "Car Audio, 1000 wat", new ArrayList<String>(), 500, true));
		assertEquals(3, adminDashboard.getProductCatalogs().get(0).getAllCategories().get(0).getAllProducts().size());
	}

	@Then("they have options to Edit a product")
	public void they_have_options_to_edit_a_product() {

		adminDashboard.getProductCatalogs().get(0).getAllCategories().get(0).getAllProducts().get(0)
				.setName("Car Bomb");
		assertEquals("Car Bomb",
				adminDashboard.getProductCatalogs().get(0).getAllCategories().get(0).getAllProducts().get(0).getName());
	}

	@Then("they have options to Delete a product")
	public void they_have_options_to_delete_a_product() {

		adminDashboard.getProductCatalogs().get(0).getAllCategories().get(0).getAllProducts().remove(0);
		assertEquals(1, adminDashboard.getProductCatalogs().get(0).getAllCategories().get(0).getAllProducts().size());
	}

	@Then("they can view all user accounts")
	public void they_can_view_all_user_accounts() {
		adminDashboard.printUsers();
		assertEquals(3, adminDashboard.getUsers().size());
	}

	@Then("they have options to Activate a user account")
	public void they_have_options_to_activate_a_user_account() {

		adminDashboard.getUsers().get(0).setActive(true);
		assertTrue(adminDashboard.getUsers().get(0).isActive());
	}

	@Then("they have options to Deactivate a user account")
	public void they_have_options_to_deactivate_a_user_account() {

		adminDashboard.getUsers().get(0).setActive(false);
		assertFalse(adminDashboard.getUsers().get(0).isActive());
	}

	@Then("they have options to Delete a user account")
	public void they_have_options_to_delete_a_user_account() {

		adminDashboard.getUsers().remove(0);
		assertEquals(2, adminDashboard.getUsers().size());
	}

	@Then("they have options to Role a user account")
	public void they_have_options_to_role_a_user_account() {

		adminDashboard.getUsers().get(0).setRole("Installer");
		assertEquals("Installer", adminDashboard.getUsers().get(0).getRole());
	}

	@Then("they can view all upcoming appointments")
	public void they_can_view_all_upcoming_appointments() {

	}

	@Then("they have options to Accepted an appointment")
	public void they_have_options_to_accepted_an_appointment() {
		adminDashboard.printCompletedInstallationRequest(adminDashboard.getUser(0));
		adminDashboard.getInstallationRequests().get(0).setState("Accepted",
				adminDashboard.getInstallationRequests().get(0));
		assertEquals("Accepted", adminDashboard.getInstallationRequests().get(0).getStats());
	}

	@Then("they have options to Reschedule an appointment")
	public void they_have_options_to_reschedule_an_appointment() {
		adminDashboard.getInstallationRequests().get(0).setState("Rescheduled",
				adminDashboard.getInstallationRequests().get(0));
		assertEquals("Rescheduled", adminDashboard.getInstallationRequests().get(0).getStats());
	}

	@Then("they have options to Completed an appointment")
	public void they_have_options_to_completed_an_appointment() {
		adminDashboard.printInstallationRequests();
		
		adminDashboard.getInstallationRequests().get(0).setState("Completed",
				adminDashboard.getInstallationRequests().get(0));
		assertEquals("Completed", adminDashboard.getInstallationRequests().get(0).getStats());
		
		adminDashboard.printCompletedInstallationRequests();
	}

}
