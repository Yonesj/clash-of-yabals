package com.example.coy;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import model.*;

import java.net.URL;
import java.util.ResourceBundle;

public class Map4Controller implements Initializable {
    @FXML
    private AnchorPane anchorPane;

    @FXML
    private ProgressBar archerHP;

    @FXML
    private Circle archerRange;

    @FXML
    private ImageView archerTower;

    @FXML
    private ImageView barrack1;

    @FXML
    private ProgressBar barrack1HP;

    @FXML
    private ImageView barrack2;

    @FXML
    private ProgressBar barrack2Hp;

    @FXML
    private ImageView camp1;

    @FXML
    private ProgressBar camp1HP;

    @FXML
    private ImageView camp2;

    @FXML
    private ProgressBar camp2HP;

    @FXML
    private ImageView cannon;

    @FXML
    private ProgressBar cannonHp;

    @FXML
    private Circle cannonRange;

    @FXML
    private ImageView exirStorage;

    @FXML
    private ProgressBar exirStorageHP;

    @FXML
    private ImageView exircollector;

    @FXML
    private ProgressBar exircollectorHp;

    @FXML
    private ImageView goldStorage;

    @FXML
    private ProgressBar goldStorageHP;

    @FXML
    private ImageView map;

    @FXML
    private ImageView mine;

    @FXML
    private ProgressBar mineHP;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private ImageView townhall;

    @FXML
    private ProgressBar townhallHP;

    @FXML
    void makeArcherRangeVisible(MouseEvent event) {
        archerRange.setVisible(!archerRange.isVisible());
    }

    @FXML
    void makeCannonRangeVisible(MouseEvent event) {
        cannonRange.setVisible(!cannonRange.isVisible());
    }

    private Map setUpMap(){
        Map map = Maps.map4;

        Building townhallB = new Building(townhall.getLayoutX(),townhall.getLayoutY() + 20,townhall,townhallHP,5000,
                "This is the heart of your village. Upgrading your Town Hall unlocks new defenses, buildings, traps and much more.");

        Defence archerTowerB = new Defence(archerTower.getLayoutX(),archerTower.getLayoutY(),archerTower,archerHP,1000,
                "Archer Towers have longer range than cannons, and unlike cannons they can attack flying enemies.",
                300,archerRange);

        Defence cannonB = new Defence(cannon.getLayoutX(),cannon.getLayoutY(),cannon,cannonHp,1000,
                "Cannons are great for point defense. Upgrade cannons to increase their firepower, but beware that your defensive turrets cannot shoot while being upgraded!",
                300,cannonRange);

        Resourse exirStorageB = new Resourse(exirStorage.getLayoutX(),exirStorage.getLayoutY(),exirStorage,exirStorageHP,0,
                "");

        Resourse goldStorageB =  new Resourse(goldStorage.getLayoutX(),goldStorage.getLayoutY(),goldStorage,goldStorageHP,0,
                "");

        Resourse mineB = new Resourse(mine.getLayoutX(),mine.getLayoutY(),mine,goldStorageHP,0,
                "");

        Resourse exircollectorB = new Resourse(exircollector.getLayoutX(),exircollector.getLayoutY(),exircollector,exircollectorHp,0,
                "");

        Building camp1B = new Building(camp1.getLayoutX(),camp1.getLayoutY(),camp1,camp1HP,0,
                "");

        Building camp2B = new Building(camp2.getLayoutX(),camp2.getLayoutY(),camp2,camp2HP,0,
                "");

        Building barrack1B = new Building(barrack1.getLayoutX(),barrack1.getLayoutY(),barrack1,barrack1HP,0,
                "");

        Building barrack2B = new Building(barrack2.getLayoutX(),barrack2.getLayoutY(),barrack2,barrack2Hp,0,
                "");

        map.addBuilding(townhallB);
        map.addBuilding(archerTowerB);
        map.addBuilding(cannonB);
        map.addBuilding(exirStorageB);
        map.addBuilding(goldStorageB);
        map.addBuilding(mineB);
        map.addBuilding(exircollectorB);
        map.addBuilding(camp1B);
        map.addBuilding(camp2B);
        map.addBuilding(barrack1B);
        map.addBuilding(barrack2B);

        return map;
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Map currentMap = setUpMap();
    }
}
