import functions.HTTP.GET;
import functions.HTTP.HttpResponse;
import main.Server.Server;

public class Main {
    public static void main(String[] args) {
        try {
            Server server = new Server(8080);
            server.start();

            GET httpGet = new GET();
            String requestURL = "http://localhost:8080/";
            HttpResponse httpResponse = httpGet.SendGet(requestURL);

            if (httpResponse.getStatusCode() == 200){
                System.out.println("API Online!");
            } else {
                System.err.println("API is Not ONLINE exiting Process!");
                System.exit(0);;
            }

        } catch (Exception e) {
            System.out.println("Failed to start server or make GET request: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
