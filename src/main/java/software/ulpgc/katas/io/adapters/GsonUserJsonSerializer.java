package software.ulpgc.katas.io.adapters;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonPrimitive;
import software.ulpgc.katas.io.ports.UserJsonSerializer;
import software.ulpgc.katas.model.UserExport;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Map;

public class GsonUserJsonSerializer implements UserJsonSerializer {
    private final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .registerTypeAdapter(Instant.class, (com.google.gson.JsonSerializer<Instant>)
                    (value, type, context) -> new JsonPrimitive(value.toString()))
            .create();

    @Override
    public void serialize(UserExport export, OutputStream output) throws IOException {
        OutputStreamWriter writer = new OutputStreamWriter(output, StandardCharsets.UTF_8);
        gson.toJson(Map.of(
                "users", export.users(),
                "metadata", Map.of(
                        "validRecords", export.users().size(),
                        "discardedRecords", export.discardedRecords()
                )), writer);
        writer.flush();
    }
}
