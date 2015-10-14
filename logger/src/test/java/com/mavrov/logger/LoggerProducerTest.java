package com.mavrov.logger;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.logging.Logger;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

/**
 * @author serg.mavrov@gmail.com
 */
@RunWith(Arquillian.class)
public class LoggerProducerTest {

    @Deployment
    public static JavaArchive getJar() {
        return ShrinkWrap.create(JavaArchive.class).addPackages(true, "com/mavrov")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    @Inject
    private transient Logger logger;

    @Test
    public void testInjection() {
        Assert.assertNotNull("the logger must be injected", logger);
        Assert.assertEquals("the logger must be related with the current class",
                this.getClass().getCanonicalName(), logger.getName());
    }

    @Test
    public void testLogMessageType() {
        logger.error("test error");
        logger.info("test info");
        logger.debug("test debug");
        logger.fatal("test fatal");
        logger.trace("test trace");
    }

}
