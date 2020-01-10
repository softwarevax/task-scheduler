package org.platform.quartz.deploy;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;

import java.io.*;

public class Application {

	public static void main(String[] args) throws Exception {
        ganymed();
	}

	/*public static void jsch() throws Exception {
        long currentTimeMillis = System.currentTimeMillis();

        String command = "uname -a";
        JSch jsch = new JSch();
        Session session = jsch.getSession("root", "120.79.255.186", 22);
        session.setPassword("Ctw130512");
        session.setConfig("StrictHostKeyChecking", "no");
        session.connect(60 * 1000);
        Channel channel = session.openChannel("exec");
        ((ChannelExec) channel).setCommand(command);
        channel.setInputStream(null);
        ((ChannelExec) channel).setErrStream(System.err);

        InputStream in = channel.getInputStream();

        channel.connect();

        byte[] tmp = new byte[1024];
        while (true) {
            while (in.available() > 0) {
                int i = in.read(tmp, 0, 1024);
                if (i < 0) break;
                System.out.print(new String(tmp, 0, i));
            }
            if (channel.isClosed()) {
                if (in.available() > 0) continue;
                System.out.println("exit-status: " + channel.getExitStatus());
                break;
            }
            try {
                Thread.sleep(1000);
            } catch (Exception ee) {
            }
        }
        channel.disconnect();
        session.disconnect();

        long currentTimeMillis1 = System.currentTimeMillis();
        System.out.println("Jsch方式"+(currentTimeMillis1-currentTimeMillis));
    }
*/
    public static void ganymed() {
         long currentTimeMillis = System.currentTimeMillis();
        String ip = "120.79.255.186";
        String username = "root";
        String password = "Ctw130512";
        String cmd = "ls";
        Connection connection = login(ip, username, password);
        String execmd = execmd(connection, cmd);
        System.out.println(execmd);
        long currentTimeMillis1 = System.currentTimeMillis();
        System.out.println("ganymed-ssh2方式"+(currentTimeMillis1-currentTimeMillis));
    }

    private static String DEFAULTCHART = "UTF-8";

    private static Connection login(String ip, String username, String password) {
        boolean flag = false;
        Connection connection = null;
        try {
            connection = new Connection(ip);
            connection.connect();// 连接
            flag = connection.authenticateWithPassword(username, password);// 认证
            if (flag) {
                System.out.println("================登录成功==================");
                return connection;
            }
        } catch (IOException e) {
            System.out.println("=========登录失败=========" + e);
            connection.close();
        }
        return connection;
    }

    /**
      * 远程执行shll脚本或者命令
      * 
      * @param cmd
      *            即将执行的命令
      * @return 命令执行完后返回的结果值
      */
    private static String execmd(Connection connection, String cmd) {
        String result = "";
        try{
            if (connection != null) {
                Session session = connection.openSession();// 打开一个会话
                session.execCommand(cmd);// 执行命令
                result = processStdout(session.getStdout(), DEFAULTCHART);
                //System.out.println(result);
                // 如果为得到标准输出为空，说明脚本执行出错了
                /*if (StringUtils.isBlank(result)) {
                    System.out.println("得到标准输出为空,链接conn:" + connection + ",执行的命令：" + cmd);
                    result = processStdout(session.getStderr(), DEFAULTCHART);
                } else {
                    System.out.println("执行命令成功,链接conn:" + connection + ",执行的命令：" + cmd);
                }*/
                connection.close();
                session.close();
            }
        } catch (IOException e) {
            System.out.println("执行命令失败,链接conn:" + connection + ",执行的命令：" + cmd + "  " + e);
            e.printStackTrace();
        }
        return result;

    }

    /**
      * 解析脚本执行返回的结果集
      * 
      * @param in
      *            输入流对象
      * @param charset
      *            编码
      * @return 以纯文本的格式返回
      */
    private static String processStdout(InputStream in, String charset) {
        InputStream stdout = new StreamGobbler(in);
        StringBuffer buffer = new StringBuffer();
        ;
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(stdout, charset));
            String line = null;
            while ((line = br.readLine()) != null) {
                buffer.append(line + "\n");
            }
            br.close();
        } catch (UnsupportedEncodingException e) {
            System.out.println("解析脚本出错：" + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("解析脚本出错：" + e.getMessage());
            e.printStackTrace();
        }
        return buffer.toString();
    }

}
