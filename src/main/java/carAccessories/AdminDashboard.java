package carAccessories;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.ConsoleHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class AdminDashboard {

	private List<Users> users;
	private List<ProductCatalog> productCatalogs;
	private List<InstallationRequest> installationRequests;
	private static final String HORIZONTAL_HR = "--------------------------------";
	private static final Logger LOGGER = Logger.getLogger(AdminDashboard.class.getName());
	private EmailService emailService = new EmailService();
	private static final String INDEX_FORMAT = "{0}. ";
	static {
	    LOGGER.setLevel(Level.FINE);
	    Handler consoleHandler = new ConsoleHandler();
	    consoleHandler.setLevel(Level.FINE);
	    consoleHandler.setFormatter(new Formatter() {
	        @Override
	        public String format(LogRecord logRecord) {
	            return logRecord.getMessage() + System.lineSeparator();
	           }
	    });
	    LOGGER.addHandler(consoleHandler);
	    LOGGER.setUseParentHandlers(false);
	}

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
			if ("completed".equalsIgnoreCase(installationRequest.getStats())) {
				LOGGER.log(Level.FINE, INDEX_FORMAT, new Object[] { i });
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
			LOGGER.log(Level.FINE, INDEX_FORMAT, new Object[] { i });
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
			LOGGER.log(Level.FINE, "{0}. {1} - {2} - {3}",
					new Object[] { i, user.getEmail(), user.getContactNumber(), user.getRole() });
			i++;
		}
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

	public void addInstallationRequest(InstallationRequest installationRequest) {

		StringBuilder messageBody = new StringBuilder();
		messageBody.append("New Installation Request has been placed on ")
				.append(installationRequest.getInstallationDate()).append("\nCustomer Details: ").append("\nCar: ")
				.append(installationRequest.getCarDetails()).append("\nProducts: ");

		for (Product product : installationRequest.getProducts()) {
			messageBody.append("\n").append(product.getName()).append(", ").append(product.getDescriptions());
		}
		Installer installer = getAvailableInstaller();
		emailService.newInstallationRequest(messageBody.toString(), installer.getEmail());
		installationRequest.setInstaller(installer);
		installationRequests.add(installationRequest);

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
				LOGGER.log(Level.FINE, INDEX_FORMAT, new Object[] { i });
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
			if (installationRequest.getCustomer().getEmail().equals(user.getEmail())
					&& "completed".equalsIgnoreCase(installationRequest.getStats())) {
				LOGGER.log(Level.FINE, INDEX_FORMAT, new Object[] { i });
				installationRequest.print();
				LOGGER.fine(HORIZONTAL_HR);
				i++;
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
