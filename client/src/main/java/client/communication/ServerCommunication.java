package client.communication;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ServerCommunication {
    private static final String URL = "http://localhost:8080/";
    private static HttpClient client = HttpClient.newBuilder().build();

    private static HttpResponse<String> sendRequest(HttpRequest request) {
        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    private static HttpResponse<String> get(String url) {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url))
                .build();

        HttpResponse<String> response = sendRequest(request);

        return response;
    }

    private static HttpResponse<String> delete(String url) {
        HttpRequest request = HttpRequest.newBuilder()
                .DELETE()
                .uri(URI.create(url))
                .build();

        HttpResponse<String> response = sendRequest(request);

        return response;
    }

    private static HttpResponse<String> post(String url, String body, String... headers) {
        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .uri(URI.create(url))
                .headers(headers)
                .build();

        HttpResponse<String> response = sendRequest(request);

        return response;
    }

}
