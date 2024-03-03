package model;

import com.google.gson.Gson;

public class AppointmentRequest {
    int requestId;
    int personId;
    String[] preferredDays;
    int[] preferredDocs;
    boolean isNew;

    public AppointmentRequest(String serverResponse) {
        Gson gson = new Gson();
        AppointmentRequest appointmentRequest = gson.fromJson(serverResponse, AppointmentRequest.class);
        this.requestId = appointmentRequest.requestId;
        this.personId = appointmentRequest.personId;
        this.preferredDays = appointmentRequest.preferredDays;
        this.preferredDocs = appointmentRequest.preferredDocs;
        this.isNew = appointmentRequest.isNew;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String[] getPreferredDays() {
        return preferredDays;
    }

    public void setPreferredDays(String[] preferredDays) {
        this.preferredDays = preferredDays;
    }

    public int[] getPreferredDocs() {
        return preferredDocs;
    }

    public void setPreferredDocs(int[] preferredDocs) {
        this.preferredDocs = preferredDocs;
    }

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean isNew) {
        this.isNew = isNew;
    }
}
