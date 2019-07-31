package com.example.red_social.Util;

public class Mensaje {
    private int id;
    private int id_emmit;
    private int id_recep;
    private String name_emmit;
    private String surname_emmit;
    private String nicK_emmit;
    private String name_recep;
    private String surname_recep;
    private String nick_recep;
    private int emmiter;
    private int reciver;
    private String text;
    private String image_emmit;
    private String image_recep;

    public Mensaje() {
    }

    public Mensaje(int id, int id_emmit, String name_emmit, String surname_emmit, String nicK_emmit, String name_recep, String surname_recep, String nick_recep, int emmiter, int reciver, String text, String image_emmit, String image_recep) {
        this.id = id;
        this.id_emmit = id_emmit;
        this.name_emmit = name_emmit;
        this.surname_emmit = surname_emmit;
        this.nicK_emmit = nicK_emmit;
        this.name_recep = name_recep;
        this.surname_recep = surname_recep;
        this.nick_recep = nick_recep;
        this.emmiter = emmiter;
        this.reciver = reciver;
        this.text = text;
        this.image_emmit = image_emmit;
        this.image_recep = image_recep;
    }

    public int getId_emmit() {
        return id_emmit;
    }

    public void setId_emmit(int id_emmit) {
        this.id_emmit = id_emmit;
    }

    public int getId_recep() {
        return id_recep;
    }

    public void setId_recep(int id_recep) {
        this.id_recep = id_recep;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName_emmit() {
        return name_emmit;
    }

    public void setName_emmit(String name_emmit) {
        this.name_emmit = name_emmit;
    }

    public String getSurname_emmit() {
        return surname_emmit;
    }

    public void setSurname_emmit(String surname_emmit) {
        this.surname_emmit = surname_emmit;
    }

    public String getNicK_emmit() {
        return nicK_emmit;
    }

    public void setNicK_emmit(String nicK_emmit) {
        this.nicK_emmit = nicK_emmit;
    }

    public String getName_recep() {
        return name_recep;
    }

    public void setName_recep(String name_recep) {
        this.name_recep = name_recep;
    }

    public String getSurname_recep() {
        return surname_recep;
    }

    public void setSurname_recep(String surname_recep) {
        this.surname_recep = surname_recep;
    }

    public String getNick_recep() {
        return nick_recep;
    }

    public void setNick_recep(String nick_recep) {
        this.nick_recep = nick_recep;
    }

    public int getEmmiter() {
        return emmiter;
    }

    public void setEmmiter(int emmiter) {
        this.emmiter = emmiter;
    }

    public int getReciver() {
        return reciver;
    }

    public void setReciver(int reciver) {
        this.reciver = reciver;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImage_emmit() {
        return image_emmit;
    }

    public void setImage_emmit(String image_emmit) {
        this.image_emmit = image_emmit;
    }

    public String getImage_recep() {
        return image_recep;
    }

    public void setImage_recep(String image_recep) {
        this.image_recep = image_recep;
    }
}