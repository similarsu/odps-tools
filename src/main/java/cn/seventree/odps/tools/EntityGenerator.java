package cn.seventree.odps.tools;

import org.apache.commons.lang3.StringUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2017/6/28.
 */
public class EntityGenerator {
    private EntityGenerator(){

    }

    private volatile static EntityGenerator entityGenerator;
    public static EntityGenerator getInstance(){
        if(entityGenerator ==null){
            synchronized (EntityGenerator.class){
                if (entityGenerator ==null){
                    entityGenerator =new EntityGenerator();
                }
            }

        }
        return entityGenerator;
    }

    public void generate(List<DBColumn> dbColumns, File path) throws IOException {

        File file=new File(path,"entity.txt");
        file.createNewFile();
        FileWriter fileWriter=new FileWriter(file);
        BufferedWriter bufferedWriter=new BufferedWriter(fileWriter);
        bufferedWriter.write(transList2String(dbColumns));
        bufferedWriter.flush();
        bufferedWriter.close();
        fileWriter.close();
    }

    private String transList2String(List<DBColumn> dbColumns){
        StringBuilder privateStringBuilder=new StringBuilder();
        StringBuilder getSetStringBuilder=new StringBuilder();
        for (int i=0; i<dbColumns.size();i++) {
            String orField=dbColumns.get(i).getField();
            String field=transField(orField);
            String type=transType(dbColumns.get(i).getType());
            String comment=dbColumns.get(i).getComment();
            privateStringBuilder.append("private");
            privateStringBuilder.append(" ");
            privateStringBuilder.append(type);
            privateStringBuilder.append(" ");
            privateStringBuilder.append(field);
            privateStringBuilder.append(";");

            if(StringUtils.isNotBlank(comment)){
                privateStringBuilder.append("//");
                privateStringBuilder.append(comment);
            }
            privateStringBuilder.append("\r\n");
            if("Date".equals(type)){
                getSetStringBuilder.append("@Temporal(TemporalType.TIMESTAMP)\r\n");
            }
            getSetStringBuilder.append("@Column(name=\"");
            getSetStringBuilder.append(orField);
            getSetStringBuilder.append("\")");
            getSetStringBuilder.append("\r\n");
            getSetStringBuilder.append("public");
            getSetStringBuilder.append(" ");
            getSetStringBuilder.append(type);
            getSetStringBuilder.append(" ");
            getSetStringBuilder.append("get");
            getSetStringBuilder.append(StringUtils.capitalize(field));
            getSetStringBuilder.append("(");
            getSetStringBuilder.append(")");
            getSetStringBuilder.append("{");
            getSetStringBuilder.append("\r\n");
            getSetStringBuilder.append("return");
            getSetStringBuilder.append(" ");
            getSetStringBuilder.append(field);
            getSetStringBuilder.append(";");
            getSetStringBuilder.append("\r\n");
            getSetStringBuilder.append("}");
            getSetStringBuilder.append("\r\n");
            getSetStringBuilder.append("public");
            getSetStringBuilder.append(" ");
            getSetStringBuilder.append("void");
            getSetStringBuilder.append(" ");
            getSetStringBuilder.append("set");
            getSetStringBuilder.append(StringUtils.capitalize(field));
            getSetStringBuilder.append("(");
            getSetStringBuilder.append(type);
            getSetStringBuilder.append(" ");
            getSetStringBuilder.append(field);
            getSetStringBuilder.append(")");
            getSetStringBuilder.append("{");
            getSetStringBuilder.append("\r\n");
            getSetStringBuilder.append("this.");
            getSetStringBuilder.append(field);
            getSetStringBuilder.append("=");
            getSetStringBuilder.append(field);
            getSetStringBuilder.append(";");
            getSetStringBuilder.append("\r\n");
            getSetStringBuilder.append("}");
            getSetStringBuilder.append("\r\n");

        }
        return privateStringBuilder.toString()+"\r\n"+getSetStringBuilder.toString();
    }

    private String transField(String names){
        StringBuilder stringBuilder=new StringBuilder();
        int i=0;
        for (String name:names.split("_")) {
            if(i==0){
                stringBuilder.append(name);
            }else{
                stringBuilder.append(StringUtils.capitalize(name));
            }
            i++;
        }
        return stringBuilder.toString();
    }

    private String transType(String type){
        if("datetime".equals(type)) {
            return "Date";
        }else if("string".equals(type)){
            return "String";
        }else if("bigint".equals(type)){
            return "Long";
        }
        else {
            return StringUtils.capitalize(type);
        }
    }
}
