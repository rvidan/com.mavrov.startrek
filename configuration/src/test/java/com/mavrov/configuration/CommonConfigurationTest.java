package com.mavrov.configuration;

import com.mavrov.configuration.common.ApplicationConfiguration;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.logging.Logger;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;

import javax.inject.Inject;

/**
 * @author serg.mavrov@gmail.com
 */
public abstract class CommonConfigurationTest {

    @Deployment
    public static JavaArchive getJar() {
        return ShrinkWrap.create(JavaArchive.class).addPackages(true, "com/mavrov")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    @Inject
    private transient Logger logger;

    public abstract ApplicationConfiguration getConfiguration();

    @Test
    public void testVariableReading() {
        logger.info("started");
        Assert.assertNotNull(getConfiguration());
        logger.info("configuration = " + getConfiguration());

        logger.info("simple key " + getConfiguration().getStringValueOfKey("simple.key"));
        Assert.assertEquals("simple_key", getConfiguration().getStringValueOfKey("simple.key"));
        logger.info("finished");

    }

}
