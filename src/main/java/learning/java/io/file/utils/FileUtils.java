package learning.java.io.file.utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * list File usage method, like filter, iterate
 */
public class FileUtils {

    /**
     * list all child file
     */
    public static void listDirectory(File dir){
        if(!dir.exists()){
            throw new IllegalArgumentException("目录" + dir + "不存在");
        }
        if(!dir.isDirectory()){
            new IllegalArgumentException(dir + "不是目录");
        }
        File[] files = dir.listFiles();
        //iterate child files
        for (File file : files) {
            if (file.isDirectory()) {
                //System.out.println("--- start " + "directory: " + file.getPath() + " ---");
                listDirectory(file);
                //System.out.println("--- end " + "directory： " + file.getPath() + " ---");
            }
            else{
                long l = file.lastModified();
                Date date = new Date(l);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                System.out.println(file.getParent() + "\t" + file.getName() + "\t"  +  file.getPath() + "\t" + sdf.format(date) );
            }
        }
    }

}
