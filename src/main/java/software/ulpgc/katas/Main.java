package software.ulpgc.katas;

import software.ulpgc.katas.io.adapters.GsonUserJsonSerializer;
import software.ulpgc.katas.io.adapters.HttpRandomUserReader;
import software.ulpgc.katas.io.adapters.JsonRandomUserDeserializer;
import software.ulpgc.katas.model.User;
import software.ulpgc.katas.model.UserExport;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Reader;
import java.net.URI;
import java.net.http.HttpClient;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    static void main() throws IOException {
        try (HttpClient client = HttpClient.newHttpClient()){
            HttpRandomUserReader source = new HttpRandomUserReader(client, URI.create("https://randomuser.me/api/?results=10"));

            UserExport export;

            try (Reader input = source.read()) {
                export = new JsonRandomUserDeserializer().deserialize(input);
            }

            try (OutputStream output = Files.newOutputStream(Path.of("users.json"))) {
                new GsonUserJsonSerializer().serialize(export, output);
            }

            for (User user: export.users()){
                System.out.println(user);
            }

        }

    }
}
