package lab3;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.Timestamp;

import org.json.JSONObject;

public class API {
    final String serverURL = "http://95.217.177.249/casino";

    /*public void createAccount(int id) {

    }*/

    public void createAccount(String id) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI
                .create(serverURL + "/createacc?id=" + id)).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        JSONObject myObject = new JSONObject(response.body());
        System.out.println(myObject);
    }

    public ResultDTO play(String id, String mode, int bet, long number) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI
                .create(serverURL + "/play" + mode + "?id=" + id + "&bet=" +
                        bet + "&number=" + number)).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        JSONObject myObject = new JSONObject(response.body());
        long realNumber = myObject.getLong("realNumber");
        long money = myObject.getJSONObject("account")
                .getLong("money");
        long deletionTime = Timestamp.valueOf(myObject.getJSONObject("account")
                .getString("deletionTime")
                .replace("T", " ")
                .replace("Z", ""))
                .getTime();
        System.out.println(myObject);
        return new ResultDTO(realNumber, money, deletionTime);
    }
}
