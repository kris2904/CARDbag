package com.example.cardgit;

import java.io.Serializable;

public class Category implements Serializable {
    private String name;
    private int id;

    public Category(int id, String name)
    {
        this.id=id;
        this.name=name;

    }

    public int getId() {
        return id; }

    public String getName() {
        return name; }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;

    }
}