package learning.java.socket;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class URLDemo {

    @Test
    public void testURL() throws MalformedURLException {
        //创建一个URL实例
        URL imooc = new URL("http://www.imooc.com");
        URL url = new URL(imooc, "/index.html?username=tom#test");
        System.out.println("协议：" + url.getProtocol());
        System.out.println("主机IP地址：" + url.getHost());
        System.out.println("端口号：" + url.getPort());
        System.out.println("查询参数：" + url.getQuery());
        System.out.println("相对路径(锚点)：" + url.getRef());
        System.out.println("默认端口号：" + url.getDefaultPort());
        System.out.println("文件路径：" + url.getPath());
    }

    @Test
    public void readHTML() throws IOException {
        URL url = new URL("http://www.baidu.com");
        InputStream is = url.openStream();
        InputStreamReader isr = new InputStreamReader(is, "UTF-8");
        BufferedReader br = new BufferedReader(isr);
        String line;
        while((line = br.readLine())!=null){
            System.out.println(line);
        }
        br.close();
        isr.close();
        is.close();
    }
}
