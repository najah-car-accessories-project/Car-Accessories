package carAccessories;

import java.util.List;
import java.util.logging.ConsoleHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
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
		  LOGGER.info(INDEX_FORMAT + "Installation Date: " + this.date);
	        LOGGER.info(INDEX_FORMAT + "Installer: " + (this.installer != null ? this.installer.getEmail() : "None assigned"));
	        LOGGER.info(INDEX_FORMAT + "Customer: " + this.customer.getEmail());
	        LOGGER.info(INDEX_FORMAT + "Car Details: " + this.carDetails);
	        LOGGER.info(INDEX_FORMAT + "State: " + this.state);
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
