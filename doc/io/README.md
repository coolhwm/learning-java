# Java I/O - 学习笔记

## 1. 编码问题
- 字符转字节序列
	- 使用`s.getBytes("GBK")`可以将字符串转换成指定编码的字节序列；
	- 若没有指定编码则使用项目默认的编码；
- 字节序列转字符串
	- 使用`new String(bytes,encode)`可以将字节序列转换成指定编码的字符串；
	- 当字节序列是某种编码时，转换成字符串也要使用这种编码方式，否则会乱码；
- 常用字符编码占用字节数
	- `GBK`：中文2字节，英文1字节；与`GB2312`一样；
	- `UTF-8`：中文3字节；英文1字节；
	- `UTF-16`：中文3字节，英文2字节；和`Unicode`一样；
	- `UTF-16BE`：中文2字节，英文2字节；
	- `IOS-8859-1`：中文1字节；英文1字节；与`ASCII`一样；
- 常见数据类型占用字节数
	- `boolen`：8位1个字节；
	- `int`：32位，4个字节；
	- `float`：32位 4个字节；
	- `double`：64位8个字节；
	- `char`：16位，2个字节；
	- `byte`：8位1个字节；
	- `short`：6位 2个字节；
	- `long`：64位 8个字节；
- Java的字节编码
	- Java是双字节编码，编码是`UTF-16BE`编码；
- 文本文件的编码
	- 文本文件就是字节序列，按照某种编码方案序列化为`byte`的存储结果，可以是任意编码的字节序列；
	- `Java`中的文本`cahr`是16位无符号整数，是字符的Unicode编码（双字节编码）；
	- 如果在中文机器上直接创建文件，那么默认保存为`ANSI`编码,，在中文系统下即为`GBK`；
	- 在工程中创建文件，编码默认是用项目编码进行保存；

## 2. File 类
- 基本概念
	- `java.io.File`用于表示文件或目录；
	- `File`类只用于表示文件或目录的信息，而不能访问内容；
- 路径分隔符
	- 可以使用双正斜杠`\\`或反斜杠`/`路径分隔符；
	- 推荐使用`File.separator`，避免Windows和Linux的差异；、
- 基本API
	- `File()`：可以使用多种方式构造；直接使用文件或使用路径和文件名；
	- `exists()`：是否存在文件；
	- `delete()`：删除文件或目录；
	- `isDirectory()`：是否目录；不是目录或目录不存在返回`false`；
	- `isFile()`：是否一个文件；不是文件或文件不存在返回`false`；
	- `createNewFile()`：创建新文件；
	- `mkdir()`：创建目录；`mkdirs()`创建多级目录；
	- `list()`：返回目录下的文件名；
	- `getPath()`：获取全路径；
	- `getName()`：获取文件名；
	- `getParent()`：获取上级目录；

## 3. RandomAccessFile 类
- 基本概念
	- 提供对文件内容的访问，可以读写文件；
	- 支持随机访问文件，支持访问文件任意位置；
	- 在硬盘上的文件是字节存储；
- 读写模式
	- `rw`读写,`r`只读；
	- 构造`RandomAccessFile`时要指定读写模式；
	- 打开文件时文件指针的开头；
- 文件内容操作
	- `write()`：一次写入一个直接，指针指向下一个位置；底层方法；
	- `read()`：读一个字节；底层方法；
	- 提供各种上层方法，可以写入各种类型，包括`String`,`Integer`等；
	- 文件读写完成后需要关闭文件；

## 4. 字节流
- `InputStream`：输入流的抽象类；
- `OutputStream`：输出流的抽象类；

### 4.1FileInputStream
- 基本概念
	- 继承了`InputStream`；
	- 抽象了应用程序读取数据的的方式；
	- 使用时会抛出`IOException`，需要进行处理；
	- 流使用完毕后需要关闭，有可能出现意想不到的错误；
	- 按位读取时，可以使用`& 0xff`将高位清零，避免数据转换错误；
	- 单字节读取不适合读较大的文件，效率和多字节读取相差较大；
- 基本API
	- `in.read()`：读取一个字节无符号填充到int低8位，-1是EOF；
	- `in.read(byte[] buff)`：批量字节读取，读取数据直接填充到字节数组；返回值为读到的字节个数；
	- `in.read(btye[] buff, int start, int size)`：`start`为数组的起始位置，`size`是读取的字节数量；
	- `in.close()`：关闭输入流；

### 4.2 FileOutputStream
- 基本概念
	- 继承了`OutputStream`；
	- 抽象了应用程序写出数据的方式；
	- 构造时可以指定追加内容还是创建新文件；
