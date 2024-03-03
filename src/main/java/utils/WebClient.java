package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WebClient {
    private static WebClient instance;
    private static final String API_URL = System.getenv("API_URL");
    private static final String API_TOKEN = System.getenv("API_TOKEN");

    private WebClient() {
    }

    /**
     * Gets the instance of the WebClient
     *
     * @return The instance of the WebClient
     */
    public static WebClient getInstance() {
        if (instance == null) {
            instance = new WebClient();
        }
        return instance;
    }

    /**
     * Sends a GET request to the API
     *
     * @param endpoint The endpoint to send the request to
     * @return The response from the API
     * @throws IOException If an error occurs while sending the request
     */
    public String sendGetRequest(String endpoint) throws IOException {
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        StringBuilder response = new StringBuilder();

        try {
            // Create URL object
            URL apiUrl = new URL(API_URL + endpoint + "?token=" + API_TOKEN);

            // Open connection
            connection = (HttpURLConnection) apiUrl.openConnection();
            connection.setRequestMethod("GET");

            // If response code is 204, return empty string
            if (connection.getResponseCode() == HttpURLConnection.HTTP_NO_CONTENT) {
                return "";
            }

            // Get the response
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
        } finally {
            // Close resources
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                connection.disconnect();
            }
        }

        return response.toString();
    }

    /**
     * Sends a POST request to the API
     *
     * @param endpoint The endpoint to send the request to
     * @param body The body of the request
     * @return The response from the API
     * @throws IOException If an error occurs while sending the request
     */
    public String sendPostRequest(String endpoint, String body) throws IOException {
        HttpURLConnection connection = null;
        try {
            // Create URL object
            URL apiUrl = new URL(API_URL + endpoint + "?token=" + API_TOKEN);

            // Open connection
            connection = (HttpURLConnection) apiUrl.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            // Send request
            connection.getOutputStream().write(body.getBytes());

            // Get the response and response code
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                return response.toString();
            } else {
                throw new IOException("HTTP error code: " + responseCode);
            }
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
}
