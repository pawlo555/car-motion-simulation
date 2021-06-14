package Objects;

import Constants.RoadConstants;
import Utilities.Crossing.Crossing;
import Utilities.Direction;
import Utilities.Vector2D;
import Utilities.PointType;

import Vehicles.AbstractVehicle;

import java.util.EnumMap;
import java.util.Objects;

public class Point {
    private EnumMap<Direction, Point> neighbors = new EnumMap<>(Direction.class);
    private AbstractVehicle vehicle;
    private final Vector2D position;
    private PointType type = PointType.NORMAL;
    private boolean isPartOfVehicle = false;
    private int allowedSpeed = RoadConstants.maxSpeed;
    private Crossing myCrossing;

    public Point(Vector2D position){
        this.position = position;
    }

    public void placeVehicle(AbstractVehicle vehicle){
        this.vehicle = vehicle;
        this.isPartOfVehicle = true;
    }

    public void removeVehicle(){
        isPartOfVehicle = false;
        this.vehicle = null;
    }

    public boolean hasVehicle(){
        if (vehicle != null || isPartOfVehicle)
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

    public PointType getType(){return type;}

    public void setAllowedSpeed(int speed){
        if (speed > RoadConstants.maxSpeed || speed < 0){
            throw new IllegalArgumentException("Allowed speed must be at least 0 and at most maxSpeed");
        }
        else
            allowedSpeed = speed;
    }

    public void setType(PointType type) {
        this.type = type;
    }

    public void addPartOfVehicle(){
        this.isPartOfVehicle = true;
    }

    public int getAllowedSpeed(){return allowedSpeed;}

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return isPartOfVehicle == point.isPartOfVehicle &&
                allowedSpeed == point.allowedSpeed &&
                Objects.equals(neighbors, point.neighbors) &&
//                Objects.equals(vehicle, point.vehicle) &&
                position.equals(point.position) &&
                type == point.type;
    }

    @Override
    public int hashCode() {
//        return Objects.hash(neighbors, vehicle, position, type, isPartOfVehicle, allowedSpeed);
        return Objects.hash(neighbors, position, type, isPartOfVehicle, allowedSpeed);
    }

    public void setCrossing(Crossing crossing){
        this.myCrossing = crossing;
    }


    public Crossing getCrossing() {
        return myCrossing;
    }

    public int getLane() {
        //TODO
        return 0;
    }
}
