package youten.redo.jarserver.server;

import java.io.BufferedReader;
import java.io.IOException;

/** 車輪の再開発 */
public class IOUtil {
    /**
     * BufferedReader -> String
     *
     * @param reader
     * @return
     */
    public static String toString(BufferedReader reader) {
        if (reader == null) {
            throw new IllegalArgumentException("reader is null");
        }
        StringBuilder sb = new StringBuilder();
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            // ignore
        }
        return sb.toString();
    }

    private IOUtil() {
        // Util
    }
}
