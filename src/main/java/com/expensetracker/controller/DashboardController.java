package com.expensetracker.controller;

import com.expensetracker.dao.ExpenseDAO;
import com.expensetracker.models.Expense;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class DashboardController extends BaseController {

    @FXML
    private TableView<Expense> expenseTable;

    @FXML
    private TableColumn<Expense, Integer> categoryId;

    @FXML
    private TableColumn<Expense, String> categoryCol;

    @FXML
    private TableColumn<Expense, Double> amountCol;

    @FXML
    private TableColumn<Expense, String> dateCol;

    @FXML
    private TableColumn<Expense, String> noteCol;

    @FXML
    private TableColumn<Expense, Void> actionCol;

    @FXML
    private Label messageLabel;

    private final ExpenseDAO expenseDAO = new ExpenseDAO();
    private ObservableList<Expense> expenseList;

    @FXML
    public void initialize() {
        loadTable();
    }

    private void loadTable() {
        // Fetch all expenses from DB
        expenseList = FXCollections.observableArrayList(expenseDAO.getAllExpenses());

        // Connect columns to Expense fields
        categoryId.setCellValueFactory(new PropertyValueFactory<>("categoryId"));
        categoryCol.setCellValueFactory(new PropertyValueFactory<>("categoryName"));
        amountCol.setCellValueFactory(new PropertyValueFactory<>("amount"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        noteCol.setCellValueFactory(new PropertyValueFactory<>("note"));

        // Add delete button to each row
        addDeleteButton();

        // Set items
        expenseTable.setItems(expenseList);
    }

    private void addDeleteButton() {
        actionCol.setCellFactory(col -> new TableCell<>() {

            private final Button delBtn = new Button("Delete");

            {
                delBtn.setStyle("-fx-text-fill: white; -fx-background-color: red;");
                delBtn.setOnAction(e -> {
                    Expense expense = getTableView().getItems().get(getIndex());
                    deleteExpense(expense.getId());
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(delBtn);
                }
            }
        });
    }

    private void deleteExpense(int id) {
        boolean success = expenseDAO.deleteExpense(id);

        if (success) {
            messageLabel.setText("Expense deleted successfully!");
            messageLabel.setStyle("-fx-text-fill: green;");
        } else {
            messageLabel.setText("Error deleting expense.");
            messageLabel.setStyle("-fx-text-fill: red;");
        }

        // Reload the table
        loadTable();
    }

    @FXML
    public void goBack() {
        switchScene("main_menu.fxml", "Expense Tracker - Menu");
    }

}
