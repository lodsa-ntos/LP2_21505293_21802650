package pt.ulusofona.lp2.theWalkingDEISIGame;

public class Pistola extends Equipamento{


    public Pistola(int id, int idTipo, int xAtual, int yAtual) {
        super(id, idTipo, xAtual, yAtual);
    }

    public int getiD() {
        return id = -3;
    }

    public int getIdTipo() {
        return idTipo = 2;
    }

    public int getXAtual() {
        return xAtual;
    }

    public int getYAtual() {
        return yAtual;
    }

    public String getTitulo() {
        return titulo = "Pistola Walther PPK";
    }

}
