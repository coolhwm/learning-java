package learning.java.socket.udp;

import java.io.IOException;
import java.net.*;

/**
 * UdpClient
 *
 * @author hwm
 * @since 2016/8/16
 **/
public class UdpClient {

    public static void main(String[] args) throws IOException {
        //定义服务器地址
        InetAddress ip = InetAddress.getByName("127.0.0.1");
        int port = 8800;
        byte[] data = "system 1".getBytes();
        //创建数据报
        DatagramPacket packet = new DatagramPacket(data, data.length, ip, port);
        //创建Socket
        DatagramSocket socket = new DatagramSocket();
        //发送
        socket.send(packet);

        byte[] buff = new byte[1024];
        DatagramPacket responsePacket = new DatagramPacket(buff, 0, buff.length);
        socket.receive(responsePacket);
        String reply = new String(buff,0,buff.length);
        System.out.println("reply = " + reply);
    }
}
