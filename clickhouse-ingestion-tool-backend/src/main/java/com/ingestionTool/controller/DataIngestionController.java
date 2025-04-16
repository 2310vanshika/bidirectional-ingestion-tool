package com.ingestionTool.controller;

import com.ingestionTool.model.IngestionRequest;

import com.ingestionTool.service.DataIngestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/ingestion")
public class DataIngestionController {

    @Autowired
    private DataIngestionService dataIngestionService;

    @PostMapping("/clickhouse-to-file")
    public String clickhouseToFile(@RequestBody IngestionRequest request) {
        // Process the ingestion from ClickHouse to Flat File
        return dataIngestionService.ingestFromClickHouseToFile(request, null, null, null);
    }

    @PostMapping("/file-to-clickhouse")
    public String fileToClickhouse(@RequestBody IngestionRequest request) {
        // Process the ingestion from Flat File to ClickHouse
        return dataIngestionService.ingestFromFileToClickHouse(request);
    }
}

