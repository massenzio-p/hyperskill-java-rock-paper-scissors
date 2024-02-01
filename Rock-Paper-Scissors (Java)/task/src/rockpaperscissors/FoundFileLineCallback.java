package rockpaperscissors;

import java.io.IOException;
import java.io.RandomAccessFile;

@FunctionalInterface
public interface FoundFileLineCallback {

    void processLine(RandomAccessFile accessFile, String line, int idx) throws IOException;
}
