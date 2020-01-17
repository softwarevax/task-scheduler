package org.platform.quartz.deploy.utils;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.CollectionUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @author twcao
 * @description 属性文件读取
 * @project task-scheduler
 * @classname ProprttyUtils
 * @date 2020/1/15 17:12
 */
public class PropertyUtils {

    public static Properties getProperty() {
        return getProperty(null);
    }

    public static Properties getProperty(final String classFileName) {
        Properties props = new Properties();
        try {
            String fileName = "application.properties";
            if(null != classFileName && !"".equals(classFileName)) {
                fileName = classFileName;
            }
            Resource resource = new ClassPathResource(fileName);
            BufferedReader bf = new BufferedReader(new InputStreamReader(resource.getInputStream()));
            props.load(bf);
            return props;
        } catch (Exception e) {
            e.printStackTrace();
            return props;
        }
    }

    public static Properties toProp(Map<String, Object> map) {
        Properties props = new Properties();
        if(CollectionUtils.isEmpty(map)) {
            return props;
        }
        Iterator<Map.Entry<String, Object>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Object> entry = iterator.next();
            props.put(entry.getKey(), entry.getValue());
        }
        return props;
    }

    public static Map<String, String> toMap(Properties properties) {
        Map<String, String> result = new HashMap<>();
        Enumeration<?> propertyNames = properties.propertyNames();
        while (propertyNames.hasMoreElements()) {
            String name = (String) propertyNames.nextElement();
            String value = properties.getProperty(name);
            result.put(name, value);
        }
        return result;
    }
}
