package com.expensetracker.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DBConnection{
    private static final String URL = "jdbc:sqlite:expense_tacker.db";

    static{
        try(Connection connection = DriverManager.getConnection(URL);
            Statement stmt = connection.createStatement()){

            // Create categories table
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS categories (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    name TEXT NOT NULL UNIQUE
                );
            """);

            // Create expenses table
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS expenses (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    category_id INTEGER NOT NULL,
                    amount REAL NOT NULL,
                    date TEXT NOT NULL,
                    note TEXT,
                    FOREIGN KEY (category_id) REFERENCES categories(id)
                );
            """);

            System.out.println("SQLite Database initialized successfully!");
        }catch (Exception e) {
        e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
