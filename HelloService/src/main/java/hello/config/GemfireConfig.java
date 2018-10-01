package hello.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.gemfire.CacheFactoryBean;


@Configuration
public class GemfireConfig {
    @Bean
    public CacheFactoryBean gemfireCache() {

//        Properties properties= new Properties();
//        properties.setProperty("log-level", "warning");
//        properties.setProperty("name", "testMember2");

        return new CacheFactoryBean();
    }
}
