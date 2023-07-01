package model;

import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;

public class Resourse extends Building{
    public Resourse(double x, double y, ImageView image, ProgressBar hpBar, int HP, String info) {
        super(x, y, image, hpBar, HP, info);
    }

    public Resourse(int HP, String info) {
        super(HP, info);
    }
}
