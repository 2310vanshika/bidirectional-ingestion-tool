package com.ingestionTool.model;

import java.util.List;
public class IngestionRequest {
    private String tableName;
    private String fileName;
    private String delimiter;
    private List<String> selectedColumns;

    // Getters
    public String getTableName() { return tableName; }
    public String getFileName() { return fileName; }
    public String getDelimiter() { return delimiter; }
    public List<String> getSelectedColumns() { return selectedColumns; }

    // Setters (optional depending on how you're using this)
    public void setTableName(String tableName) { this.tableName = tableName; }
    public void setFileName(String fileName) { this.fileName = fileName; }
    public void setDelimiter(String delimiter) { this.delimiter = delimiter; }
    public void setSelectedColumns(List<String> selectedColumns) { this.selectedColumns = selectedColumns; }
}

