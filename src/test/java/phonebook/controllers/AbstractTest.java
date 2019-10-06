package phonebook.controllers;

import java.io.IOException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import phonebook.boot.Application;
import phonebook.checking.DBDriver;
import phonebook.config.HyperSQLconfig;
import phonebook.config.WebConfig;
import phonebook.utils.PropertyLoader;

/**
 * Class AbstractTest realize common test methods.
 *
 * @author Multiscripter
 * @version 2019-10-05
 * @since 2018-10-05
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest(classes = {Application.class, HyperSQLconfig.class})
@WebMvcTest(controllers = {Router.class})
@ContextConfiguration(classes = {WebConfig.class, HyperSQLconfig.class})
@WebAppConfiguration
@ActiveProfiles("HyperSQL")
public abstract class AbstractTest {
    /**
     * DB driver.
     */
    protected static DBDriver driver;

    /**
     * MockMvc.
     */
    protected MockMvc mvc;

    /**
     * Sql file path.
     */
    private static String path;

    /**
     * Web application context.
     */
    @Autowired
    protected WebApplicationContext webApplicationContext;

    /**
     * Actions after test.
     * @throws Exception exception.
     */
    @After
    public void afterTest() throws Exception  {
        driver.executeSqlScript(path);
    }

    /**
     * Action before test.
     * @throws Exception exception.
     */
    @Before
    public void beforeTest() throws Exception {
        this.mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        driver.executeSqlScript(path);
    }

    /**
     * Actions before all tests.
     * @throws Exception exception.
     */
    @BeforeClass
    public static void beforeAllTests() throws Exception {
        ClassLoader loader = AbstractTest.class.getClassLoader();
        path = loader.getResource(".").getPath();
        path = path.replaceFirst("^/(.:/)", "$1");
        String dbmsName = new PropertyLoader(String.format("%s%s", path, "activeDBMS.properties")).getPropValue("name");
        driver = new DBDriver(path + dbmsName);
        path = loader.getResource(String.format("phonebook.%s.sql", dbmsName))
                .getPath();
        path = path.replaceFirst("^/(.:/)", "$1");
        driver.executeSqlScript(path);
    }

    /**
     * Converts the Java object into JSON string.
     * @param obj the Java object.
     * @return JSON string.
     * @throws JsonProcessingException Json exception.
     */
    protected String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }

    /**
     * Converts the JSON string into Java object.
     * @param json JSON string.
     * @param clazz Java class
     * @param <T> target type.
     * @return Java object.
     * @throws JsonParseException Json parse exception.
     * @throws JsonMappingException Json mapping exception.
     * @throws IOException I/O exception.
     */
    protected <T> T mapFromJson(String json, Class<T> clazz)
            throws JsonParseException, JsonMappingException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, clazz);
    }
}
