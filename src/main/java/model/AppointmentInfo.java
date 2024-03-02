package model;

public class AppointmentInfo {
    int doctorId;
    int personId;
    String appointmentTime;
    boolean isNewPatientAppointment;

    public AppointmentInfo(int doctorId, int personId, String appointmentTime, boolean isNewPatientAppointment) {
        System.out.println("Creating appointment info for doctor " + doctorId + " for person " + personId + " at " + appointmentTime + " for new patient? " + isNewPatientAppointment);
        this.doctorId = doctorId;
        this.personId = personId;
        this.appointmentTime = appointmentTime;
        this.isNewPatientAppointment = isNewPatientAppointment;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public int getPersonId() {
        return personId;
    }

    public void setAppointmentTime(String appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public String getAppointmentTime() {
        return appointmentTime;
    }

    public void setIsNewPatientAppointment(boolean isNewPatientAppointment) {
        this.isNewPatientAppointment = isNewPatientAppointment;
    }

    public boolean getIsNewPatientAppointment() {
        return isNewPatientAppointment;
    }
}