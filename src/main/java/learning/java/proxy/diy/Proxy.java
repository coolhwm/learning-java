package learning.java.proxy.diy;

import org.apache.commons.io.FileUtils;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 *  自定义动态代理类
 *
 * @author hwm
 * @since 2016/9/5
 **/
public class Proxy {

    public static Object newProxyInstance(Class infce, InvocationHandler h) throws Exception {
        //拼装代码
        String source = generateProxySourceCode(infce);

        //输出文件
        String fileName = System.getProperty("user.dir") + "\\target\\classes\\learning\\java\\proxy\\diy\\$Proxy0.java";
        System.out.println(fileName);
        File file = new File(fileName);
        FileUtils.writeStringToFile(file, source, "UTF-8");

        //获取系统编译器
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        //获取文件管理器
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
        //获取文件
        Iterable<? extends JavaFileObject> units = fileManager.getJavaFileObjects(file);
        //获取编译任务
        JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, null, null, null, units);
        //编译
        task.call();
        //关闭文件管理器
        fileManager.close();

        //获取类加载器
        ClassLoader cl = ClassLoader.getSystemClassLoader();
        //加载类
        Class<?> c = cl.loadClass("learning.java.proxy.diy.$Proxy0");

        //获取构造器(在动态源码中编写的)
        Constructor<?> constructor = c.getConstructor(InvocationHandler.class);
        //创建对象
        return constructor.newInstance(h);
    }

    public static String generateProxySourceCode(Class infce) {
        String methodStr = "";
        for (Method method : infce.getMethods()) {
            methodStr += "    @Override\n" +
                    "    public void " + method.getName() + "() {\n" +
                    "       try{" +
                    "       Method md = " + infce.getName() + ".class.getMethod(\"" + method.getName() +"\");\n"+
                    "       h.invoke(this, md);\n" +
                    "       } catch(Exception e){e.printStackTrace();} \n" +
                    "    }\n";
        }

        return "package learning.java.proxy.diy; \n" +
        "import learning.java.proxy.diy.InvocationHandler; \n" +
        "import java.lang.reflect.Method; \n" +
        "public class $Proxy0  implements " + infce.getName() + "{\n" +
        "    private InvocationHandler h;" +
        "    public $Proxy0(InvocationHandler h) {\n" +
        "        super(); \n" +
        "        this.h = h;\n" +
        "    }\n" +
            methodStr +
        "}";
    }
}
