package com.example.coy;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Shadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import model.*;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Objects;
import java.util.Random;
import java.util.ResourceBundle;

public class Map4Controller implements Initializable {
    public static Player defenderPlayer;
    public static Player attackerPlayer;
    public static boolean attackMode;
    private Random rand = new Random();


    //sql constance
    private final String url = "jdbc:mysql://localhost/usersData";
    private final String usernameDB = "root";
    private final String passwordDB = "1111";


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


    //home variables
    @FXML
    private Label usernameField;
    @FXML
    private ImageView star;

    @FXML
    private Rectangle rectangle1;

    @FXML
    private Rectangle rectangle2;
    @FXML
    private Label levelLabel;

    @FXML
    private ImageView attackButton;

    @FXML
    private ImageView troopButton;


    //attack variables
    @FXML
    private Rectangle attackBar;
    @FXML
    private ImageView barbarIcon;
    @FXML
    private ImageView archerICon;
    @FXML
    private ImageView giantIcon;
    @FXML
    private ImageView goblinIcon;
    @FXML
    private ImageView startAttackButton;
    @FXML
    private ImageView cancelAttackButton;
    @FXML
    private ImageView nextMapButton;

    //troops variables
    @FXML
    private Label troopsLabel;
    @FXML
    private ImageView exitButton;
    @FXML
    private Rectangle troopRectangle1;
    @FXML
    private Rectangle troopRectangle2;
    @FXML
    private ImageView goblinIcon1;
    @FXML
    private ImageView goblinInfo;
    @FXML
    private ImageView archerICon1;
    @FXML
    private ImageView archerInfo;
    @FXML
    private ImageView barbarIcon1;
    @FXML
    private ImageView barbarInfo;
    @FXML
    private ImageView giantIcon1;
    @FXML
    private ImageView giantInfo;


    @FXML
    private ImageView archerExit;
    @FXML
    private ImageView archerPanel;
    @FXML
    private ImageView barbarExit;
    @FXML
    private ImageView barbarPanel;
    @FXML
    private ImageView giantExit;
    @FXML
    private ImageView giantPanel;
    @FXML
    private ImageView goblinExit;
    @FXML
    private ImageView goblinPanel;


    @FXML
    void showUserInfo(MouseEvent event) {

    }

    @FXML
    void attack(MouseEvent event) {
        Player target = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, usernameDB, passwordDB);

            String command = "SELECT data.userID FROM data";
            Statement statement = connection.prepareStatement(command);
            ResultSet resultTable = statement.executeQuery(command);

            int size = 0;
            while (resultTable.next()) {
                size = resultTable.getInt(1);
            }

            int id = rand.nextInt(size);
            String command2 = String.format( "SELECT data.userID , data.username, data.password, data.level, data.winCount, data.losses, data.map FROM data WHERE data.userID = %d",id);
            Statement statement2 = connection.prepareStatement(command2);
            ResultSet result = statement2.executeQuery(command2);

