package Objects;

import Utilities.Direction;
import Utilities.Vector2D;
import Vehicles.AbstractVehicle;

import java.util.EnumMap;

public class Point {
    private EnumMap<Direction, Point> neighbors = new EnumMap<>(Direction.class);
    private AbstractVehicle vehicle;
    private final Vector2D position;

    public Point(Vector2D position){
        this.position = position;
    }

    public void placeVehicle(AbstractVehicle vehicle){
        this.vehicle = vehicle;
    }

    public void removeVehicle(){
        this.vehicle = null;
    }

    public boolean hasVehicle(){
        if (vehicle == null)
            return true;
        else
            return false;
    }

    public void addNeighbor(Direction direction, Point point){
        neighbors.put(direction, point);
    }

    public Point getNeighbor(Direction direction){
        return neighbors.get(direction);
    }

    public Vector2D getPosition(){
        return this.position;
    }

    @Override
    public String toString() {
        String msg = "Point at " +position+" neighbors: ";
        for (Direction direction : Direction.values()){
            if (neighbors.get(direction) != null)
                msg += direction.toString()+neighbors.get(direction).getPosition().toString()+" ";
            else
                msg += direction.toString()+" null ";
        }
        return msg;
    }
}
