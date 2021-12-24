package lab3;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.JSONObject;

public class API {
    final String serverURL = "http://95.217.177.249/casino";

    /*public void createAccount(int id) {

    }*/

    public void createAccount(String id) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(serverURL + "/createacc?id=" + id)).build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        JSONObject myObject = new JSONObject(response.body());
        System.out.println(myObject); // Your json object
        //String encoding = con.getContentEncoding();
        //encoding = encoding == null ? "UTF-8" : encoding;
        //String body = (String) IOUtils.toString(in, encoding);
        //System.out.println(body);
    }
}