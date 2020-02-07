package org.platform.quartz.utils.langs;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;

/**
 * @author ctw
 * @Project： task-scheduler
 * @Package: org.platform.quartz.utils.langs
 * @Description:
 * @date 2020/2/6 19:32
 */
@Slf4j
public class ObjectUtils extends org.apache.commons.lang3.ObjectUtils {

    public static void merge(Object source, Object target) {
        try {
            BeanUtils.copyProperties(target, source);
        } catch (Exception e) {
            log.error(e.getMessage(),  e);
        }
    }
}
