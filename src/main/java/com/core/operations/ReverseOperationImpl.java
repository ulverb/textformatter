package com.core.operations;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ReverseOperationImpl implements TextOperation {
    @Override
    public void runOperation(String inputFileName, String outputFileName) throws IOException {

        Path initialFile = Paths.get(inputFileName);
        Path reversedFile = Paths.get(outputFileName);

        try (Stream<String> lines = Files.lines(initialFile, StandardCharsets.UTF_8)) {

            List<String> reversedList = lines.collect(Collectors.toList());
            Collections.reverse(reversedList);
            Files.write(reversedFile, reversedList, StandardOpenOption.CREATE);
        }
    }
}