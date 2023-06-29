package model;

import javafx.scene.image.ImageView;

public class Building {
    private double x;
    private double y;
    private ImageView image;
    private int HP;
    private String info;

    public Building(int HP, String info) {
        this.HP = HP;
        this.info = info;
    }

    //getters
    public int getHP() {
        return HP;
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

    public String getInfo() {
        return info;
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

    public void setHP(int HP) {
        this.HP = HP;
    }
}
