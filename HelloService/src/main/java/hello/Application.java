package hello;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import org.springframework.data.gemfire.config.annotation.EnableCachingDefinedRegions;
import org.springframework.data.gemfire.config.annotation.EnableClusterConfiguration;
import org.springframework.data.gemfire.config.annotation.EnableEntityDefinedRegions;
import org.springframework.data.gemfire.repository.config.EnableGemfireRepositories;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import hello.domain.AppointmentSlots;
//import hello.service.AppointmentSlotsRepository;
import org.apache.geode.cache.Region;
import org.apache.geode.cache.client.ClientCache;
import org.apache.geode.cache.client.ClientCacheFactory;
import org.apache.geode.cache.client.ClientRegionShortcut;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//@SpringBootApplication
//@EnableCachingDefinedRegions
//@ClientCacheApplication(name = "X")
//@EnableGemfireRepositories(basePackageClasses = AppointmentSlotsRepository.class)
//@EnableEntityDefinedRegions(basePackageClasses = AppointmentSlots.class, clientRegionShortcut = ClientRegionShortcut.LOCAL)
//@EnableClusterConfiguration(host="localhost", port = 40410)
public class Application {

    private static final String[] AUTHORS =
            ("Anton Chekhov,C. J. Cherryh,Dorothy Parker,Douglas Adams,Emily Dickinson,"
                    + "Ernest Hemingway,F. Scott Fitzgerald,Henry David Thoreau,Henry Wadsworth Longfellow,"
                    + "Herman Melville,Jean-Paul Sartre,Mark Twain,Orson Scott Card,Ray Bradbury,Robert Benchley,"
                    + "Somerset Maugham,Stephen King,Terry Pratchett,Ursula K. Le Guin,William Faulkner")
                    .split(",");
    final static private Logger LOG = LogManager.getLogger();

    @Bean
    ApplicationRunner runner(ApplicationContext applicationContext) {
        return args -> {
//            Stream.of(applicationContext.getBeanDefinitionNames()).iterator().forEachRemaining(System.out::println);

            ClientCache cache = new ClientCacheFactory().addPoolLocator("127.0.0.1", 10334)
                    .set("log-level", "WARN").create();

            // create a local region that matches the server region
//            Region<String, String> region =
//                    cache.<String, String>createClientRegionFactory(ClientRegionShortcut.PROXY)
//                            .create("AppointmentSlots");

            Region<Set, String> region =
                    cache.<Set, String>createClientRegionFactory(ClientRegionShortcut.PROXY)
                    .create("test");

            Set s = new HashSet();
            s.add("B");

            Object ret = region.get(s);
            s = new HashSet();
            s.add("A");
            s.add("B");
            s.add("C");
            region.put(s, "value");

//            printQuotes(region);
            cache.close();

        };
    }

    public static void main(String[] args) {
        new SpringApplicationBuilder(Application.class).web(WebApplicationType.NONE).build().run(args);
    }

    public static void printQuotes(Map<String, String> region) {
        // initial fetch invokes the cache loader
        {
            long elapsed = printQuotesAndMeasureTime(region);
            System.out.println(
                    String.format("\n\nLoaded %d definitions in %d ms\n\n", AUTHORS.length, elapsed));
        }

        // fetch from cache, really fast!
        {
            long elapsed = printQuotesAndMeasureTime(region);
            System.out.println(
                    String.format("\n\nFetched %d cached definitions in %d ms\n\n", AUTHORS.length, elapsed));
        }
    }

    private static long printQuotesAndMeasureTime(Map<String, String> region) {
        long start = System.currentTimeMillis();
        Arrays.stream(AUTHORS)
                .forEach(author -> System.out.println(author + ": " + region.get(author)));

        return System.currentTimeMillis() - start;
    }


}
