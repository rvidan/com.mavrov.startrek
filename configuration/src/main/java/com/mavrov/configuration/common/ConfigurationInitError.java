package com.mavrov.configuration.common;

/**
 * @author serg.mavrov@gmail.com
 */
public class ConfigurationInitError extends Error {

    private static final long serialVersionUID = 2708318266080153790L;
    // -=-=-
    private static final String MESSAGE = "The configuration cannot be loaded.";

    // -=-=-
    public ConfigurationInitError(Throwable cause) {
        super(MESSAGE, cause);
    }

}
