package model;

public class Player {
    private int id;
    private static int playerCounter = 5;
    private String username;
    private String password;
    private int level;
    private int losses;
    private int wins;
    private String map;

    public Player(int id,String username, String password, int level, String map) {
        if(id == 0){
            this.id = playerCounter++;
        }else {
            this.id = id;
        }
        this.username = username;
        this.password = password;
        this.level = level;
        this.map = map;
        this.wins = 0;
        this.losses = 0;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getLevel() {
        return level;
    }

    public int getLosses() {
        return losses;
    }

    public int getWins() {
        return wins;
    }

    public String getMap() {
        return map;
    }
}
