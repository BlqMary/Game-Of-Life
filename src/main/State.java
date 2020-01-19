package main;

public enum State {
    DEAD,ALIVE;

    public State changeState(){
        return (this == DEAD) ? ALIVE : DEAD;
    }
}
