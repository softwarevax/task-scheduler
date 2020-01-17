package org.platform.quartz.deploy.utils;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

/**
 * @author twcao
 * @description 网络工具类
 * @project task-scheduler
 * @classname NetUtils
 * @date 2020/1/10 16:59
 */
public class NetUtils {

    /**
     * ipV4地址
     * @return
     */
    public static Set<String> getLocalAllIps() {
        Set<String> sets = new HashSet<>();
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            NetworkInterface networkInterface;
            Enumeration<InetAddress> inetAddresses;
            InetAddress inetAddress;
            String ip;
            while (networkInterfaces.hasMoreElements()) {
                networkInterface = networkInterfaces.nextElement();
                inetAddresses = networkInterface.getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    inetAddress = inetAddresses.nextElement();
                    if (inetAddress != null && inetAddress instanceof Inet4Address) {
                        ip = inetAddress.getHostAddress();
                        sets.add(ip);
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return sets;
    }

    /**
     * 是否是合法端口
     * @param port
     * @return
     */
    public static boolean isPort(int port) {
        if(port >= 1 && port <= 65535) {
            return true;
        }
        return false;
    }
}
