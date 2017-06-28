package cn.seventree.odps.tools;

import java.io.File;

/**
 * Created by Administrator on 2017/6/28.
 */
public class FileUtils {
    public static File createPath(String path){
        File file=new File(path);
        if(!file.exists()){
            file.mkdirs();
        }
        return file;
    }
}
