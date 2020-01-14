package org.platform.quartz.deploy.ssh.ganymed;

import ch.ethz.ssh2.ChannelCondition;
import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.SCPClient;
import ch.ethz.ssh2.Session;
import org.platform.quartz.deploy.ssh.IOUtils;
import org.platform.quartz.deploy.ssh.SecureShell;
import org.platform.quartz.deploy.ssh.StringUtils;
import org.platform.quartz.deploy.ssh.Xftp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author twcao
 * @description  Ganymed shell
    example：
        GanymedSecureShell secureShell = new GanymedSecureShell("120.79.255.186");
        secureShell.setStopIfAbsent(false);
        secureShell.login("root", "Ctw130512");
        secureShell.close();
 * @project task-scheduler
 * @classname SecureShell
 * @date 2020/1/13 8:38
 */
public class GanymedSecureShell implements SecureShell, Xftp {

    public static final Logger logger = LoggerFactory.getLogger(GanymedSecureShell.class);

    /**
     * 默认字符集
     */
    public static final String DEFAULT_CHARSET = "UTF-8";

    public static final int TIME_OUT = 5 * 1000;

    private String ip;

    private int port;

    private String username;

    private String password;

    private Connection connection;

    private SCPClient scpClient;

    /**
     * 如果远程或本地有一个文件不存在，则停止所有操作， 默认为true, 创建文件或目录
     */
    private boolean stopIfAbsent = true;

    public GanymedSecureShell() {
        this(null, null);
    }

    public GanymedSecureShell(String ip) {
        this(ip, null);
    }

    public GanymedSecureShell(String ip, Integer port) {
        this.ip = ip == null ||  "".equals(ip) ? "127.0.0.1" : ip;
        this.port = port == null ? 22 : port;
    }

    /**
     * 用户名密码登录
     *
     * @param username 用户名
     * @param password 用户密码
     * @return 是否登录成功
     */
    @Override
    public boolean login(String username, String password) {
        if(StringUtils.isAnyBlank(username, password)) {
            return false;
        }
        this.username = username;
        this.password = password;
        try {
            connection = new Connection(this.ip, this.port);
            connection.connect();
            boolean flag = connection.authenticateWithPassword(this.username, this.password);
            scpClient = connection.createSCPClient();
            return flag;
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            if(connection != null) {
                connection.close();
            }
        }
        return false;
    }

    /**
     * 执行shell命令
     *
     * @param cmd shell/cmd命令
     * @return 控制台返回结果
     */
    @Override
    public String execute(String cmd) {
        // 一个session只能执行一个命令
        Session session = null;
        try {
            if(connection != null) {
                session = connection.openSession();
                session.execCommand(cmd);
                InputStream is = session.getStdout();
                return IOUtils.toString(is, DEFAULT_CHARSET);
            }
        } catch (IOException e) {
            if(session != null) {
                InputStream err = session.getStderr();
                logger.error(IOUtils.toString(err, "utf-8"));
            }
            logger.error(e.getMessage(), e);
        } finally {
            if(session != null) {
                session.close();
            }
        }
        return null;
    }

    /**
     Session session = secureShell.openBashPTY();
     InputStream is = new StreamGobbler(session.getStdout());
     BufferedReader stdoutReader = new BufferedReader(new InputStreamReader(is));
     PrintWriter out =new PrintWriter(session.getStdin());
     new Thread(() -> {
     while (true) {
     Scanner scanner = new Scanner(System.in);
     String cmd = scanner.nextLine();
     if("exit".equals(cmd)) {
     out.close();
     System.exit(0);
     }
     out.println(cmd);
     out.flush();
     }
     }).start();
     while (true) {
     String line = stdoutReader.readLine();
     if (line == null) {
     break;
     }
     System.out.println(line);
     }
     * bash命令
     * @return
     */
    public Session openBashPTY() {
        Session session = null;
        try {
            if(connection != null) {
                session = connection.openSession();
                session.requestPTY("bash");
                session.startShell();
                session.waitForCondition(ChannelCondition.CLOSED | ChannelCondition.EOF | ChannelCondition.EXIT_STATUS, TIME_OUT);
            }
        } catch (IOException e) {
            if(session != null) {
                session.close();
            }
            logger.error(e.getMessage(), e);
        }
        return session;
    }

    /**
     * 关闭安全连接
     *
     * @return 是否关闭成功
     */
    @Override
    public boolean close() {
        if(connection != null) {
            connection.close();
        }
        return true;
    }

    /**
     * 上传文件
     *
     * @param localPaths  本地路径，只能为文件
     * @param remotePath 远程路径,只能为路径
     * @return 是否成功
     */
    @Override
    public boolean put(List<String> localPaths, String remotePath) {
        if(StringUtils.isBlank(remotePath) || localPaths == null || localPaths.size() == 0) {
            return false;
        }
        try {
            List<String> paths = new ArrayList<>();
            for(String localPath : localPaths) {
                File localFile = new File(localPath);
                if(localFile.exists()) {
                    paths.add(localPath);
                }
            }
            if(stopIfAbsent && paths.size() != localPaths.size()) { //如果有文件不存在
                return false;
            }
            String[] arrays = new String[paths.size()];
            scpClient.put(paths.toArray(arrays), remotePath);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 下载文件
     * @param localPath  本地路径、只能为路径
     * @param remotePaths 远程路径、只能为文件
     * @return 是否成功
     */
    @Override
    public boolean get(String localPath, List<String> remotePaths) {
        try {
            if(StringUtils.isBlank(localPath) || remotePaths == null || remotePaths.size() == 0) {
                return false;
            }
            File localFile = new File(localPath);
            if(!localFile.exists()) { //文件或路径不存在
                if(stopIfAbsent) { // 是否停止操作
                    return false;
                }
            }
            if(localFile.isFile() && remotePaths.size() > 1) { //不能将多个文件写到一个文件中
                return false;
            }
            if(localFile.isFile() && remotePaths.size() == 1) {
                scpClient.get(remotePaths.get(0), localPath);
            }
            if(localFile.isDirectory()) {
                for (String remotePath : remotePaths) {
                    scpClient.get(remotePath, localPath);
                }
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void setStopIfAbsent(boolean stopIfAbsent) {
        this.stopIfAbsent = stopIfAbsent;
    }

    public Connection getConnection() {
        return connection;
    }
}
