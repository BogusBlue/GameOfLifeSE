public class Threader extends Thread{
    // Gonna put all the thread stuff here
    private Boolean Done = false;
    private Cell myCell;
    private NeighborHelper NH;

    public Threader(Cell myCell, NeighborHelper NH){
        this.myCell = myCell;
        this.NH = NH;
    }

    public Boolean IsDone(){
        return Done;
    }

    public void UpdateCells(GridDisp GUI){
        myCell.AdvanceGen(GUI);
    }

    public void run() { // Needed for thread classes
        myCell.CompNextGen(NH);
    }
}
