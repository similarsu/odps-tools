package cn.seventree.odps.tools;

import java.io.*;
import java.util.List;

/**
 * Created by Administrator on 2017/6/28.
 */
public class SelectStatementGenerator {
    private SelectStatementGenerator(){

    }

    private volatile static SelectStatementGenerator odpsSelectGenerate;
    public static SelectStatementGenerator getInstance(){
        if(odpsSelectGenerate==null){
            synchronized (SelectStatementGenerator.class){
                if (odpsSelectGenerate==null){
                    odpsSelectGenerate=new SelectStatementGenerator();
                }
            }

        }
        return odpsSelectGenerate;
    }

    public void generate(List<DBColumn> dbColumns, File path) throws IOException {

        File file=new File(path,"select.txt");
        file.createNewFile();
        FileWriter fileWriter=new FileWriter(file);
        BufferedWriter bufferedWriter=new BufferedWriter(fileWriter);
        bufferedWriter.write(transList2String(dbColumns));
        bufferedWriter.flush();
        bufferedWriter.close();
        fileWriter.close();

    }

    private String transList2String(List<DBColumn> dbColumns){
        StringBuilder stringBuilder=new StringBuilder();
        for (int i=0; i<dbColumns.size();i++){

            stringBuilder.append(dbColumns.get(i).getField());
            if(i!=dbColumns.size()-1){
                stringBuilder.append(",");
            }
            stringBuilder.append("\r\n");
        }
        return stringBuilder.toString();
    }

}
