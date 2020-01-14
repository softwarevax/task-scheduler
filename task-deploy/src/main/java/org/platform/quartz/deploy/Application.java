package org.platform.quartz.deploy;

import org.platform.quartz.deploy.git.GitHelper;
import org.platform.quartz.deploy.maven.MavenHelper;
import org.platform.quartz.deploy.ssh.IOUtils;
import org.platform.quartz.deploy.ssh.ganymed.GanymedSecureShell;

import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;

public class Application {

	public static void main(String[] args) throws Exception {

		String localPath = "D:/tmp/source_codes/data-tunnel-parent";

		String targetPath = "/data/test";

		// 1、拉取代码
		GitHelper.download("https://github.com/softwarevax/data-tunnel-parent.git", "softwarevax", "*******", localPath, "origin");

		// 2、替换配置文件
		String replace = "E:\\project\\idea\\git\\data-tunnel-parent\\bin";

		String path = localPath + "/bin";
		File file = new File(localPath + "/pom.xml");
		String content = IOUtils.toString(new FileInputStream(file), "utf-8");
		content = content.replace(replace, path);
		IOUtils.write(content, file);

		// 3、运行打包，如果不指定maven地址，可以从环境中获取
		MavenHelper.execute(localPath + "/pom.xml", "install", "G:/apache-maven-3.5.0-alibaba");

		// 4、压缩成tar.gz
		MavenHelper.compress(path + ".tar.gz", new File(path).listFiles());
		GanymedSecureShell secureShell = new GanymedSecureShell("120.79.255.186");
		secureShell.setStopIfAbsent(false);
		secureShell.login("root", "**********");

		// 5、文件上传
		secureShell.put(Arrays.asList(path + ".tar.gz"), targetPath);
		// 6、解压并程序启动. nohup命令是后台运行的，一直无法返回结果，需要定时关闭或使用其他命令

		secureShell.execute("tar -xvf /data/test/bin.tar.gz -C /data/test");
		secureShell.execute("nohup java -classpath /data/test/tunnel-test-1.0.0.jar:/data/test/lib/* org.platform.tunnel.test.App >  /data/test/test.log 2>&1");
		secureShell.close();
	}
}
