package com.ingestionTool.utils;

import java.io.*;
import java.util.*;

public class CsvUtils {

    public static List<Map<String, String>> readCsv(String filePath, String delimiter, List<String> columns) throws IOException {
        List<Map<String, String>> rows = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String[] headers = br.readLine().split(delimiter);
            int[] columnIndices = Arrays.stream(columns.toArray(new String[0]))
                                        .mapToInt(col -> Arrays.asList(headers).indexOf(col))
                                        .toArray();
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(delimiter);
                Map<String, String> row = new HashMap<>();
                for (int i = 0; i < columnIndices.length; i++) {
                    row.put(columns.get(i), values[columnIndices[i]]);
                }
                rows.add(row);
            }
        }
        return rows;
    }

    public static int writeCsv(List<Map<String, Object>> data, List<String> columns, String filePath, String delimiter) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            bw.write(String.join(delimiter, columns));
            bw.newLine();
            for (Map<String, Object> row : data) {
                List<String> line = new ArrayList<>();
                for (String col : columns) {
                    line.add(String.valueOf(row.getOrDefault(col, "")));
                }
                bw.write(String.join(delimiter, line));
                bw.newLine();
            }
        }
        return data.size();
    }
}
