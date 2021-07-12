package pt.ulusofona.lp2.theWalkingDEISIGame;

public class Porta {
    private final int xAtual;
    private final int yAtual;

    public Porta(int xAtual, int yAtual) {
        this.xAtual = xAtual;
        this.yAtual = yAtual;
    }

    public int getXAtual() {
        return xAtual;
    }

    public int getYAtual() {
        return yAtual;
    }

    @Override
    public String toString() {
        return xAtual + " : " + yAtual;
    }
}
