package org.platform.quartz.deploy.deploy;

import org.platform.quartz.deploy.utils.StringUtils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author twcao
 * @description 上下文
 * @classname Context
 * @date 2020/1/3 11:59
 */
public class Context {

    private Map<String, String> parameters;

    public Context() {
        this.parameters = new HashMap<>();
    }

    public Context(Map<String, String> parameters) {
        this();
        if(parameters != null) {
            this.parameters = parameters;
        }
    }

    /**
     * Removes all of the mappings from this map.
     */
    public void clear() {
        if(parameters.size() > 0) {
            parameters.clear();
        }
    }

    /**
     * Gets value mapped to key, returning null if unmapped.
     * @param key
     * @return
     */
    public Boolean	getBoolean(String key) {
        return getBoolean(key, null);
    }

    /**
     * Gets value mapped to key, returning defaultValue if unmapped.
     * @param key
     * @param defaultValue
     * @return
     */
    public Boolean	getBoolean(String key, Boolean defaultValue) {
        return get(key, defaultValue);
    }

    /**
     * Gets value mapped to key, returning null if unmapped.
     * @param key
     * @return
     */
    public Integer	getInteger(String key) {
        return get(key, null);
    }

    /**
     * Gets value mapped to key, returning defaultValue if unmapped.
     * @param key
     * @param defaultValue
     * @return
     */
    public Integer	getInteger(String key, Integer defaultValue) {
        return get(key, defaultValue);
    }

    /**
     * Gets value mapped to key, returning null if unmapped.
     * @param key
     * @return
     */
    public Long getLong(String key) {
        return get(key, null);
    }

    /**
     * Gets value mapped to key, returning defaultValue if unmapped.
     * @param key
     * @param defaultValue
     * @return
     */
    public Long getLong(String key, Long defaultValue) {
        return get(key, defaultValue);
    }

    /**
     *  return parameters
     * @return
     */
    public Map<String, String>	getParameters() {
        return this.parameters;
    }

    public String getString(String key) {
        return get(key, null);
    }

    /**
     * Gets value mapped to key, returning defaultValue if unmapped.
     * @param key
     * @param defaultValue
     * @return
     */
    public String getString(String key, String defaultValue) {
        return get(key, defaultValue);
    }

    /**
     * put a element to paramters
     * @param key
     * @param value
     * @param override
     */
    public void put(String key, String value, boolean override) {
        if(override) {
            this.parameters.put(key,value);
        }
    }

    /**
     * put a element to paramters, override element
     * @param key
     * @param value
     */
    public void put(String key, String value) {
        put(key, value, true);
    }

    /**
     * put some paramters
     * @param map
     */
    public void putAll(Map<String, String> map) {
        this.parameters.putAll(map);
    }

    /**
     * 根据前缀获取
     * @param prefix
     * @return
     */
    public Map<String, String> getPrefix(String prefix) {
        Map<String, String> map = new HashMap<>();
        Iterator<String> iterator = this.parameters.keySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            if(StringUtils.isBlank(key)) {
                continue;
            }
            if(key.startsWith(prefix)) {
                map.put(key, this.parameters.get(key));
            }
        }
        return map;
    }

    /**
     * toString
     * @return
     */
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        Iterator<Map.Entry<String, String>> iterator = this.parameters.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();
            sb.append(entry.getKey()).append(":").append(entry.getValue()).append(";");
        }
        return sb.toString();
    }

    /**
     * Gets value mapped to key, returning defaultValue if unmapped.
     * @param key
     * @param defaultValue
     * @param <T>
     * @return
     */
    private <T> T get(String key, T defaultValue) {
        return parameters.containsKey(key) ? (T) parameters.get(key) : defaultValue;
    }
}
