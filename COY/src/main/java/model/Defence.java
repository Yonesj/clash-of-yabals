package model;

import com.example.coy.Map2Controller;
import com.example.coy.Map4Controller;
import com.example.coy.MapController;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

import static java.lang.Math.*;
import static java.lang.Thread.currentThread;
import static java.lang.Thread.sleep;

public class Defence extends Building implements Runnable {
    private int damage;
    private Circle range;
    private ArrayList<Troop> targets = new ArrayList<>();
    ListIterator<Troop> iterator = targets.listIterator();
    private Troop target;
    private double distance = 0;
    private MapController mapController;

    public Defence(double x, double y, ImageView image, ProgressBar hpBar, int HP, String info, int damage, Circle range,MapController mapController) {
        super(x, y, image, hpBar, HP, info);
        this.damage = damage;
        this.range = range;
        this.mapController = mapController;
    }

    public Defence(int HP, String info, int damage) {
        super(HP, info);
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }

    public Circle getRange() {
        return range;
    }

    public void setRange(Circle range) {
        this.range = range;
    }

    public void setTargets(ArrayList<Troop> targets) {
        this.targets = targets;
    }

    public void addTarget(Troop target) {
        targets.add(target);
    }

    @Override
    public void run() {
        while (getHP() > 0) {
            detectTarget();
            defend();
        }
    }

    private void detectTarget() {
        synchronized (mapController.troops) {
            targets = mapController.troops;
        }

        synchronized (targets) {
            for (Troop troop : targets) {
                if (abs(troop.getX() - getX()) <= range.getRadius()) {
                    if (abs(troop.getY() - getY()) <= range.getRadius()) {
                        target = troop;
                        break;
                    }
                }
            }
        }
    }

    private void defend() {
        if(target != null) {
            target.getHpBar().setVisible(true);

            while (target.getHP() > 0 && getHP() > 0) {
                target.setHP(target.getHP() - damage);
                target.updateHPbar();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println(e.getStackTrace());
                }
            }

            if (target.getHP() <= 0) {
                target.getImage().setVisible(false);
                target.getHpBar().setVisible(false);
                targets.remove(target);
                mapController.troops.remove(target);
                target = null;
            } else {
                getImage().setVisible(false);
                currentThread().interrupt();
            }
        }
    }

    public void myNotify(){
        synchronized (this) {
            this.notify();
        }
    }
}


/*
        if (getHP() > 0) {
            if(targets.size() == 0){
                synchronized (this) {
                    try {
                       this.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
//            double minimumDistance = 2000;
//            synchronized (targets) {
//                for (Troop troop : targets){
//                    distance = sqrt(pow(getX() - troop.getX(),2) + pow(getY() - troop.getY(),2));
//                    if(distance < minimumDistance){
//                        minimumDistance = distance;
//                        target = troop;
//                    }
//                }
//            }
//            while (distance > range.getRadius()){
//                try {
//                    sleep(100);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//                distance = sqrt(pow(getX() - target.getX(),2) + pow(getY() - target.getY(),2));
//            }
            while (true) {
                synchronized (targets) {
                    iterator = targets.listIterator();
                    //Troop troop : targets
                    Troop troop = null;
                    while (iterator.hasNext()){
                        troop = iterator.next();
                        double distance = pow(getX() - troop.getX(), 2) + pow(getY() - troop.getY(), 2);
                        if (distance <= pow(range.getRadius(), 2)) {
                            target = troop;
                            break;
                        }
                    }
                }
                if(target != null){
                    break;
                }
            }
        } else {
            getImage().setVisible(false);
            currentThread().interrupt();
        }

        defend();
 */