package Vehicles;

import Utilities.Vector2D;
import Objects.Point;
import Utilities.Direction;
import Utilities.MoveHelper;
import Utilities.PointType;
import Utilities.VehicleObserver;

import java.util.ArrayList;

public abstract class AbstractVehicle {
    protected final int length;
    protected final int width = 1;
    protected Point headPoint; //first point that vehicle occupies
    protected ArrayList<Point> tailPoints = new ArrayList<>(); //if vehicle has length > 1
    protected ArrayList<VehicleObserver> observers = new ArrayList<>();
    protected int speed = 0;
    protected boolean moved = false;

    public AbstractVehicle(int length, Point headPoint){
        this.length = length;
        initializePoints(headPoint);
    }

    public AbstractVehicle(int length, int startSpeed, Point headPoint){
        this.length = length;
        initializePoints(headPoint);
        setSpeed(startSpeed);
    }

    public void accelerate(int units){
        if (speed + units <= headPoint.getAllowedSpeed())
            speed += units;
    }

    public void decelerate(int units){
        if (speed - units >= 0)
            speed -= units;
    }

    public void setSpeed(int speed){
        int allowedSpeed = headPoint.getAllowedSpeed();
        if (speed > allowedSpeed){
            throw new IllegalArgumentException("Can't set speed higher than allowedSpeed on certain point");
        }
        else{
            this.speed = speed;
        }
    }

    public boolean canMove(){
        return MoveHelper.canMove(headPoint, speed);
    }

    public void move(){
        Point movePosition = MoveHelper.getMovePosition(headPoint, speed);
        if (movePosition != null && movePosition != headPoint && !moved){
            clearPoints();
            if (movePosition.getType() == PointType.SIMULATION_EXIT){
                //vehicle reaches end of the road and should disappear
                notifyExit(movePosition.getRoadName());
                observers.clear();
                this.moved = true;
            }
            else{
                initializePoints(movePosition);
                notifyMoved();
                this.moved = true;
            }
        }
    }

    private void initializePoints(Point headPoint){
        headPoint.placeVehicle(this);
        this.headPoint = headPoint;
        Point tailPoint = headPoint;
        for (int i = 1; i < length; i++){
            if (tailPoint.getType() == PointType.SIMULATION_ENTRY)
                break;
            tailPoint = tailPoint.getNeighbor(Direction.BACK);
            tailPoint.addPartOfVehicle();
            tailPoints.add(tailPoint);
        }
    }

    public void clearPoints(){
        //clears points occupied by vehicle
        headPoint.removeVehicle();
        headPoint = null;
        for (Point tailPoint : tailPoints){
            tailPoint.removeVehicle();
        }
        tailPoints.clear();
    }

    public void notifyMoved(){
        for (VehicleObserver observer : observers){
            observer.carMoved(this);
        }
    }

    public void notifyExit(String roadName){
        for (VehicleObserver observer : observers){
            observer.carExit(this, roadName);
        }
    }

    public void addObserver(VehicleObserver observer){
        observers.add(observer);
    }

    public void removeObserver(VehicleObserver observer){
        observers.remove(observer);
    }

    public void setMoved(boolean moved){
        this.moved = moved;
    }

    public int getSpeed(){return speed;}
    public int getLength(){return length;}
    public Point getHeadPoint(){return headPoint;}

    @Override
    public String toString() {
        return "AbstractVehicle{" +
                "position=" + headPoint.getPosition() +
                ", length=" + length +
                ", speed=" + speed +
                '}';
    }

    // TODO make this function get proper values
    public Vector2D getPosition() {
        return new Vector2D(headPoint.getPosition().x,headPoint.getPosition().y);
    }

}
