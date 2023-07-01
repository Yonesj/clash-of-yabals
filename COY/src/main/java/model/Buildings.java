package model;

public class Buildings {
    public static Building townHall1 = new Building(5000,
            "This is the heart of your village. Upgrading your Town Hall unlocks new defenses, buildings, traps and much more.");

    public static Building camp_1 = new Building(700,"Your troops are stationed in Army Camps. Build more camps and upgrade them to muster a powerful army.");

    public static Resourse goldMine_1 = new Resourse(1000,"The Gold Mine produces gold. Upgrade it to boost its production and gold storage capacity.");

    public static Building wall_1 = new Building(7000,"Walls are great for keeping your village safe and your enemies in the line of fire");

    public static Resourse exirCollector_1 = new Resourse(1000,"Elixir is pumped from the Ley Lines coursing underneath your village. Upgrade your Elixir Collectors to maximize elixir production.");

    public static Defence cannon_1 = new Defence(1000,"Cannons are great for point defense. Upgrade cannons to increase their firepower, but beware that your defensive turrets cannot shoot while being upgraded!",300);

    public static Defence archerTower_1 = new Defence(1000,"Archer Towers have longer range than cannons, and unlike cannons they can attack flying enemies.",180);

    public static Building barrack_1 = new Building(900,"The Barracks allow you to train troops to attack your enemies");

    public static Resourse goldStorage_1 = new Resourse(2000,"All your precious gold is stored here. Don't let sneaky goblins anywhere near!");

    public static Resourse exirStorage_1 = new Resourse(2000,"These storages contain the elixir pumped from underground.");

    public static Building clanCastle_1 = new Building(3400,"The Clan Castle houses your Treasury and any reinforcement troops or spells sent by your clanmates.");

    public static Building laboratory_1 = new Building(1000,"What dark secrets do the Alchemists hide inside their Laboratory? Nobody has dared to look. All we know is that their research makes our spells and troops harder, better, faster and stronger!");


}
