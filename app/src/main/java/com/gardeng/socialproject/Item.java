package com.gardeng.socialproject;

public class Item {
    public int image;
    public String name;
    public String content;

    public Item() {

    }

    public Item(int image, String name, String content) {
        this.image = image;
        this.name = name;
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
