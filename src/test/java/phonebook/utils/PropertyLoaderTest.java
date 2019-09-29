package phonebook.utils;

import java.io.File;
import java.net.URI;
import java.nio.file.Paths;
import org.junit.Test;
import static org.junit.Assert.assertFalse;

/**
 * Класс PropertyLoaderTest тестирует класс PropertyLoader.
 *
 * @author Goureev Ilya (mailto:ill-jah@yandex.ru)
 * @version 2019-09-29
 * @since 2019-05-28
 */
public class PropertyLoaderTest {
    /**
     * Тестирует public PropertyLoader(URL url) throws IOException.
     * @throws Exception исключение.
     */
    @Test
    public void testPropertyLoaderWithURLparam() throws Exception {
        URI uri = ClassLoader.getSystemClassLoader()
                .getResource("MySQL.properties").toURI();
        String path = Paths.get(uri).toString();
        PropertyLoader pl = new PropertyLoader(new File(path).toURI().toURL());
        assertFalse(pl.getProperties().isEmpty());
    }
}
