package model;

public class Player {
    private int id;
    private static int playerCounter = 1;
    private String username;
    private String password;
    private int level;
    private int losses;
    private int wins;
    private Map map;

    public Player(String username, String password, int level, Map map) {
        this.username = username;
        this.password = password;
        this.level = level;
        this.map = map;
        this.id = playerCounter++;
        this.wins = 0;
        this.losses = 0;
    }


}
