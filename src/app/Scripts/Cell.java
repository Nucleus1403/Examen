package app.Scripts;

import app.Parameters.Parameters;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.Objects;

public class Cell extends JButton {
    private final int row;
    private final int col;
    private int value;

    public Cell(final int row, final int col,
         final ActionListener actionListener) {
        this.row = row;
        this.col = col;
        addActionListener(actionListener);
        setText("");
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Cell cell = (Cell) obj;
        return row == cell.row &&
                col == cell.col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }
}

