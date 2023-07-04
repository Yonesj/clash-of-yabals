package model;

import com.example.coy.Map1Controller;
import com.example.coy.Map2Controller;
import com.example.coy.Map3Controller;
import com.example.coy.Map4Controller;
import javafx.application.Platform;
import javafx.scene.control.Control;
import javafx.scene.control.Label;

public class Timer implements Runnable{
    int leftTime = 60;
    private Label timeLabel;
    public Map4Controller map4Controller;
    public Map3Controller map3Controller;
    public Map2Controller map2Controller;
    private Map1Controller map1Controller;

    public Timer(Label timeLabel) {
        this.timeLabel = timeLabel;
    }

    @Override
    public void run() {
        while (leftTime >= 0){
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    timeLabel.setText(Integer.toString(leftTime) + " S");
                }
            });
            leftTime--;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(e.getStackTrace());
            }
        }

//        Platform.runLater(new Runnable() {
//            @Override
//            public void run() {
//                int leftTime = 120;
//
//                do {
//                    timeLabel.setText(Integer.toString(leftTime));
//                    leftTime--;
//                    try {
//                        Thread.sleep(10000);
//                    } catch (InterruptedException e) {
//                        System.out.println(e.getStackTrace());
//                    }
//                } while (leftTime >= 0);
//            }
//        });

        if(map4Controller != null) {
            map4Controller.showResultPanel();
        }else if(map3Controller != null) {
            map3Controller.showResultPanel();
        }else if(map2Controller != null) {
            map2Controller.showResultPanel();
        }
//        else if(map1Controller != null) {
//            map1Controller.showResultPanel();
//        }
    }
}
