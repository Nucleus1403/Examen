package app.Scripts;

import app.Parameters.Parameters;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.*;

public class Minesweeper {

    public static Cell[] reusableStorage = new Cell[new Parameters().GetGridSize()];

    private int gridSize;

    public static Cell[][] cells;

    private JFrame  frame;
    private JButton reset;
    private JButton giveUp;
    private Parameters p;

    private final ActionListener actionListener = actionEvent -> {
        Object source = actionEvent.getSource();
        if (source == reset) {
            CreateMines();
        } else if (source == giveUp) {
            RevealBoardAndDisplay("You gave up.");
        } else {
            HandleCell((Cell) source);
        }
    };

    public Minesweeper() {
        p = new Parameters();

        this.gridSize =p.GetGridSize();
        cells = new Cell[p.GetGridSize()][p.GetGridSize()];


        frame = new JFrame("Minesweeper");
        frame.setSize(p.GetPixelSize(), p.GetPixelSize());
        frame.setLayout(new BorderLayout());

        InitializeButtonPanel();
        InitializeGrid();

        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private void InitializeButtonPanel() {
        JPanel buttonPanel = new JPanel();

        reset = new JButton("Reset");
        giveUp = new JButton("Give Up");

        reset.addActionListener(actionListener);
        giveUp.addActionListener(actionListener);

        buttonPanel.add(reset);
        buttonPanel.add(giveUp);
        frame.add(buttonPanel, BorderLayout.SOUTH);
    }

    private void InitializeGrid() {
        Container grid = new Container();
        grid.setLayout(new GridLayout(gridSize, gridSize));

        for (int row = 0; row < gridSize; row++) {
            for (int col = 0; col < gridSize; col++) {
                cells[row][col] = new Cell(row, col, actionListener);
                grid.add(cells[row][col]);
            }
        }
        CreateMines();
        frame.add(grid, BorderLayout.CENTER);
    }

    private void ResetAllCells() {
        for (int row = 0; row < gridSize; row++) {
            for (int col = 0; col < gridSize; col++) {
                cells[row][col].Reset();
            }
        }
    }

    private void CreateMines() {
        ResetAllCells();

        final int    mineCount = (int) p.GetPopulationMultiplier() * gridSize;
        final Random random    = new Random();

        // Map all (row, col) pairs to unique integers
        Set<Integer> positions = new HashSet<>(gridSize * gridSize);
        for (int row = 0; row < gridSize; row++) {
            for (int col = 0; col < gridSize; col++) {
                positions.add(row * gridSize + col);
            }
        }

        // Initialize mines
        for (int index = 0; index < mineCount; index++) {
            int choice = random.nextInt(positions.size());
            int row    = choice / gridSize;
            int col    = choice % gridSize;
            cells[row][col].SetValue(p.GetMineCount());
            positions.remove(choice);
        }

        // Initialize neighbour counts
        for (int row = 0; row < gridSize; row++) {
            for (int col = 0; col < gridSize; col++) {
                if (!cells[row][col].isAMine()) {
                    cells[row][col].UpdateNeighbourCount();
                }
            }
        }
    }

    private void HandleCell(Cell cell) {
        if (cell.isAMine()) {
            cell.setForeground(Color.RED);
            cell.Reveal();
            RevealBoardAndDisplay("You clicked on a mine!");
            return;
        }
        if (cell.GetValue() == 0) {
            Set<Cell> positions = new HashSet<>();
            positions.add(cell);
            Cascade(positions);
        } else {
            cell.Reveal();
        }
        CheckForWin();
    }

    private void RevealBoardAndDisplay(String message) {
        for (int row = 0; row < gridSize; row++) {
            for (int col = 0; col < gridSize; col++) {
                if (!cells[row][col].isEnabled()) {
                    cells[row][col].Reveal();
                }
            }
        }

        JOptionPane.showMessageDialog(
                frame, message, "Game Over",
                JOptionPane.ERROR_MESSAGE
        );

        CreateMines();
    }

    private void Cascade(Set<Cell> positionsToClear) {
        while (!positionsToClear.isEmpty()) {
            // Set does not have a clean way for retrieving
            // a single element. This is the best way I could think of.
            Cell cell = positionsToClear.iterator().next();
            positionsToClear.remove(cell);
            cell.Reveal();

            cell.GetNeighbours(Minesweeper.reusableStorage);
            for (Cell neighbour : Minesweeper.reusableStorage) {
                if (neighbour == null) {
                    break;
                }
                if (neighbour.GetValue() == 0
                        && neighbour.isEnabled()) {
                    positionsToClear.add(neighbour);
                } else {
                    neighbour.Reveal();
                }
            }
        }
    }

    private void CheckForWin() {
        boolean won = true;
        outer:
        for (Cell[] cellRow : cells) {
            for (Cell cell : cellRow) {
                if (!cell.isAMine() && cell.isEnabled()) {
                    won = false;
                    break outer;
                }
            }
        }

        if (won) {
            JOptionPane.showMessageDialog(
                    frame, "You have won!", "Congratulations",
                    JOptionPane.INFORMATION_MESSAGE
            );
        }
    }
}