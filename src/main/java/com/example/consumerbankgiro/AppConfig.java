package com.example.consumerbankgiro;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Configuration
@Profile("localhost")
public class AppConfig {


    @Bean
    DataSource databaseBuilder() {
        return MyDataSourceBuilder.create()
                .withUrl("jdbc:postgresql://localhost:5432/transactions")
                .withUsername("postgres")
                .withPassword("root")
                .withDriverName("org.postgresql.Driver")
                .build();
    }

}


