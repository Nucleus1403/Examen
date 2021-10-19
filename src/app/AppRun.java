package app;

import app.Parameters.Parameters;
import app.Scripts.Minesweeper;

import javax.swing.*;

public class AppRun {

    private static void Run() {
        try {
            // applies the look of OS making it look native.
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        } catch (Exception ignore) {
        }
        // Launch the program
        new Minesweeper();
    }

    public static void main(String[] args) {

        Parameters p = new Parameters();
        SwingUtilities.invokeLater(() -> Run());
    }
}