            result.next();
            target = new Player(result.getInt("userID"),result.getString("username"),result.getString("password"),
                    result.getInt("level"),result.getString("map"));

            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }


        switch (target.getMap()){
            case "map1":
                Map1Controller.defenderPlayer = target;
                Map1Controller.attackerPlayer = this.defenderPlayer;
                Map1Controller.attackMode = true;
                break;
            case "map2":
                Map2Controller.defenderPlayer = target;
                Map2Controller.attackerPlayer = this.defenderPlayer;
                Map2Controller.attackMode = true;
                break;
            case "map3":
                Map3Controller.defenderPlayer = target;
                Map3Controller.attackerPlayer = this.defenderPlayer;
                Map3Controller.attackMode = true;
                break;
            case "map4":
                Map4Controller.defenderPlayer = target;
                Map4Controller.attackerPlayer = this.defenderPlayer;
                Map4Controller.attackMode = true;
        }

        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.hide();

        Parent root1 = null;
        try {
            root1 = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(target.getMap() + ".fxml")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene1 = new Scene(root1, 480, 320);
        Stage stage1 = new Stage();
        stage1.setFullScreen(true);
        stage1.setScene(scene1);
        stage1.show();
    }

    @FXML
    void troopsInfo(MouseEvent event) {
        troopsLabel.setVisible(true);
        exitButton.setVisible(true);
        troopRectangle1.setVisible(true);
        troopRectangle2.setVisible(true);
        archerICon1.setVisible(true);
        barbarIcon1.setVisible(true);
        giantIcon1.setVisible(true);
        goblinIcon1.setVisible(true);
        goblinInfo.setVisible(true);
        giantInfo.setVisible(true);
        barbarInfo.setVisible(true);
        archerInfo.setVisible(true);
    }

    @FXML
    void showArcherInfo(MouseEvent event) {
        troopsLabel.setVisible(false);
        exitButton.setVisible(false);
        troopRectangle1.setVisible(false);
        troopRectangle2.setVisible(false);
        archerICon1.setVisible(false);
        barbarIcon1.setVisible(false);
        giantIcon1.setVisible(false);
        goblinIcon1.setVisible(false);
        goblinInfo.setVisible(false);
        giantInfo.setVisible(false);
        barbarInfo.setVisible(false);
        archerInfo.setVisible(false);

        archerPanel.setVisible(true);
        archerExit.setVisible(true);
    }

    @FXML
    void showBarbarInfo(MouseEvent event) {
        troopsLabel.setVisible(false);
        exitButton.setVisible(false);
        troopRectangle1.setVisible(false);
        troopRectangle2.setVisible(false);
        archerICon1.setVisible(false);
        barbarIcon1.setVisible(false);
        giantIcon1.setVisible(false);
        goblinIcon1.setVisible(false);
        goblinInfo.setVisible(false);
        giantInfo.setVisible(false);
        barbarInfo.setVisible(false);
        archerInfo.setVisible(false);

        barbarPanel.setVisible(true);
        barbarExit.setVisible(true);
    }

    @FXML
    void showGiantInfo(MouseEvent event) {
        troopsLabel.setVisible(false);
        exitButton.setVisible(false);
        troopRectangle1.setVisible(false);
        troopRectangle2.setVisible(false);
        archerICon1.setVisible(false);
        barbarIcon1.setVisible(false);
        giantIcon1.setVisible(false);
        goblinIcon1.setVisible(false);
        goblinInfo.setVisible(false);
        giantInfo.setVisible(false);
        barbarInfo.setVisible(false);
        archerInfo.setVisible(false);

        giantPanel.setVisible(true);
        giantExit.setVisible(true);
    }

    @FXML
    void showGoblinInfo(MouseEvent event) {
        troopsLabel.setVisible(false);
        exitButton.setVisible(false);
        troopRectangle1.setVisible(false);
        troopRectangle2.setVisible(false);
        archerICon1.setVisible(false);
        barbarIcon1.setVisible(false);
        giantIcon1.setVisible(false);
        goblinIcon1.setVisible(false);
        goblinInfo.setVisible(false);
        giantInfo.setVisible(false);
        barbarInfo.setVisible(false);
        archerInfo.setVisible(false);

        goblinPanel.setVisible(true);
        goblinExit.setVisible(true);
    }

    @FXML
    void exitInfoPanel(MouseEvent event) {
        troopsLabel.setVisible(false);
        exitButton.setVisible(false);
        troopRectangle1.setVisible(false);
        troopRectangle2.setVisible(false);
        archerICon1.setVisible(false);
        barbarIcon1.setVisible(false);
        giantIcon1.setVisible(false);
        goblinIcon1.setVisible(false);
        goblinInfo.setVisible(false);
        giantInfo.setVisible(false);
        barbarInfo.setVisible(false);
        archerInfo.setVisible(false);
    }

    @FXML
    void exitArcherPanel(MouseEvent event) {
        archerPanel.setVisible(false);
        archerExit.setVisible(false);

        troopsLabel.setVisible(true);
        exitButton.setVisible(true);
        troopRectangle1.setVisible(true);
        troopRectangle2.setVisible(true);
        archerICon1.setVisible(true);
        barbarIcon1.setVisible(true);
        giantIcon1.setVisible(true);
        goblinIcon1.setVisible(true);
        goblinInfo.setVisible(true);
        giantInfo.setVisible(true);
        barbarInfo.setVisible(true);
        archerInfo.setVisible(true);
    }

    @FXML
    void exitBarbarPanel(MouseEvent event) {
        barbarPanel.setVisible(false);
        barbarExit.setVisible(false);

        troopsLabel.setVisible(true);
        exitButton.setVisible(true);
        troopRectangle1.setVisible(true);
        troopRectangle2.setVisible(true);
        archerICon1.setVisible(true);
        barbarIcon1.setVisible(true);
        giantIcon1.setVisible(true);
        goblinIcon1.setVisible(true);
        goblinInfo.setVisible(true);
        giantInfo.setVisible(true);
        barbarInfo.setVisible(true);
        archerInfo.setVisible(true);
    }

    @FXML
    void exitGiantPanel(MouseEvent event) {
        giantPanel.setVisible(false);
        giantExit.setVisible(false);

        troopsLabel.setVisible(true);
        exitButton.setVisible(true);
        troopRectangle1.setVisible(true);
        troopRectangle2.setVisible(true);
        archerICon1.setVisible(true);
        barbarIcon1.setVisible(true);
        giantIcon1.setVisible(true);
        goblinIcon1.setVisible(true);
        goblinInfo.setVisible(true);
        giantInfo.setVisible(true);
        barbarInfo.setVisible(true);
        archerInfo.setVisible(true);
    }

    @FXML
    void exitGoblinPanel(MouseEvent event) {
        goblinPanel.setVisible(false);
        goblinExit.setVisible(false);

        troopsLabel.setVisible(true);
        exitButton.setVisible(true);
        troopRectangle1.setVisible(true);
        troopRectangle2.setVisible(true);
        archerICon1.setVisible(true);
        barbarIcon1.setVisible(true);
        giantIcon1.setVisible(true);
        goblinIcon1.setVisible(true);
        goblinInfo.setVisible(true);
        giantInfo.setVisible(true);
        barbarInfo.setVisible(true);
        archerInfo.setVisible(true);
    }

    @FXML
    void makeArcherRangeVisible(MouseEvent event) {
        archerRange.setVisible(!archerRange.isVisible());
    }

    @FXML
    void makeCannonRangeVisible(MouseEvent event) {
        cannonRange.setVisible(!cannonRange.isVisible());
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Map currentMap = setUpMap();

        rectangle1.setVisible(true);
        rectangle2.setVisible(true);
        star.setVisible(true);
        levelLabel.setVisible(true);
        usernameField.setText(defenderPlayer.getUsername());
        usernameField.setVisible(true);

        if(attackMode){
            attackButton.setVisible(false);
            troopButton.setVisible(false);

            attackBar.setVisible(true);
            barbarIcon.setVisible(true);
            giantIcon.setVisible(true);
            goblinIcon.setVisible(true);
            archerICon.setVisible(true);
            startAttackButton.setVisible(true);
            cancelAttackButton.setVisible(true);
            nextMapButton.setVisible(true);

            giantIcon.setEffect(new DropShadow(10,Color.BLACK));
        }else {
            attackBar.setVisible(false);
            barbarIcon.setVisible(false);
            giantIcon.setVisible(false);
            goblinIcon.setVisible(false);
            archerICon.setVisible(false);
            startAttackButton.setVisible(false);
            cancelAttackButton.setVisible(false);
            nextMapButton.setVisible(false);

            attackButton.setVisible(true);
            troopButton.setVisible(true);
        }
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
                "These storages contain the elixir pumped from underground.");

        Resourse goldStorageB =  new Resourse(goldStorage.getLayoutX(),goldStorage.getLayoutY(),goldStorage,goldStorageHP,0,
                "All your precious gold is stored here. Don't let sneaky goblins anywhere near!");

        Resourse mineB = new Resourse(mine.getLayoutX(),mine.getLayoutY(),mine,goldStorageHP,0,
                "\"The Gold Mine produces gold. Upgrade it to boost its production and gold storage capacity.\"");

        Resourse exircollectorB = new Resourse(exircollector.getLayoutX(),exircollector.getLayoutY(),exircollector,exircollectorHp,0,
                "Elixir is pumped from the Ley Lines coursing underneath your village. Upgrade your Elixir Collectors to maximize elixir production.");

        Building camp1B = new Building(camp1.getLayoutX(),camp1.getLayoutY(),camp1,camp1HP,0,
                "Your troops are stationed in Army Camps. Build more camps and upgrade them to muster a powerful army.");

        Building camp2B = new Building(camp2.getLayoutX(),camp2.getLayoutY(),camp2,camp2HP,0,
                "Your troops are stationed in Army Camps. Build more camps and upgrade them to muster a powerful army.");

        Building barrack1B = new Building(barrack1.getLayoutX(),barrack1.getLayoutY(),barrack1,barrack1HP,0,
                "The Barracks allow you to train troops to attack your enemies");

        Building barrack2B = new Building(barrack2.getLayoutX(),barrack2.getLayoutY(),barrack2,barrack2Hp,0,
                "The Barracks allow you to train troops to attack your enemies");

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
}
