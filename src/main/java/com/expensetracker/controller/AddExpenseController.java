package com.expensetracker.controller;

import com.expensetracker.dao.CategoryDAO;
import com.expensetracker.dao.ExpenseDAO;
import com.expensetracker.models.Category;
import com.expensetracker.models.Expense;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class AddExpenseController {

    @FXML
    private ComboBox<Category> categoryCombo;

    @FXML
    private TextField amountField;

    @FXML
    private DatePicker datePicker;

    @FXML
    private TextArea noteField;

    @FXML
    private Label messageLabel;

    private final CategoryDAO categoryDAO = new CategoryDAO();
    private final ExpenseDAO expenseDAO = new ExpenseDAO();

    @FXML
    public void initialize() {
        // Load categories inside ComboBox
        categoryCombo.getItems().addAll(categoryDAO.getAllCategories());
    }

    @FXML
    public void handleAddExpense() {
        Category selectedCategory = categoryCombo.getValue();
        String amountText = amountField.getText().trim();
        String date = (datePicker.getValue() != null)
                ? datePicker.getValue().toString()
                : "";
        String note = noteField.getText().trim();

        // Validation
        if (selectedCategory == null) {
            showError("Please select a category.");
            return;
        }
        if (amountText.isEmpty()) {
            showError("Amount cannot be empty.");
            return;
        }
        if (date.isEmpty()) {
            showError("Please select a date.");
            return;
        }

        double amount;
        try {
            amount = Double.parseDouble(amountText);
        } catch (NumberFormatException e) {
            showError("Amount must be a number.");
            return;
        }

        // Create Expense object
        Expense expense = new Expense(
                selectedCategory.getId(),
                amount,
                date,
                note
        );

        boolean success = expenseDAO.addExpense(expense);

        if (success) {
            showSuccess("Expense added successfully!");

            amountField.clear();
            noteField.clear();
            datePicker.setValue(null);
            categoryCombo.setValue(null);
        } else {
            showError("Error adding expense.");
        }
    }

    private void showSuccess(String msg) {
        messageLabel.setText(msg);
        messageLabel.setStyle("-fx-text-fill: green;");
    }

    private void showError(String msg) {
        messageLabel.setText(msg);
        messageLabel.setStyle("-fx-text-fill: red;");
    }
}
