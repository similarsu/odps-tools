package cn.seventree.odps.tools;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * Created by coolearth on 17-9-12.
 */
public class MacVandorUtils {
    private MacVandorUtils(){

    }

    public static void process(String orgName,String targetName,String split,String oldValue,String newValue){
        try {
            BufferedReader bufferedReader=new BufferedReader(new FileReader(orgName));
            String line=null;
            FileWriter fileWriter=new FileWriter(targetName);
            while ((line=bufferedReader.readLine())!=null){
                String[] contents=line.split(split);
                if(contents.length>0){
                    StringBuilder stringBuilder=new StringBuilder();
                    for(int i=0;i<contents.length;i++){
                        if(i%2==1){
                            stringBuilder.append(contents[i].replaceAll(oldValue,newValue));
                        }else {
                            stringBuilder.append(contents[i]);
                        }
                    }
                    fileWriter.write(stringBuilder.toString().replaceAll("\\\\'","`")+"\r\n");
                }else {
                    fileWriter.write(line.replaceAll("\\\\'","`")+"\r\n");
                }

            }
            fileWriter.flush();
            fileWriter.close();
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
