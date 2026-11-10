package software.ulpgc.katas;

import software.ulpgc.katas.io.adapters.HttpRandomUserReader;

import java.io.IOException;
import java.io.Reader;
import java.net.URI;
import java.net.http.HttpClient;

public class Main {
    static void main() throws IOException {
        try (HttpClient client = HttpClient.newHttpClient()) {
            HttpRandomUserReader source = new HttpRandomUserReader(
                    client, URI.create("https://randomuser.me/api/?results=10"));

            try (Reader input = source.read()) {

            }
        }


    }
}
