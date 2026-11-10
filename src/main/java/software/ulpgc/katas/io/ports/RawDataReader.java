package software.ulpgc.katas.io.ports;

import java.io.IOException;
import java.io.Reader;

public interface RawDataReader {
    Reader read() throws IOException;

}
