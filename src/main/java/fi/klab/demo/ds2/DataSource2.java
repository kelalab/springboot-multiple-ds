package fi.klab.demo.ds2;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSource2 {
    
    @Bean(name="datasource2properties")
    public DataSourceProperties getDataSourceProperties() {
        System.out.println("in getDataSource");
        Map<String, String> env = System.getenv();
        DataSourceProperties dsProperties = new DataSourceProperties();
        dsProperties.setDriverClassName("org.postgresql.Driver");
        dsProperties.setUrl("jdbc:postgresql://"+env.get("DATASOURCE_2")+"/");
        dsProperties.setName("data2");
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

    @Bean(name="dataSource2a")
    public DataSource dataSource2() {
        return getDataSourceProperties()
          .initializeDataSourceBuilder()
          .build();
    }

}
