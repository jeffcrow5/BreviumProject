package model;

import com.google.gson.Gson;

import java.util.ArrayList;

public class Schedule {
    private ArrayList<AppointmentInfo> appointments;
    public Schedule(String serverResponse) {
        Gson gson = new Gson();
        this.appointments = gson.fromJson(serverResponse, ArrayList.class);
    }

    public ArrayList<AppointmentInfo> getAppointments() {
        return appointments;
    }

    public void setAppointments(ArrayList<AppointmentInfo> appointments) {
        this.appointments = appointments;
    }

    public void addAppointment(AppointmentInfoRequest appointmentInfoRequest) {
        // TODO: add appointment based on specific rules
        /*
        Appointments may only be scheduled on the hour.
        Appointments can be scheduled as early as 8 am UTC and as late as 4 pm UTC.
        Appointments may only be scheduled on weekdays during the months of November and December 2021.
        Appointments can be scheduled on holidays.
        For a given doctor, you may only have one appointment scheduled per hour (though different doctors may have appointments at the same time).
        For a given patient, each appointment must be separated by at least one week. For example, if Bob Smith has an appointment on 11/17 you may schedule another appointment on or before 11/10 or on or after 11/24.
        Appointments for new patients may only be scheduled for 3 pm and 4 pm.

         */


    }
}
