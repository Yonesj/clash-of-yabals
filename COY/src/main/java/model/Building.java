package model;

import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;

public class Building /*extends Thread*/{
    private double x;
    private double y;
    private ImageView image;
    private ProgressBar hpBar;
    private int maxHP;
    private int HP;
    private String info;

    public Building(double x, double y, ImageView image, ProgressBar hpBar, int HP, String info) {
        this.x = x;
        this.y = y;
        this.image = image;
        this.hpBar = hpBar;
        this.HP = HP;
        this.maxHP = HP;
        this.info = info;
    }

    public Building(int HP, String info) {
        this.HP = HP;
        this.maxHP = HP;
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

    public ProgressBar getHpBar() {
        return hpBar;
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

    public void setHpBar(ProgressBar hpBar) {
        this.hpBar = hpBar;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public void updateHPbar(){
        if(HP <= 0){
            hpBar.setVisible(false);
            HP = 0;
        }
        double percent = (double) HP / maxHP;
        hpBar.setProgress(percent);
    }
}
