package com.example.consumerbankgiro;

import org.springframework.boot.jdbc.DataSourceBuilder;

import javax.sql.DataSource;


public class MyDataSourceBuilder {
    DataSourceBuilder<?> dataSourceBuilder;

    MyDataSourceBuilder() {
        this.dataSourceBuilder = DataSourceBuilder.create();
    }

    public MyDataSourceBuilder withUsername(String username) {
        dataSourceBuilder.username(username);
        return this;
    }

    public MyDataSourceBuilder withPassword(String password) {
        dataSourceBuilder.password(password);
        return this;
    }

    public MyDataSourceBuilder withDriverName(String driverName) {
        dataSourceBuilder.driverClassName(driverName);
        return this;
    }

    public MyDataSourceBuilder withUrl(String url) {
        dataSourceBuilder.url(url);
        return this;
    }

    public DataSource build() {
        return dataSourceBuilder.build();
    }

    public static MyDataSourceBuilder create() {
        return new MyDataSourceBuilder();
    }
}