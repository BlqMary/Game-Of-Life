package main;

import java.awt.*;

public class Cell {
    private State state;
    private int neighbours;
    private Color color;
    private NeighboursColor neighboursColor;


    public Cell(){
        this.state = State.DEAD;
        this.color = Color.WHITE;
        this.neighboursColor = new NeighboursColor();
    }

    public State getState() {
        return state;
    }

    public int getNeighbours(){
        return this.neighbours;
    }

    public void addNeighbour(Cell cell){
        this.neighbours++;
        this.neighboursColor.incrementColorsCount(cell.getColor());
    }

    public void changeState(){
        this.state = this.state.changeState();
        if(this.state == State.DEAD) this.color = Color.WHITE;
        else{
            this.color = this.neighboursColor.getMaxColor();
        }
    }

    public void resetNeighbourCount(){
        this.neighbours = 0;
        this.neighboursColor.resetCount();
    }

    public Color getColor(){
        return this.color;
    }
}
