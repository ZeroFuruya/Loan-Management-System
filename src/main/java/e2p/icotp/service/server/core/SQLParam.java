package e2p.icotp.service.server.core;

public class SQLParam {
    private int type;
    private String field;
    private Object data;

    // new SQLParam(Types.VARCHAR, "id", employee.getId());
    public SQLParam(int type, String field, Object data) {
        this.type = type;
        this.field = field;
        this.data = data;
    }

    // GETTERS

    public int getType() {
        return type;
    }

    public String getField() {
        return field;
    }

    public Object getData() {
        return data;
    }

    // SETTERS

    public void setType(int value) {
        type = value;
    }

    public void setField(String value) {
        field = value;
    }

    public void setData(Object value) {
        data = value;
    }
}
