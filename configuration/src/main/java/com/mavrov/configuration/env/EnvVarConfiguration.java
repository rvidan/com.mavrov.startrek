package com.mavrov.configuration.env;

import com.mavrov.configuration.common.ConfigurationInitError;
import com.mavrov.configuration.common.StandardConfiguration;
import org.jboss.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Properties;

/**
 * @author serg.mavrov@gmail.com
 */
@ApplicationScoped
@EnvironmentVariables
public class EnvVarConfiguration extends StandardConfiguration {

    @Inject
    private transient Logger logger;
    //-=-=-=-
    private Properties environmentStateByStart;

    /**
     * Init the configuration with the provided configuration file.
     */
    @PostConstruct
    public void init() {
        try {
            logger.info("configuration loading started");
            environmentStateByStart = System.getProperties();
            environmentStateByStart.stringPropertyNames().stream().forEach(
                    property ->
                            logger.info(property + "=" + environmentStateByStart.get(property))
            );
            logger.info("configuration loading finished");
        } catch (Exception cex) {
            logger.fatal(cex.getMessage(), cex);
            throw new ConfigurationInitError(cex);
        }
    }

    @Override
    public String getStringValueOfKey(String key) {
        String res = getKeys().get(key);
        if (res == null) {
            String sysProperty = environmentStateByStart.getProperty(key);
            if (sysProperty == null) {
                throw new IllegalArgumentException("there is no system variable " + key);
            }
            getKeys().putIfAbsent(key, sysProperty);
            return getKeys().get(key);
        } else {
            return res;
        }
    }

}
