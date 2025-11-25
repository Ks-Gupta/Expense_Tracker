package com.expensetracker.controller;

import com.expensetracker.dao.ExpenseDAO;
import com.expensetracker.models.Expense;
import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.control.ComboBox;

import java.time.Year;
import java.util.List;

public class ChartsController extends BaseController {

    @FXML
    private ComboBox<String> monthCombo;

    @FXML
    private ComboBox<String> yearCombo;

    @FXML
    private PieChart pieChart;

    @FXML
    private BarChart<String, Number> barChart;

    private final ExpenseDAO expenseDAO = new ExpenseDAO();

    @FXML
    public void initialize() {
        monthCombo.getItems().addAll(
                "01","02","03","04","05","06",
                "07","08","09","10","11","12"
        );

        int currentYear = Year.now().getValue();
        for (int y = currentYear - 5; y <= currentYear; y++) {
            yearCombo.getItems().add(String.valueOf(y));
        }
    }

    @FXML
    public void loadCharts() {
        String month = monthCombo.getValue();
        String year = yearCombo.getValue();

        if (month == null || year == null) return;

        List<Expense> expenses = expenseDAO.getExpensesByMonth(month, year);

        loadPie(expenses);
        loadBar(expenses);
    }

    private void loadPie(List<Expense> list) {
        pieChart.getData().clear();

        for (Expense e : list) {
            boolean exists = false;

            for (PieChart.Data d : pieChart.getData()) {
                if (d.getName().equals(e.getCategoryName())) {
                    d.setPieValue(d.getPieValue() + e.getAmount());
                    exists = true;
                    break;
                }
            }

            if (!exists) {
                pieChart.getData().add(new PieChart.Data(e.getCategoryName(), e.getAmount()));
            }
        }
    }

    private void loadBar(List<Expense> list) {
        barChart.getData().clear();
        XYChart.Series<String, Number> series = new XYChart.Series<>();

        for (Expense e : list) {
            series.getData().add(new XYChart.Data<>(e.getDate(), e.getAmount()));
        }

        barChart.getData().add(series);
    }

    @FXML
    public void goBack() {
        switchScene("main_menu.fxml", "Expense Tracker - Menu");
    }

}
