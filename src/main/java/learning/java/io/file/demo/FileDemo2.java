package learning.java.io.file.demo;


import learning.java.io.file.utils.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import java.io.File;

/**
 *  file iterate demo
 */
@RunWith(BlockJUnit4ClassRunner.class)
public class FileDemo2 {

    @Test
    public void listFiles(){
        File dir = new File("G:/2/ServiceManageDeskFlowNX3");
        FileUtils.listDirectory(dir);
    }
}
