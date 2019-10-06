package phonebook.config;

import java.util.Properties;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

/**
 * Production DB configuration.
 *
 * @author Multiscripter
 * @version 2019-10-06
 * @since 2017-10-01
 */
@Configuration
public class DbConfig {
    @Bean
    DataSource datasource() {
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
}
