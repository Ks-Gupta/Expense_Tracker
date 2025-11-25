package com.expensetracker.controller;

import com.expensetracker.dao.CategoryDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AddCategoryController extends BaseController {

    @FXML
    private TextField categoryField;

    @FXML
    private Label messageLabel;

    private final CategoryDAO categoryDAO = new CategoryDAO();

    @FXML
    public void handleAddCategory() {
        String name = categoryField.getText().trim();

        if (name.isEmpty()) {
            messageLabel.setText("Category name cannot be empty!");
            messageLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        boolean success = categoryDAO.addCategory(name);

        if (success) {
            messageLabel.setText("Category added successfully!");
            messageLabel.setStyle("-fx-text-fill: green;");
            categoryField.clear();
        } else {
            messageLabel.setText("Error: Category already exists or DB error.");
            messageLabel.setStyle("-fx-text-fill: red;");
        }
    }

    @FXML
    public void goBack() {
        switchScene("main_menu.fxml", "Expense Tracker - Menu");
    }

}
