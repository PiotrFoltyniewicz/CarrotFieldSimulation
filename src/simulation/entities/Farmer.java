package simulation.entities;

import java.awt.Point;
import java.util.*;

import general.FileHandler;
import simulation.actions.*;
import simulation.environment.*;

enum farmerActions {
    PLANT, REPAIR, NONE
}

public class Farmer extends DynamicEntity {

    private Dog ownedDog;
    private int turnsLeft;
    private farmerActions farmerAction;

    public Farmer(Tile currentTile, int sightRange) {
        super(currentTile, sightRange);
        ownedDog = new Dog(currentTile, sightRange);
        farmerAction = farmerActions.PLANT;
    }

    private double calculateDistance(Point p1, Point p2) {
        return Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
    }

    private void move() {
        List<Tile> possibleTiles = new ArrayList<>();
        Point position = currentTile.getPosition();
        for (Tile tile : surroundingTiles) {
            if (tile != currentTile && calculateDistance(tile.getPosition(), position) <= 1.1) {
                possibleTiles.add(tile);
            }
        }
        Random random = new Random();
        currentTile = possibleTiles.get(random.nextInt(possibleTiles.size()));
    }

    private boolean chooseRepairTile() {

        return currentTile.getIsDestroyed();
    }

    private void spotNearestRabbit() {
        Tile closestRabbitToDog = null;
        double distanceToDog = Double.MAX_VALUE;
        for (Tile tile : surroundingTiles) {
            if (tile.getHasRabbit()) {
                double currDist = calculateDistance(tile.getPosition(), ownedDog.getPosition());

                if (closestRabbitToDog == null) {
                    closestRabbitToDog = tile;
                    distanceToDog = currDist;

                } else if (currDist < distanceToDog) {
                    closestRabbitToDog = tile;
                    distanceToDog = currDist;
                }
            }
        }
        if (closestRabbitToDog != null) {
            ownedDog.setTargetTile(closestRabbitToDog);
        }
    }

    private boolean choosePlantCarrot() {
        return !currentTile.getHasCarrot();
    }

    @Override
    public EntityAction getAction() {

        spotNearestRabbit();
        if (turnsLeft > 0) {
            turnsLeft--;
            return new NoneAction();
        }
        switch (farmerAction) {
            case REPAIR:
                farmerAction = farmerActions.NONE;
                return new RepairTileAction(currentTile);
            case PLANT:
                farmerAction = farmerActions.NONE;
                return new PlantCarrotAction(currentTile);
            case NONE:
                break;
        }
        FileHandler fileHandler = FileHandler.getInstance();
        Random random = new Random();

        if (chooseRepairTile()) {
            int minTime = Integer.parseInt(fileHandler.readSettingsParameter("minLandRepairTime"));
            int maxTime = Integer.parseInt(fileHandler.readSettingsParameter("maxLandRepairTime"));
            turnsLeft = random.nextInt(maxTime - minTime + 1) + minTime;
            farmerAction = farmerActions.REPAIR;
        } else if (choosePlantCarrot()) {
            int minTime = Integer.parseInt(fileHandler.readSettingsParameter("minPlantTime"));
            int maxTime = Integer.parseInt(fileHandler.readSettingsParameter("maxPlantTime"));
            turnsLeft = random.nextInt(maxTime - minTime + 1) + minTime;
            farmerAction = farmerActions.PLANT;
        } else {
            move();
        }
        return new NoneAction();
    }

    public Dog getDog() {
        return ownedDog;
    }
}