- 基本API
	- `out.write(int b)`：写出一个byte到流，b的低8位；
	- `out.write(byte[] buff)`：将字节数组都写入到流；
	- `out.write(byte[], int start, int size)`：将字节数组从指定位置开始写入指定长度到流；
	- `out.close()`：关闭输出流；
	- `out.flush()`：刷新流，确保都被写出；

### 4.3 DataOutputStream/DataInputStream
- 基本概念
	- 对普通文件流的扩展封装， 可以更方便的读取各种基本Java数据类型；
	- 使用装饰模式实现，包装了最基本的读写方法；
	- 构造时需要传入`FileInputStream`和`FileOutputStream`；
- 基本API
	- 读取方法：`wirteInt`,`writeDouble`,`writeLong`,`writeUTF`,`writeChars`；
	- 写入方法：`readInt`,`readDouble`,`readLong`,`readUTF`,`readChars`；

### 4.4 BufferedOutputStream/BufferedInputStream
- 基本概念
	- 为IO带缓冲区的操作，一般打开文件进行写入或读取操作时，都会加上缓冲，这种流模式提高了IO的性能；
	- 写出数据时需要手工`flush`；
	- 性能相比单字节读取性能较高，但没有批量字节读取性能高；

### 4.5 EOF
- 文件结尾，读到`-1`就读到结尾；

## 5. 字符流	
- `Reader`：输入流的抽象类；
- `Writer`：输出流的抽象类；
- 一次处理一个字符，底层仍然是基本的字节序列，由于编码不同，一次读取的直接大小不一定相同。

### 5.1 InputStreamReader
- 基本概念
	- 完成`byte`流解析为`char`流，按照编码进行解析；
	- 和字节流一样，可以使用字符数组`char[] buffer`进行读取；
	- 默认解析编码为工程编码，在构造时可以指定解析的编码；


### 5.2 OutputStreamWriter 
- 基本概念
	- 完成`char`流解析为`byte`流，按照编码进行解析；
	- 输出流可以指定编码格式，可以进行编码转换；

### 5.3 FileWrite/FileReader
- 基本概念
	- 可以直接使用个文件构造；
	- 无法指定编码；
	- 可以设置是否追加内容；

### 5.4 BufferedReader/BufferedWriter/PrintWriter
- 基本概念
	- 字符流过滤器；
	- 拥有`readLine()`等封装操作，不识别换行符；
	- 可以在构造`InputStreamReader`时指定文件编码并封装；
	- `PrintWriter`的构造会更加简单，输出换行操作比较方便；

``` java
//BufferedReader的构造函数
BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("F:/nohup.out"), "GBK"));
String line;
while((line = br.readLine()) != null){
    System.out.println(line);
}
```

## 5. 序列化流
- 基本概念
	- 对象的序列化，就是将`Object`转换成`byte`序列，反之叫做对象的反序列化；
	- 对象要序列化，必须实现`Serializable`接口，否则将出现异常；接口没有任何方法，只是一个规定与标准；
	- 序列化后的对象，可以保存在文件中，也可以通过网络进行传输；
	- 字段加上`transient`的字段默认不会被序列化；需要自己完成元素的序列化；
- 自定义序列化
	- 可以在对象中实现`writeObject`和`readObject`方法自定义序列化行为；
	- 可以实现`Externalizable`接口自定义序列化行为；
	- 自定义序列化可以提高性能，可以进行加密等安全操作；
- 继承关系时的序列化操作
	- 父类实现`Serializable`接口，子类自动实现了该接口；
	- 若父类没有实现`Serializable`则会显示调用其构造方法创建实例；
```
//自定义序列化
private void writeObject(java.io.ObjectOutputStream s) throws IOException {
    s.defaultWriteObject();
    s.writeUTF(stuAge);  //自定义序列化字段
}
private void readObject(java.io.ObjectInputStream s) throws IOException, ClassNotFoundException {
    s.defaultReadObject();
    this.stuAge = s.readUTF(); //自定义序列化字段
}
```

### 5.1 ObjectOutputStream
- 基本概念
	- 是一个包装的过滤流，需要使用一个`OutputStream`构造；
	- 完成对象的序列化；
- 基本API
	- `wirteObject`：将一个对象序列化并写入流中；

### 5.2 ObjectInputStream 
- 基本概念
	- 是一个包装的过滤流，需要使用一个`IntputStream`构造；
	- 完成对象的反序列化；
- 基本API
	- `readObject`：将一个对象反序列化并生成一个对象实例；
