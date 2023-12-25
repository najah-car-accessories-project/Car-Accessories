package carAccessories;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class AdminDashboard {

	private List<Users> users;
	private List<ProductCatalog> productCatalogs;
	private List<InstallationRequest> installationRequests;
	private static final String HORIZONTAL_HR = "--------------------------------";
	private static Logger LOGGER = Logger.getLogger(AdminDashboard.class.getName());
	private EmailService emailService = new EmailService();
	private static final String INDEX_FORMAT = "{0}. ";


	public AdminDashboard() {
		this.users = new ArrayList<>();
		this.productCatalogs = new ArrayList<>();
		this.installationRequests = new ArrayList<>();
	}

	public Users getUser(int index) {
		return users.get(index);
	}

	void printCompletedInstallationRequests() {
		LOGGER.info(HORIZONTAL_HR);
		LOGGER.info("\t\t\t Completed Installation Requests: ");
		int i = 0;
		for (InstallationRequest installationRequest : installationRequests) {
			if ("completed".equalsIgnoreCase(installationRequest.getStats())) {
				LOGGER.log(Level.INFO, INDEX_FORMAT, new Object[] { i });
				installationRequest.print();
				LOGGER.info(HORIZONTAL_HR);
				i++;
			}
		}
	}

	void printInstallationRequests() {
		LOGGER.info(HORIZONTAL_HR);
		LOGGER.info("\t\t\t Installation Requests: ");
		int i = 0;
		for (InstallationRequest installationRequest : installationRequests) {
			LOGGER.log(Level.INFO, INDEX_FORMAT, new Object[] { i });
			installationRequest.print();
			LOGGER.info(HORIZONTAL_HR);
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
	private void configureLogger() {
        Logger rootLogger = Logger.getLogger("");
        rootLogger.getHandlers()[0].setFormatter(new SimpleFormatter());
        rootLogger.setLevel(java.util.logging.Level.INFO);
    }

    private class SimpleFormatter extends java.util.logging.SimpleFormatter {
        @Override
        public synchronized String format(java.util.logging.LogRecord record) {
            return record.getMessage() + "\n";
        }
    }
	void printUsers() {
		configureLogger();
		int i = 0;
		for (Users user : users) {
			LOGGER.info(i + ". ");
			user.print();
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
				LOGGER.log(Level.INFO, INDEX_FORMAT, new Object[] { i });
				installationRequest.print();
				LOGGER.info(HORIZONTAL_HR);
				i++;
			}
		}
	}

	public void printCompletedInstallationRequest(Users user) {
		LOGGER.info(HORIZONTAL_HR);
		LOGGER.info("\t\t\t Order Installation Requests: ");
		int i = 0;
		for (InstallationRequest installationRequest : installationRequests) {
			if (installationRequest.getCustomer().getEmail().equals(user.getEmail())
					&&  installationRequest.getStats().equalsIgnoreCase("completed") ) {
				LOGGER.log(Level.INFO, INDEX_FORMAT, new Object[] { i });
				installationRequest.print();
				LOGGER.info(HORIZONTAL_HR);
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
