package learning.java.socket.tcp;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 基于TPC协议的SOCKET通信，服务器断
 *
 * @author hwm
 * @since 2016/8/15
 **/
public class Server {

    public static void main(String[] args) throws IOException {
        //启动服务端
        ServerSocket server = new ServerSocket(8888);
        System.out.println("服务器即将启动，等待客户端连接");
        int count = 0;
        //接受请求
        while (true) {
            Socket socket = server.accept();
            ServerThread thread = new ServerThread(socket);
            thread.start();
            count ++;
            System.out.println("count = " + count);
        }

        //server.close();
    }
}
