import groovy.transform.ToString;

public class Tabla {
     private static final int Size = 410;
     private int TypeNUmber;
     ///singleton
     private static Tabla instance = null;
     ///
     public Tabla()
     {

     }
     /// singleton
     public static Tabla GetInstance()
     {
          if(instance==null)
               instance = new Tabla();
          return instance;

     }
     ///

     public Tabla(int typeNUmber)
     {
          this.TypeNUmber = typeNUmber;
     }

     @Override
     public String toString() {
          return "Tabla{" +
                  "TypeNUmber=" + TypeNUmber +
                  '}';
     }
}
