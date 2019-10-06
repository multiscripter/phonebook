package phonebook.checking;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.NoSuchFileException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
//import org.junit.Ignore;
import org.junit.Test;
import phonebook.controllers.AbstractTest;
import phonebook.utils.PropertyLoader;

/**
 * Class DBDriverTest tests class DBDriver.
 *
 * @author Multiscripter
 * @version 2019-10-06
 * @since 2018-03-09
 */
public class DBDriverTest {
    /**
     * DBMS driver.
     */
    private static DBDriver driver;

    /**
     * Local file name with sql.
     */
    private static String sqlScriptName;

    /**
     * Actions before all tests.
     * @throws Exception exception.
     */
    @BeforeClass
    public static void beforeAllTests() throws Exception {
        // H2 | HyperSQL | PostgreSQL
        /*
        if (db.equals("H2")) {
            // http://www.h2database.com/html/features.html#in_memory_databases
            // В H2 алиасы по умолчанию могут быть выкючены.
            // http://www.h2database.com/html/faq.html#column_names_incorrect
            url = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
            user = "sa";
            pass = "";
        } else if (db.equals("HyperSQL")) {
            url = "jdbc:hsqldb:mem:jpack3p1ch1task0;get_column_name=false;ifexists=true";
            user = "SA";
            pass = "";
        } else if (db.equals("PostgreSQL")) {
            url = "jdbc:postgresql://localhost:5432/jpack3p1ch1task0";
            user = "postgres";
            pass = "postgresrootpass";
        }*/
        /**
         * Gets the absolute path to the resource folder.
         * If uses Maven: target/test-classes/
         */
        ClassLoader loader = AbstractTest.class.getClassLoader();
        String path = loader.getResource(".").getPath();
        path = path.replaceFirst("^/(.:/)", "$1");
        String dbmsName = new PropertyLoader(String.format("%s%s", path, "activeDBMS.properties")).getPropValue("name");
        driver = new DBDriver(path + dbmsName);
        path = loader.getResource(String.format("phonebook.%s.sql", dbmsName))
                .getPath();
        sqlScriptName = path.replaceFirst("^/(.:/)", "$1");
    }

    /**
     * Actions before each test.
     * @throws Exception exception.
     */
    @Before
    public void beforeEachTest() throws Exception {
        driver.executeSqlScript(sqlScriptName);
    }

    /**
     * Tests public int delete(String query) throws SQLException.
     * @throws Exception exception.
     */
    @Test
    public void testDelete() throws Exception {
        int affected = driver.delete("delete from phone_book");
        assertEquals(6, affected);
    }

    /**
     * Tests public int delete(String query) throws SQLException.
     * Explicit connection setup.
     * @throws Exception exception.
     */
    @Test
    public void testDeleteConnectionEstablished() throws Exception {
        driver.setConnection();
        int affected = driver.delete("delete from phone_book");
        assertEquals(6, affected);
    }

    /**
     * Tests public int delete(String query) throws SQLException.
     * Connection closed.
     * @throws Exception exception.
     */
    @Test
    public void testDeleteConnectionClosed() throws Exception {
        driver.close();
        int affected = driver.delete("delete from phone_book");
        assertEquals(6, affected);
    }

    /**
     * Tests public int delete(String query) throws SQLException.
     * Выброс SQLException.
     * @throws SQLException exception (java.sql.SQLException).
     */
    @Test(expected = SQLException.class)
    public void testDeleteThrowsSQLException() throws SQLException {
        driver.delete("delete from zzzz");
    }

    /**
     * Tests public void executeSql(String query) throws SQLException.
     * @throws Exception exception.
     */
    @Test
    public void testExecuteSql() throws Exception {
        boolean result = driver.executeSql("select * from phone_book");
        assertTrue(result);
    }

    /**
     * Tests public void executeSql(String query) throws SQLException.
     * Выброс SQLException.
     * @throws SQLException exception (java.sql.SQLException).
     */
    @Test(expected = SQLException.class)
    public void testExecuteSqlThrowsSQLException() throws SQLException {
        driver.executeSql("select * from test_table");
    }

    /**
     * Tests public int[] executeSqlScript(String name) throws IOException, NullPointerException, SQLException, URISyntaxException.
     * Выброс NoSuchFileException.
     * @throws NoSuchFileException exception "No such file".
     */
    @Test(expected = NoSuchFileException.class)
    public void testExecuteSqlScriptThrowsNoSuchFileException() throws NoSuchFileException {
        try {
            driver.executeSqlScript("junior.pack3.p1.ch1.task0.test.sql");
        } catch (NoSuchFileException ex) {
            throw new NoSuchFileException(ex.getFile());
        } catch (IOException | SQLException | URISyntaxException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Tests public boolean isValid() throws SQLException.
     * @throws Exception exception.
     */
    @Test
    public void testIsValid() throws Exception {
        driver.setConnection();
        assertTrue(driver.isValid());
    }

    /**
     * Tests public LinkedList<HashMap<String, String>> select(String query) throws SQLException.
     * @throws Exception exception.
     */
    @Test
    public void testSelect() throws Exception {
        LinkedList<HashMap<String, String>> result = driver.select("select * from phone_book");
        assertTrue(result.size() > 0);
    }

    /**
     * Tests public LinkedList<HashMap<String, String>> select(String query) throws SQLException.
     * Выброс SQLException.
     * @throws SQLException exception (java.sql.SQLException).
     */
    @Test(expected = SQLException.class)
    public void testSelectThrowsSQLException() throws SQLException {
        driver.select("select * from test_table");
    }

    /**
     * Tests public void setConnection() throws SQLException.
     * @throws Exception exception.
     */
    @Test
    public void testSetConnection() throws Exception {
        driver.close();
        driver.setConnection();
        assertTrue(driver.isValid());
    }

    /**
     * Tests public int update(String query) throws SQLException.
     * @throws Exception exception.
     */
    @Test
    public void testUpdate() throws Exception {
        int affected = driver.update("update phone_book set first_name = 'Zorro' where id = 1");
        assertEquals(1, affected);
    }

    /**
     * Actions after all tests.
     * @throws Exception exception.
     */
    @AfterClass
    public static void afterAllTests() throws Exception {
        driver.close();
    }
}