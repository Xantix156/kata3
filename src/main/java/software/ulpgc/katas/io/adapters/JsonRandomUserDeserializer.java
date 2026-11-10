package software.ulpgc.katas.io.adapters;

import com.google.gson.*;
import software.ulpgc.katas.io.adapters.Dtos.UserDto;
import software.ulpgc.katas.io.ports.UserDeserializer;
import software.ulpgc.katas.model.Gender;
import software.ulpgc.katas.model.User;
import software.ulpgc.katas.model.UserExport;

import java.io.Reader;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class JsonRandomUserDeserializer implements UserDeserializer {


    @Override
    public UserExport deserialize(Reader reader) {
        try {
            JsonObject root = JsonParser.parseReader(reader).getAsJsonObject();
            return deserialize(root.getAsJsonArray("results"));
        } catch (RuntimeException exception) {
            return new UserExport(List.of(), 1);
        }
    }

    private UserExport deserialize(JsonArray array) {
        List<User> users = new ArrayList<>();
        int discardedRecords = 0;
        for (JsonElement item : array) {
            try {
                users.add(deserialize(item));
            } catch (RuntimeException exception) {
                discardedRecords++;
            }
        }
        return new UserExport(users, discardedRecords);
    }

    private User deserialize(JsonElement item) {
        return deserialize(new Gson().fromJson(item, UserDto.class));
    }

    private User deserialize(UserDto user) {
        if (user == null || user.name == null || user.dob == null || isBlank(user.name.title)
                || isBlank(user.name.first) || isBlank(user.name.last) || isBlank(user.gender) || isBlank(user.dob.date)) {
            throw new IllegalArgumentException("Incomplete user");
        }
        return new User(
                String.join(" ", user.name.title, user.name.first, user.name.last),
                Gender.valueOf(user.gender),
                Instant.parse(user.dob.date)
        );
    }

    private boolean isBlank(String value) {
        return value == null || value.isBlank();
    }
}
