package phonebook.config;

import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json
        .MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml
        .Jaxb2RootElementHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import phonebook.models.EntryList;

/**
 * Class WebConfig realize configuration servlet-context.xml.
 *
 * @author Multiscripter
 * @version 2019-10-01
 * @since 2019-10-01
 */
@Configuration
@ComponentScan(basePackages = "phonebook.*")
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(this.xmlMessageConverter());
        converters.add(this.jsonMessageConverter());
    }

    @Bean
    public Jaxb2RootElementHttpMessageConverter xmlMessageConverter() {
        return new Jaxb2RootElementHttpMessageConverter();
    }

    @Bean
    public MappingJackson2HttpMessageConverter jsonMessageConverter() {
        return new MappingJackson2HttpMessageConverter();
    }

    /**
     * Root element of entry list.
     * Required for XML response when querying a list of entries.
     * @return entry list bean.
     */
    @Bean
    public EntryList entryList() {
        return new EntryList();
    }
}
