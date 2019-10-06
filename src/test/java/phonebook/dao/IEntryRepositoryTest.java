package phonebook.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import phonebook.checking.DBDriver;
import phonebook.config.HyperSQLconfig;
import phonebook.models.Entry;
import phonebook.utils.PropertyLoader;
import static org.junit.Assert.assertEquals;

/**
 * Class IEntryRepositoryTest tests IEntryRepository.
 *
 * @author Multiscripter
 * @version 2019-10-06
 * @since 2018-09-19
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {HyperSQLconfig.class})
@WebAppConfiguration
@EnableJpaRepositories("phonebook.dao")
public class IEntryRepositoryTest {
    /**
     * DB driver.
     */
    private static DBDriver driver;

    /**
     * Sql file path.
     */
    private static String path;

    /**
     * Entry repository.
     */
    @Autowired
    private IEntryRepository repo;

    /**
     * Actions after each test.
     * @throws java.lang.Exception exception.
     */
    @After
    public void afterTest() throws Exception  {
        driver.executeSqlScript(path);
    }

    /**
     * Actions after all tests.
     * @throws java.lang.Exception exception.
     */
    @AfterClass
    public static void afterAllTests() throws Exception {
        driver.close();
    }

    /**
     * Actions before all tests.
     * @throws java.lang.Exception exception.
     */
    @BeforeClass
    public static void beforeAllTests() throws Exception {
        ClassLoader loader = IEntryRepositoryTest.class.getClassLoader();
        path = loader.getResource(".").getPath();
        path = path.replaceFirst("^/(.:/)", "$1");
        String dbmsName = new PropertyLoader(String.format("%s%s", path, "activeDBMS.properties")).getPropValue("name");
        driver = new DBDriver(path + dbmsName);
        path = loader.getResource(String.format("phonebook.%s.sql", dbmsName))
                .getPath();
        path = path.replaceFirst("^/(.:/)", "$1");
    }

    /**
     * Actions before each test.
     * @throws java.lang.Exception exception.
     */
    @Before
    public void beforeEachTest() throws Exception {
        driver.executeSqlScript(path);
    }

    /**
     * Tests Iterable<Entry> findAll().
     * @throws java.lang.Exception exception.
     */
    @Test
    public void testFindAll() throws Exception {
        String query = "select * from phone_book";
        List<Entry> expected = this.getExpected(query);
        List<Entry> actual = repo.findAll();
        assertEquals(expected, actual);
    }

    /**
     * Tests List<Entry> findByLastName(String lastName).
     * @throws java.lang.Exception exception.
     */
    @Test
    public void testFindByLastName() throws Exception {
        String query = "select * from phone_book where last_name = 'Алексеев'";
        List<Entry> expected = this.getExpected(query);
        List<Entry> actual = repo.findByLastName("Алексеев");
        assertEquals(expected, actual);
    }

    /**
     * Tests List<Entry> findByNumber(String number);
     * @throws java.lang.Exception exception.
     */
    @Test
    public void testFindByNumber() throws Exception {
        String query = "select * from phone_book where number = 333333";
        List<Entry> expected = this.getExpected(query);
        List<Entry> actual = repo.findByNumber(333333);
        assertEquals(expected, actual);
    }

    /**
     * Gets list of expected entries.
     * @param query sql select query.
     * @return list of expected entries.
     * @throws java.lang.Exception exception.
     */
    private List<Entry> getExpected(final String query) throws Exception {
        List<HashMap<String, String>> result = driver.select(query);
        List<Entry> expected = new ArrayList<>();
        for (HashMap<String, String> item : result) {
            Entry entry = new Entry();
            entry.setId(Long.parseLong(item.get("id")));
            entry.setNumber(Integer.parseInt(item.get("number")));
            entry.setLastName(item.get("last_name"));
            entry.setFirstName(item.get("first_name"));
            entry.setMiddleName(item.get("middle_name"));
            entry.setAddress(item.get("address"));
            expected.add(entry);
        }
        return expected;
    }
}
