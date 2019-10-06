package phonebook.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Build with command: mvn clean package
 * Run with command: java -jar ./target/%локальное_имя_файла%.war
 * By default, the application context is the root of the web server.
 * Therefore, the application opens at http://localhost:8080/
 *
 * @author Multiscripter
 * @version 2019-10-01
 * @since 2017-10-01
 */
@SpringBootApplication
@ComponentScan(basePackages = "phonebook.*")
@EntityScan("phonebook.models")
@EnableJpaRepositories("phonebook.dao")
public class Application {
    /**
     * The main method. Application Entry Point.
     * @param args array of startup arguments.
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
