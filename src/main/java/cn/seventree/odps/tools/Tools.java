package cn.seventree.odps.tools;

import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/6/28.
 */
public class Tools {
    /*public static void main(String[] args) {
        String odpsFile;
        String odpsFormatDir;
        try {
            odpsFile=readInput("输入代处理的odps描述文件位置:");
            odpsFormatDir=readInput("输入处理后存放的目录:");
            File path=FileUtils.createPath(odpsFormatDir);
            List<DBColumn> dbColumns=OdpsFormat.getInstance().generate(odpsFile);
            SelectStatementGenerator.getInstance().generate(dbColumns,path);
            CreateOdpsStatementGenerator.getInstance().generate(dbColumns,path);
            CreateAdsStatementGenerator.getInstance().generate(dbColumns,path);
            DocStatementGenerator.getInstance().generate(dbColumns,path);
            EntityGenerator.getInstance().generate(dbColumns,path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    public static void main(String[] args) {
        MacVandorUtils.process("/home/coolearth/oui.csv","/home/coolearth/oui.csv.f","\"",",","，");
    }

    private static String readInput(String prompt) throws IOException {
        String result=null;
        do{
            System.out.print(prompt);
            InputStreamReader inputStreamReader=new InputStreamReader(System.in);
            result=new BufferedReader(inputStreamReader).readLine();
        }while (StringUtils.isBlank(result));
        return result;
    }

}
