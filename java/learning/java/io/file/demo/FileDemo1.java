package learning.java.io.file.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import java.io.File;

/**
 *  file basic demo
 */
@RunWith(BlockJUnit4ClassRunner.class)
public class FileDemo1 {

    @Test
    public void getFileInfo(){
        File file = new File("G:/javaindex", "text.txt");
        System.out.println(file.getAbsolutePath());
        System.out.println(file.getName());
        System.out.println(file.getParent());
        System.out.println(file.getUsableSpace());
    }

    @Test
    public void createFileByPathAndFileName() throws Exception{
        File file = new File("G:/javaindex", "text.txt");
        if(!file.exists()){
            file.createNewFile();
        }
    }


    @Test
    public void createFile() throws Exception{
        File file = new File("G:/javaio");
        if(!file.exists()){
            file.createNewFile();
        }
        else{
            file.delete();
        }
    }


    @Test
    public void readFile() throws Exception{
        File file = new File("G:/javaio");
        System.out.println(file.exists());
        File index = new File("G:" + File.separator +"javaindex");
        if(!index.exists()){
            index.mkdir();
        }
        else{
            index.delete();
        }
    }
}
