////package com.ingestionTool.controller;
////
////import org.springframework.http.ResponseEntity;
////import org.springframework.web.bind.annotation.*;
////import java.util.*;
////import com.ingestionTool.model.ClickHouseRequest;
////
////@CrossOrigin(origins = "http://localhost:3000")
////@RestController
////@RequestMapping("/connect")
////public class ClickHouseController {
////
////  @PostMapping("/clickhouse")
////  public ResponseEntity<Map<String, Object>> connectToClickHouse(@RequestBody ClickHouseRequest request) {
////      // Replace with real logic to connect to ClickHouse and fetch columns
////      List<String> columns = List.of("id", "name", "email");
////      return ResponseEntity.ok(Map.of("columns", columns));
////  }
////}
//
//package com.ingestionTool.controller;
//
//import com.ingestionTool.model.ClickHouseRequest;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.sql.*;
//import java.util.*;
//
//@CrossOrigin(origins = "http://localhost:3000")
//@RestController
//@RequestMapping("/connect")
//public class ClickHouseController {
//
//    private final String clickhouseUrl = "jdbc:clickhouse://localhost:8123/default";
//    private final String clickhouseUser = "default";
//    private final String clickhousePassword = "";
//
//    // ✅ Existing endpoint (preserved)
//    @PostMapping("/clickhouse")
//    public ResponseEntity<Map<String, Object>> connectToClickHouse(@RequestBody ClickHouseRequest request) {
//        List<String> columns = List.of("id", "name", "email");
//        return ResponseEntity.ok(Map.of("columns", columns));
//    }
//
//    // ✅ Get list of all ClickHouse tables
//    @GetMapping("/clickhouse/tables")
//    public ResponseEntity<List<String>> getTables() throws SQLException {
//        List<String> tables = new ArrayList<>();
//        try (Connection conn = DriverManager.getConnection(clickhouseUrl, clickhouseUser, clickhousePassword);
//             ResultSet rs = conn.getMetaData().getTables(null, null, "%", new String[]{"TABLE"})) {
//            while (rs.next()) {
//                tables.add(rs.getString("TABLE_NAME"));
//            }
//        }
//        return ResponseEntity.ok(tables);
//    }
//
//    // ✅ Get columns for selected tables
//    @PostMapping("/clickhouse/columns")
//    public ResponseEntity<Map<String, List<String>>> getColumnsForTables(@RequestBody List<String> tables) throws SQLException {
//        Map<String, List<String>> tableColumns = new HashMap<>();
//        try (Connection conn = DriverManager.getConnection(clickhouseUrl, clickhouseUser, clickhousePassword)) {
//            for (String table : tables) {
//                List<String> columns = new ArrayList<>();
//                try (PreparedStatement stmt = conn.prepareStatement("DESCRIBE TABLE " + table);
//                     ResultSet rs = stmt.executeQuery()) {
//                    while (rs.next()) {
//                        columns.add(rs.getString(1));
//                    }
//                }
//                tableColumns.put(table, columns);
//            }
//        }
//        return ResponseEntity.ok(tableColumns);
//    }
//
//    // ✅ Preview 100 rows from JOIN or custom query
//    @PostMapping("/clickhouse/preview")
//    public ResponseEntity<List<Map<String, Object>>> previewClickHouseData(@RequestBody Map<String, Object> request) throws SQLException {
//        String query = (String) request.get("query");
//        List<Map<String, Object>> preview = new ArrayList<>();
//
//        try (Connection conn = DriverManager.getConnection(clickhouseUrl, clickhouseUser, clickhousePassword);
//             PreparedStatement stmt = conn.prepareStatement(query + " LIMIT 100");
//             ResultSet rs = stmt.executeQuery()) {
//
//            ResultSetMetaData meta = rs.getMetaData();
//            int columnCount = meta.getColumnCount();
//
//            while (rs.next()) {
//                Map<String, Object> row = new HashMap<>();
//                for (int i = 1; i <= columnCount; i++) {
//                    row.put(meta.getColumnLabel(i), rs.getObject(i));
//                }
//                preview.add(row);
//            }
//        }
//        return ResponseEntity.ok(preview);
//    }
//}
//
package com.ingestionTool.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

import com.ingestionTool.model.ClickHouseRequest;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/connect")
public class ClickHouseController {

    @PostMapping("/clickhouse")
    public ResponseEntity<Map<String, Object>> connectToClickHouse(@RequestBody ClickHouseRequest request) {
        List<String> columns = List.of("id", "name", "email"); // Simulated
        return ResponseEntity.ok(Map.of("columns", columns));
    }

    // ✅ New: Return list of available tables
    @PostMapping("/tables")
    public ResponseEntity<Map<String, Object>> getTables(@RequestBody ClickHouseRequest request) {
        List<String> tables = List.of("users", "orders", "products"); // Simulated
        return ResponseEntity.ok(Map.of("tables", tables));
    }

    // ✅ New: Return columns for selected tables
    @PostMapping("/columns")
    public ResponseEntity<Map<String, Object>> getColumns(@RequestBody Map<String, Object> payload) {
        List<String> tables = (List<String>) payload.get("tables");

        // Simulated: For each table, return fake columns
        List<Map<String, String>> columns = new ArrayList<>();
        for (String table : tables) {
            columns.add(Map.of("table", table, "name", "id"));
            columns.add(Map.of("table", table, "name", "name"));
        }
        return ResponseEntity.ok(Map.of("columns", columns));
    }

    // ✅ New: Return preview (e.g. first 100 rows) based on join query
    @PostMapping("/preview")
    public ResponseEntity<Map<String, Object>> previewData(@RequestBody Map<String, Object> payload) {
        String joinQuery = (String) payload.get("joinQuery");

        // Simulated data preview
        List<Map<String, Object>> preview = List.of(
                Map.of("id", 1, "name", "Alice", "email", "alice@example.com"),
                Map.of("id", 2, "name", "Bob", "email", "bob@example.com")
        );
        return ResponseEntity.ok(Map.of("preview", preview));
    }
}

