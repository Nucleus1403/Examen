public class Notebook {

    private String Ram;
    private String Type;
    private String Color;
    public static int MAX_POWER = 100;

    public Notebook(String color,String type,String ram)
    {
     this.Color = color;
     this.Type = type;
     this.Ram = ram;
    }
    public void printState()
    {
        System.out.println(Type);
    }
    public void changeCPU(String data)
    {
        this.Type = data;
    }

    public String toString() {
        return String.valueOf(MAX_POWER);
    }

}
