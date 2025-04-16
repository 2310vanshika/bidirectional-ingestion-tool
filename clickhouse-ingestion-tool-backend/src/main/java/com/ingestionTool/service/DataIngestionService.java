package com.ingestionTool.service;

import com.ingestionTool.model.FlatFileIngestionRequest;
import com.ingestionTool.model.IngestionRequest;
import com.ingestionTool.utils.CsvUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DataIngestionService {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DataIngestionService(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    // ✅ ClickHouse → Flat File
    public String ingestFromClickHouseToFile(IngestionRequest request, List<String> selectedColumns, String filePath, String delimiter) {
        String columns = String.join(",", selectedColumns);
        String query = String.format("SELECT %s FROM %s", columns, request.getTableName());

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(query);

        try {
            int writtenCount = CsvUtils.writeCsv(rows, selectedColumns, filePath, delimiter);
            return "Successfully wrote " + writtenCount + " records to Flat File.";
        } catch (IOException e) {
            throw new RuntimeException("Failed to write CSV file: " + e.getMessage());
        }
    }

    // ✅ Flat File → ClickHouse
    public String ingestFromFileToClickHouse(IngestionRequest request) {
        try {
            List<Map<String, String>> rows = CsvUtils.readCsv(request.getFileName(), request.getDelimiter(), request.getSelectedColumns());
            int count = 0;

            for (Map<String, String> row : rows) {
                String columnNames = String.join(",", request.getSelectedColumns());
                String values = row.values().stream()
                        .map(val -> "'" + val.replace("'", "''") + "'")
                        .collect(Collectors.joining(","));
                String insertQuery = String.format("INSERT INTO %s (%s) VALUES (%s)", crequest.getTableName(), columnNames, values);
                jdbcTemplate.execute(insertQuery);
                count++;
            }
            return "Successfully ingested " + count + " records into ClickHouse.";
        } catch (IOException e) {
            throw new RuntimeException("Failed to read CSV: " + e.getMessage());
        }
    }
}
