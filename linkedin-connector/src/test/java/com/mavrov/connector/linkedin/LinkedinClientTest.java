package com.mavrov.connector.linkedin;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
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
public class LinkedinClientTest {

    @Deployment
    public static JavaArchive getJar() {
        return ShrinkWrap.create(JavaArchive.class).addPackages(true, "com/mavrov")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    @Inject @LinkedInProcess
    private ConnectorClient client;

    @Test
    public void testAuthorization() {
        Assert.assertNotNull(client);
        client.authorize();
    }

}
