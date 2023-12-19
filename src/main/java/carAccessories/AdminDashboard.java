package carAccessories;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class AdminDashboard {

	private List<Users> users;
	private List<ProductCatalog> productCatalogs;
	private List<InstallationRequest> installationRequests;
	private static final String HORIZONTAL_HR = "--------------------------------";
	private static final Logger LOGGER = Logger.getLogger(AdminDashboard.class.getName());
	private EmailService emailService = new EmailService();

	public AdminDashboard() {
		this.users = new ArrayList<>();
		this.productCatalogs = new ArrayList<>();
		this.installationRequests = new ArrayList<>();
	}

	public Users getUser(int index) {
		return users.get(index);
	}

	void printCompletedInstallationRequests() {
		LOGGER.fine(HORIZONTAL_HR);

		LOGGER.fine("\t\t\t Completed Installation Requests: ");
		int i = 0;
		for (InstallationRequest installationRequest : installationRequests) {
			if (installationRequest.getStats().equalsIgnoreCase("completed")) {
				LOGGER.fine(i + ". ");
				installationRequest.print();
				LOGGER.fine(HORIZONTAL_HR);
				i++;
			}
		}
	}

	void printInstallationRequests() {
		LOGGER.fine(HORIZONTAL_HR);

		LOGGER.fine("\t\t\t Installation Requests: ");
		int i = 0;
		for (InstallationRequest installationRequest : installationRequests) {
			LOGGER.fine(i + ". ");
			installationRequest.print();
			LOGGER.fine(HORIZONTAL_HR);

			i++;
		}
	}

	public List<ProductCatalog> getProductCatalogs() {
		return productCatalogs;
	}

	public int authenticateUser(String email, String password) {
		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).checkEmail(email)) {
				if (users.get(i).checkPassword(password)) {
					users.get(i).signIn(email, password);
					return i;
				} else
					return -1;
			}
		}
		return -1;
	}

	public List<Users> getUsers() {
		return users;
	}

	void printUsers() {
		int i = 0;
		for (Users user : users) {
			LOGGER.fine(i + ". " + user.getEmail() + " - " + user.getContactNumber() + " - " + user.getRole());
			i++;
		}

	}

	public int getTotalNumberOfUsers() {
		return users.size();
	}

	public boolean addUser(Users user) {
		if (user.createAccount(user.getEmail(), user.getPassword(), "0000000000")) {
			users.add(user);
			return true;
		}
		return false;
	}

	public void addProductCatalog(ProductCatalog catalog) {
		productCatalogs.add(catalog);
	}

	void addInstallationRequest(InstallationRequest installationRequest) {
		installationRequests.add(installationRequest);

		String messageBody = "New Installation Request has been placed on " + installationRequest.getInstallationDate()
				+ "\nCustomer Details: ";
		messageBody += "\nCar: " + installationRequest.getCarDetails();
		messageBody += "\nProducts: ";

		for (Product product : installationRequest.getProducts()) {
			messageBody += "\n" + product.getName()+ ", " + product.getDescriptions();
		}
		emailService.newInstallationRequest(messageBody, getAvailableInstaller().getEmail());
	}

	public Installer getAvailableInstaller() {
		for (Users user : users) {
			if (user.getRole().equalsIgnoreCase("installer")) {
				Installer installer = (Installer) user;
				if (installer.getAvailability()) {
					return installer;
				}
			}
		}
		return null;
	}

	public List<InstallationRequest> getInstallationRequests() {
		return installationRequests;
	}

	public void printInstallationRequest(Users user) {
		int i = 0;
		for (InstallationRequest installationRequest : installationRequests) {
			if (installationRequest.getCustomer().getEmail().equals(user.getEmail())) {
				LOGGER.fine(i + ". ");
				installationRequest.print();
				LOGGER.fine(HORIZONTAL_HR);
				i++;
			}
		}

	}

	public void printCompletedInstallationRequest(Users user) {
		LOGGER.fine(HORIZONTAL_HR);
		LOGGER.fine("\t\t\t Order Installation Requests: ");
		int i = 0;
		for (InstallationRequest installationRequest : installationRequests) {
			if (installationRequest.getCustomer().getEmail().equals(user.getEmail())) {
				if (installationRequest.getStats().equalsIgnoreCase("completed")) {
					LOGGER.fine(i + ". ");
					installationRequest.print();
					LOGGER.fine(HORIZONTAL_HR);
					i++;
				}
			}
		}

	}

	public List<Product> searchProduct(String searchKey) {
		List<Product> products = new ArrayList<>();
		for (ProductCatalog productCatalog : productCatalogs) {
			products.addAll(productCatalog.searchProducts(searchKey));
		}
		return products;
	}

}
