package fi.klab.demo.ds2;

import java.util.Objects;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.lang.NonNull;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
  basePackageClasses = Repository2.class,
  entityManagerFactoryRef = "ds2EntityManagerFactory",
  transactionManagerRef = "ds2TransactionManager"
)
public class DS2EM {
    
    @Primary
    @Bean(name="ds2EntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean ds2EntityManagerFactory(
    @Qualifier("dataSource2a") @NonNull DataSource dataSource ){
        
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan("fi.klab.demo");
        HibernateJpaVendorAdapter vendorAdapter
          = new HibernateJpaVendorAdapter();
          
        vendorAdapter.setShowSql(true);
        vendorAdapter.setGenerateDdl(true);
        em.setJpaVendorAdapter(vendorAdapter);
        return em;

    }

    @Bean
    public PlatformTransactionManager ds2TransactionManager(
      @Qualifier("ds2EntityManagerFactory") LocalContainerEntityManagerFactoryBean ds2EntityManagerFactory) {
        return new JpaTransactionManager(Objects.requireNonNull(ds2EntityManagerFactory.getObject()));
      }

}
