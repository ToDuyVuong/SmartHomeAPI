package vn.smarthome.smarthomeapi.config;

import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class JacksonConfiguration {
    @Autowired
    private Hibernate5Module hibernate5Module;

    @PostConstruct
    public void configureJacksonObjectMapper() {
        hibernate5Module.configure(Hibernate5Module.Feature.FORCE_LAZY_LOADING, true);
    }

    @Bean
    public Hibernate5Module hibernate5Module() {
        return new Hibernate5Module();
    }
}