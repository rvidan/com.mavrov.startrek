package com.mavrov.configuration;

import com.mavrov.configuration.common.ApplicationConfiguration;
import com.mavrov.configuration.env.EnvironmentVariables;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.runner.RunWith;

import javax.inject.Inject;

/**
 * @author serg.mavrov@gmail.com
 */
@RunWith(Arquillian.class)
public class EnvironmentVariablesConfigurationTest extends CommonConfigurationTest {

    @Inject
    @EnvironmentVariables
    private ApplicationConfiguration configuration;

    @Override
    public ApplicationConfiguration getConfiguration() {
        return configuration;
    }

}
