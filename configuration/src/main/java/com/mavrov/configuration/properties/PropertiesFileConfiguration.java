package com.mavrov.configuration.properties;

import com.mavrov.configuration.common.ConfigurationInitError;
import com.mavrov.configuration.common.StandardConfiguration;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.jboss.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 * @author serg.mavrov@gmail.com
 */
@ApplicationScoped
@PropertiesFileVariables
public class PropertiesFileConfiguration extends StandardConfiguration {

    @Inject
    private transient Logger logger;
    //-=-=-=-
    private PropertiesConfiguration configuration;
    //-=-=-=

    @PostConstruct
    public void init() {
        try {
            logger.info("configuration loading started");
            configuration = new PropertiesConfiguration("settings.properties");
            logger.info("configuration = " + configuration);
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
            String sysProperty = configuration.getString(key);
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
