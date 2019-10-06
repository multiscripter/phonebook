package phonebook.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * Class HyperSQLconfig contains HyperSQL configuration.
 *
 * @author Multiscripter
 * @version 2019-10-06
 * @since 2018-10-06
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "entityManagerFactory",
        transactionManagerRef = "HyperSQLTransactionManager"
)
public class HyperSQLconfig {
    @Bean(name = "HyperSQLdatasource")
    @Profile("HyperSQL")
    public DataSource getHyperSQLdataSource() {
        Properties properties = new Properties();
        //properties.setProperty("useUnicode", "yes");
        //properties.setProperty("characterEncoding", "UTF-8");
        properties.setProperty("get_column_name", "false");
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.hsqldb.jdbc.JDBCDriver");
        dataSource.setUrl("jdbc:hsqldb:mem:phone_book");
        dataSource.setUsername("SA");
        dataSource.setPassword("");
        dataSource.setConnectionProperties(properties);
        DatabasePopulatorUtils.execute(createDatabasePopulator(), dataSource);
        return dataSource;
    }

    @Bean(name="entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em
                = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(this.getHyperSQLdataSource());
        em.setPackagesToScan("phonebook");
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(false);
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(this.additionalProperties());
        return em;
    }

    @Bean(name = "transactionManager")
    public PlatformTransactionManager getHyperSQLTransactionManager(
            @Qualifier("entityManagerFactory") EntityManagerFactory
                    barEntityManagerFactory
    ) {
        return new JpaTransactionManager(barEntityManagerFactory);
    }

    private DatabasePopulator createDatabasePopulator() {
        ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
        databasePopulator.addScript(new ClassPathResource("phonebook.HyperSQL.sql"));
        return databasePopulator;
    }

    private Properties additionalProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.HSQLDialect");
        return properties;
    }
}
