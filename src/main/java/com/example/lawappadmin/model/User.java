package com.example.lawappadmin.model;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class User {
    String username;
    String password;
    boolean enabled;

    boolean admin;

    public User(String username, String password, boolean enabled, boolean admin) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.admin = admin;
    }

    public User(String text, boolean isList){
        String [] contents = text.split(",");
        if(!isList) {
            this.username = getValue(contents[2]);
            this.password = getValue(contents[3]);
            this.enabled = getValue(contents[4]).equals("true");
            this.admin = getValue(contents[5]).equals("true");
        }else {
            this.username = getValue(contents[1]);
            this.password = getValue(contents[2]);
            this.enabled = getValue(contents[3]).equals("true");
            this.admin = getValue(contents[4]).equals("true");
        }
    }

    private String  getValue(String source){
        StringBuilder builder = new StringBuilder();
        char [] chars = source.toCharArray();
        for (char aChar : chars) {
            if (aChar != '"' && aChar != '}' && aChar != ']'){
                builder.append(aChar);
            }
        }
        return builder.toString().split(":")[1];
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                '}';
    }
}
