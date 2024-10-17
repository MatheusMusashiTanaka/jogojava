public class monstro {
    public int PV;
    public int NV;
    public int Dmg;

    public monstro(int NV) {
        this.PV = NV * 8;
        this.Dmg = (int) (NV * 4.5);
    }
}
