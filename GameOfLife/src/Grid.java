import java.util.ArrayList;
import java.util.Scanner;

public class Grid implements NeighborHelper{
    int GensToCalc;
    Cell[] [] gridCells;

    public Grid(Scanner starterScanner){
        // Test:
        // while(starterScanner.hasNextLine())
        //    System.out.println(starterScanner.nextLine());
        String input;
        char[] inputs;

        // NOTE: DON'T do Cell[] [] gridCells = {new Cell[20], new Cell[20]};
        // would have to do gridCells[0][i][0][j]
        gridCells = new Cell[20] [20];
        for(int i = 0; i < 20; i++){

            input = starterScanner.next();
            inputs = input.toCharArray();

            for(int j = 0; j < 20; j++){

                gridCells[j][i] = new Cell(j, i);

                if(inputs[j] == 'X')
                    gridCells[j][i].Spawn();
                else
                    gridCells[j][i].Die();

            }
        }

        System.out.println("Starting Grid:\n");
        PrintGrid();

        GensToCalc = (starterScanner.nextInt());
        System.out.println("Gens to calculate: " + GensToCalc);
    }

    public void PrintGrid(){

        for(int i = 0; i < 20; i++){

            for(int j = 0; j < 20; j++){

                if (gridCells[j][i].isAlive())
                    System.out.print("X");
                else
                    System.out.print("O");
            }
            System.out.print('\n');

        }
    }

    public void AdvanceGrid() throws InterruptedException {

        ArrayList<Threader> AllThreads = new ArrayList<>();

        GridDisp GUI = new GridDisp(this);

        for(int g = 0; g < GensToCalc; g++){ // Computing all generations

            for(int i = 0; i < 20; i++){

                for(int j = 0; j < 20; j++){
                    //System.out.println("Cell ["+j+"]["+i+"] Thread Starting");

                    Threader CurrentThread = new Threader(gridCells[j][i], this);
                    AllThreads.add(CurrentThread);
                    CurrentThread.start();
                }

                for(Threader t: AllThreads){t.join();} // may not be necessary
            }
            for(Threader t: AllThreads){t.join(); }
            for(Threader t: AllThreads){t.UpdateCells(GUI);}
            System.out.println("\nGeneration " + (g+1) + ":\n");
            PrintGrid();

        }

        AllThreads.clear();

        System.out.println("\nFinal Product:\n");

        PrintGrid();

        System.out.println("\n" + GensToCalc + " generations computed");
    }

    public int NeighborHelp(int x, int y){
        int neighborCount = 0;

        try {
            if (gridCells[x - 1][y].isAlive())
                neighborCount++;
        } catch (Exception e){}
        try {
            if (gridCells[x + 1][y].isAlive())
                neighborCount++;
        } catch (Exception e){}
        try {
            if (gridCells[x - 1][y + 1].isAlive())
                neighborCount++;
        } catch (Exception e){}
        try {
            if (gridCells[x][y + 1].isAlive())
                neighborCount++;
        } catch (Exception e){}
        try {
            if (gridCells[x + 1][y + 1].isAlive())
                neighborCount++;
        } catch (Exception e){}
        try {
            if (gridCells[x - 1][y - 1].isAlive())
                neighborCount++;
        } catch (Exception e){}
        try {
            if (gridCells[x][y - 1].isAlive())
                neighborCount++;
        } catch (Exception e){}
        try {
            if (gridCells[x + 1][y - 1].isAlive())
                neighborCount++;
        } catch (Exception e){}

        return neighborCount;
    }
}
