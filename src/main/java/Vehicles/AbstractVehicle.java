package Vehicles;

import Utilities.Vector2D;

public abstract class AbstractVehicle {
    private final int length;
    private final int width = 1;
    private final int maxSpeed;
    private int speed = 0;

    public AbstractVehicle(int length, int maxSpeed){
        this.length = length;
        this.maxSpeed = maxSpeed;
    }

    public AbstractVehicle(int length, int maxSpeed, int startSpeed){
        this.length = length;
        this.maxSpeed = maxSpeed;
        this.speed = startSpeed;
    }

    public void accelerate(int units){
        if (speed + units <= maxSpeed)
            speed += units;
    }

    public void decelerate(int units){
        if (speed - units >= 0)
            speed -= units;
    }

    public int getSpeed(){return speed;}
    public int getLength(){return length;}

    // TODO make this function get proper values
    public Vector2D getPosition() {
        return new Vector2D(5,5);
    }

}
