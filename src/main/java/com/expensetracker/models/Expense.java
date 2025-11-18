package com.expensetracker.models;

public class Expense {

    private int id;
    private int categoryId;
    private String name;
    private String categoryName;
    private double amount;
    private String date;
    private String note;

    // constructor for inserting new expenses
    public Expense(int categoryId, double amount, String date, String note) {
        this.categoryId = categoryId;
        this.amount = amount;
        this.date = date;
        this.note = note;
    }

    //constructor for reading expenses from DB

    public Expense(int id, int categoryId, String categoryName, double amount, String date, String note) {
        this.id = id;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.amount = amount;
        this.date = date;
        this.note = note;
    }

    //Getter and Setter

    public int getId() {
        return id;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public String getName() {
        return name;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public double getAmount() {
        return amount;
    }

    public String getDate() {
        return date;
    }

    public String getNote() {
        return note;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setNote(String note) {
        this.note = note;
    }

}
