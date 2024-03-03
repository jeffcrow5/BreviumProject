import com.google.gson.Gson;
import model.AppointmentInfoRequest;
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
            client.sendPostRequest(startEndpoint, "");
            System.out.println("Test system reset successfully");
        } catch (Exception e) {
            System.out.println("Error occurred while resetting test system");
            e.printStackTrace();
        }

        // Get the initial monthly schedule
        Schedule schedule = null;
        String scheduleEndpoint = "/Schedule";

        try {
            String response = client.sendGetRequest(scheduleEndpoint);
            // Returns array of AppointmentInfo objects
            schedule = new Schedule(response);

            System.out.println("Initial monthly schedule retrieved successfully");
        } catch (Exception e) {
            System.out.println("Error occurred while retrieving initial monthly schedule");
            e.printStackTrace();
            System.exit(1);
        }

        // Take in each appointment request
        int responseCode = 200;

        while (responseCode != 204) {
            String appointmentRequestEndpoint = "/AppointmentRequest";
            try {
                String response = client.sendGetRequest(appointmentRequestEndpoint);

                // TODO: Handle 204 response from server more elegantly
                // Modify WebClient to return response code alongside response instead of just a string
                if (response.isEmpty()) {
                    responseCode = 204;
                    continue;
                }

                AppointmentRequest appointmentRequest = new AppointmentRequest(response);
                AppointmentInfoRequest requestToSend = schedule.addAppointment(appointmentRequest);

                // Mark an appointment slot as taken
                Gson gson = new Gson();
                String appointmentRequestPostEndpoint = "/Schedule";
                client.sendPostRequest(appointmentRequestPostEndpoint, gson.toJson(requestToSend));
            } catch (Exception e) {
                System.out.println("Error occurred while processing appointment request");
                e.printStackTrace();
            }
        }

        // Stop the test system
        String stopEndpoint = "/Stop";
        try {
            client.sendPostRequest(stopEndpoint, "");
        } catch (Exception e) {
            System.out.println("Error occurred while stopping test system");
            e.printStackTrace();
        }
    }
}