package org.platform.quartz.deploy.ssh.jsch;

/**
 * @author twcao
 * @description jsch方式登录
 * @project task-scheduler
 * @classname JschSecureShell
 * @date 2020/1/13 11:01
 */
public class JschSecureShell {
    /**

     public static void jsch() throws Exception {
     long currentTimeMillis = System.currentTimeMillis();

     String command = "uname -a";
     JSch jsch = new JSch();
     Session session = jsch.getSession("root", "120.79.255.186", 22);
     session.setPassword("********");
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
}
