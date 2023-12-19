package carAccessories;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {

	private static AdminDashboard adminDashboard;
	private static int userIndex;
	private static Scanner scan = new Scanner(System.in);
	private static String email, password;
	private static ProductCatalog productCatalog;
	static int categoryIndex = 0;
	static int productIndex = 0;
	private static Users user;
	final static String invalidInputMsg = "Invalid input! Please enter a valid option.";
	final static String invalidSelectionMsq = "Invalid selection! Please try again...";
	final static String logoutMsq = "Logged Out...\n";
	final static String hr = "================================================================================";

	public static void main(String[] args) {

		init();

		while (true) {
			String new_pass;
			System.out.println(hr);
			System.out.println("\t\tWelcome to Car Accessories Store");
			System.out.println("1. Sign In");
			System.out.println("2. Sign Up");
			System.out.println("3. Exit");
			System.out.println("\nPlease select an option:");

			int select;

			try {
				select = scan.nextInt();
			} catch (java.util.InputMismatchException e) {
				System.out.println(invalidInputMsg);
				scan.nextLine();
				return;
			}

			switch (select) {
			case 1:
				System.out.println("Enter Username:");
				email = scan.next();

				System.out.println("Enter Password:");
				password = scan.next();

				userIndex = adminDashboard.authenticateUser(email, password);

				if (userIndex == -1) {
					System.out.println("Invalid Credentials! Please try again...\n");
					return;

				}

				user = adminDashboard.getUsers().get(userIndex);

				if (user.checkRole("Admin"))
					adminDashboardActivities();
				else if (user.checkRole("Customer"))
					customerActivities();
				else if (user.checkRole("Installer"))
					installerActivities();

				break;

			case 2:

				System.out.println("Enter Email:");
				email = scan.next();
				System.out.println("Enter Password:");
				password = scan.next();
				System.out.println("Reenter Password:");
				new_pass = scan.next();

				if (!password.equals(new_pass)) {
					System.out.println("Password doesn't match! Please try again...");
					System.out.println("\n");
					return;
				}

				boolean isValid = adminDashboard.addUser(new Customer(email, password, "Customer"));

				if (isValid) {
					System.out.println("Account Created Successfully!");
					userIndex = adminDashboard.authenticateUser(email, password);

					if (userIndex == -1) {
						System.out.println("Invalid Credentials! Please try again...\n");
						return;
					}

					user = adminDashboard.getUsers().get(userIndex);

					customerActivities();

				} else
					System.out.println("Account Creation Failed! Please try again...");
				break;

			case 3:
				System.out.println(logoutMsq);
				System.exit(0);

			default:
				System.out.println(invalidSelectionMsq);
				System.out.println("\n");
			}
		}
	}

	public static void init() {

		adminDashboard = new AdminDashboard();
		adminDashboard.addUser(new Customer("majdbasem6@gmail.com", "majd123", "Customer"));
		adminDashboard.addUser(new Admin("drhaya@gmail.com", "drhaya01", "Admin"));
		adminDashboard.addUser(new Installer("mohammadbadawi01@gmail.com", "badawi01", "Installer"));

		productCatalog = new ProductCatalog();

		ProductCategory carAudio = new ProductCategory("Car Audio");
		ProductCategory carSecurity = new ProductCategory("Car Security");
		ProductCategory z = new ProductCategory("z");

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

		InstallationRequest installationRequest1 = new InstallationRequest("07-10-2023 9:00",
				new Installer("mohammadbadawi01@gmail.com", "badawi01", "Installer"),
				new Customer("majdbasem6@gmail.com", "majd123", "Customer"), carAudio.getAllProducts(),
				"M8 twin terbo stage 3");

		adminDashboard.addInstallationRequest(installationRequest1);
		adminDashboard.addInstallationRequest(installationRequest1);

		adminDashboard.addInstallationRequest(installationRequest1);
		adminDashboard.addInstallationRequest(installationRequest1);

		productCatalog.addCategory(z);
		adminDashboard.addProductCatalog(productCatalog);
	}

	public static void adminDashboardActivities() {
		while (true) {
			System.out.println("\t\tWelcome " + user.email + " - Signed In as Admin");
			System.out.println(hr);
			System.out.println("1. Add Product");
			System.out.println("2. Edit Or Delete Product");
			System.out.println("3. View Products");

			System.out.println("4. Add Product Category");
			System.out.println("5. Edit Product Category");
			System.out.println("6. Delete Product Category");

			System.out.println("7. View Product Categories");
			System.out.println("8. View Users");
			System.out.println("9. Edit Or Delete Users");
			System.out.println("10. Change State or Delete Installation Requests");

			System.out.println("11. Sign Out");
			System.out.println("\nPlease select an option:");

			int select;

			try {
				select = scan.nextInt();
			} catch (java.util.InputMismatchException e) {
				System.out.println(invalidInputMsg);
				scan.nextLine();
				return;
			}

			System.out.println("\n");
			switch (select) {
			case 1:
				productCatalog.printCategories();
				System.out.println("Choose Category:");
				categoryIndex = scan.nextInt();
				System.out.println("Enter Product Name:");
				String name = scan.next();
				System.out.println("Enter Product Description:");
				String descriptions = scan.next();
				System.out.println("Enter Product Price:");
				double price = scan.nextDouble();
				System.out.println("Enter Product Availability:");
				boolean isAvailable = scan.nextBoolean();

				String category = productCatalog.getAllCategories().get(categoryIndex).getName();

				Product newProduct = new Product(name, descriptions, new ArrayList<String>(), price, isAvailable);

				productCatalog.addProductToCategory(newProduct, category);
				System.out.println("Product Added Successfully.\n");
				break;
			case 2:

				productCatalog.printCategories();
				System.out.println("Enter Catagory number:");
				categoryIndex = scan.nextInt();
				if (productCatalog.getAllCategories().size() > (categoryIndex + 1) && categoryIndex >= 0) {

					productCatalog.getAllCategories().get(categoryIndex).printProducts();

					System.out.println("Enter product number:");
					int productNumber = scan.nextInt();

					if (productCatalog.getAllCategories().get(categoryIndex).getAllProducts()
							.size() > (productIndex + 1) && productNumber >= 0) {

						System.out.println("1. Edit Product");
						System.out.println("2. Delete Product");
						int select2 = scan.nextInt();
						switch (select2) {
						case 1:
							System.out.println("Enter New Name:");
							String newName = scan.next();
							System.out.println("Enter New Description:");
							String newDescription = scan.next();
							System.out.println("Enter New Price:");
							double newPrice = scan.nextDouble();
							System.out.println("Enter New Availability:");
							boolean newAvailability = scan.nextBoolean();

							Product newProduct2 = new Product(newName, newDescription, new ArrayList<String>(),
									newPrice, newAvailability);

							productCatalog.getAllCategories().get(categoryIndex).getAllProducts().set(productNumber,
									newProduct2);
							System.out.println("Product Edited Successfully.\n");
							break;
						case 2:
							productCatalog.getAllCategories().get(categoryIndex).getAllProducts().remove(productNumber);
							System.out.println("Product Deleted Successfully.\n");
							break;
						default:
							System.out.println(invalidSelectionMsq);
							System.out.println("\n");
						}
					} else {
						System.out.println(invalidSelectionMsq);
					}
				} else
					System.out.println(invalidSelectionMsq);
				break;

			case 3:

				productCatalog.printCatalog();
				break;
			case 4:
				System.out.println("Enter Category Name:");
				String categoryName = scan.next();
				System.out.println("Enter Category Description:");
				String categoryDescription = scan.next();
				ProductCategory newCategory = new ProductCategory(categoryName, categoryDescription);
				productCatalog.addCategory(newCategory);
				System.out.println("Category Added Successfully.\n");

				break;
			case 5:
				// edit category name and description
				productCatalog.printCategories();
				System.out.println("Enter Category number:");
				categoryIndex = scan.nextInt();
				if (productCatalog.getAllCategories().size() > (categoryIndex + 1) && categoryIndex >= 0) {
					System.out.println("Enter New Name:");
					String newName = scan.next();
					System.out.println("Enter New Description:");
					String newDescription = scan.next();

					ProductCategory newCategory2 = new ProductCategory(newName, newDescription);

					productCatalog.updateCategory(newCategory2);
					System.out.println("Category Edited Successfully.\n");
				} else
					System.out.println(invalidSelectionMsq);
				break;
			case 6:
				productCatalog.printCategories();
				System.out.println("Enter Category number:");
				categoryIndex = scan.nextInt();
				if (productCatalog.getAllCategories().size() > (categoryIndex + 1) && categoryIndex >= 0) {
					productCatalog.getAllCategories().remove(categoryIndex);
					System.out.println("Category Deleted Successfully.\n");
				} else

					System.out.println(invalidSelectionMsq);

				break;
			case 7:

				productCatalog.printCategories();
				break;

			case 8:
				adminDashboard.printUsers();
				break;
			case 9:
				adminDashboard.printUsers();
				System.out.println("Enter User number:");

				int userNumber = scan.nextInt();
				if (adminDashboard.getUsers().size() > (userNumber + 1) && userNumber >= 0) {
					System.out.println("1. Edit User");
					System.out.println("2. Delete User");
					int select2 = scan.nextInt();
					switch (select2) {
					case 1:

						System.out.println("Enter New Role:");
						String newRole = scan.next();

						adminDashboard.getUsers().get(userNumber).setRole(newRole);
						System.out.println("User Edited Successfully.\n");
						break;
					case 2:
						adminDashboard.getUsers().remove(userNumber);
						System.out.println("User Deleted Successfully.\n");
						break;
					default:
						System.out.println(invalidSelectionMsq);
						System.out.println("\n");
					}
				} else {
					System.out.println(invalidSelectionMsq);
				}

				break;
			case 10:

				adminDashboard.printInstallationRequests();
				System.out.println("Enter Request number:");
				int requestNumber = scan.nextInt();
				if (adminDashboard.getInstallationRequests().size() > (requestNumber) && requestNumber >= 0) {
					System.out.println("1. Edit Request");
					System.out.println("2. Delete Request");
					System.out.println("3. Back");
					int select2 = scan.nextInt();
					switch (select2) {
					case 1:
						// change state
						System.out.println("Enter New State:");
						String newState = scan.next();
						adminDashboard.getInstallationRequests().get(requestNumber).setState(newState,
								adminDashboard.getInstallationRequests().get(requestNumber));
						System.out.println("Product Edited Successfully.\n");
						break;
					case 2:
						// sure to delete
						System.out.println("Are you sure you want to delete this request? (Y/N)");
						String sure = scan.next();
						if (sure.equalsIgnoreCase("Y")) {
							adminDashboard.getInstallationRequests().remove(requestNumber);
							System.out.println("Request Deleted Successfully.\n");
						} else {
							System.out.println("Request Not Deleted.\n");
						}
						break;
					default:
						System.out.println(invalidSelectionMsq);
						System.out.println("\n");
					}

				} else {
					System.out.println(invalidSelectionMsq);
				}
				break;
			case 11:
				System.out.println(logoutMsq);
				return;
			}

		}

	}

	public static void customerActivities() {
		String password;

		while (true) {
			System.out.println("\t\tWelcome " + user.email + " - Signed In as Customer");
			System.out.println(hr);
			System.out.println("1. View Products <Search>");
			System.out.println("2. Add Installation Request <View Products>");
			System.out.println("3. Edit or Delete Installation Request");
			System.out.println("4. Change Email");
			System.out.println("5. Change Password");
			System.out.println("6. Change Contact Number");
			System.out.println("7. View Profile");
			System.out.println("8. View installation Requests");
			System.out.println("9. View Orders");
			System.out.println("10. Sign Out");
			System.out.println("\nPlease select an option:");
			int select;

			try {
				select = scan.nextInt();
			} catch (java.util.InputMismatchException e) {
				System.out.println(invalidInputMsg);
				scan.nextLine();
				return;
			}

			System.out.println("\n");

			switch (select) {
			case 1:
				productCatalog.printCatalog();

				System.out.println("Enter Search Key: ");
				String searchKey = scan.next();
				List<Product> productsSearch = adminDashboard.searchProduct(searchKey);
				int i = 0;
				for (Product product : productsSearch) {
					System.out.println(i + ". ");
					product.print();
					System.out.println(hr);
					i++;
				}
				break;
			case 2:
				ArrayList<Product> products = new ArrayList<>();

				productCatalog.printCatalog();
				System.out.println("Enter Car Details: ");
				String carDetails = scan.next();

				System.out.println("Enter Date dd-MM-yyyy");
				String date = scan.next();
				System.out.println("Enter Time HH:mm");
				String time = scan.next();
				date += " " + time;

				boolean addingProducts = true;
				while (addingProducts) {
					productCatalog.printCategories();
					System.out.println("Choose Category:");
					int categoryIndex = scan.nextInt();

					if (categoryIndex < 0 || categoryIndex >= productCatalog.getAllCategories().size()) {
						System.out.println(invalidSelectionMsq);
						continue;
					}

					ProductCategory selectedCategory = productCatalog.getAllCategories().get(categoryIndex);
					selectedCategory.printProducts();
					System.out.println("Choose Product:");
					int productIndex = scan.nextInt();

					if (productIndex < 0 || productIndex >= selectedCategory.getAllProducts().size()) {
						System.out.println(invalidSelectionMsq);
						continue;
					}

					Product selectedProduct = selectedCategory.getAllProducts().get(productIndex);
					if (selectedProduct.isAvailable()) {
						products.add(selectedProduct);
						System.out.println("Product Added Successfully.\n");
					} else
						System.out.println("Product is not available.\n");

					System.out.println("1. Add Another Product");
					System.out.println("2. Done");
					System.out.println("3. Back");
					int select2 = scan.nextInt();

					switch (select2) {
					case 1:
						break;
					case 2:
						adminDashboard.addInstallationRequest(
								new InstallationRequest(date, (Customer) user, products, carDetails));
						System.out.println("Installation Request Added Successfully.\n");
						addingProducts = false;
						break;
					case 3:
						return;
					default:
						System.out.println(invalidSelectionMsq);
						break;
					}
				}
				break;

			case 3:
				adminDashboard.printInstallationRequest(user);

				System.out.println("Choose Request:");
				int requestNumber = scan.nextInt();
				if (adminDashboard.getInstallationRequests().size() > (requestNumber) && requestNumber >= 0) {
					System.out.println("1. Edit Request");
					System.out.println("2. Delete Request");
					System.out.println("3. Back");
					int select2 = scan.nextInt();
					switch (select2) {
					case 1:
						// change state
						System.out.println("Enter New State:");
						String newState = scan.next();
						adminDashboard.getInstallationRequests().get(requestNumber).setState(newState,
								adminDashboard.getInstallationRequests().get(requestNumber));
						System.out.println("Product Edited Successfully.\n");
						break;
					case 2:
						System.out.println("Are you sure you want to delete this request? (Y/N)");
						String sure = scan.next();
						if (sure.equalsIgnoreCase("Y")) {
							adminDashboard.getInstallationRequests().remove(requestNumber);
							System.out.println("Request Deleted Successfully.\n");
						} else {
							System.out.println("Request Not Deleted.\n");
						}
						break;
					default:
						System.out.println(invalidSelectionMsq);
						System.out.println("\n");
					}

				} else {
					System.out.println(invalidSelectionMsq);
				}
				;
				break;
			case 4:
				String newEmail;
				while (true) {
					System.out.println("Enter New Email:");
					newEmail = scan.next();
					if (user.isValidEmail(newEmail))
						break;
					System.out.println("\nInvalid Email! Please try again...\n");
				}

				System.out.println("\nEnter your password for confirmation:");
				password = scan.next();
				if (!user.checkPassword(password)) {
					System.out.println("\nIncorrect password! the operation has been cancelled...\n");
					break;
				}

				user.setEmail(newEmail);
				System.out.println("\nEmail Changed Successfully.\n");
				break;

			case 5:
				System.out.println("Enter your old password:");
				String oldPassword = scan.next();
				if (!user.checkPassword(oldPassword)) {
					System.out.println("\nIncorrect password! the operation has been cancelled...\n");
					break;
				}

				String newPassword;
				while (true) {
					System.out.println("\nEnter new password:");
					newPassword = scan.next();
					if (user.isPasswordValid(newPassword))
						break;
					System.out.println("\nInvalid Password! Please try again...\n");
				}

				user.setPassword(newPassword);
				System.out.println("\nPassword Changed Successfully.\n");
				break;

			case 6:
				String newContactNumber;
				while (true) {
					System.out.println("Enter New Contact Number:");
					newContactNumber = scan.next();
					if (user.isValidContactNumber(newContactNumber))
						break;
					System.out.println("\nInvalid Contact Number! Please try again...\n");
				}

				System.out.println("Enter your password for confirmation:");
				password = scan.next();
				if (!user.checkPassword(password)) {
					System.out.println("\nIncorrect password! the operation has been cancelled...\n");
					break;
				}
				user.setContactNumber(newContactNumber);
				System.out.println("\nContact Number Changed Successfully.\n");
				break;
			case 7:
				user.print();
				break;
			case 8:
				adminDashboard.printInstallationRequest(user);
				break;
			case 9:
				adminDashboard.printCompletedInstallationRequest(user);
				break;
			case 10:
				System.out.println(logoutMsq);
				scan.nextLine();
				return;

			default:
				System.out.println(invalidSelectionMsq);
				System.out.println("\n");
			}
		}
	}

	public static void installerActivities() {
		String password;

		while (true) {
			System.out.println("\t\tWelcome " + user.getEmail() + " - Signed In as Installer");
			System.out.println(hr);
			System.out.println("1. View Installation Requests");
			System.out.println("2. Edit Or Delete Installation Appointment");
			System.out.println("3. Change Email");
			System.out.println("4. Change Password");
			System.out.println("5. Change Contact Number");
			System.out.println("6. View Profile");
			System.out.println("7. View orders");
			System.out.println("8. Change Availability");
			System.out.println("9. Sign Out");
			System.out.println("\nPlease select an option:");
			int select;

			try {
				select = scan.nextInt();
			} catch (java.util.InputMismatchException e) {
				System.out.println(invalidInputMsg);
				scan.nextLine();
				return;
			}

			System.out.println("\n");

			switch (select) {
			case 1:
				adminDashboard.printInstallationRequests();
				break;
			case 2:
				adminDashboard.printInstallationRequests();
				System.out.println("Choose Request:");
				int requestNumber = scan.nextInt();
				if (adminDashboard.getInstallationRequests().size() > (requestNumber) && requestNumber >= 0) {
					System.out.println("1. Edit Request");
					System.out.println("2. Delete Request");
					System.out.println("3. Back");
					int select2 = scan.nextInt();
					switch (select2) {
					case 1:
						// change state
						System.out.println("Enter New State:");
						String newState = scan.next();
						adminDashboard.getInstallationRequests().get(requestNumber).setState(newState,
								adminDashboard.getInstallationRequests().get(requestNumber));
						adminDashboard.getInstallationRequests().get(requestNumber).setInstaller((Installer) user);
						System.out.println("Product Edited Successfully.\n");
						break;
					case 2:
						// sure to delete
						System.out.println("Are you sure you want to delete this request? (Y/N)");
						String sure = scan.next();
						if (sure.equalsIgnoreCase("Y")) {
							adminDashboard.getInstallationRequests().remove(requestNumber);
							System.out.println("Request Deleted Successfully.\n");
						} else {
							System.out.println("Request Not Deleted.\n");
						}
						break;
					default:
						System.out.println(invalidSelectionMsq);
						System.out.println("\n");
					}
				} else {
					System.out.println(invalidSelectionMsq);

				}
				break;
			case 3:
				String newEmail;
				while (true) {
					System.out.println("Enter New Email:");
					newEmail = scan.next();
					if (user.isValidEmail(newEmail))
						break;
					System.out.println("\nInvalid Email! Please try again...\n");
				}
				System.out.println("\nEnter your password for confirmation:");
				password = scan.next();
				if (!user.checkPassword(password)) {
					System.out.println("\nIncorrect password! the operation has been cancelled...\n");
					break;
				}
				user.setEmail(newEmail);
				System.out.println("\nEmail Changed Successfully.\n");
				break;
			case 4:
				System.out.println("Enter your old password:");
				String oldPassword = scan.next();
				if (!user.checkPassword(oldPassword)) {
					System.out.println("\nIncorrect password! the operation has been cancelled...\n");
					break;
				}

				String newPassword;
				while (true) {
					System.out.println("\nEnter new password:");
					newPassword = scan.next();
					if (user.isPasswordValid(newPassword))
						break;
					System.out.println("\nInvalid Password! Please try again...\n");
				}

				user.setPassword(newPassword);
				System.out.println("\nPassword Changed Successfully.\n");
				break;
			case 5:
				String newContactNumber;
				while (true) {
					System.out.println("Enter New Contact Number:");
					newContactNumber = scan.next();
					if (user.isValidContactNumber(newContactNumber))
						break;
					System.out.println("\nInvalid Contact Number! Please try again...\n");
				}

				System.out.println("Enter your password for confirmation:");
				password = scan.next();
				if (!user.checkPassword(password)) {
					System.out.println("\nIncorrect password! the operation has been cancelled...\n");
					break;
				}

				user.setContactNumber(newContactNumber);
				System.out.println("\nContact Number Changed Successfully.\n");
				break;
			case 6:
				user.print();

				break;
			case 7:
				adminDashboard.printCompletedInstallationRequests();
				break;
			case 8:
				System.out.println("Enter Y Or N: ");
				String state = scan.next();
				boolean newAvailability;
				if (state.equalsIgnoreCase("y"))
					newAvailability = true;
				else if (state.equalsIgnoreCase("n"))
					newAvailability = false;
				else {
					System.out.println(invalidSelectionMsq);
					break;
				}
				((Installer) user).setAvailability(newAvailability);
				break;
			case 9:
				System.out.println(logoutMsq);
				scan.nextLine();
				return;
			}

		}
	}

}
