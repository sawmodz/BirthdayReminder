package com.blizz.birthdayreminder.bo;

public class Club {
    int id;
    String name;


    public Club() {
    }

    public Club(String name) {
        this.name = name;
    }

    public Club(int id, String name) {
        this.id = id;
        this.name = name;
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
}
