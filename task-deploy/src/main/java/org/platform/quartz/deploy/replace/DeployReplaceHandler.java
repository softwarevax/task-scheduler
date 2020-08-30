package org.platform.quartz.deploy.replace;

import lombok.extern.slf4j.Slf4j;
import org.platform.quartz.deploy.utils.FileUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author twcao
 * @description 执行替换操作
 * @project task-scheduler
 * @classname DeployReplaceStepChain
 * @date 2020/1/16 9:32
 */
@Slf4j
public class DeployReplaceHandler implements ReplaceHandle {

    private List<ReplaceStep> steps;

    public DeployReplaceHandler() {
        steps = new ArrayList<>();
    }

    @Override
    public void addStep(ReplaceStep step) {
        steps.add(step);
    }

    @Override
    public boolean doReplace() {
        try {
            // sort by order from small to large, performing the smallest first
            Collections.sort(steps, Comparator.comparing(ReplaceStep::getOrder));
            for(ReplaceStep step : steps) {
                String replacePath = FileUtils.merge(step.getCodePath(), step.getFilePath());
                FileUtils.replace(replacePath, step.getFilterFileName(), step.getOld(), step.getTarget());
            }
            return true;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return false;
        }
    }
}
