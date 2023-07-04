package com.example.coy;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import model.Player;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class Map1Controller implements Initializable {
    public static Player defenderPlayer;
    public static Player attackerPlayer;
    public static boolean attackMode;
    private Random rand = new Random();

    @FXML
    private ImageView archer1;

    @FXML
    private ImageView archer2;

    @FXML
    private ImageView archer3;

    @FXML
    private ImageView archer4;
    @FXML
    private ImageView archerTower1;

    @FXML
    private ImageView archerTower2;

    @FXML
    private ImageView archerTower3;

    @FXML
    private ImageView archerTower4;

    @FXML
    private ImageView barrack1;

    @FXML
    private ImageView barrack2;

    @FXML
    private ImageView barrack3;

    @FXML
    private ImageView camp1;

    @FXML
    private ImageView camp2;

    @FXML
    private ImageView camp3;

    @FXML
    private ImageView cannon1;

    @FXML
    private ImageView cannon2;

    @FXML
    private ImageView cannon3;

    @FXML
    private ImageView clanCastle;

    @FXML
    private ImageView exirStorage1;

    @FXML
    private ImageView exirStorage2;

    @FXML
    private ImageView exircollector1;

    @FXML
    private ImageView exircollector2;

    @FXML
    private ImageView goldStorage1;

    @FXML
    private ImageView goldStorage2;

    @FXML
    private ImageView lab;

    @FXML
    private ImageView map;

    @FXML
    private ImageView mine1;

    @FXML
    private ImageView mine2;

    @FXML
    private ImageView mine3;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private ImageView townhall;

    @FXML
    private ImageView wall1;

    @FXML
    private ImageView wall10;

    @FXML
    private ImageView wall11;

    @FXML
    private ImageView wall12;

    @FXML
    private ImageView wall13;

    @FXML
    private ImageView wall14;

    @FXML
    private ImageView wall15;

    @FXML
    private ImageView wall16;

    @FXML
    private ImageView wall17;

    @FXML
    private ImageView wall18;

    @FXML
    private ImageView wall19;

    @FXML
    private ImageView wall2;

    @FXML
    private ImageView wall20;

    @FXML
    private ImageView wall21;

    @FXML
    private ImageView wall22;

    @FXML
    private ImageView wall23;

    @FXML
    private ImageView wall24;

    @FXML
    private ImageView wall25;

    @FXML
    private ImageView wall26;

    @FXML
    private ImageView wall27;

    @FXML
    private ImageView wall28;

    @FXML
    private ImageView wall3;

    @FXML
    private ImageView wall4;

    @FXML
    private ImageView wall5;

    @FXML
    private ImageView wall6;

    @FXML
    private ImageView wall7;

    @FXML
    private ImageView wall8;

    @FXML
    private ImageView wall9;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        map.setOnMouseClicked(event -> {
            if(event.getButton() == MouseButton.SECONDARY){
                double x = scrollPane.getHvalue();
                double y = scrollPane.getVvalue();
//                map.setCursor(Cursor.TEXT);
                if(event.getClickCount() == 1) {
                    map.setFitWidth(map.getImage().getWidth() * 1.2);
                    map.setFitHeight(map.getImage().getHeight() * 1.2);
                }else if(event.getClickCount() == 2) {
                    map.setFitWidth(map.getImage().getWidth() / 1.2);
                    map.setFitHeight(map.getImage().getHeight() / 1.2);
                }

                scrollPane.setHvalue(x);
                scrollPane.setVvalue(y);
            }
        });


    }
}
