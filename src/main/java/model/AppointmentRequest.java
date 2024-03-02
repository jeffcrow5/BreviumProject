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
}
