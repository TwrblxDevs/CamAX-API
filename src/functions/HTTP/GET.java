package functions.HTTP;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class GET {
    public HttpResponse SendGet(String requestURL) {
        HttpURLConnection con = null;
        BufferedReader reader = null;
        StringBuilder response = new StringBuilder();
        int statusCode = -1;

        try {
            @SuppressWarnings("deprecation")
            URL url = new URL(requestURL);
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            statusCode = con.getResponseCode();

            reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

        } catch (MalformedURLException e) {
            System.out.println("Malformed URL: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO Exception: " + e.getMessage());
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    System.out.println("Error closing reader: " + e.getMessage());
                }
            }
            if (con != null) {
                con.disconnect();
            }
        }

        return new HttpResponse(statusCode, response.toString());
    }
}
