package rockpaperscissors;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

public class ScoreRepository implements AutoCloseable {

    private final File file;

    public ScoreRepository(File file, String name) {
        this.file = file;
        try {
            if (!this.file.isFile()) {
                boolean created = file.createNewFile();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() {
    }

    public void putScore(String playerName,
                         UnaryOperator<Integer> reducer) {
        findAndProcessScoreLine(playerName, (accessFile, line, idx) -> {
            if (line == null) {
                accessFile.write(String.format(
                        "%s %d%n",
                        playerName,
                        reducer.apply(0)).getBytes(StandardCharsets.UTF_8)
                );
                return;
            }

            String[] stringTokens = line.split(" ");
            Integer currentScore = Integer.parseInt(stringTokens[1]);
            stringTokens[1] = reducer.apply(currentScore).toString();
            accessFile.seek(idx);
            String toSave = Arrays.stream(stringTokens)
                    .collect(Collectors.joining(" "));
            toSave = toSave + "\n";
            accessFile.write(toSave.getBytes(StandardCharsets.UTF_8));
        });
    }

    private void findAndProcessScoreLine(String playerName, FoundFileLineCallback callback) {
        try (var accessFile = new RandomAccessFile(this.file, "rw")) {
            String line;
            int idx = 0;
            while ((line = accessFile.readLine()) != null) {
                if (line.contains(playerName)) {
                    callback.processLine(accessFile, line, idx);
                    return;
                }
                idx += line.length() + 1;
            }
            callback.processLine(accessFile, line, idx);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int getScore(String playerName) {
        AtomicInteger score = new AtomicInteger();
        findAndProcessScoreLine(playerName, (accessFile, line, idx) -> {
            int storedScore = 0;
            if (line != null) {
                String[] stringTokens = line.split(" ");
                storedScore = Integer.parseInt(stringTokens[1]);
            }
            score.set(storedScore);
        });
        return score.get();
    }
}
