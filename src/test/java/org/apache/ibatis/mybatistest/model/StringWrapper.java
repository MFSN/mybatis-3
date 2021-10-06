package org.apache.ibatis.mybatistest.model;

public class StringWrapper {
    private String value;

    public StringWrapper(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "StringWrapper{" +
                "value='" + value + '\'' +
                '}';
    }
}
