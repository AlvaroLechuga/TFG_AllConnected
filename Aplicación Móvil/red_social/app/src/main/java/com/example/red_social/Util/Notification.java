package com.example.red_social.Util;

public class Notification {
    private String name_emmit;
    private String surname_emmit;
    private String description;
    private int id_user_emmit;

    public Notification() {
    }

    public Notification(String name_emmit, String surname_emmit, String description, int id_user_emmit) {
        this.name_emmit = name_emmit;
        this.surname_emmit = surname_emmit;
        this.description = description;
        this.id_user_emmit = id_user_emmit;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId_user_emmit() {
        return id_user_emmit;
    }

    public void setId_user_emmit(int id_user_emmit) {
        this.id_user_emmit = id_user_emmit;
    }
}
