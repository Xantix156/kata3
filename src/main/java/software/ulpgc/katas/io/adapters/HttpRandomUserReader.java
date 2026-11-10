package software.ulpgc.katas.io.adapters;

import software.ulpgc.katas.io.ports.RawDataReader;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpRandomUserReader implements RawDataReader {
    private final HttpClient client;
    private final URI uri;

    public HttpRandomUserReader(HttpClient client, URI url) {
        this.client = client;
        this.uri = url;
    }

    @Override
    public Reader read() throws IOException {
        HttpRequest request = HttpRequest.newBuilder(uri).GET().build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return new StringReader(response.body());
        } catch (InterruptedException exception) {
            throw new IOException(exception);
        }
    }
}
