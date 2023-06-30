package model;

import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import static java.lang.Thread.currentThread;

import java.util.ArrayList;

import static java.lang.Math.*;

public class Troop implements Runnable /*extends Thread*/{
    private double x;
    private double y;
    private ImageView image;
    private ProgressBar hpBar;
    private ArrayList<Building> targets;
    private int HP;
    private int maxHp;
    private int damage;
    private int range;
    private int movementSpeed;
    private String info;


    private Building target = null;
    private double distance = 0;

    public Troop(double x, double y, ImageView image,ProgressBar hpBar,ArrayList<Building> targets,int HP, int damage, int range,int movementSpeed, String info) {
        this.x = x;
        this.y = y;
        this.image = image;
        this.hpBar = hpBar;
        this.targets = targets;
        this.HP = HP;
        this.maxHp = HP;
        this.damage = damage;
        this.range = range;
        this.movementSpeed = movementSpeed;
        this.info = info;
    }

    public int getHP() {
        return HP;
    }

    public int getDamage() {
        return damage;
    }

    public int getRange() {
        return range;
    }

    public int getMovementSpeed() {
        return movementSpeed;
    }

    public String getInfo() {
        return info;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public ImageView getImage() {
        return image;
    }

    public ProgressBar getHpBar() {
        return hpBar;
    }

    public ArrayList<Building> getTargets() {
        return targets;
    }

    public Building getTarget() {
        return target;
    }

    public double getDistance() {
        return distance;
    }

    //setters
    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setImage(ImageView image) {
        this.image = image;
    }

    public void setHpBar(ProgressBar hpBar) {
        this.hpBar = hpBar;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public void setTargets(ArrayList<Building> targets) {
        this.targets = targets;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public void setMovementSpeed(int movementSpeed) {
        this.movementSpeed = movementSpeed;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void setTarget(Building target) {
        this.target = target;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    @Override
    public void run(){
        detectTarget();
    }

    private void detectTarget(){
        if(targets.size() == 0){
            image.setVisible(false);
            currentThread().interrupt();
        }

        double minimumDistance = 2000;

        for (Building building : targets){
            distance = sqrt(pow(x - building.getX(),2) + pow(y - building.getY(),2));
            if(distance < minimumDistance){
                minimumDistance = distance;
                target = building;
            }
        }

        move();
    }

    private void move(){
        boolean xReached = false;
        boolean yReached = false;
//        TranslateTransition transition = new TranslateTransition();
//        transition.setNode(image);
//        transition.setDuration(Duration.millis(1000));
//        transition.setAutoReverse(false);



        while (distance > range && HP > 0 && target.getHP() > 0){
            if(abs(x - target.getX()) < movementSpeed){
                xReached = true;
//                transition.setByX(0);
            }else if(x < target.getX()) {
                x += movementSpeed;
//                transition.setByX(movementSpeed);
            }else if(x > target.getX()){
                x -= movementSpeed;
                hpBar.setLayoutX(hpBar.getLayoutX() - movementSpeed);
//                transition.setByX(-movementSpeed);
            }

            if(abs(y - target.getY()) < movementSpeed){
                yReached = true;
//                transition.setByY(0);
            }else if(y < target.getY()){
                y += movementSpeed;
//                transition.setByY(movementSpeed);
            }else if(y > target.getY()){
                y -= movementSpeed;
//                transition.setByY(-movementSpeed);
            }

            image.setLayoutX(x);
            image.setLayoutY(y);
            hpBar.setLayoutX(x);
            hpBar.setLayoutY(y - 10);

            if(yReached && xReached){
                distance = 0;
            }else {
                distance = sqrt(pow(x - target.getX(), 2) + pow(y - target.getY(), 2));
            }

//            transition.play();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(e.getStackTrace());
            }
        }

        if(target.getHP() <= 0){
            detectTarget();
        }else if(HP <= 0){
            image.setVisible(false);
            currentThread().interrupt();
        }else {
            attack();
        }
    }

    private void attack(){
        target.getHpBar().setVisible(true);

        while (target.getHP() > 0 && HP > 0){
            target.setHP(target.getHP() - damage);
            target.updateHPbar();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(e.getStackTrace());
            }
        }

        if(target.getHP() <= 0){
            target.getImage().setVisible(false);
            targets.remove(target);
            detectTarget();
        }else {
            image.setVisible(false);
            currentThread().interrupt();
        }
    }

    public void updateHPbar(){
        if(HP <= 0){
            hpBar.setVisible(false);
            HP = 0;
        }
        double percent = (double) HP / maxHp;
        hpBar.setProgress(percent);
    }
}
