package model;

import java.util.ArrayList;

public class Map {
    private ArrayList<Building> buildings;

    public Map(){
        buildings = new ArrayList<>();
    }

    public void addBuilding(Building building){
        buildings.add(building);
    }

    public ArrayList<Building> getBuildings() {
        return buildings;
    }
}
