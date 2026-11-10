package software.ulpgc.katas.io.ports;

import software.ulpgc.katas.model.UserExport;


import java.io.Reader;

public interface UserDeserialize {
    UserExport deserialize(Reader reader);
}
