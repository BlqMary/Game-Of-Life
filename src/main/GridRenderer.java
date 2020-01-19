package main;

import javax.swing.*;
import java.awt.*;

class GridRenderer extends JPanel {

    private final Simulation simulation;
    private final Grid grid;
    private final Visualization visualization;
    int widthScale;
    int heightScale;

    GridRenderer(Simulation simulation, Grid grid, Visualization visualization) {
        this.simulation = simulation;
        this.grid = grid;
        this.visualization = visualization;
    }

    @Override
    protected void paintComponent(Graphics component) {
        super.paintComponent(component);

       if(grid.getHeight() > grid.getWidth()){
           double ratio = (double)grid.getWidth()/(double)grid.getHeight();
           this.setBounds(75,100,(int)(ratio*(visualization.frame.getHeight()-150)), visualization.frame.getHeight()-150);
       }
       else{
           double ratio = (double)grid.getHeight()/(double)grid.getWidth();
           this.setBounds(75,100,visualization.frame.getWidth()-150, (int)(ratio*(visualization.frame.getWidth()-150)));
       }

        widthScale = this.getWidth() / grid.getWidth();
        heightScale = this.getHeight() / grid.getHeight();


        Cell [][]cells = grid.getCells();
        for(int x = 0; x < grid.getWidth(); x++)
            for(int y = 0; y < grid.getHeight(); y++){
            component.setColor(cells[x][y].getColor());
            component.fillRect(x * widthScale, y * heightScale, widthScale, heightScale);
        }

    }

}