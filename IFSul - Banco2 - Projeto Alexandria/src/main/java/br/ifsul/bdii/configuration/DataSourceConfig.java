package br.ifsul.bdii.configuration;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSourceConfig {
    
    @Bean
    @ConfigurationProperties("app.datasource")
    DataSource dataSource() {
        @SuppressWarnings("rawtypes")
        DataSourceBuilder dsBuilder = DataSourceBuilder.create();
            dsBuilder.driverClassName("com.mysql.cj.jdbc.Driver");
            dsBuilder.url("jdbc:mysql://localhost:3306/biblio_alexandria");
            dsBuilder.username("root");
            return dsBuilder.build();       
    }
}
