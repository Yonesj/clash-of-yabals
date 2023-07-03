package com.example.coy;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import model.Player;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Objects;
import java.util.ResourceBundle;

public class SignUpPanelController implements Initializable {
    //sql constance
    private final String url = "jdbc:mysql://localhost/usersData";
    private final String usernameDB = "root";
    private final String passwordDB = "1111";

    public Player player;
    private String username;
    private String password;

    @FXML
    private ImageView background;

    @FXML
    private Label emptyPassword;

    @FXML
    private Label emptyUsername;

    @FXML
    private TextField passwordInputTextField;

    @FXML
    private TextField usernameInputTextField;

    private boolean validateInput(){
        ResultSet resultTable;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, usernameDB, passwordDB);

            String command = "SELECT data.userID , data.username FROM data";
            Statement statement = connection.prepareStatement(command);
            resultTable = statement.executeQuery(command);

            String user = null;

            while (resultTable.next()) {
                user = resultTable.getString("username");
                if(user.equals(username)){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("this username is unavailable!");
                    alert.show();
                    return false;
                }
            }

            player = new Player(0,username,password, 1,"map4");

            String command2 = String.format("INSERT INTO `data` (`userID`, `username`, `password`, `level`, `winCount`, `losses`, `map`) VALUES ('%d', '%s', '%s', '1', '0', '0', 'map4')",player.getId(),username,password);
            Statement statement2 = connection.prepareStatement(command2);
            statement2.execute(command2);

            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }


        return true;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        passwordInputTextField.setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.ENTER){
                password = passwordInputTextField.getText();
                username = usernameInputTextField.getText();

                if(username == null || username.equals("")){
                    emptyUsername.setVisible(true);
                }else if(password == null || password.equals("")){
                    emptyPassword.setVisible(true);
                }else if(validateInput()){
                    Node source = (Node) event.getSource();
                    Stage stage = (Stage) source.getScene().getWindow();
                    stage.hide();

                    Map4Controller.attackMode =false;
                    Map4Controller.defenderPlayer = player;

                    Parent root1 = null;
                    try {
                        root1 = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("map4.fxml")));
                    } catch (IOException e) {
                        System.out.println(e.getStackTrace());
                    }
                    Scene scene1 = new Scene(root1, 480, 320);
                    Stage stage1 = new Stage();
                    stage1.setFullScreen(true);
                    stage1.setScene(scene1);
                    stage1.show();
                }
            }
        });
    }

}
