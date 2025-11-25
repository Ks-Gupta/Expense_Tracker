package com.expensetracker.dao;

import com.expensetracker.db.DBConnection;
import com.expensetracker.models.Expense;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExpenseDAO {

    public boolean addExpense(Expense expense) {
        String query = "INSERT INTO expenses(category_id, amount, date, note) VALUES (?, ?, ?, ?)";

        try(Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)){


            stmt.setInt(1, expense.getCategoryId());
            stmt.setDouble(2, expense.getAmount());
            stmt.setString(3, expense.getDate());
            stmt.setString(4, expense.getNote());

            stmt.executeUpdate();
            return true;
        }catch (SQLException e){
            System.out.println("Error adding Expense: " + e.getMessage());
            return false;
        }
    }

    public boolean updateExpense(Expense expense) {
        String sql = "UPDATE expenses SET category_id=?, amount=?, date=?, note=? WHERE id=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, expense.getCategoryId());
            stmt.setDouble(2, expense.getAmount());
            stmt.setString(3, expense.getDate());
            stmt.setString(4, expense.getNote());
            stmt.setInt(5, expense.getId());

            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Error updating expense: " + e.getMessage());
            return false;
        }
    }

    public boolean deleteExpense(int id) {
        String sql = "DELETE FROM expenses WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Error deleting expense: " + e.getMessage());
            return false;
        }

    }

    public List<Expense> getAllExpenses() {
        List<Expense> list = new ArrayList<>();

        String sql = """
                SELECT e.id, e.category_id, c.name AS categoryName,
                       e.amount, e.date, e.note
                FROM expenses e
                JOIN categories c ON e.category_id = c.id
                ORDER BY e.date DESC
                """;

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new Expense(
                        rs.getInt("id"),
                        rs.getInt("category_id"),
                        rs.getString("categoryName"),
                        rs.getDouble("amount"),
                        rs.getString("date"),
                        rs.getString("note")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }


    public List<Expense> getExpensesByMonth(String month, String year) {
        List<Expense> list = new ArrayList<>();

        String sql = """
                SELECT e.id, e.category_id, c.name AS categoryName,
                       e.amount, e.date, e.note
                FROM expenses e
                JOIN categories c ON e.category_id = c.id
                WHERE strftime('%m', e.date) = ? AND strftime('%Y', e.date) = ?
                ORDER BY e.date DESC
                """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, month);
            stmt.setString(2, year);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                list.add(new Expense(
                        rs.getInt("id"),
                        rs.getInt("category_id"),
                        rs.getString("categoryName"),
                        rs.getDouble("amount"),
                        rs.getString("date"),
                        rs.getString("note")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}
