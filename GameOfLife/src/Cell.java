public class Cell {
    private Boolean CellStatus;
    private Boolean FutureStatus;
    private int NeighborCount;
    public int xAddress, yAddress;

    public Cell(int x, int y){
        xAddress = x;
        yAddress = y;
    }

    public Boolean isAlive(){
        return CellStatus;
    }
    public void Die(){
        CellStatus = false;
    }
    public void Spawn(){
        CellStatus = true;
    }

    public void FutureDie(){
        FutureStatus = false;
    }
    public void FutureSpawn(){
        FutureStatus = true;
    }
    public Boolean SeeFuture() { return FutureStatus; }

    public void CompNextGen(NeighborHelper NH){

        // The following line helped show that it was multithreading
        // System.out.println("Computing next generation for cell "+xAddress+","+yAddress);

        NeighborCount = NH.NeighborHelp(xAddress, yAddress);


        if(isAlive()) {
            if (NeighborCount == 3 || NeighborCount == 2)
                FutureSpawn();
            else
                FutureDie();
        }
        else{
            if(NeighborCount == 3)
                FutureSpawn();
            else
                FutureDie();
        }

        return;
    }

    public void AdvanceGen(GridDisp GUI){
        CellStatus = FutureStatus;
        GUI.UpdateDisplay(this, xAddress, yAddress);

        return;
    }

}
