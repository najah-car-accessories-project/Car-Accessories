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
		System.out.println("Installation Date: " + date);
		System.out.println("Installer: " + installer.getName());
		System.out.println("Customer: " + customer.getName());
		System.out.println("Car Details: " + carDetails);
		System.out.println("State: " + state);
		System.out.println("Products: ");
		for (Product product : products) {
			product.print();
		}
	}

//	void setCarDetails(String carDetails) {
//		this.carDetails = carDetails;
//	}

	public String getCarDetails() {
		return carDetails;
	}

	public String getInstallationDate() {
		return date;
	}

//	public void setInstallationDate(String installationDate) {
//		this.date = installationDate;
//	}

//	public Installer getInstaller() {
//		return installer;
//	}

//	public void setInstaller(Installer installer) {
//		this.installer = installer;
//	}

	public Customer getCustomer() {
		return customer;
	}

//	public void setCustomer(Customer customer) {
//		this.customer = customer;
//	}

	public List<Product> getProducts() {
		return products;
	}

//	public void setProducts(List<Product> products) {
//		this.products = products;
//	}
//
//	public void setEmailService(EmailService emailService) {
//		this.emailService = emailService;
//	}

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

}
