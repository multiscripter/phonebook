package phonebook.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

/**
 * The PropertyLoader class realize the function of loading property files..
 *
 * @author Multiscripter
 * @version 2019-05-28
 * @since 2017-12-23
 */
public final class PropertyLoader {
    /**
     * File path.
     */
    private String path;

    /**
     * Properties.
     */
    private final Properties props = new Properties();

    /**
     * Constructor without parameters.
     */
    public PropertyLoader() {
    }

    /**
     * Constructor.
     * @param name property file local name.
     * @throws IOException I/O exception.
     */
    public PropertyLoader(String name) throws IOException {
        this();
        this.load(name);
    }

    /**
     * Constructor.
     * @param url URL of property file.
     * @throws IOException I/O exception.
     */
    public PropertyLoader(URL url) throws IOException {
        this();
        this.load(new File(url.getFile()).toString());
    }

    /**
     * Gets properties.
     * @return object with properties.
     */
    public Properties getProperties() {
        return this.props;
    }

    /**
     * Gets property by name.
     * @param propName property`s name.
     * @return property`s value.
     */
    public String getPropValue(String propName) {
        return this.props.getProperty(propName);
    }

    /**
     * Loads properties from a file.
     * @param name property file local name.
     * @throws IOException I/O exception.
     */
    public void load(String name) throws IOException {
        Path path = Paths.get(name);
        InputStream is = Files.newInputStream(path);
        this.props.load(is);
    }
}
