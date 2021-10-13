public class TablaNrInstanta {

    private static final int Size = 410;
    private int TypeNUmber;
    private int TypeNUmber2;
    public static int count;

    public TablaNrInstanta()
    {
        count++;
    }


    public TablaNrInstanta(int typeNUmber)
    {
        count++;
        this.TypeNUmber = typeNUmber;
    }

    @Override
    public String toString() {
        return "TablaNrInstanta{" +
                "TypeNUmber=" + TypeNUmber +
                ", TypeNUmber2=" + TypeNUmber2 +
                '}';
    }
}
