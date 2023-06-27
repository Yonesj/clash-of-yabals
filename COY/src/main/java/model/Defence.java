package model;

public class Defence extends Building{
    private int damage;

    public Defence(int HP, String info, int damage) {
        super(HP, info);
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }
}
