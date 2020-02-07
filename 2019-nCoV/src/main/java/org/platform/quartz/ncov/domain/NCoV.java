package org.platform.quartz.ncov.domain;

import lombok.Data;

import java.util.List;

/**
 * @author ctw
 * @Project： task-scheduler
 * @Package: org.platform.quartz.nCoV.domain
 * @Description: 病例详情
 * @date 2020/2/6 17:46
 */
@Data
public class NCoV {

    private String author;

    private String date;

    /**
     * 确诊
     */
    private Integer diagnosed;

    /**
     * 疑似
     */
    private Integer suspect;

    /**
     * 死亡
     */
    private Integer death;

    /**
     * 治愈
     */
    private Integer cured;

    /**
     * 疑似增长
     */
    private Integer suspectIncr;

    /**
     * 34个省的统计情况
     * eg: 湖北 确诊 19665 例，治愈 672 例，死亡 549 例
     */
    private List<String> list;

    /**
     * 全国历史记录
     {
     "date":"2020-02-05",
     "confirmedNum":28060,
     "suspectedNum":24702,
     "curesNum":1153,
     "deathsNum":564,
     "suspectedIncr":5328
     }
     */
    private List<History> history;

    /**
     * 各个地区的详细
     */
    private List<Area> area;
}
