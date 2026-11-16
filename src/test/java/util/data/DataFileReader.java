package util.data;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.junit.jupiter.params.provider.Arguments;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class DataFileReader {
    public static Stream<Arguments> readCSV(String path, String... fields) {
        try (
                Reader reader = Files.newBufferedReader(Path.of(path), StandardCharsets.UTF_8);
                CSVParser parser = CSVFormat.DEFAULT
                        .builder()
                        .setHeader()
                        .setSkipHeaderRecord(true)
                        .get()
                        .parse(reader)
                ) {
            List<Arguments> rows = parser.getRecords().stream()
                    .map(row -> Arguments.of(
                            Arrays.stream(fields).map(row::get).toList()
                    ))
                    .toList();

            return rows.stream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
