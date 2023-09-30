import javax.swing.*;
import java.awt.*;

public class GridDisp extends JFrame {
    private Grid mainGrid;
    private Color green = Color.GREEN;
    private Color gray = Color.GRAY;
    private JButton[][] GridCellColor = new JButton[20][20];
    private Container stuff;

    public GridDisp(Grid mainGrid){
        this.mainGrid = mainGrid;

        // Set up grid for the JPanel
        stuff = getContentPane();
        stuff.setLayout(new GridLayout(20, 20));

        for(int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                GridCellColor[j][i] = new JButton(); // Filling JButton array with JButtons

                if (mainGrid.gridCells[j][i].isAlive())
                    GridCellColor[j][i].setBackground(green); // Setting colors
                else
                    GridCellColor[j][i].setBackground(gray); // Setting colors

                stuff.add(GridCellColor[j][i]); // Adds colored buttons to the grid
            }
        }

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 400);
        setVisible(true);

    }

    public void UpdateGrid(){

        for(int i = 0; i < 20; i++)
            for(int j = 0; j < 20; j++){
                GridCellColor[j][i] = new JButton(); // Filling JButton array with JButtons
                if(mainGrid.gridCells[j][i].isAlive())
                    GridCellColor[j][i].setBackground(green); // Setting colors
                else
                    GridCellColor[j][i].setBackground(gray); // Setting colors
            }
    }

    public void UpdateDisplay(Cell myCell, int x, int y){
        if(myCell.isAlive()) {
            GridCellColor[x][y].setBackground(green);
        }
        else{
            GridCellColor[x][y].setBackground(gray);
        }
        return;
    }
}
