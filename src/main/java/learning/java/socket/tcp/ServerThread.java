package learning.java.socket.tcp;

import java.io.*;
import java.net.Socket;

/**
 * 处理线程
 *
 * @author hwm
 * @date 2016/8/16
 **/
public class ServerThread extends Thread {
    private Socket socket;

    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            System.out.println("服务端已接收客户端请求：" + socket.getInetAddress().toString());
            //读取信息
            InputStream is = socket.getInputStream();
            //缓冲流
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            //读取流数据
            String info;
            while((info = br.readLine()) != null){
                System.out.println("客户端提交信息 info = " + info);
            }
            socket.shutdownInput();

            //响应客户端
            OutputStream os = socket.getOutputStream();
            PrintWriter pw = new PrintWriter(os);
            pw.println("登录成功");
            pw.flush();
            socket.shutdownOutput();

            //关闭资源
            System.out.println("客户端关闭连接，信息传递完毕");
            br.close();
            pw.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
