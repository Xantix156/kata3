package software.ulpgc.katas.io.adapters.Dtos;

import software.ulpgc.katas.io.ports.UserDeserializer;
import software.ulpgc.katas.model.UserExport;

import java.io.Reader;

public class JsonRandomUserDeserializer implements UserDeserializer {


    @Override
    public UserExport deserialize(Reader reader) {
        return null;
    }
}
