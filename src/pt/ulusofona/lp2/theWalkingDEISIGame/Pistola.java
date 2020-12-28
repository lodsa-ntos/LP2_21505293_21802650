package pt.ulusofona.lp2.theWalkingDEISIGame;

public class Pistola extends Equipamento{

    public Pistola(int id, int idTipo, int xAtual, int yAtual) {
        super(id, idTipo, xAtual, yAtual);
        countUsos = 3;
    }

    public int getID() {
        return id;
    }

    public int getIdTipo() {
        return idTipo;
    }

    public int getXAtual() {
        return xAtual;
    }

    public int getYAtual() {
        return yAtual;
    }

    public String getTitulo() {
        return titulo;
    }

}
