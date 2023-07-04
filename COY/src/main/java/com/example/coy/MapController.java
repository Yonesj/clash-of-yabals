package com.example.coy;

import model.Player;
import model.Troop;

import java.util.ArrayList;
import java.util.Random;

public abstract class MapController {
    public static Player defenderPlayer;
    public static Player attackerPlayer;
    public static boolean attackMode;
    public static ArrayList<Troop> troops = new ArrayList<>();
    public Random rand = new Random();
}
