package carAccessories;

import java.util.ArrayList;
import java.util.List;


public class AdminDashboard {

    private List<Users> users;
    private List<ProductCatalog> productCatalogs;
    private List<InstallationRequest> installationRequests;
    private static final String HORIZONTAL_HR = "--------------------------------";
    public AdminDashboard() {
        this.users = new ArrayList<>();
        this.productCatalogs = new ArrayList<>();
        this.installationRequests = new ArrayList<>();    }
    
	public Users getUser(int index) {
		return users.get(index);
	}

	void printCompletedInstallationRequests() {
		System.out.println(HORIZONTAL_HR);

		System.out.println("\t\t\t Completed Installation Requests: ");
		int i = 0;
		for (InstallationRequest installationRequest : installationRequests) {
			if (installationRequest.getStats().equalsIgnoreCase("completed")) {
				System.out.println(i + ". ");
				installationRequest.print();
				System.out.println(HORIZONTAL_HR);
				i++;
			}
		}
	}
	void printInstallationRequests() {
		System.out.println(HORIZONTAL_HR);

		System.out.println("\t\t\t Installation Requests: ");
		int i = 0;
		for (InstallationRequest installationRequest : installationRequests) {
			System.out.println(i + ". ");
			installationRequest.print();
			System.out.println(HORIZONTAL_HR);
			
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
                }
                else
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
			System.out
					.println(i + ". " + user.getEmail() + " - " + user.getContactNumber() + " - " + user.getRole());
			i++;
		}

	}

    public int getTotalNumberOfUsers() {
        return users.size();
    }
    

    public boolean addUser(Users user) {
    	if(user.createAccount(user.getEmail(), user.getPassword(),"0000000000")) {
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
	}
	public List<InstallationRequest> getInstallationRequests() {
		return installationRequests;
	}

	public void printInstallationRequest(Users user) {
		int i = 0;
		for (InstallationRequest installationRequest : installationRequests) {
			if (installationRequest.getCustomer().getEmail().equals(user.getEmail())) {
				System.out.println(i + ". ");
				installationRequest.print();
				System.out.println(HORIZONTAL_HR);
				i++;
			}
		}
		
	}

	public void printCompletedInstallationRequest(Users user) {
		System.out.println(HORIZONTAL_HR);
		System.out.println("\t\t\t Order Installation Requests: ");
		int i = 0;
		for (InstallationRequest installationRequest : installationRequests) {
			if (installationRequest.getCustomer().getEmail().equals(user.getEmail())) {
				if (installationRequest.getStats().equalsIgnoreCase("completed")) {
					System.out.println(i + ". ");
					installationRequest.print();
					System.out.println(HORIZONTAL_HR);
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
