package com.example.red_social.Util;

public class Usuario {

    private int id;
    private String name;
    private String surname;
    private String direction;
    private String country;
    private String birthday;
    private String nick;
    private String email;
    private String password;
    private String image;
    private String description;
    private String getToken;

    public Usuario() {
    }

    public Usuario(int id,  String name, String surname, String direction, String country, String birthday, String nick, String email, String password, String image, String description) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.direction = direction;
        this.country = country;
        this.birthday = birthday;
        this.nick = nick;
        this.email = email;
        this.password = password;
        this.image = image;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGetToken() {
        return getToken;
    }

    public void setGetToken(String getToken) {
        this.getToken = getToken;
    }
}
