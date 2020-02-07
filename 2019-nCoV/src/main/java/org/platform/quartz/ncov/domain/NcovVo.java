package org.platform.quartz.ncov.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * @author ctw
 * @Project： task-scheduler
 * @Package: org.platform.quartz.nCoV.domain
 * @Description:
 * @date 2020/2/6 18:11
 */
@Data
@Entity
@Table(name = "2019_ncov")
public class NcovVo {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer locationId;

    private String areaName;

    private Integer pLocationId;

    private String date;

    private Integer confirmedCount;

    private Integer suspectedCount;

    private Integer curedCount;

    private Integer deadCount;

    private String comment;

    private String updateTime;
}
