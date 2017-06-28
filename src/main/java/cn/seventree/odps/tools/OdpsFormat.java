package cn.seventree.odps.tools;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/28.
 */
public class OdpsFormat {
    private OdpsFormat(){

    }
    private volatile static OdpsFormat odpsFormat;
    public static OdpsFormat getInstance(){
        if(odpsFormat==null){
            synchronized (OdpsFormat.class){
                if(odpsFormat==null){
                    odpsFormat=new OdpsFormat();
                }
            }
        }
        return odpsFormat;
    }

    public List<DBColumn> generate(String file){
        List<DBColumn> dbColumns=new ArrayList<DBColumn>();
        try {
            InputStreamReader inputStreamReader=new InputStreamReader(new FileInputStream(file),"GBK");
            BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
            String line=null;
            while ((line=bufferedReader.readLine())!=null){
                String[] columns=line.split("\\|");
                if(columns.length!=5){
                    continue;
                }
                dbColumns.add(new DBColumn(columns[1].trim(),columns[2].trim(),columns[3].trim(),columns[4].trim()));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dbColumns;
    }
}
