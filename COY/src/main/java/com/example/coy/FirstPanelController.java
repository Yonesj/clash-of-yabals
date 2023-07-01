package com.example.coy;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;


public class FirstPanelController{

    @FXML
    void signIn(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("signIn-panel.fxml")));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Sign in page");
        stage.show();
        stage.setResizable(false);
        stage.centerOnScreen();
    }

    @FXML
    void signUp(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("signUp-panel.fxml")));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Sign up page");
        stage.show();
        stage.setResizable(false);
        stage.centerOnScreen();
    }

}
