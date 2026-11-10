package software.ulpgc.katas;

import software.ulpgc.katas.io.adapters.HttpRandomUserReader;
import software.ulpgc.katas.io.adapters.JsonRandomUserDeserializer;
import software.ulpgc.katas.model.User;
import software.ulpgc.katas.model.UserExport;

import java.io.IOException;
import java.io.Reader;
import java.net.URI;
import java.net.http.HttpClient;

public class Main {
    static void main() throws IOException {
        try (HttpClient client = HttpClient.newHttpClient()){
            HttpRandomUserReader source = new HttpRandomUserReader(client, URI.create("https://randomuser.me/api/?results=10"));

            UserExport export;

            try (Reader input = source.read()) {
                export = new JsonRandomUserDeserializer().deserialize(input);
            }

            for (User user: export.users()){
                System.out.println(user);
            }

        }

    }
}
