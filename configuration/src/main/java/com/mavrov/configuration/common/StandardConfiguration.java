package com.mavrov.configuration.common;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author serg.mavrov@gmail.com
 */
public abstract class StandardConfiguration implements ApplicationConfiguration {

    private Map<String, String> keys = new ConcurrentHashMap<>();
    // -=-=-

    protected Map<String, String> getKeys() {
        return keys;
    }

    @Override
    public String toString() {
        ToStringBuilder tsb = new ToStringBuilder(this);
        keys.entrySet().stream().forEach(
                es -> tsb.append(es.getKey(), es.getValue())
        );
        return tsb.toString();
    }

}
