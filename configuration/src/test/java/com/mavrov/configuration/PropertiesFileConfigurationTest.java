package com.mavrov.configuration;

import com.mavrov.configuration.common.ApplicationConfiguration;
import com.mavrov.configuration.properties.PropertiesFileVariables;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.runner.RunWith;

import javax.inject.Inject;

/**
 * @author serg.mavrov@gmail.com
 */
@RunWith(Arquillian.class)
public class PropertiesFileConfigurationTest extends CommonConfigurationTest {

    @Inject
    @PropertiesFileVariables
    private ApplicationConfiguration configuration;

    @Override
    public ApplicationConfiguration getConfiguration() {
        return configuration;
    }

}
