package com.mavrov.configuration.common;

/**
 * @author serg.mavrov@gmail.com
 */
public interface ApplicationConfiguration {

    String getStringValueOfKey(String key);

    default Integer getIntValueOfKey(String key) {
        return Integer.valueOf(getStringValueOfKey(key));
    }

}
