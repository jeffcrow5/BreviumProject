import model.AppointmentRequest;
import model.Schedule;
import utils.WebClient;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class SchedulingApp {
    public static void main(String[] args) {
        WebClient client = WebClient.getInstance();

        // Reset test system
        String startEndpoint = "/Start";

        // Hit this endpoint to reset the test system before each 'run' of your program.
        try {
            String response = client.sendPostRequest(startEndpoint, "");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Get the initial monthly schedule
        Schedule schedule = null;
        String scheduleEndpoint = "/Schedule";

        try {
            String response = client.sendGetRequest(scheduleEndpoint);
            // Returns array of AppointmentInfo objects
            schedule = new Schedule(response);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Take in each appointment request
        int responseCode = 200;
        while (responseCode != 204) {
            String appointmentRequestEndpoint = "/AppointmentRequest";
            try {
                String response = client.sendGetRequest(appointmentRequestEndpoint);
                if (responseCode == 204) {
                    break;
                }
                AppointmentRequest appointmentRequest = new AppointmentRequest(response);
                // TODO - handle appointment request

                // FIXME: Remove this once appointment request has been implemented
                responseCode = 204;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }




        // Stop the test system
        String stopEndpoint = "/Stop";
        try {
            client.sendPostRequest(stopEndpoint, "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}