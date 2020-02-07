package org.platform.quartz.ncov.domain;

import com.alibaba.fastjson.JSON;
import lombok.Data;

/**
 * @author ctw
 * @Project： task-scheduler
 * @Package: org.platform.quartz.nCoV.domain
 * @Description:
 * @date 2020/2/6 17:36
 */
@Data
public class ResultDto {

    private Integer errcode;

    private NCoV data;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
