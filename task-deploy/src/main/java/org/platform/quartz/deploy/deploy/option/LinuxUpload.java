package org.platform.quartz.deploy.deploy.option;

import com.alibaba.fastjson.JSONArray;
import lombok.extern.slf4j.Slf4j;
import org.platform.quartz.deploy.deploy.Context;
import org.platform.quartz.deploy.ssh.ganymed.GanymedSecureShell;
import org.platform.quartz.deploy.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author twcao
 * @description linux文件上传
 * @project task-scheduler
 * @classname LinuxUpload
 * @date 2020/1/17 13:59
 */
@Slf4j
public class LinuxUpload implements Upload {

    public static final Logger logger = LoggerFactory.getLogger(LinuxUpload.class);

    @Override
    public boolean execute(Context ctx) {
        try {
            GanymedSecureShell secureShell = new GanymedSecureShell(ctx.getString(Constants.Upload.UPLOAD_HOST_NAME));
            //secureShell.setStopIfAbsent(false);
            secureShell.login(ctx.getString(Constants.Upload.UPLOAD_USER_NAME), ctx.getString(Constants.Upload.UPLOAD_PWD));
            List<String> filterFiles = JSONArray.parseArray(ctx.getString(Constants.FileFilter.TMP_FILTER_FILES), String.class);
            secureShell.put(filterFiles, ctx.getString(Constants.Upload.UPLOAD_OUTCOME_PATH));
            secureShell.close();
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return false;
        }
    }
}
