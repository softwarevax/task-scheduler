package org.platform.quartz.ncov.domain;

import lombok.Data;

import java.util.List;

/**
 * @author ctw
 * @Project： task-scheduler
 * @Package: org.platform.quartz.nCoV.domain
 * @Description:
 * @date 2020/2/6 17:40
 */
@Data
public class Area {

    private String provinceName;

    private String provinceShortName;

    private Integer confirmedCount;

    private Integer suspectedCount;

    private Integer curedCount;

    private Integer deadCount;

    private String comment;

    private Integer locationId;

    private List<City> cities;

    private String preProvinceName;

    private YesterdayIncreased yesterdayIncreased;
}
