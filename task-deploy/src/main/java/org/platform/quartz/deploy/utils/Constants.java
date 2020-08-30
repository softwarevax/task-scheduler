package org.platform.quartz.deploy.utils;

/**
 * @author ctw
 * @Project： task-scheduler
 * @Package: org.platform.quartz.deploy.utils
 * @Description: key常量
 * @date 2020/8/30 9:53
 */
public class Constants {

    /**
     * deploy workspace
     */
    public static final String DEPLOY_WORK_SPACE = "deploy.workspace";


    /**
     * source code path
     */
    public static final String TMP_PROJECT_SOURCE_CODE_PATH = "tmp.code.path";

    public static class Git {
        /**
         * git repository address
         */
        public static final String GIT_REPO_HTTP_URI = "deploy.git.repository.http.uri";

        /**
         * git repository branch
         */
        public static final String GIT_REPO_BRANCH = "deploy.git.repository.branch";

        /**
         * git repository username
         */
        public static final String GIT_REPO_USERNAME = "deploy.git.username";

        /**
         * git repository password
         */
        public static final String GIT_REPO_PWD = "deploy.git.password";
    }

    public static class FileReplace {
        /**
         * file replace property prefix
         */
        public static final String PROPERTY_PREFIX = "deploy.replace";

        /**
         * file replace property SUFFIX
         */
        public static final String SUFFIX_FILE_PATH = "file.path";

        /**
         * file replace property SUFFIX
         */
        public static final String SUFFIX_OLD = "old";

        /**
         * file replace property SUFFIX
         */
        public static final String SUFFIX_TARGET = "target";

        /**
         * file replace property SUFFIX
         If "file.path" is configured as a path, any file that satisfies "filter.file.name" will be replaced
         *
         */
        public static final String SUFFIX_FILTER_FILE_NAME = "filter.file.name";
    }

    public static class Maven {
        /**
         *  maven project pom path
         */
        public static final String POM_PATH = "deploy.maven.pom.path";

        /**
         *  maven install path
         */
        public static final String MAVEN_PATH = "deploy.maven.root.path";

        /**
         * package command
         */
        public static final String MAVEN_COMMAND = "deploy.maven.cmd";
    }

    public static class FileFilter {
        /**
         * file filter property prefix
         */
        public static final String PROPERTY_PREFIX = "deploy.filter";

        /**
         * file filter path
         */
        public static final String PATH = PROPERTY_PREFIX + "." + "path";

        /**
         * file filter EXT
         */
        public static final String FILE_EXT = PROPERTY_PREFIX + "." + "file.ext";

        /**
         * filter file
         */
        public static final String TMP_FILTER_FILES = "tmp.deploy.filter.file";
    }

    public static class ArchiveCompress {
        /**
         * enable compress or not
         */
        public static final String COMPRESS_ENABLE = "deploy.compress.enable";

        /**
         * compress file target path
         */
        public static final String COMPRESS_TARGET_PATH = "deploy.compress.target.path";

        /**
         * compress file suffix
         */
        public static final String COMPRESS_FILE_SUFFIX = "deploy.compress.suffix";
    }

    public static class Upload {
        /**
         * upload hostname
         */
        public static final String UPLOAD_HOST_NAME = "deploy.upload.target.hostname";

        /**
         * upload username
         */
        public static final String UPLOAD_USER_NAME = "deploy.upload.target.username";

        /**
         * upload password
         */
        public static final String UPLOAD_PWD = "deploy.upload.target.password";

        /**
         * upload outcome path
         */
        public static final String UPLOAD_OUTCOME_PATH = "deploy.upload.target.outcome.path";
    }
}
