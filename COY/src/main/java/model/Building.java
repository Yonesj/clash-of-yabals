package model;

public class Building {
    private int HP;
    private String info;

    public Building(int HP, String info) {
        this.HP = HP;
        this.info = info;
    }

    public int getHP() {
        return HP;
    }

    public String getInfo() {
        return info;
    }
}
