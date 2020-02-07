package org.platform.quartz.ncov.domain;

import com.alibaba.fastjson.JSON;
import lombok.Data;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author ctw
 * @Project： task-scheduler
 * @Package: org.platform.quartz.nCoV.domain
 * @Description:
 * @date 2020/2/6 17:38
 */
@Data
@Entity
@Table(name = "2019_ncov_history")
public class History {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String date;

    private Integer confirmedNum;

    private Integer suspectedNum;

    private Integer curesNum;

    private Integer deathsNum;

    private Integer suspectedIncr;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        History history = (History) o;
        return date.equals(history.date) &&
                confirmedNum.equals(history.confirmedNum) &&
                suspectedNum.equals(history.suspectedNum) &&
                curesNum.equals(history.curesNum) &&
                deathsNum.equals(history.deathsNum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, confirmedNum, suspectedNum, curesNum, deathsNum);
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
