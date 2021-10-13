public class Main {

    public static void main(String[] args) {
        Notebook n = new Notebook("whiteNigga","i5","4GB");
        n.printState();
        n.changeCPU("i7");
        String s = n.toString();
        int v = Notebook.MAX_POWER;

        Tabla t1 = new Tabla();
        Tabla t2 = new Tabla(210);

        TablaNrInstanta Tnr = new TablaNrInstanta();
        TablaNrInstanta Tn2 = new TablaNrInstanta(210);

        System.out.println(TablaNrInstanta.count);
        //singleton
        ///Tabla.GetInstance().toString();
        ///
    }
}
