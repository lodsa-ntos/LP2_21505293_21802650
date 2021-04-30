package pt.ulusofona.lp2.theWalkingDEISIGame;

public class Porta {
    private int xAtual;
    private int yAtual;

    public Porta(int xAtual, int yAtual) {
        this.xAtual = xAtual;
        this.yAtual = yAtual;
    }

    public int getxAtual() {
        return xAtual;
    }

    public int getyAtual() {
        return yAtual;
    }

    @Override
    public String toString() {
        return xAtual + " : " + yAtual;
    }
}
