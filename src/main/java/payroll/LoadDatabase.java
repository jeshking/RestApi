package payroll;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class LoadDatabase {
    private static final Log LOG = LogFactory.getLog(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(EmployeeRepository repository) {
        return args -> {

            LOG.info("Preloading " + repository.save(new Employee("Bilbo Baggins", "burglar")));
            LOG.info("Preloading " + repository.save(new Employee("Frodo Baggins", "thief")));
        };
    }
}
