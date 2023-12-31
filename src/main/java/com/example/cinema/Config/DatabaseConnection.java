package com.example.cinema.Config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import javax.sql.DataSource;


@Configuration

public class DatabaseConnection {

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/cinema");
        String username = System.getenv("USERNAME");
        String password = System.getenv("PASSWORD");

        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }
}
