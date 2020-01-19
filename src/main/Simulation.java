package main;


public class Simulation {
    private int [] reproductionConditions;
    private int [] surviveConditions;
    private Grid grid;


    public Simulation(int [] reproductionConditions, int [] surviveConditions, int gridWidth, int gridHeight){
        this.reproductionConditions = reproductionConditions;
        this.surviveConditions = surviveConditions;
        this.grid = new Grid(gridWidth,gridHeight);
    }

    public Simulation(int [] reproductionConditions, int [] surviveConditions, int gridWidth, int gridHeight,String pattern){
        this.reproductionConditions = reproductionConditions;
        this.surviveConditions = surviveConditions;
        this.grid = new Grid(gridWidth,gridHeight,pattern);
    }

    public void generation(){
        countNeighbours();
        tick();
    }

    private void countNeighbours(){
        grid.countLivingNeighbours();
    }

    //moment zmiany stanów komórek
    private void tick(){
        for(Cell[] cells : grid.getCells())
            for(Cell cell: cells){
                State cellState = cell.getState();
                int neighboursCount = cell.getNeighbours();


                if(cellState == State.ALIVE){ //jezeli jest żywa to sprawdzamy czy taka może pozostać
                    boolean canStayAlive = false;
                    for(int surviveCondition : surviveConditions) {
                        if (neighboursCount == surviveCondition) {
                            canStayAlive = true;
                            break;
                        }
                    }
                    if(!canStayAlive) cell.changeState();
                }
                else{ //jeżeli jest martwa to sprawdzamy czy może się zmienić na żywą
                    for(int reproductionCondition : reproductionConditions)
                        if(neighboursCount == reproductionCondition){
                            cell.changeState();
                            break;
                        }
                }
            }
    }

    //sprawdzenie czy dalej są jakieś żywe komórki
    public boolean isRunning(){
        for(Cell[] cells : grid.getCells())
            for(Cell cell: cells){
                if(cell.getState() == State.ALIVE) return true;
            }
        return false;
    }

    public Grid getGrid() {
        return grid;
    }
}
