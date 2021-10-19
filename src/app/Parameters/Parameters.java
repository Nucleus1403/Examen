package app.Parameters;

public class Parameters implements IParameters {

    //grid size
    private static final int GRID_SIZE = 10;

    private static final int MINE = 10;

    // The size in pixels for the frame.
    private static final int SIZE = 800;

    // The number of mines at generated is the grid size * this constant
    private static final double POPULATION_CONSTANT = 1.7;

    public int GetGridSize() {
        return GRID_SIZE;
    }

    public int GetMineCount() {
        return MINE;
    }

    public int GetPixelSize() {
        return SIZE;
    }

    public double GetPopulationMultiplier() {
        return POPULATION_CONSTANT;
    }
}
