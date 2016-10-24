package io.netty.example.http.json;

/**
 * Created by CCC on 2016/10/24.
 */
public class DataModel {

    private String name;
    private String value;

    public DataModel(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
