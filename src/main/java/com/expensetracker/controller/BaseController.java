package com.expensetracker.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;

public abstract class BaseController {

    protected void switchScene(String fxmlFile, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/expensetracker/ui/" + fxmlFile));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) Stage.getWindows().filtered(Window::isShowing).get(0);
            stage.setScene(scene);
            stage.setTitle(title);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
