package phonebook.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Собирать командой: mvn clean package
 * Запускать командой: java -jar ./target/%локальное_имя_файла%.war
 * По умолчанию контекст приложения - это корень вэб-вервера.
 * Поэтому приложение открывается по адресу http://localhost:8080/
 * @author Multiscripter
 * @version 2019-10-01
 * @since 2017-10-01
 */
@SpringBootApplication
@ComponentScan(basePackages = "phonebook.*")
@EntityScan("phonebook.models")
@EnableJpaRepositories("phonebook.services")
public class Application {
    /**
     * Главный метод. Точка входа в приложение.
     * @param args массив аргументов запуска.
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
