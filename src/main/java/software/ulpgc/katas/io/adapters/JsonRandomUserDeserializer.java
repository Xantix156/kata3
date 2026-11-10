package software.ulpgc.katas.io.adapters;

import software.ulpgc.katas.io.ports.UserDeserialize;
import software.ulpgc.katas.model.UserExport;

import java.io.Reader;

public class JsonRandomUserDeserializer implements UserDeserialize {
    @Override
    public UserExport deserialize(Reader reader){
        return null;
    }
}
