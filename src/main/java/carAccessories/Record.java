package carAccessories;

import java.util.ArrayList;
import java.util.List;

public class Record {
	protected ArrayList<Appointment> appointmentsRecord = new ArrayList<>();
	protected ArrayList<Customer> customers = new ArrayList<>();


	public boolean addAppointment(Appointment appointment, Customer customer) {
		if (isAvailable(appointment)) {
			appointmentsRecord.add(appointment);
			customers.add(customer);
			return true;
		}

		return false;
	}

	public boolean isAvailable(Appointment appointment) {
		for (Appointment appointmentX : appointmentsRecord) {
			if (appointmentX.date.equals(appointment.date) && appointmentX.time.equals(appointment.time)) {
				return false;
			}
		}
		return true;
	}

	public Integer getIndex(Appointment appointment) {
		Integer index = 0;
		for (Appointment appointmentX : appointmentsRecord) {
			if (appointmentX.date.equals(appointment.date) && appointmentX.time.equals(appointment.time)) {
				return index;
			}
			index++;
		}

		return -1;
	}

	public Integer getIndex(Customer customer) {
		Integer index = 0;
		for (Customer customerX : customers) {
			if (customerX.equals(customer)) {
				return index;
			}
			index++;
		}

		return -1;
	}

	public boolean deleteAppointment(Appointment appointment) {
		int index = getIndex(appointment);
		if (index >= 0) {
			appointmentsRecord.remove(index);
			customers.remove(index);
			return true;
		}

		return false;

	}

	public boolean editAppointment(Appointment old, Appointment appointment) {

		if (isAvailable(appointment)) {
			int index = getIndex(old);
			if (index >= 0) {
				appointmentsRecord.set(index, appointment);
				return true;
			}
		}

		return false;
	}

}
