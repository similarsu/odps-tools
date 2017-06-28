package cn.seventree.odps.tools;

/**
 * Created by Administrator on 2017/6/28.
 */
public class DBColumn {
    private String field;
    private String type;
    private String label;
    private String comment;

    public DBColumn(String field, String type, String label, String comment) {
        this.field = field;
        this.type = type;
        this.label = label;
        this.comment = comment;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
