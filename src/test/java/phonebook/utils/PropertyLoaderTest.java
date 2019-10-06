package phonebook.utils;

import java.io.File;
import java.net.URI;
import java.nio.file.Paths;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertFalse;

/**
 * Class PropertyLoaderTest tests class PropertyLoader.
 *
 * @author Multiscripter
 * @version 2019-10-06
 * @since 2019-05-28
 */
public class PropertyLoaderTest {
    /**
     * DBMS name.
     */
    private static String dbmsName;

    /**
     * Actions before all tests.
     * @throws java.lang.Exception exception.
     */
    @BeforeClass
    public static void beforeAllTests() throws Exception {
        ClassLoader loader = PropertyLoaderTest.class.getClassLoader();
        String path = loader.getResource(".").getPath();
        path = path.replaceFirst("^/(.:/)", "$1");
        dbmsName = new PropertyLoader(String.format("%s%s", path, "activeDBMS.properties")).getPropValue("name");
    }

    /**
     * Tests public PropertyLoader(URL url) throws IOException.
     * @throws Exception exception.
     */
    @Test
    public void testPropertyLoaderWithURLparam() throws Exception {
        URI uri = ClassLoader.getSystemClassLoader()
                .getResource(dbmsName + ".properties").toURI();
        String path = Paths.get(uri).toString();
        PropertyLoader pl = new PropertyLoader(new File(path).toURI().toURL());
        assertFalse(pl.getProperties().isEmpty());
    }
}
