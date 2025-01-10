package simulation.entities;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import simulation.actions.*;
import simulation.environment.Tile;

public class Dog extends DynamicEntity {

    private Tile targetTile;

    public Dog(Tile currentTile, int sightRange) {
        super(currentTile, sightRange);
    }

    private double calculateDistance(Point p1, Point p2) {
        return Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
    }

    private boolean chooseKillRabbit() {
        return currentTile.getHasRabbit();
    }

    private void spotNearestRabbit() {
        if (targetTile != null && targetTile.getHasRabbit()) {
            return;
        }
        double distance = Double.MAX_VALUE;
        Point position = currentTile.getPosition();
        for (Tile tile : surroundingTiles) {
            if (tile.getHasRabbit()) {
                double currDist = calculateDistance(tile.getPosition(), position);

                if (targetTile == null) {
                    targetTile = tile;
                    distance = currDist;

                } else if (currDist < distance) {
                    targetTile = tile;
                    distance = currDist;
                }
            }
        }
    }

    private void randomMove() {
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

    private void targetMove() {
        List<Tile> possibleTiles = new ArrayList<>();
        Point position = currentTile.getPosition();
        for (Tile tile : surroundingTiles) {
            if (tile != currentTile && calculateDistance(tile.getPosition(), position) <= 1.1) {
                possibleTiles.add(tile);
            }
        }
        Tile bestTile = possibleTiles.get(0);
        double bestDist = calculateDistance(bestTile.getPosition(), targetTile.getPosition());
        for (Tile tile : possibleTiles) {
            double currDist = calculateDistance(tile.getPosition(), targetTile.getPosition());
            if (currDist < bestDist) {
                bestTile = tile;
                bestDist = currDist;
            }
        }
        currentTile = bestTile;
    }

    @Override
    public EntityAction getAction() {
        if (chooseKillRabbit()) {
            return new KillRabbitAction(currentTile);
        }
        spotNearestRabbit();
        if (targetTile != null) {
            targetMove();
        } else {
            randomMove();
        }
        return new NoneAction();
    }

    public void setTargetTile(Tile targetTile) {
        this.targetTile = targetTile;
    }

}
