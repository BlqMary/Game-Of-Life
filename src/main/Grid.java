package main;


public class Grid {
    private Cell [][] cells;
    private int width;
    private int height;

    public Grid(int width, int height){
        this.cells = new Cell[width][height];
        this.height = height;
        this.width = width;
        initializeCells();
    }

    public Grid(int width, int height, String pattern){
        this.cells = new Cell[width][height];
        this.height = height;
        this.width = width;
        initializeCells();
        switch(pattern){
            case "dakota": initDakota(); break;
            case "gosper": initGosper(); break;
            case "diehard": initDieHard(); break;
            default: throw new IllegalArgumentException("Pattern does not exist");
        }
    }

    private void initializeCells(){
        for(int i = 0; i < this.width; i++)
            for(int j = 0; j < this.height; j++)
                cells[i][j] = new Cell();
    }

    public void countLivingNeighbours(){
        for(int i = 0; i < this.width; i++)
            for(int j = 0; j < this.height; j++){
                Cell cell = cells[i][j];
                cell.resetNeighbourCount();
                if (i > 0 && cells[i - 1][j].getState() == State.ALIVE) cell.addNeighbour(cells[i - 1][j]); //zliczenie wszystkich sąsiadów którzy są żywi
                if (j > 0 && cells[i][j - 1].getState() == State.ALIVE) cell.addNeighbour(cells[i][j - 1]);
                if (i > 0 && j > 0
                        && cells[i - 1][j - 1].getState() == State.ALIVE) cell.addNeighbour(cells[i - 1][j - 1]);
                if (i < this.width - 1
                        && cells[i + 1][j].getState() == State.ALIVE) cell.addNeighbour(cells[i + 1][j]);
                if (j < this.height - 1
                        && cells[i][j + 1].getState() == State.ALIVE) cell.addNeighbour(cells[i][j + 1]);
                if (i < this.width - 1 && j < this.height - 1
                        && cells[i + 1][j + 1].getState() == State.ALIVE) cell.addNeighbour(cells[i + 1][j + 1]);
                if (i < this.width - 1 && j > 0
                        && cells[i + 1][j - 1].getState() == State.ALIVE) cell.addNeighbour(cells[i + 1][j - 1]);
                if (i > 0 && j < this.height - 1
                        && cells[i - 1][j + 1].getState() == State.ALIVE) cell.addNeighbour(cells[i - 1][j + 1]);
            }
    }

    public Cell[][] getCells() {
        return cells;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void changeStateOfCell(int x, int y){
        cells[x][y].changeState();
    }

    private void initDakota(){
        if( width < 10 || height < 10)
            throw new IllegalArgumentException("Grid too small");
        int x = 4;
        int y = getHeight()/2;
        cells[x][y].changeState();
        cells[x+1][y].changeState();
        cells[x+2][y].changeState();
        cells[x+3][y].changeState();
        cells[x+3][y-1].changeState();
        cells[x+3][y-2].changeState();
        cells[x+2][y-3].changeState();
        cells[x][y-4].changeState();
        cells[x-1][y-4].changeState();
        cells[x-3][y-3].changeState();
        cells[x-3][y-1].changeState();
        cells[x-2][y].changeState();
        cells[x-1][y].changeState();
    }

    private void initGosper(){
        if(width < 41 || height < 20)
            throw new IllegalArgumentException("Grid too small");
        int x = 20;
        int y = 10;

        //left square
        cells[x-13][y].changeState();
        cells[x-14][y].changeState();
        cells[x-13][y-1].changeState();
        cells[x-14][y-1].changeState();

        //right square
        cells[x+20][y-2].changeState();
        cells[x+20][y-3].changeState();
        cells[x+21][y-3].changeState();
        cells[x+21][y-2].changeState();

        //left "gun"
        cells[x][y].changeState();
        cells[x+2][y].changeState();
        cells[x+3][y].changeState();
        cells[x+2][y-1].changeState();
        cells[x+1][y-2].changeState();
        cells[x-1][y-3].changeState();
        cells[x-2][y-3].changeState();
        cells[x-3][y-2].changeState();
        cells[x-4][y-1].changeState();
        cells[x-4][y].changeState();
        cells[x-4][y+1].changeState();
        cells[x-3][y+2].changeState();
        cells[x-2][y+3].changeState();
        cells[x-1][y+3].changeState();
        cells[x+1][y+2].changeState();
        cells[x+2][y+1].changeState();

        //right "gun"
        cells[x+6][y-1].changeState();
        cells[x+6][y-2].changeState();
        cells[x+6][y-3].changeState();
        cells[x+7][y-1].changeState();
        cells[x+7][y-2].changeState();
        cells[x+7][y-3].changeState();
        cells[x+8][y-4].changeState();
        cells[x+8][y].changeState();
        cells[x+10][y-5].changeState();
        cells[x+10][y-4].changeState();
        cells[x+10][y].changeState();
        cells[x+10][y+1].changeState();
    }

    private void initDieHard(){
        if(width < 10 || height < 5)
            throw new IllegalArgumentException("Grid too small");
        int x = getWidth()/2;
        int y = getHeight()/2;
        cells[x-2][y].changeState();
        cells[x-2][y-1].changeState();
        cells[x-3][y-1].changeState();
        cells[x+2][y].changeState();
        cells[x+3][y].changeState();
        cells[x+4][y].changeState();
        cells[x+3][y-2].changeState();
    }
}
