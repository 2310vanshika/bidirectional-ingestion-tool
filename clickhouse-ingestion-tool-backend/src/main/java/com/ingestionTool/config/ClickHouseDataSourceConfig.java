package com.ingestionTool.config;

import com.clickhouse.jdbc.ClickHouseDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

import java.sql.SQLException;
import java.util.Properties;

@Configuration
public class ClickHouseDataSourceConfig {

    @Value("${clickhouse.url}")
    private String url;

    @Value("${clickhouse.user}")
    private String user;

    @Value("${clickhouse.password}")
    private String password;

    @Bean
    public DataSource dataSource() {
        Properties props = new Properties();
        props.setProperty("user", user);
        props.setProperty("password", password);
        try {
			return new ClickHouseDataSource(url, props);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
    }
}
