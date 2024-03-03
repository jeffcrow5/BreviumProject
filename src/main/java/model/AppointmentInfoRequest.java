package model;

import java.util.concurrent.atomic.AtomicInteger;

public class AppointmentInfoRequest {
    private static final AtomicInteger counter = new AtomicInteger(1);
    private int doctorId;
    private int personId;
    private String appointmentTime; // Datetime string
    private boolean isNewPatientAppointment;
    private final int requestId;

    public AppointmentInfoRequest() {
        this.requestId = counter.getAndIncrement();
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(String appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public boolean isNewPatientAppointment() {
        return isNewPatientAppointment;
    }

    public void setNewPatientAppointment(boolean newPatientAppointment) {
        isNewPatientAppointment = newPatientAppointment;
    }

    public int getRequestId() {
        return requestId;
    }
}