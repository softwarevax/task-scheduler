package org.platform.quartz.ncov.domain;

import lombok.Data;

/**
 * @author ctw
 * @Project： task-scheduler
 * @Package: org.platform.quartz.nCoV.domain
 * @Description:
 * @date 2020/2/6 17:42
 */
@Data
public class YesterdayIncreased {

    private Integer confirmedCount;

    private Integer suspectedCount;

    private Integer curedCount;

    private Integer deadCount;
}
