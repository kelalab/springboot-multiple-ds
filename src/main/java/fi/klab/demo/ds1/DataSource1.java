package fi.klab.demo.ds1;

import java.util.Map;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSource1 {
    
    @Bean(name="datasource1properties")
    public DataSourceProperties getDataSourceProperties() {
        Map<String, String> env = System.getenv();
        DataSourceProperties dsProperties = new DataSourceProperties();
        dsProperties.setDriverClassName("org.postgresql.Driver");
        dsProperties.setUrl("jdbc:postgresql://"+env.get("DATASOURCE_1")+"/");
        dsProperties.setName("data1");
        dsProperties.setPassword("example");
        dsProperties.setUsername("postgres");
        /*Map<String, String> env = System.getenv();
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("org.postgresql.Driver");
        dataSourceBuilder.url("jdbc:postgresql://"+env.get("DATASOURCE_1"));
        dataSourceBuilder.username("postgres");
        dataSourceBuilder.password("example");
        return dataSourceBuilder.build();*/
        return dsProperties;
    }

    @Bean(name="dataSource1a")
    public DataSource dataSource1() {
        return getDataSourceProperties()
          .initializeDataSourceBuilder()
          .build();
    }
}