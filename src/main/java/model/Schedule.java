package model;

import com.google.gson.Gson;

import java.time.LocalDate;
import java.time.LocalTime;
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

    /**
     * Adds an appointment to the schedule based on the given appointment request
     * @param appointmentRequest The parameters for the appointment
     * @return The appointment info request for the new appointment
     */
    public AppointmentInfoRequest addAppointment(AppointmentRequest appointmentRequest) {
        /*
        Appointments may only be scheduled on the hour.
        Appointments can be scheduled as early as 8 am UTC and as late as 4 pm UTC.
        Appointments may only be scheduled on weekdays during the months of November and December 2021.
        Appointments can be scheduled on holidays.
        For a given doctor, you may only have one appointment scheduled per hour (though different doctors may have appointments at the same time).
        For a given patient, each appointment must be separated by at least one week. For example, if Bob Smith has an appointment on 11/17 you may schedule another appointment on or before 11/10 or on or after 11/24.
        Appointments for new patients may only be scheduled for 3 pm and 4 pm.
        */
        AppointmentInfoRequest appointmentInfoRequest = new AppointmentInfoRequest();
        appointmentInfoRequest.setPersonId(appointmentRequest.getPersonId());
        appointmentInfoRequest.setNewPatientAppointment(appointmentRequest.isNew());

        // Calculate the next available appointment time based on constraints
        boolean appointmentValid = false;
        LocalDate currentApptDate = LocalDate.of(2021, 11, 1);
        LocalTime currentApptTime = LocalTime.of(8, 0);
        int currentDoctorId = 1;

        // First check if preferred days are available
        for (String preferredDay : appointmentRequest.getPreferredDays()) {
            LocalDate preferredDate = LocalDate.parse(preferredDay);
            if (preferredDate.getDayOfWeek().getValue() > 6) {
                continue;
            }
            if (preferredDate.getMonthValue() < 11) {
                continue;
            }

            currentApptDate = preferredDate;
            currentApptTime = LocalTime.of(8, 0);

            // Loop through times of day to see if any are available (and doctor is available at that time)
            while (!appointmentValid) {
                if (appointmentTimeValid(currentApptDate, currentApptTime, currentDoctorId, appointmentRequest.getPersonId(), appointmentRequest.isNew())) {
                    appointmentValid = true;
                } else {
                    if (currentDoctorId == 3) {
                        currentDoctorId = 1;
                        if (currentApptTime.getHour() == 16) {
                            currentApptTime = LocalTime.of(8, 0);
                            currentApptDate = currentApptDate.plusDays(1);
                        } else {
                            currentApptTime = currentApptTime.plusHours(1);
                        }
                    } else {
                        currentDoctorId++;
                    }
                }
            }
        }

        while (!appointmentValid) {
            if (appointmentTimeValid(currentApptDate, currentApptTime, currentDoctorId, appointmentRequest.getPersonId(), appointmentRequest.isNew())) {
                appointmentValid = true;
            } else {
                // Increment the current appointment time
                if (currentApptTime.getHour() == 16) {
                    currentApptTime = LocalTime.of(8, 0);
                    currentApptDate = currentApptDate.plusDays(1);
                } else {
                    currentApptTime = currentApptTime.plusHours(1);
                }
            }
        }

        appointmentInfoRequest.setAppointmentTime(currentApptDate.atTime(currentApptTime).toString());
        appointmentInfoRequest.setDoctorId(currentDoctorId);

        // Add the appointment to the list of appointments
        appointments.add(new AppointmentInfo(currentDoctorId, appointmentRequest.getPersonId(), currentApptDate.atTime(currentApptTime).toString(), appointmentRequest.isNew()));

        return appointmentInfoRequest;
    }

    public boolean appointmentTimeValid(LocalDate date, LocalTime time, int doctorId, int personId, boolean isNewPatient) {
        // TODO: Verify that this works as intended
        return date.getDayOfWeek().getValue() < 6 &&
                date.getMonthValue() > 10 && date.getYear() == 2021 &&
                time.getHour() >= 8 && time.getHour() <= 16 &&
                doctorAvailable(doctorId, date.atTime(time).toString()) &&
                patientAvailable(personId, date.atTime(time).toString(), isNewPatient);
    }

    private boolean doctorAvailable(int doctorId, String appointmentTime) {
        for (AppointmentInfo appointment : appointments) {
            if (appointment.getDoctorId() == doctorId) {
                if (appointment.getAppointmentTime().equals(appointmentTime)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean patientAvailable(int personId, String appointmentTime, boolean isNewPatient) {
        // TODO: Check if the current appointment time is at least one week after the last appointment for the current patient
        return true;
    }
}
