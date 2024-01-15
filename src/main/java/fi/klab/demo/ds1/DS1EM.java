package fi.klab.demo.ds1;

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
  basePackageClasses = Repository1.class,
  entityManagerFactoryRef = "ds1EntityManagerFactory",
  transactionManagerRef = "ds1TransactionManager"
)
public class DS1EM {

    @Primary
    @Bean(name="ds1EntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean ds1EntityManagerFactory(
    @Qualifier("dataSource1a") @NonNull DataSource dataSource ){

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
    public PlatformTransactionManager ds1TransactionManager(
      @Qualifier("ds1EntityManagerFactory") LocalContainerEntityManagerFactoryBean ds1EntityManagerFactory) {
        return new JpaTransactionManager(Objects.requireNonNull(ds1EntityManagerFactory.getObject()));
      }
}
