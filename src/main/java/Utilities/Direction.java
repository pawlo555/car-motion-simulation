package Utilities;

public enum Direction {
    RIGHT,
    LEFT,
    BACK,
    FRONT;

    public Direction opposite(){
        switch(this){
            case RIGHT:
                return LEFT;
            case LEFT:
                return RIGHT;
            case FRONT:
                return BACK;
            case BACK:
                return FRONT;
            default:
                return null;
        }
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
