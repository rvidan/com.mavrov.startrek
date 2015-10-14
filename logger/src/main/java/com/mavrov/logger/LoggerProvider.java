package com.mavrov.logger;

import org.jboss.logging.Logger;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

/**
 * The standard dynamix config logger provider.
 *
 * @author serg.mavrov@gmail.com
 */
public class LoggerProvider {

    /**
     * Produce new instance of the logger.
     *
     * @param injectionPoint the point where the instance has been injected
     * @return the created instance
     */
    @Produces @Dependent
    public Logger produceLog(InjectionPoint injectionPoint) {
        return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
    }

}