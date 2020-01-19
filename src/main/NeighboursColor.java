package main;

import java.awt.*;
import java.util.Random;

public class NeighboursColor {
    public int magentaCount;
    public int cyanCount;

    public NeighboursColor(){
        this.magentaCount = 0;
        this.cyanCount = 0;
    }

    public void resetCount(){
        this.magentaCount = 0;
        this.cyanCount = 0;
    }

    public Color getMaxColor(){
        if(magentaCount == cyanCount){
            return (new Random().nextInt(2) == 0) ? Color.MAGENTA : Color.CYAN;
        }
        return (magentaCount > cyanCount) ?  Color.MAGENTA : Color.CYAN;
    }

    public void incrementColorsCount(Color cellColor){
        if(cellColor == Color.MAGENTA)
            magentaCount++;
        else cyanCount++;
    }
}
