package com.ingestionTool.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/upload")
public class FlatFileUploadController {

    @PostMapping("/flatfile")
    public ResponseEntity<Map<String, Object>> uploadFlatFile(@RequestParam("file") MultipartFile file) {
        // TODO: Replace with actual parsing logic
        List<String> columns = List.of("column1", "column2", "column3");
        return ResponseEntity.ok(Map.of("columns", columns));
    }
}
