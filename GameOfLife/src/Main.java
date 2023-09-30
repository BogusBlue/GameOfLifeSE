import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

interface NeighborHelper{
    int NeighborHelp(int x, int y);
}

public class Main {
    public static void main(String args[]) throws FileNotFoundException, InterruptedException {
        // 'throws' in case there's no file found for the scanner

        // To read a file:
        // "C:\Users\lawso\Downloads\Start.txt"
        File startingInfo = new File("C:/Users/lawso/Downloads/Start.txt"); // Enter file path as string
        Scanner starterScanner = new Scanner(startingInfo);

        Grid mainGrid = new Grid(starterScanner);

        mainGrid.AdvanceGrid();

        //starterScanner.close();
    }
}
