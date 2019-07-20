package com.example.red_social.Util;

public class Publicacion {
    private int id;
    private int id_user;
    private String text;
    private String response_id;
    private String created_at;

    public Publicacion() {
    }



    public Publicacion(int id, int id_user, String text, String response_id, String created_at) {
        this.id = id;
        this.id_user = id_user;
        this.text = text;
        this.response_id = response_id;
        this.created_at = created_at;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getResponse_id() {
        return response_id;
    }

    public void setResponse_id(String response_id) {
        this.response_id = response_id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
