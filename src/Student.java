interface IStudent{
    static final String FINANTARE_BUGETAR=null;
    static final String FINANTARE_TAXA=null;
    boolean esteIntegralist();
}
interface IPersoana{
    void SetNume(String nume);
    void SetPrenume(String prenume);

}

class Student implements IPersoana,IStudent {
    public String nume;
    public String prenume;
    public boolean esteIntegralist()
    {
        return true;
    }
    public void SetNume(String nume)
    {
        this.nume = nume;
    }
    public void SetPrenume(String prenume)
    {
        this.prenume = prenume;
    }

}
