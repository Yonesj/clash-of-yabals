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
import model.Map;
import model.Maps;
import model.Player;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.ResourceBundle;

public class SignInPanelController implements Initializable {
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

            String command = "SELECT data.userID , data.username, data.password, data.level, data.winCount, data.losses, data.map FROM data";
            Statement statement = connection.prepareStatement(command);
            resultTable = statement.executeQuery(command);

            String user = null;
            String pass = null;

            while (resultTable.next()) {
                user = resultTable.getString("username");
                if(user.equals(username)){
                    pass = resultTable.getString("password");
                    if(pass.equals(password)){
                        player = new Player(resultTable.getInt("userID"),resultTable.getString("username"),resultTable.getString("password"),
                                resultTable.getInt("level"),resultTable.getInt("winCount"),resultTable.getInt("losses"),resultTable.getString("map"));

                        return true;
                    }else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("incorrect password!");
                        alert.show();
                        return false;
                    }
                }
            }

            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("user not found 404:)");
        alert.show();
        return false;
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

                    switch (player.getMap()){
                        case "map1":
                            Map1Controller.defenderPlayer = player;
                            Map1Controller.attackMode = false;
                            break;
                        case "map2":
                            Map2Controller.defenderPlayer = player;
                            Map2Controller.attackMode = false;
                            break;
                        case "map3":
                            Map3Controller.defenderPlayer = player;
                            Map3Controller.attackMode = false;
                            break;
                        case "map4":
                            Map4Controller.defenderPlayer = player;
                            Map4Controller.attackMode = false;
                    }

                    Parent root1 = null;
                    try {
                        root1 = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(player.getMap() + ".fxml")));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
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
