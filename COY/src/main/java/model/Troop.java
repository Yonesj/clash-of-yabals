package model;

import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.ArrayList;

import static java.lang.Math.*;

public class Troop extends Thread{
    private double x;
    private double y;
    private ImageView image;
    private ArrayList<Building> targets = new ArrayList<>();
    private int HP;
    private int damage;
    private int range;
    private int movementSpeed;
    private String info;


    private Building target = null;
    private double distance = 0;

    public Troop(double x, double y, ImageView image,ArrayList<Building> targets,int HP, int damage, int range,int movementSpeed, String info) {
        this.x = x;
        this.y = y;
        this.image = image;
        this.targets = targets;
        this.HP = HP;
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

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setImage(ImageView image) {
        this.image = image;
    }

    @Override
    public void run(){
        detectTarget();
        move();
        attack();
    }

    private void detectTarget(){
        double minimumDistance = 2000;

        for (Building building : targets){
            distance = sqrt(pow(x - building.getX(),2) + pow(y - building.getY(),2));
            if(distance < minimumDistance){
                minimumDistance = distance;
                target = building;
            }
        }

    }

    private void move(){
        boolean xReached = false;
        boolean yReached = false;
//        TranslateTransition transition = new TranslateTransition();
//        transition.setNode(image);
//        transition.setDuration(Duration.millis(1000));
//        transition.setAutoReverse(false);



        while (distance > 0){
            if(abs(x - target.getX()) < movementSpeed){
                xReached = true;
//                transition.setByX(0);
            }else if(x < target.getX()) {
                x += movementSpeed;
//                transition.setByX(movementSpeed);
            }else if(x > target.getX()){
                x -= movementSpeed;
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
    }

    private void attack(){
        while (target.getHP() > 0){
            target.setHP(target.getHP() - damage);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(e.getStackTrace());
            }
        }

        if(target.getHP() <= 0){
            target.getImage().setVisible(false);
        }
    }
}
