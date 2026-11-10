package software.ulpgc.katas.io.ports;

import software.ulpgc.katas.model.UserExport;

import java.io.IOException;
import java.io.OutputStream;

public interface UserJsonSerializer {
    void serialize(UserExport export, OutputStream output) throws IOException;
}
