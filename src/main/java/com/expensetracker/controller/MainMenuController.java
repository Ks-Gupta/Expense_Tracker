package com.expensetracker.controller;



import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainMenuController extends BaseController{

    @FXML
    public void openAddCategory() {
        switchScene("add_category.fxml", "Add Category");
    }

    @FXML
    public void openAddExpense() {
        switchScene("add_expense.fxml", "Add Expense");
    }

    @FXML
    public void openDashboard() {
        switchScene("dashboard.fxml", "Expense Dashboard");
    }

    @FXML
    public void openCharts() {
        switchScene("charts.fxml", "Analytics");
    }
}
