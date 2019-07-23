package com.example.red_social.Util;

public class Noticia {

    private int id;
    private String title;
    private int id_category;
    private String text;
    private String image;
    private int id_user;
    private String nameTitle;

    public Noticia() {
    }

    public Noticia(int id, String title, int id_category, String text, String image, int id_user, String nameTitle) {
        this.id = id;
        this.title = title;
        this.id_category = id_category;
        this.text = text;
        this.image = image;
        this.id_user = id_user;
        this.nameTitle = nameTitle;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId_category() {
        return id_category;
    }

    public void setId_category(int id_category) {
        this.id_category = id_category;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getNameTitle() {
        return nameTitle;
    }

    public void setNameTitle(String nameTitle) {
        this.nameTitle = nameTitle;
    }
}
