package phonebook.config;

import java.util.Properties;
import javax.sql.DataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
public class DbConfig {

    @Bean(name = "MySQLdatasource")
    @Profile("MySQL")
    DataSource getMySQLdataSource() {
        Properties properties = new Properties();
        properties.setProperty("useUnicode", "yes");
        properties.setProperty("characterEncoding", "UTF-8");
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/phone_book");
        dataSource.setUsername("root");
        dataSource.setPassword("mysqlrootpass");
        dataSource.setConnectionProperties(properties);
        DatabasePopulatorUtils.execute(createDatabasePopulator(), dataSource);
        return dataSource;
    }

    private DatabasePopulator createDatabasePopulator() {
        ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
        databasePopulator.addScript(new ClassPathResource("phonebook.MySQL.sql"));
        return databasePopulator;
    }

    /*private DataSource createDataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.type(org.apache.tomcat.jdbc.pool.DataSource.class);
        dataSourceBuilder.driverClassName("com.mysql.cj.jdbc.Driver");
        dataSourceBuilder.url("jdbc:mysql://localhost:3306/phone_book");
        dataSourceBuilder.username("root");
        dataSourceBuilder.password("mysqlrootpass");
        return dataSourceBuilder.build();
    }

    private Properties additionalProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        return properties;
    }

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em
                = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(this.getMySQLdataSource());
        em.setPackagesToScan("phonebook.models");
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(this.additionalProperties());
        return em;
    }*/
}
