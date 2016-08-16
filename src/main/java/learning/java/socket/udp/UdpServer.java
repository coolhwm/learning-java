package learning.java.socket.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * Server
 *
 * @author hwm
 * @since 2016/8/16
 **/
public class UdpServer {

    public static void main(String[] args) throws IOException {
        //创建socket
        DatagramSocket socket = new DatagramSocket(8800);
        //创建数据
        byte[] data = new byte[1024];
        DatagramPacket packet = new DatagramPacket(data, data.length);
        //接受数据，阻塞
        System.out.println("服务器已启动");
        socket.receive(packet);
        String info = new String(data,0,packet.getLength());
        System.out.println("info = " + info);

        //响应客户端
        InetAddress clientIp = packet.getAddress();
        int clientPort = packet.getPort();
        byte[] response = "hello".getBytes();
        DatagramPacket responsePacket = new DatagramPacket(response, response.length, clientIp, clientPort);
        socket.send(responsePacket);

        //关闭资源
        socket.close();

    }
}
