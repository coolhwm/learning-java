# Java Socket - 学习笔记

## 1. 简介
- 网络通信的必备条件
	- IP地址；
	- 协议；
	- 端口号；
- `TCP/IP`协议
	- `TCP`：`Transmission Control Protocol`，传输控制协议；
	- `IP`：`Internet Protocol`，互联网协议；
	- `TCP/IP`协议也称`TCP/IP协议栈`，是世界上使用最广泛的协议；
	- 以TCP和IP为基础的不同层次上多个协议的集合；
- TCP / IP 模型
	- 分层架构：物理、数据链路、网络(`IP`)、传输(`TCP`)、应用（`HTTP,FTP,SMTP,TELNET`）；
- IP 地址
	- 为了实现网络中不同计算机之间的通信，每台机器必须有一个唯一的标识，这就是`IP地址`；
	- IP地址格式：32位的二进制数；
- 端口号
	- 一个主机可以同时运行不同的应用程序，端口号用于区分不同的应用程序；
	- 端口范围：`0~1023`为系统保留；`65535`为最大端口号；
	- `IP地址`和`端口号`组成了所谓`Socket`，`Socket`是网络上运行的程序之间双向通信链路的终结点，是`TCP`和`UDP`的基础；
	- 常用端口：`http`-80,`ftp`-21,`telnet`-23；
- Java 中对网络的支持
	- `InetAddress`：用于标识网络上的硬件资源；
	- `URL`：统一资源定位符，通过URL可以直接读取或写入网络上的数据；
	- `Sockets`：使用`TCP`协议实现网络通信的`Socket`相关类；
	- `Datagram`：使用`UDP`协议将数据保存在数据报中，通过网络进行通信；

## 2. InetAddress
- 基本概念
	- 表示互联网协议的IP地址；
- 获取`InetAddress`对象
	- 使用静态方法，通过各种方式获取其实例；
- 常用API
	- `InetAddress.getLocalHost()`：获取本机IP；
	- `addr.getHostAddress()`：获取主机IP地址；
	- `addr.getHostName()`：获取主机名称；

## 3. URL
- 基本概念
	- `URL(Uniform Resource Locator)`：统一资源定位符，表示Internet上某一资源的地址；
	- `URL`由协议名称与资源名称组成，中间用冒号隔开；
	-  `java.net.URL`是Java语言中URL的实现；
-  创建`URL`实例
	-  使用字符串构造；
	-  使用端口、主机、协议、文件创建；
	-  根据父URL创建子URL；
-  常用API
	-  `url.getProtocol()`：获取协议名称；
	-  `url.getHost()`：获取主机IP地址；
	-  `url.getPort()`：获取端口号，未指定时为`-1`；
	-  `url.getQuery()`：获取查询字符串；
	-  `url.getRef()`：获取相对路径（锚点）；
	-  `url.getDefaultPort()`：获取协议的默认端口号；
	-  `url.getPath()`：获取文件路径；
	-  `url.openStream()`：获取URL资源的输入流，用于读取资源数据；

## 4. Socket 通信
### 4.1 通信模型
- 服务器`Server`建立服务端`监听socket`，等待并接受连接请求；
- 客户端`Client`创建`连接socket`并向服务端发送请求；
- 服务端接受请求后创建`连接socket`，通信刚开始；
- 通信过程中，服务端和客户端通过`InputStream`和`OutputStream`进行数据交换；
- 结束通信时，双方需要分别关闭`socket`及相关资源；
![通信模型](./1471185721116.png)

### 4.2 通信实现步骤
- 创建`ServerSocket`和`Socket`；
- 打开连接到`Socket`的输入/输出流；
- 按照协议对`Socket`进行读/写操作；
- 关闭输入流、输出流，关闭`Socket`；

### 4.3 ServerSocket
> 此类实现服务器套接字。服务器套接字等待请求通过网络传入。它基于该请求执行某些操作，然后可能向请求者返回结果。 

- 创建服务端`ServerSocket`
	- 创建`Socket`时绑定指定的端口；
- 常用API
	- `accept()`：监听并接受到此Socket的链接；链接创建成功会创建一个`Socket`实例，与当前客户端进行通信；
	- `close()`：关闭`socket`；
	- `getInetAddress()`：返回服务器套接字的本地地址；
	- `getLocalPort()`：返回套接字监听的端口号；

### 4.4 Socket
> 此类实现客户端套接字（也可以就叫“套接字”）。套接字是两台机器间通信的端点。 

- 创建客户端`Socket`
	- 通过IP地址及端口号创建；
- 常用API
	- `close()`：关闭套接字；
	- `getInputStream()`：获取输入流；
	- `getOutputStream()`：获取输出流；
	- `shutdownInput()`：关闭输入流；
	- `shutdownOutput()`：关闭输出流；

### 4.5 简单范例
服务端：
``` java
//启动服务端
ServerSocket server = new ServerSocket(8888);
System.out.println("服务器即将启动，等待客户端连接");
//接受请求
Socket socket = server.accept();
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
System.out.println("客户端关闭连接，信息传递完毕");
//关闭资源
socket.shutdownInput();
br.close();
socket.close();
server.close();
```

客户端：
``` java
//建立socket
Socket socket = new Socket("127.0.0.1", 8888);
//获取输出流，发送消息
OutputStream os = socket.getOutputStream();
PrintWriter pw = new PrintWriter(os);
for (int i = 0; i < 10; i++) {
    pw.println("--->" + i);
    pw.flush();
}
//关闭资源
socket.shutdownOutput();
pw.close();
socket.close();
```

## 4. UDP 通信
### 4.1 基本概念
- UDP协议是无连接，不可靠的；
- 以数据报`Datagram`作为数据载体；
- 使用`DatagramPacket`和`DatagrameSocket`进行通信；

### 4.2 DatagramPacket


### 4.2 DatagramSocket