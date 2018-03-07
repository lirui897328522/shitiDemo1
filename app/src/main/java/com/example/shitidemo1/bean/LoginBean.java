package com.example.shitidemo1.bean;

/**
 * Created by 李瑞 on 2018/3/4.
 */

public class LoginBean {
    private int image;
    private String name;

    public LoginBean() {
    }

    public LoginBean(int image, String name) {
        this.image = image;
        this.name = name;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "LoginBean{" +
                "image=" + image +
                ", name='" + name + '\'' +
                '}';
    }
}
