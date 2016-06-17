package guru.springboot.configuration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by Bipin on 9/29/2015.
 */
@Configuration
@EnableAutoConfiguration
@EntityScan(basePackages = {"guru.springboot.domain"})
@EnableJpaRepositories(basePackages = {"guru.springboot.repositories"})
@EnableTransactionManagement
public class RepositoryConfiguration {
}
