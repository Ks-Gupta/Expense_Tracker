package com.expensetracker.models;

public class Category {

    private int Id;
    private String Name;

    public Category(int Id, String Name) {
        this.Id = Id;
        this.Name = Name;
    }

    public Category(String Name) {
        this.Name = Name;
    }

    public int getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    @Override
    public String toString() {
        return Name;
    }

}
