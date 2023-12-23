package carAccessories;

import java.util.List;

public class InstallationRequest {
	String date;
	private Installer installer;
	private Customer customer;
	private List<Product> products;
	private EmailService emailService = new EmailService();
	private String state;
	private String carDetails;

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
		System.out.println("Installation Date: " + this.date);
		System.out.println("Installer: " + this.installer.getEmail());
		System.out.println("Customer: " + this.customer.getEmail());
		System.out.println("Car Details: " + this.carDetails);
		System.out.println("State: " + this.state);
		System.out.println("Products: ");
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
