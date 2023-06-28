package model;

public class Defence extends Building{
    private int damage;
    private int range;

    public Defence(int HP, String info, int damage,int range) {
        super(HP, info);
        this.damage = damage;
        this.range = range;
    }

    public int getDamage() {
        return damage;
    }

    public int getRange() {
        return range;
    }
}
