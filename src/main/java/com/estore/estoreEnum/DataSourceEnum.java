package com.estore.estoreEnum;

public enum DataSourceEnum {

    ds1("ds1"), ds2("ds2");

    private String key;

    DataSourceEnum(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
