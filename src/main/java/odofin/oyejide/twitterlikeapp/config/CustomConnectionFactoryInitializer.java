package odofin.oyejide.twitterlikeapp.config;


import io.r2dbc.spi.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.r2dbc.connection.init.CompositeDatabasePopulator;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;

@Configuration
public class CustomConnectionFactoryInitializer {
    /**
     * Bean definition for initializing the connection factory.
     *
     * @param connectionFactory The ConnectionFactory to be initialized.
     * @return A ConnectionFactoryInitializer configured with the specified ConnectionFactory
     *         and a database populator using the 'schema.sql' resource file.
     */
    @Bean
    public ConnectionFactoryInitializer initializer(ConnectionFactory connectionFactory) {
        ConnectionFactoryInitializer initializer = new ConnectionFactoryInitializer();

        // Set the provided ConnectionFactory
        initializer.setConnectionFactory(connectionFactory);

        // Create a CompositeDatabasePopulator to allow for multiple populators
        CompositeDatabasePopulator populator = new CompositeDatabasePopulator();

        // Add a ResourceDatabasePopulator using the 'schema.sql' resource file
        populator.addPopulators(new ResourceDatabasePopulator(new ClassPathResource("schema.sql")));

        // Set the CompositeDatabasePopulator on the initializer
        initializer.setDatabasePopulator(populator);

        // Return the configured ConnectionFactoryInitializer
        return initializer;
    }

}
