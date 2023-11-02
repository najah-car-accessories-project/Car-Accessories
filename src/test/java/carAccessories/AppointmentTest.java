package carAccessories;

import io.cucumber.java.en.*;
import static org.junit.Assert.assertEquals;

public class AppointmentTest {
	String date, time;
	Customer customer;
	Appointment appointment, selected;
	Record record = new Record();
	int i;
	
	public AppointmentTest() {
		customer = new Customer("majd@gmail.com", "majd0567", "Customer", "0599364789");
		customer.signIn("majd@gmail.com", "majd0567");
		record.addAppointment(new Appointment("27/11/2023", "11"), customer);
		record.addAppointment(new Appointment("14/06/2024", "2"), customer);
	}
	
	@Given("the Customer is logged in")
	public void the_customer_is_logged_in() {
		assertEquals(true, customer.isSignedIn);
	}

	@When("the Customer select add appointment option")
	public void the_customer_select_add_appointment_option() {
	}

	@When("the Customer chooses {string} of the appointment in DD\\/MM\\/YYYY format")
	public void the_customer_chooses_of_the_appointment_in_dd_mm_yyyy_format(String date) {
		this.date = date;
	}

	@When("the Customer chooses {string} of the appointment")
	public void the_customer_chooses_of_the_appointment(String time) {
		this.time = time;
	}

	@Then("appointment will be added and recorded")
	public void appointment_will_be_added_and_recorded() {
		appointment = new Appointment(date, time);
		assertEquals(true, record.addAppointment(appointment, customer));
	}

	@Then("{string} message will appear")
	public void message_will_appear(String msg) {
		System.out.println(msg);
	}

	@When("selected date and time are reserved by another Customer")
	public void selected_date_and_time_are_reserved_by_another_customer() {
		appointment = new Appointment(date, time);
		assertEquals(false, record.isAvailable(appointment));
	}

	@Then("appointment will not be added")
	public void appointment_will_not_be_added() {
		assertEquals(false, record.addAppointment(appointment, customer));
	}

	@When("the Customer select change appointment option")
	public void the_customer_select_change_appointment_option() {
	}

	@When("the Customer selects the appointment he wants to change {string}")
	public void the_customer_selects_the_appointment_he_wants_to_change(String string) {
		i = Integer.parseInt(string) - 1;
		selected = record.appointmentsRecord.get(i);
	}

	@When("the Customer chooses a new {string} of the appointment in DD\\/MM\\/YYYY format")
	public void the_customer_chooses_a_new_of_the_appointment_in_dd_mm_yyyy_format(String date) {
		this.date = date;
	}

	@When("the Customer chooses a new {string} of the appointment")
	public void the_customer_chooses_a_new_of_the_appointment(String time) {
		this.time = time;
	}

	@Then("appointment will be edited and recorded")
	public void appointment_will_be_edited_and_recorded() {
		appointment = new Appointment(date, time);
		assertEquals(true, record.editAppointment(selected, appointment));
	}

	@Then("appointment will not be changed")
	public void appointment_will_not_be_changed() {
		appointment = new Appointment(date, time);
		assertEquals(false, record.editAppointment(selected, appointment));
	}

	@When("the Customer select delete appointment option")
	public void the_customer_select_delete_appointment_option() {
	}

	@When("the Customer selects the appointment he wants to delete {string}")
	public void the_customer_selects_the_appointment_he_wants_to_delete(String string) {
		i = Integer.parseInt(string) - 1;
		selected = record.appointmentsRecord.get(i);
	}

	@Then("appointment will be deleted")
	public void appointment_will_be_deleted() {
		assertEquals(true, record.deleteAppointment(selected));
	}
}
