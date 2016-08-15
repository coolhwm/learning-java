package learning.java.socket.tcp;

import java.io.*;
import java.net.Socket;

/**
 * 客户端
 *
 * @author hwm
 * @date 2016/8/15
 **/
public class Client {

    public static void main(String[] args) throws IOException {
        //建立socket
        Socket socket = new Socket("127.0.0.1", 8888);
        //获取输出流，发送消息~
        OutputStream os = socket.getOutputStream();
        PrintWriter pw = new PrintWriter(os);
        for (int i = 0; i < 10; i++) {
            pw.println("--->" + i);
            pw.flush();
        }
        socket.shutdownOutput();

        //获取输入流程，接收消息
        InputStream is = socket.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line;
        while((line = br.readLine()) != null){
            System.out.println("response = " + line);
        }
        socket.shutdownInput();

        //关闭资源
        br.close();
        pw.close();
        socket.close();
    }
}
