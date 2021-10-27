package app.Scripts;

import app.Parameters.Parameters;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class Cell extends JButton {
    private final int row;
    private final int col;
    private int value;

    public Cell(int row,int col,final ActionListener actionListener) {
        this.row = row;
        this.col = col;
        addActionListener(actionListener);
        //setText("");

        Image icon = new ImageIcon("C:\\Users\\claud\\IdeaProjects\\Project2\\src\\resources\\flag.png").getImage().getScaledInstance(70 ,66,Image.SCALE_DEFAULT);
        this.setIcon(new ImageIcon(icon));
    }
    
    public int GetValue() {
        return value;
    }

    public void SetValue(int value) {
        this.value = value;
    }

    public boolean isAMine() {
        return value == (new Parameters().GetMineCount());
    }

    public void Reset() {
        SetValue(0);
        setEnabled(true);
        setText("");
    }

    public void Reveal() {
        setEnabled(false);
        setText(isAMine() ? "X" : String.valueOf(value));
    }

    public void UpdateNeighbourCount() {
        GetNeighbours(Minesweeper.reusableStorage);
        for (Cell neighbour : Minesweeper.reusableStorage) {
            if (neighbour == null) {
                break;
            }
            if (neighbour.isAMine()) {
                value++;
            }
        }
    }

    public void GetNeighbours(final Cell[] container) {
        // Empty all elements first
        for (int i = 0; i < Minesweeper.reusableStorage.length; i++) {
            Minesweeper.reusableStorage[i] = null;
        }

        int index = 0;

        for (int rowOffset = -1; rowOffset <= 1; rowOffset++) {
            for (int colOffset = -1; colOffset <= 1; colOffset++) {
                // Make sure that we don't count ourselves
                if (rowOffset == 0 && colOffset == 0) {
                    continue;
                }
                int rowValue = row + rowOffset;
                int colValue = col + colOffset;

                if (rowValue < 0 || rowValue >= (new Parameters().GetGridSize())
                        || colValue < 0 || colValue >= (new Parameters().GetGridSize())) {
                    continue;
                }

                container[index++] = Minesweeper.cells[rowValue][colValue];
            }
        }
    }
}

