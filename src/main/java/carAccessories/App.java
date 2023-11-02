package carAccessories;

import java.util.ArrayList;
import java.util.Scanner;

public class App {
    static int userIndex;
    private static Scanner scan = new Scanner(System.in);
	private static ArrayList<Users> users = new ArrayList<>();
	private static String email, password;
	private static Record record = new Record();
	
	public static void init() {
        users.add(new Admin("mohammadbadawi@gmail.com", "mohammadbadawi2001", "Admin"));
        users.add(new Customer("haya@gmail.com", "drhaya9999", "Customer"));
        users.add(new Customer("majd@gmail.com", "majd0567", "Customer"));
        users.add(new Installer("ahmad@gmail.com", "ahmad2000 ", "Installer"));

        Customer customer = (Customer)users.get(1);
		record.addAppointment(new Appointment("27/11/2023", "11"), customer);
		record.addAppointment(new Appointment("14/06/2024", "2"), customer);
	}
	
	public static int authenticateUser() {
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
	
	public static void main(String[] args) {
		init();
		
		while (true) {
            System.out.println("Enter Username:");
            email = scan.nextLine();
            
            if (email.equalsIgnoreCase("exit"))
            	System.exit(0);

            System.out.println("\nEnter Password:");
            password = scan.nextLine();

            System.out.println();

            userIndex = authenticateUser();

            if (userIndex != -1) {
                if (users.get(userIndex).checkRole("Admin"))
                	adminActivities(); 
                else if (users.get(userIndex).checkRole("Customer"))
                	customerActivities();             
                else if (users.get(userIndex).checkRole("Installer"))
                	installerActivities();   
            }
            
            else if (email.equalsIgnoreCase("exit"))
            	break;

            else
            	System.out.println("The username or password is incorrect. Please try again...\n");
        }
		
	}
	
	public static void adminActivities() {
		while (true) {
			System.out.println("\t\tWelcome " + users.get(userIndex).email + " - Signed In as Admin");
	        System.out.println("================================================================================");
	        System.out.println("1. Sign Out");
	        System.out.println("\nPlease select an option:");
	        int select = scan.nextInt();
	        System.out.println();
	        
	        switch (select) {
	        case 1:
	        	System.out.println("Logged Out...\n");
	            scan.nextLine();
	            return;
	        	
	        default:
	            System.out.println("Invalid selection! Please try again...");
	            System.out.println();	
	        }
		}
	}
	
	public static void customerActivities() {
		int i;
		int index;
		Customer customer = (Customer)users.get(userIndex);
		ArrayList<Integer> appIndex = new ArrayList<>();
		String password;
		
		while (true) {
			System.out.println("\t\tWelcome " + users.get(userIndex).email + " - Signed In as Customer");
	        System.out.println("================================================================================");
	        System.out.println("1. Add Installation Appointment");
	        System.out.println("2. Edit Installation Appointment");
	        System.out.println("3. Delete Installation Appointment");
	        System.out.println("4. Show Appointment");
	        System.out.println("5. Change Email");
	        System.out.println("6. Change Password");
	        System.out.println("7. Change Contact Number");
	        System.out.println("8. Sign Out");
	        System.out.println("\nPlease select an option:");
	        int select = scan.nextInt();
	        System.out.println();
	        
	        switch (select) {
	        case 1:
	        	System.out.println("Choose Day:");
				String day = scan.next();
				System.out.println("Choose Month:");
				String month = scan.next();
				System.out.println("Choose Year:");
				String year = scan.next();
				System.out.println("Choose Time:");
				String time = scan.next();
				
				String date = day + "/" + month + "/" + year;

				boolean validAppointment = record.addAppointment(new Appointment(date, time), customer);
				if (validAppointment)
					System.out.println("Add Appointment Successfully.\n");
				else
					System.out.print("Unsuccessfull Operation!");
				break;	
	        	
	        case 2:
	        	i = 1;
	        	appIndex.clear();
	        	System.out.println("Appointments List:");
	            System.out.println("------------------------------------");
	        	for (int j = 0; j < record.appointmentsRecord.size(); j++) {
	        		if (record.customers.get(j).equals(customer)) {
	        			System.out.println(i++ + ". " + record.appointmentsRecord.get(j).date + "   Time: " + record.appointmentsRecord.get(j).time);
	        			appIndex.add(j);
	        		}
	        	}
	        	
	        	System.out.println();
	            System.out.println("\nPlease select an appointment:");
	            index = scan.nextInt();
            	if (index > i || index < 1) {
            		System.out.println("Invalid selection! Please try again...");
            		break;
            	}
	            index--;
	            index = appIndex.get(index);          
	            System.out.println();
	            System.out.println("Choose New Day:");
				String newDay = scan.next();
				System.out.println("Choose New Month:");
				String newMonth = scan.next();
				System.out.println("Choose New Year:");
				String newYear = scan.next();
				System.out.println("Choose New Time:");
				String newTime = scan.next();
				
				String newdate = newDay + "/" + newMonth + "/" + newYear;
				
				validAppointment = record.editAppointment(record.appointmentsRecord.get(index), new Appointment(newdate, newTime));

				if (validAppointment)
					System.out.println("Edit Appointment Successfully.\n");
				else
					System.out.print("Unsuccessfull Operation!");
				
				break;
	        	
	        	
	        case 3:
	        	i = 1;
	        	appIndex.clear();
	        	System.out.println("Appointments List:");
	            System.out.println("------------------------------------");
	        	for (int j = 0; j < record.appointmentsRecord.size(); j++) {
	        		if (record.customers.get(j).equals(customer)) {
	        			System.out.println(i++ + ". " + record.appointmentsRecord.get(j).date + "   Time: " + record.appointmentsRecord.get(j).time);
	        			appIndex.add(j);
	        		}
	        	}
	        	
	        	System.out.println();
	            System.out.println("\nPlease select an appointment:");
	            index = scan.nextInt();
	            if (index > i || index < 1) {
            		System.out.println("Invalid selection! Please try again...");
            		break;
            	}	
	            index--;
	            index = appIndex.get(index);          
	            System.out.println();
	            			
				validAppointment = record.deleteAppointment(record.appointmentsRecord.get(index));

				if (validAppointment)
					System.out.println("Delete Appointment Successfully.\n");
				else
					System.out.print("Unsuccessfull Operation!");
				
				break;
	        	
	        case 4:
	        	i = 1;
	        	appIndex.clear();
	        	System.out.println("Appointments List:");
	            System.out.println("------------------------------------");
	        	for (int j = 0; j < record.appointmentsRecord.size(); j++) {
	        		if (record.customers.get(j).equals(customer)) {
	        			System.out.println(i++ + ". " + record.appointmentsRecord.get(j).date +	"   Time: " + record.appointmentsRecord.get(j).time);
	        			appIndex.add(j);
	        		}
	        	}
	        	
	        	System.out.println();
	        	break;
	        	
	        case 5:
	        	String newEmail;
	        	while (true) {
		        	System.out.println("Enter New Email:");
					newEmail = scan.next();
					if (customer.isValidEmail(newEmail))
						break;
					System.out.println("\nInvalid Email! Please try again...\n");
	        	}
	        	
	        	System.out.println("\nEnter your password for confirmation:");
				password = scan.next();
				if (!customer.checkPassword(password)) {
					System.out.println("\nIncorrect password! the operation has been cancelled...\n");
					break;
				}
				
				customer.setEmail(newEmail);
				System.out.println("\nEmail Changed Successfully.\n");
				break;
				
	        case 6:
	        	System.out.println("Enter your old password:");
				String oldPassword = scan.next();
				if (!customer.checkPassword(oldPassword)) {
					System.out.println("\nIncorrect password! the operation has been cancelled...\n");
					break;
				}
				
	        	String newPassword;
	        	while (true) {
		        	System.out.println("\nEnter new password:");
					newPassword = scan.next();
					if (customer.isPasswordValid(newPassword))
						break;
					System.out.println("\nInvalid Password! Please try again...\n");
	        	}
				
				customer.setPassword(newPassword);
				System.out.println("\nPassword Changed Successfully.\n");
				break;
	        
	        case 7:
	        	String newContactNumber;
	        	while (true) {
		        	System.out.println("Enter New Contact Number:");
		        	newContactNumber = scan.next();
					if (customer.isValidEmail(newContactNumber))
						break;
					System.out.println("\nInvalid Contact Number! Please try again...\n");
	        	}
	        	
	        	System.out.println("Enter your password for confirmation:");
				password = scan.next();
				if (!customer.checkPassword(password)) {
					System.out.println("\nIncorrect password! the operation has been cancelled...\n");
					break;
				}
				
				customer.setContactNumber(newContactNumber);
				System.out.println("\nContact Number Changed Successfully.\n");
				break;
				
	        case 8:
	        	System.out.println("Logged Out...\n");
	            scan.nextLine();
	            return;
	        	
	        default:
	            System.out.println("Invalid selection! Please try again...");
	            System.out.println();	
	        }
		}
	}
	
	public static void installerActivities() {
		while (true) {
			System.out.println("\t\tWelcome " + users.get(userIndex).email + " - Signed In as Installer");
	        System.out.println("================================================================================");
	        System.out.println("1. Sign Out");
	        System.out.println("\nPlease select an option:");
	        int select = scan.nextInt();
	        System.out.println();
	        
	        switch (select) {
	        case 1:
	        	System.out.println("Logged Out...\n");
	            scan.nextLine();
	            return;
	        	
	        default:
	            System.out.println("Invalid selection! Please try again...");
	            System.out.println();	
	        }
		}
	}
}
