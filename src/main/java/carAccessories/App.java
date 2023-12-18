package carAccessories;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;


public class App {

	private static AdminDashboard adminDashboard;
	private static int userIndex;
	private static Scanner scan = new Scanner(System.in);
    private static final Logger logger = Logger.getLogger(App.class.getName());
	private static String email, password;
	private static ProductCatalog productCatalog;
	static int categoryIndex = 0;
	static int productIndex = 0;
	private static Users user;
	final static String invalidInputMsg  = "Invalid input! Please enter a valid option.";
	final static String hr = "================================================================================";
	
	public static void main(String[] args) {

		init();
		

		while (true) {
			String new_pass;
			logger.info(hr);
			logger.info("\t\tWelcome to Car Accessories Store");
			logger.info("1. Sign In");
			logger.info("2. Sign Up");
			logger.info("3. Exit");
			logger.info("\nPlease select an option:");

			int select;

			try {
				select = scan.nextInt();
			} catch (java.util.InputMismatchException e) {
				logger.info(invalidInputMsg);
				scan.nextLine();
				return;
			}

			switch (select) {
			case 1:
				logger.info("Enter Username:");
				email = scan.next();

				logger.info("Enter Password:");
				password = scan.next();

				userIndex = adminDashboard.authenticateUser(email, password);

				if (userIndex == -1) {
					logger.info("Invalid Credentials! Please try again...\n");
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

				logger.info("Enter Email:");
				email = scan.next();
				logger.info("Enter Password:");
				password = scan.next();
				logger.info("Reenter Password:");
				new_pass = scan.next();

				if (!password.equals(new_pass)) {
					logger.info("Password doesn't match! Please try again...");
					logger.info("\n");
					return;
				}

				boolean isValid = adminDashboard.addUser(new Customer(email, password, "Customer"));

				if (isValid) {
					logger.info("Account Created Successfully!");
					userIndex = adminDashboard.authenticateUser(email, password);

					if (userIndex == -1) {
						logger.info("Invalid Credentials! Please try again...\n");
						return;
					}

					user = adminDashboard.getUsers().get(userIndex);

					customerActivities();

				} else
					logger.info("Account Creation Failed! Please try again...");
				break;

			case 3:
				logger.info("Logged Out...\n");
				System.exit(0);

			default:
				logger.info("Invalid selection! Please try again...");
				logger.info("\n");
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
				new Installer("mohammadbadawi01@gmail.com", "badawi01", "Installer"), new Customer("majdbasem6@gmail.com", "majd123", "Customer"),
				carAudio.getAllProducts(), "M8 twin terbo stage 3");

		adminDashboard.addInstallationRequest(installationRequest1);
		adminDashboard.addInstallationRequest(installationRequest1);

		adminDashboard.addInstallationRequest(installationRequest1);
		adminDashboard.addInstallationRequest(installationRequest1);

		productCatalog.addCategory(z);
		adminDashboard.addProductCatalog(productCatalog);
	}


	public static void adminDashboardActivities() {
		while (true) {
			logger.info("\t\tWelcome " + user.email + " - Signed In as Admin");
			logger.info(hr);
			logger.info("1. Add Product");
			logger.info("2. Edit Or Delete Product");
			logger.info("3. View Products");

			logger.info("4. Add Product Category");
			logger.info("5. Edit Product Category");
			logger.info("6. Delete Product Category");

			logger.info("7. View Product Categories");
			logger.info("8. View Users");
			logger.info("9. Edit Or Delete Users");
			logger.info("10. Change State or Delete Installation Requests");

			logger.info("11. Sign Out");
			logger.info("\nPlease select an option:");

			int select;

			try {
				select = scan.nextInt();
			} catch (java.util.InputMismatchException e) {
				logger.info(invalidInputMsg);
				scan.nextLine();
				return;
			}

			logger.info("\n");
			switch (select) {
			case 1:
				productCatalog.printCategories();
				logger.info("Choose Category:");
				categoryIndex = scan.nextInt();
				logger.info("Enter Product Name:");
				String name = scan.next();
				logger.info("Enter Product Description:");
				String descriptions = scan.next();
				logger.info("Enter Product Price:");
				double price = scan.nextDouble();
				logger.info("Enter Product Availability:");
				boolean isAvailable = scan.nextBoolean();

				String category = productCatalog.getAllCategories().get(categoryIndex).getName();

				Product newProduct = new Product(name, descriptions, new ArrayList<String>(), price, isAvailable);

				productCatalog.addProductToCategory(newProduct, category);
				logger.info("Product Added Successfully.\n");
				break;
			case 2:

				productCatalog.printCategories();
				logger.info("Enter Catagory number:");
				categoryIndex = scan.nextInt();
				if (productCatalog.getAllCategories().size() > (categoryIndex + 1) && categoryIndex >= 0) {

					productCatalog.getAllCategories().get(categoryIndex).printProducts();

					logger.info("Enter product number:");
					int productNumber = scan.nextInt();

					if (productCatalog.getAllCategories().get(categoryIndex).getAllProducts()
							.size() > (productIndex + 1) && productNumber >= 0) {

						logger.info("1. Edit Product");
						logger.info("2. Delete Product");
						int select2 = scan.nextInt();
						switch (select2) {
						case 1:
							logger.info("Enter New Name:");
							String newName = scan.next();
							logger.info("Enter New Description:");
							String newDescription = scan.next();
							logger.info("Enter New Price:");
							double newPrice = scan.nextDouble();
							logger.info("Enter New Availability:");
							boolean newAvailability = scan.nextBoolean();

							Product newProduct2 = new Product(newName, newDescription, new ArrayList<String>(),
									newPrice, newAvailability);

							productCatalog.getAllCategories().get(categoryIndex).getAllProducts().set(productNumber,
									newProduct2);
							logger.info("Product Edited Successfully.\n");
							break;
						case 2:
							productCatalog.getAllCategories().get(categoryIndex).getAllProducts().remove(productNumber);
							logger.info("Product Deleted Successfully.\n");
							break;
						default:
							logger.info("Invalid selection! Please try again...");
							logger.info("\n");
						}
					} else {
						logger.info("Invalid selection! Please try again...");
					}
				} else
					logger.info("Invalid selection! Please try again...");
				break;

			case 3:

				productCatalog.printCatalog();
				break;
			case 4:
				logger.info("Enter Category Name:");
				String categoryName = scan.next();
				logger.info("Enter Category Description:");
				String categoryDescription = scan.next();
				ProductCategory newCategory = new ProductCategory(categoryName, categoryDescription);
				productCatalog.addCategory(newCategory);
				logger.info("Category Added Successfully.\n");

				break;
			case 5:
				// edit category name and description
				productCatalog.printCategories();
				logger.info("Enter Category number:");
				categoryIndex = scan.nextInt();
				if (productCatalog.getAllCategories().size() > (categoryIndex + 1) && categoryIndex >= 0) {
					logger.info("Enter New Name:");
					String newName = scan.next();
					logger.info("Enter New Description:");
					String newDescription = scan.next();

					ProductCategory newCategory2 = new ProductCategory(newName, newDescription);

					productCatalog.updateCategory(newCategory2);
					logger.info("Category Edited Successfully.\n");
				} else
					logger.info("Invalid selection! Please try again...");
				break;
			case 6:
				productCatalog.printCategories();
				logger.info("Enter Category number:");
				categoryIndex = scan.nextInt();
				if (productCatalog.getAllCategories().size() > (categoryIndex + 1) && categoryIndex >= 0) {
					productCatalog.getAllCategories().remove(categoryIndex);
					logger.info("Category Deleted Successfully.\n");
				} else

					logger.info("Invalid selection! Please try again...");

				break;
			case 7:

				productCatalog.printCategories();
				break;

			case 8:
				adminDashboard.printUsers();
				break;
			case 9:
				adminDashboard.printUsers();
				logger.info("Enter User number:");

				int userNumber = scan.nextInt();
				if (adminDashboard.getUsers().size() > (userNumber + 1) && userNumber >= 0) {
					logger.info("1. Edit User");
					logger.info("2. Delete User");
					int select2 = scan.nextInt();
					switch (select2) {
					case 1:

						logger.info("Enter New Role:");
						String newRole = scan.next();

						adminDashboard.getUsers().get(userNumber).setRole(newRole);
						logger.info("User Edited Successfully.\n");
						break;
					case 2:
						adminDashboard.getUsers().remove(userNumber);
						logger.info("User Deleted Successfully.\n");
						break;
					default:
						logger.info("Invalid selection! Please try again...");
						logger.info("\n");
					}
				} else {
					logger.info("Invalid selection! Please try again...");
				}

				break;
			case 10:

				adminDashboard.printInstallationRequests();
				logger.info("Enter Request number:");
				int requestNumber = scan.nextInt();
				if (adminDashboard.getInstallationRequests().size() > (requestNumber) && requestNumber >= 0) {
					logger.info("1. Edit Request");
					logger.info("2. Delete Request");
					logger.info("3. Back");
					int select2 = scan.nextInt();
					switch (select2) {
					case 1:
						// change state
						logger.info("Enter New State:");
						String newState = scan.next();
						adminDashboard.getInstallationRequests().get(requestNumber).setState(newState,
								adminDashboard.getInstallationRequests().get(requestNumber));
						logger.info("Product Edited Successfully.\n");
						break;
					case 2:
						// sure to delete
						logger.info("Are you sure you want to delete this request? (Y/N)");
						String sure = scan.next();
						if (sure.equalsIgnoreCase("Y")) {
							adminDashboard.getInstallationRequests().remove(requestNumber);
							logger.info("Request Deleted Successfully.\n");
						} else {
							logger.info("Request Not Deleted.\n");
						}
						break;
					default:
						logger.info("Invalid selection! Please try again...");
						logger.info("\n");
					}

				} else {
					logger.info("Invalid selection! Please try again...");
				}
				break;
			case 11:
				logger.info("Logged Out...\n");
				return;
			}

		}

	}

	
	public static void customerActivities() {
		String password;

		while (true) {
			logger.info("\t\tWelcome " + user.email + " - Signed In as Customer");
			logger.info(hr);
			logger.info("1. View Products <Search>");
			logger.info("2. Add Installation Request <View Products>");
			logger.info("3. Edit or Delete Installation Request");
			logger.info("4. Change Email");
			logger.info("5. Change Password");
			logger.info("6. Change Contact Number");
			logger.info("7. View Profile");
			logger.info("8. View installation Requests");
			logger.info("9. View Orders");
			logger.info("10. Sign Out");
			logger.info("\nPlease select an option:");
			int select;

			try {
				select = scan.nextInt();
			} catch (java.util.InputMismatchException e) {
				logger.info(invalidInputMsg);
				scan.nextLine();
				return;
			}

			logger.info("\n");

			switch (select) {
			case 1:
				productCatalog.printCatalog();

				logger.info("Enter Search Key: ");
				String searchKey = scan.next();
				List<Product> productsSearch = adminDashboard.searchProduct(searchKey);
				int i = 0;
				for (Product product : productsSearch) {
					logger.info(i + ". ");
					product.print();
					logger.info("---------------------------------------------------");
					i++;
				}
				break;
			case 2:
			    ArrayList<Product> products = new ArrayList<>();

			    productCatalog.printCatalog();
			    logger.info("Enter Car Details: ");
			    String carDetails = scan.next();

			    logger.info("Enter Date dd-MM-yyyy");
			    String date = scan.next();
			    logger.info("Enter Time HH:mm");
			    String time = scan.next();
			    date += " " + time;

			    boolean addingProducts = true;
			    while (addingProducts) {
			        productCatalog.printCategories();
			        logger.info("Choose Category:");
			        int categoryIndex = scan.nextInt();

			        if (categoryIndex < 0 || categoryIndex >= productCatalog.getAllCategories().size()) {
			            logger.info("Invalid selection! Please try again...");
			            continue;
			        }

			        ProductCategory selectedCategory = productCatalog.getAllCategories().get(categoryIndex);
			        selectedCategory.printProducts();
			        logger.info("Choose Product:");
			        int productIndex = scan.nextInt();

			        if (productIndex < 0 || productIndex >= selectedCategory.getAllProducts().size()) {
			            logger.info("Invalid selection! Please try again...");
			            continue;
			        }

			        Product selectedProduct = selectedCategory.getAllProducts().get(productIndex);
			        if(selectedProduct.isAvailable()) {
			        	products.add(selectedProduct);
			        logger.info("Product Added Successfully.\n");
			        }else
			        	logger.info("Product is not available.\n");
			        

			        logger.info("1. Add Another Product");
			        logger.info("2. Done");
			        logger.info("3. Back");
			        int select2 = scan.nextInt();

			        switch (select2) {
			            case 1:
			                break;
			            case 2:
			                adminDashboard.addInstallationRequest(new InstallationRequest(date, (Customer) user, products, carDetails));
			                logger.info("Installation Request Added Successfully.\n");
			                addingProducts = false;
			                break;
			            case 3:
			                return;
			            default:
			                logger.info("Invalid selection! Please try again...");
			                break;
			        }
			    }
			    break;


			case 3:
				adminDashboard.printInstallationRequest(user);

				logger.info("Choose Request:");
				int requestNumber = scan.nextInt();
				if (adminDashboard.getInstallationRequests().size() > (requestNumber) && requestNumber >= 0) {
					logger.info("1. Edit Request");
					logger.info("2. Delete Request");
					logger.info("3. Back");
					int select2 = scan.nextInt();
					switch (select2) {
					case 1:
						// change state
						logger.info("Enter New State:");
						String newState = scan.next();
						adminDashboard.getInstallationRequests().get(requestNumber).setState(newState,
								adminDashboard.getInstallationRequests().get(requestNumber));
						logger.info("Product Edited Successfully.\n");
						break;
					case 2:
						logger.info("Are you sure you want to delete this request? (Y/N)");
						String sure = scan.next();
						if (sure.equalsIgnoreCase("Y")) {
							adminDashboard.getInstallationRequests().remove(requestNumber);
							logger.info("Request Deleted Successfully.\n");
						} else {
							logger.info("Request Not Deleted.\n");
						}
						break;
					default:
						logger.info("Invalid selection! Please try again...");
						logger.info("\n");
					}

				} else {
					logger.info("Invalid selection! Please try again...");
				}
				;
				break;
			case 4:
				String newEmail;
				while (true) {
					logger.info("Enter New Email:");
					newEmail = scan.next();
					if (user.isValidEmail(newEmail))
						break;
					logger.info("\nInvalid Email! Please try again...\n");
				}

				logger.info("\nEnter your password for confirmation:");
				password = scan.next();
				if (!user.checkPassword(password)) {
					logger.info("\nIncorrect password! the operation has been cancelled...\n");
					break;
				}

				user.setEmail(newEmail);
				logger.info("\nEmail Changed Successfully.\n");
				break;

			case 5:
				logger.info("Enter your old password:");
				String oldPassword = scan.next();
				if (!user.checkPassword(oldPassword)) {
					logger.info("\nIncorrect password! the operation has been cancelled...\n");
					break;
				}

				String newPassword;
				while (true) {
					logger.info("\nEnter new password:");
					newPassword = scan.next();
					if (user.isPasswordValid(newPassword))
						break;
					logger.info("\nInvalid Password! Please try again...\n");
				}

				user.setPassword(newPassword);
				logger.info("\nPassword Changed Successfully.\n");
				break;

			case 6:
				String newContactNumber;
				while (true) {
					logger.info("Enter New Contact Number:");
					newContactNumber = scan.next();
					if (user.isValidContactNumber(newContactNumber))
						break;
					logger.info("\nInvalid Contact Number! Please try again...\n");
				}

				logger.info("Enter your password for confirmation:");
				password = scan.next();
				if (!user.checkPassword(password)) {
					logger.info("\nIncorrect password! the operation has been cancelled...\n");
					break;
				}
				user.setContactNumber(newContactNumber);
				logger.info("\nContact Number Changed Successfully.\n");
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
				logger.info("Logged Out...\n");
				scan.nextLine();
				return;

			default:
				logger.info("Invalid selection! Please try again...");
				logger.info("\n");
			}
		}
	}

	
	
	public static void installerActivities() {
		String password;

		while (true) {
			logger.info("\t\tWelcome " + user.getEmail() + " - Signed In as Installer");
			logger.info(hr);
			logger.info("1. View Installation Requests");
			logger.info("2. Edit Or Delete Installation Appointment");
			logger.info("3. Change Email");
			logger.info("4. Change Password");
			logger.info("5. Change Contact Number");
			logger.info("6. View Profile");
			logger.info("7. View orders");
			logger.info("8. Change Availability");
			logger.info("9. Sign Out");
			logger.info("\nPlease select an option:");
			int select;

			try {
				select = scan.nextInt();
			} catch (java.util.InputMismatchException e) {
				logger.info(invalidInputMsg);
				scan.nextLine();
				return;
			}

			logger.info("\n");

			switch (select) {
			case 1:
				adminDashboard.printInstallationRequests();
				break;
			case 2:
				adminDashboard.printInstallationRequests();
				logger.info("Choose Request:");
				int requestNumber = scan.nextInt();
				if (adminDashboard.getInstallationRequests().size() > (requestNumber) && requestNumber >= 0) {
					logger.info("1. Edit Request");
					logger.info("2. Delete Request");
					logger.info("3. Back");
					int select2 = scan.nextInt();
					switch (select2) {
					case 1:
						// change state
						logger.info("Enter New State:");
						String newState = scan.next();
						adminDashboard.getInstallationRequests().get(requestNumber).setState(newState,
								adminDashboard.getInstallationRequests().get(requestNumber));
						adminDashboard.getInstallationRequests().get(requestNumber).setInstaller((Installer) user);
						logger.info("Product Edited Successfully.\n");
						break;
					case 2:
						// sure to delete
						logger.info("Are you sure you want to delete this request? (Y/N)");
						String sure = scan.next();
						if (sure.equalsIgnoreCase("Y")) {
							adminDashboard.getInstallationRequests().remove(requestNumber);
							logger.info("Request Deleted Successfully.\n");
						} else {
							logger.info("Request Not Deleted.\n");
						}
						break;
					default:
						logger.info("Invalid selection! Please try again...");
						logger.info("\n");
					}
				} else {
					logger.info("Invalid selection! Please try again...");

				}
				break;
			case 3:
				String newEmail;
				while (true) {
					logger.info("Enter New Email:");
					newEmail = scan.next();
					if (user.isValidEmail(newEmail))
						break;
					logger.info("\nInvalid Email! Please try again...\n");
				}
				logger.info("\nEnter your password for confirmation:");
				password = scan.next();
				if (!user.checkPassword(password)) {
					logger.info("\nIncorrect password! the operation has been cancelled...\n");
					break;
				}
				user.setEmail(newEmail);
				logger.info("\nEmail Changed Successfully.\n");
				break;
			case 4:
				logger.info("Enter your old password:");
				String oldPassword = scan.next();
				if (!user.checkPassword(oldPassword)) {
					logger.info("\nIncorrect password! the operation has been cancelled...\n");
					break;
				}

				String newPassword;
				while (true) {
					logger.info("\nEnter new password:");
					newPassword = scan.next();
					if (user.isPasswordValid(newPassword))
						break;
					logger.info("\nInvalid Password! Please try again...\n");
				}

				user.setPassword(newPassword);
				logger.info("\nPassword Changed Successfully.\n");
				break;
			case 5:
				String newContactNumber;
				while (true) {
					logger.info("Enter New Contact Number:");
					newContactNumber = scan.next();
					if (user.isValidContactNumber(newContactNumber))
						break;
					logger.info("\nInvalid Contact Number! Please try again...\n");
				}

				logger.info("Enter your password for confirmation:");
				password = scan.next();
				if (!user.checkPassword(password)) {
					logger.info("\nIncorrect password! the operation has been cancelled...\n");
					break;
				}

				user.setContactNumber(newContactNumber);
				logger.info("\nContact Number Changed Successfully.\n");
				break;
			case 6:
				user.print();

				break;
			case 7:
				adminDashboard.printCompletedInstallationRequests();
				break;
			case 8:
				logger.info("Enter Y Or N: ");
				String state = scan.next();
				boolean newAvailability;
				if (state.equalsIgnoreCase("y"))
					newAvailability = true;
				else if (state.equalsIgnoreCase("n"))
					newAvailability = false;
				else {
					logger.info("Invalid selection! Please try again...");
					break;
				}
				((Installer) user).setAvailability(newAvailability);
				break;
			case 9:
				logger.info("Logged Out...\n");
				scan.nextLine();
				return;
			}

		}
	}

}
