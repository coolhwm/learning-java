package learning.java.socket;


import org.junit.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

public class InetAddressDemo {

    @Test
    public void test1() throws UnknownHostException {
        //获取本机的IP地址
        InetAddress address = InetAddress.getLocalHost();

        System.out.println("计算机名称：" + address.getHostName());
        System.out.println("计算机IP地址：" + address.getHostAddress());
        byte[] b = address.getAddress();
        System.out.println("直接数组形式的IP" + Arrays.toString(b) );
        System.out.println(address);
    }
}
