package com.core.operations;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SortOperationImpl implements TextOperation{
    @Override
    public void runOperation(String inputFileName, String outputFileName) throws IOException {

        Path initialFile = Paths.get(inputFileName);
        Path reversedFile = Paths.get(outputFileName);

        try (Stream<String> lines = Files.lines(initialFile, StandardCharsets.UTF_8)) {

            List<String> sortedList = lines.sorted().collect(Collectors.toList());
            Files.write(reversedFile, sortedList, StandardOpenOption.CREATE);
        }
    }
}
