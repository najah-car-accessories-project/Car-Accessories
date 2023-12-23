package carAccessories;

import java.util.List;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InstallationRequest {
	String date;
	private Installer installer;
	private Customer customer;
	private List<Product> products;
	private EmailService emailService = new EmailService();
	private String state;
	private String carDetails;
	private static final Logger LOGGER = Logger.getLogger(InstallationRequest.class.getName());
	private static final String INDEX_FORMAT = "{0}. ";
	static {
	    LOGGER.setLevel(Level.SEVERE);
	    Handler consoleHandler = new ConsoleHandler();
	    consoleHandler.setLevel(Level.SEVERE);
        consoleHandler.setFormatter(new PlainTextFormatter());

	    LOGGER.addHandler(consoleHandler);
	    LOGGER.setUseParentHandlers(false);
	}
	
	public InstallationRequest(String date, Installer installer, Customer customer, List<Product> products,
			String carDetails) {
		this.date = date;
		this.installer = installer;
		this.customer = customer;
		this.products = products;
		this.carDetails = carDetails;
		this.state = "Pending";
	}

	public InstallationRequest(String date, Customer customer, List<Product> products, String carDetails) {
		this.date = date;
		this.customer = customer;
		this.products = products;
		this.carDetails = carDetails;
		this.state = "Pending";
	}

	void print() {
		 LOGGER.log(Level.INFO, "{0}Installation Date: {1}", new Object[]{INDEX_FORMAT, this.date});
	        LOGGER.log(Level.INFO, "{0}Installer: {1}", new Object[]{INDEX_FORMAT, (this.installer != null ? this.installer.getEmail() : "None assigned")});
	        LOGGER.log(Level.INFO, "{0}Customer: {1}", new Object[]{INDEX_FORMAT, this.customer.getEmail()});
	        LOGGER.log(Level.INFO, "{0}Car Details: {1}", new Object[]{INDEX_FORMAT, this.carDetails});
	        LOGGER.log(Level.INFO, "{0}State: {1}", new Object[]{INDEX_FORMAT, this.state});
	        LOGGER.info("Products: ");
		for (Product product : products) {
			product.print();
		}
	}

	public String getCarDetails() {
		return carDetails;
	}

	public String getInstallationDate() {
		return date;
	}

	public Customer getCustomer() {
		return customer;
	}

	public List<Product> getProducts() {
		return products;
	}

	public String getStats() {

		return state;
	}

	void setState(String state, InstallationRequest installationRequest) {
		if (state.equalsIgnoreCase("Accepted")) {
			String messageBody = "Your Installation Request has been Accepted on "
					+ installationRequest.getInstallationDate() + "\nThank You";
			emailService.orderConfirmations(messageBody, installationRequest.getCustomer().getEmail());
		} else if (state.equalsIgnoreCase("Rejected")) {
			String messageBody = "Rescheduled Your Installation Request, Your Installation Request has been Rejected"
					+ installationRequest.getInstallationDate() + "\nThank You";
			emailService.orderConfirmations(messageBody, installationRequest.getCustomer().getEmail());
		}

		this.state = state;
	}

	public void setInstaller(Installer installer2) {
		this.installer = installer2;
	}

}
