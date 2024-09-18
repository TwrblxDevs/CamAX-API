import functions.HTTP.GET;
import functions.HTTP.HttpResponse;
import main.Server.Server;

import java.io.InputStream;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        Properties properties = new Properties();
        try (InputStream input = Main.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                System.err.println("Sorry, unable to find config.properties");
                System.exit(1);
            }
            properties.load(input);

            int port = Integer.parseInt(properties.getProperty("server.port", "8080"));

            String baseURL = properties.getProperty("BaseAPIUrl", "http://localhost");
            String requestURL = baseURL + ":" + port + "/";

            System.out.println("Starting Java API Server on port " + port + ", please wait...");
            Thread.sleep(1000); 

            Server server = new Server(port);
            server.start();

            GET httpGet = new GET();
            HttpResponse httpResponse = httpGet.SendGet(requestURL);

            if (httpResponse.getStatusCode() == 200) {
                System.out.println("API Online!");
            } else {
                System.err.println("API is not online. Exiting process.");
                System.exit(1);
            }

        } catch (Exception e) {
            System.err.println("Failed to start server or make GET request: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }
}
