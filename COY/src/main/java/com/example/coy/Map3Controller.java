package com.example.coy;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
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
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Map3Controller extends MapController implements Initializable{
    private ExecutorService executorService = Executors.newCachedThreadPool();

    private Map currentMap;
    private Building townhallB;

    //sql constance
    private final String url = "jdbc:mysql://localhost/usersData";
    private final String usernameDB = "root";
    private final String passwordDB = "1111";

    //attack
    private boolean isBarbarSelected = false;
    private boolean isGiantSelected = false;
    private boolean isArcherSelected = false;
    private boolean isAttackStarted;
    private int giantCount = 6;
    private int barbarCount = 12;
    private int archerCount = 5;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private ImageView archerExit;

    @FXML
    private ProgressBar archerHP;

    @FXML
    private ImageView archerICon;

    @FXML
    private ImageView archerICon1;

    @FXML
    private ImageView archerInfo;

    @FXML
    private ImageView archerPanel;

    @FXML
    private Circle archerRange;

    @FXML
    private Rectangle archerSelected;

    @FXML
    private ImageView archerTower;

    @FXML
    private Rectangle attackBar;

    @FXML
    private ImageView attackButton;

    @FXML
    private ImageView barbarExit;

    @FXML
    private ImageView barbarIcon;

    @FXML
    private ImageView barbarIcon1;

    @FXML
    private ImageView barbarInfo;

    @FXML
    private ImageView barbarPanel;

    @FXML
    private Rectangle barbarSelected;

    @FXML
    private ImageView barrack1;

    @FXML
    private ProgressBar barrack1HP;

    @FXML
    private ImageView cancelAttackButton;

    @FXML
    private ImageView cannon;

    @FXML
    private ProgressBar cannonHp;

    @FXML
    private Circle cannonRange;

    @FXML
    private ImageView clanCastle;

    @FXML
    private ProgressBar clanCastleHP;

    @FXML
    private ImageView exirStorage;

    @FXML
    private ProgressBar exirStorageHP;

    @FXML
    private ImageView exircollector;

    @FXML
    private ProgressBar exircollectorHp;

    @FXML
    private ImageView exitButton;

    @FXML
    private ImageView giantExit;

    @FXML
    private ImageView giantIcon;

    @FXML
    private ImageView giantIcon1;

    @FXML
    private ImageView giantInfo;

    @FXML
    private ImageView giantPanel;

    @FXML
    private Rectangle giantSelected;

    @FXML
    private ImageView goblinExit;

    @FXML
    private ImageView goblinIcon;

    @FXML
    private ImageView goblinIcon1;

    @FXML
    private ImageView goblinInfo;

    @FXML
    private ImageView goblinPanel;

    @FXML
    private ImageView goldStorage;

    @FXML
    private ProgressBar goldStorageHP;

    @FXML
    private ImageView lab;

    @FXML
    private ProgressBar labHP;

    @FXML
    private Label levelLabel;

    @FXML
    private ImageView map;

    @FXML
    private ImageView nextMapButton;

    @FXML
    private Label percentage;

    @FXML
    private Rectangle rectangle1;

    @FXML
    private Rectangle rectangle2;

    @FXML
    private Rectangle resultPanel;

    @FXML
    private ImageView returnHomeButton;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private ImageView star;

    @FXML
    private ImageView star1;

    @FXML
    private ImageView star2;

    @FXML
    private ImageView star3;

    @FXML
    private ImageView startAttackButton;

    @FXML
    private Label timeLabel;

    @FXML
    private ImageView townhall;

    @FXML
    private ProgressBar townhallHP;

    @FXML
    private ImageView troopButton;

    @FXML
    private Rectangle troopRectangle1;

    @FXML
    private Rectangle troopRectangle2;

    @FXML
    private Label troopsLabel;

    @FXML
    private Label usernameField;

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
            String command2 = String.format("SELECT data.userID , data.username, data.password, data.level, data.winCount, data.losses, data.map FROM data WHERE data.userID = %d", id);
            Statement statement2 = connection.prepareStatement(command2);
            ResultSet result = statement2.executeQuery(command2);

            result.next();
            target = new Player(result.getInt("userID"), result.getString("username"), result.getString("password"),
                    result.getInt("level"), result.getString("map"));

            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }


        switch (target.getMap()) {
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
    void goToNextMap(MouseEvent event) {
        attack(event);
    }

    @FXML
    void selectBarBar(MouseEvent event) {
        isBarbarSelected = true;
        isGiantSelected = false;
        isArcherSelected = false;

        barbarSelected.setVisible(true);
        giantSelected.setVisible(false);
        archerSelected.setVisible(false);
    }

    @FXML
    void selectGiant(MouseEvent event) {
        isGiantSelected = true;
        isBarbarSelected = false;
        isArcherSelected = false;

        barbarSelected.setVisible(false);
        giantSelected.setVisible(true);
        archerSelected.setVisible(false);
    }

    @FXML
    void selectArcher(){
        isGiantSelected = false;
        isBarbarSelected = false;
        isArcherSelected = true;

        barbarSelected.setVisible(false);
        giantSelected.setVisible(false);
        archerSelected.setVisible(true);
    }

    @FXML
    void startAttack(MouseEvent event) {
        nextMapButton.setVisible(false);
        timeLabel.setVisible(true);

        isAttackStarted = true;

        Timer timer = new Timer(timeLabel);
        timer.map3Controller = this;
        executorService.execute(timer);
    }

    @FXML
    void endBattle(MouseEvent event) {
        showResultPanel();
    }

    public void showResultPanel(){
        executorService.shutdownNow();

        resultPanel.setVisible(true);
        returnHomeButton.setVisible(true);

        int percent = (int) (((9.0 - currentMap.getBuildings().size()) / 9.0) * 100);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                percentage.setText(Integer.toString(percent));
                percentage.setVisible(true);
            }
        });


        int starCount = 0;
        for (Building building: currentMap.getBuildings()){
            if(building == townhallB){
                starCount++;
                break;
            }
        }
        if(percent > 50){
            starCount++;
        }
        if(percent == 100){
            starCount++;
        }

        switch (starCount){
            case 1:
                star1.setVisible(true);
                break;
            case 2:
                star1.setVisible(true);
                star2.setVisible(true);
                break;
            case 3:
                star1.setVisible(true);
                star2.setVisible(true);
                star3.setVisible(true);
        }


        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, usernameDB, passwordDB);

            String command = null;
            if(starCount == 0) {
                command = String.format("UPDATE data SET losses = %d WHERE userID = %d",attackerPlayer.getLosses() + 1,attackerPlayer.getId());
            }else {
                command = String.format("UPDATE data SET winCount = %d WHERE userID = %d",attackerPlayer.getWins() + 1,attackerPlayer.getId());
            }
            Statement statement = connection.prepareStatement(command);
            statement.execute(command);

            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    void goToHomeMap(MouseEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.hide();

        switch (attackerPlayer.getMap()){
            case "map1":
                Map1Controller.defenderPlayer = attackerPlayer;
                Map1Controller.attackMode = false;
                break;
            case "map2":
                Map2Controller.defenderPlayer = attackerPlayer;
                Map2Controller.attackMode = false;
                break;
            case "map3":
                Map3Controller.defenderPlayer = attackerPlayer;
                Map3Controller.attackMode = false;
                break;
            case "map4":
                Map4Controller.defenderPlayer = attackerPlayer;
                Map4Controller.attackMode = false;
        }

        Parent root1 = null;
        try {
            root1 = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(attackerPlayer.getMap() + ".fxml")));
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
        if (!attackMode) {
            archerRange.setVisible(!archerRange.isVisible());
        }
    }

    @FXML
    void makeCannonRangeVisible(MouseEvent event) {
        if(!attackMode) {
            cannonRange.setVisible(!cannonRange.isVisible());
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        rectangle1.setVisible(true);
        rectangle2.setVisible(true);
        star.setVisible(true);
        levelLabel.setVisible(true);
        levelLabel.setText(Integer.toString(defenderPlayer.getLevel()));
        usernameField.setText(defenderPlayer.getUsername());
        usernameField.setVisible(true);

        if (attackMode) {
            currentMap = setUpMap();

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
        } else {
            currentMap = null;

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


        if (attackMode) {
            Defence archerTowerB = new Defence(archerTower.getLayoutX(), archerTower.getLayoutY(), archerTower, archerHP, 1000,
                    "Archer Towers have longer range than cannons, and unlike cannons they can attack flying enemies.",
                    300, archerRange,this);

            Defence cannonB = new Defence(cannon.getLayoutX(), cannon.getLayoutY(), cannon, cannonHp, 1000,
                    "Cannons are great for point defense. Upgrade cannons to increase their firepower, but beware that your defensive turrets cannot shoot while being upgraded!",
                    300, cannonRange,this);

            currentMap.addBuilding(archerTowerB);
            currentMap.addBuilding(cannonB);

            executorService.execute(cannonB);
            executorService.execute(archerTowerB);
        }

        Image giantImage = new Image("giant_7.png");
        Image barbarImage = new Image("barbarian_9.png");
        Image archerImage = new Image("archer_5.png");

        map.setOnMouseClicked(event -> {
            if (attackMode && isAttackStarted) {
                double x = event.getSceneX();
                double y = 0;
                if (scrollPane.getVvalue() == 0) {
                    y = event.getSceneY();
                } else {
                    y = event.getSceneY() + (scrollPane.getVvalue() * 300);
                }

                ProgressBar heroHp = new ProgressBar(1);
                heroHp.setVisible(false);
                heroHp.setLayoutX(x);
                heroHp.setLayoutY(y - 10);
                heroHp.setPrefSize(50, 15);

                ImageView hero = new ImageView();
                hero.setLayoutX(x);
                hero.setLayoutY(y);

                Troop troop = null;

                if (isBarbarSelected && (barbarCount != 0)) {
                    hero.setImage(barbarImage);
                    hero.setFitWidth(40);
                    hero.setFitHeight(50);

                    troop = new Troop(x, y, hero, heroHp, currentMap.getBuildings(), 300, 100, 0, 30, FavoriteTarget.ANY, "This fearless warrior relies on his bulging muscles and striking mustache to wreak havoc in enemy villages. Release a horde of Barbarians and enjoy the mayhem!");
                    barbarCount--;
                } else if (isGiantSelected && (giantCount != 0)) {
                    hero.setImage(giantImage);
                    hero.setFitWidth(50);
                    hero.setFitHeight(60);

                    troop = new Troop(x, y, hero, heroHp, currentMap.getBuildings(), 1300, 100, 0, 30, FavoriteTarget.DEFENCE, "These big guys may seem calm, but show them a turret or cannon and you'll see their fury unleashed! Slow yet durable, these warriors are best used to soak up hits");
                    giantCount--;
                } else if (isArcherSelected && (archerCount != 0)) {
                    hero.setImage(archerImage);
                    hero.setFitWidth(40);
                    hero.setFitHeight(50);

                    troop = new Troop(x, y, hero, heroHp, currentMap.getBuildings(), 200, 200, 200, 30, FavoriteTarget.ANY, "archer");
                    archerCount--;
                }

                if (troop != null) {
                    anchorPane.getChildren().addAll(hero, heroHp);

                    synchronized (troops) {
                        troops.add(troop);
                    }
                    executorService.execute(troop);
                }
//            cannonB.myNotify();
//            archerB.myNotify();
            }
        });
    }

    private Map setUpMap() {
        Map map = Maps.map4;

        townhallB = new Building(townhall.getLayoutX(), townhall.getLayoutY() + 20, townhall, townhallHP, 5000,
                "This is the heart of your village. Upgrading your Town Hall unlocks new defenses, buildings, traps and much more.");

        Resourse exirStorageB = new Resourse(exirStorage.getLayoutX(), exirStorage.getLayoutY(), exirStorage, exirStorageHP, 700,
                "These storages contain the elixir pumped from underground.");

        Resourse goldStorageB = new Resourse(goldStorage.getLayoutX(), goldStorage.getLayoutY(), goldStorage, goldStorageHP, 700,
                "All your precious gold is stored here. Don't let sneaky goblins anywhere near!");

        Resourse exircollectorB = new Resourse(exircollector.getLayoutX(), exircollector.getLayoutY(), exircollector, exircollectorHp, 500,
                "Elixir is pumped from the Ley Lines coursing underneath your village. Upgrade your Elixir Collectors to maximize elixir production.");

        Building barrack1B = new Building(barrack1.getLayoutX(), barrack1.getLayoutY(), barrack1, barrack1HP, 500,
                "The Barracks allow you to train troops to attack your enemies");

        Building clanCastleB = new Building(clanCastle.getLayoutX(), clanCastle.getLayoutY(), clanCastle, clanCastleHP, 1000, "clan castle");

        Building labB = new Building(lab.getLayoutX(), lab.getLayoutY(), lab, labHP, 1000, "lab");


        map.addBuilding(townhallB);
        map.addBuilding(exirStorageB);
        map.addBuilding(goldStorageB);
        map.addBuilding(exircollectorB);
        map.addBuilding(labB);
        map.addBuilding(clanCastleB);
        map.addBuilding(barrack1B);


        return map;
    }
}
