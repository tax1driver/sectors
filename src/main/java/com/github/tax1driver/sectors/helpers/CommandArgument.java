package com.github.tax1driver.sectors.helpers;

public class CommandArgument {
    enum ArgumentType {
        TEXT,
        NUMBER,
        CUSTOM
    }

    private String name;
    private String desc;
    private ArgumentType type;
    private String customTypeRegex;
    private boolean isOptional;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public ArgumentType getType() {
        return type;
    }

    public void setType(ArgumentType type) {
        this.type = type;
    }

    public String getCustomTypeRegex() {
        return customTypeRegex;
    }

    public void setCustomTypeRegex(String customTypeRegex) {
        this.customTypeRegex = customTypeRegex;
    }

    public boolean isOptional() {
        return isOptional;
    }

    public void setOptional(boolean optional) {
        isOptional = optional;
    }

    public CommandArgument(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }
}
