package com.example.red_social.Util;

public class Like {
    private int id;
    private int id_publication;
    private int id_user_emmiter;
    private int id_user_reciver;

    public Like() {
    }

    public Like(int id, int id_publication, int id_user_emmiter, int id_user_reciver) {
        this.id = id;
        this.id_publication = id_publication;
        this.id_user_emmiter = id_user_emmiter;
        this.id_user_reciver = id_user_reciver;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_publication() {
        return id_publication;
    }

    public void setId_publication(int id_publication) {
        this.id_publication = id_publication;
    }

    public int getId_user_emmiter() {
        return id_user_emmiter;
    }

    public void setId_user_emmiter(int id_user_emmiter) {
        this.id_user_emmiter = id_user_emmiter;
    }

    public int getId_user_reciver() {
        return id_user_reciver;
    }

    public void setId_user_reciver(int id_user_reciver) {
        this.id_user_reciver = id_user_reciver;
    }
}
