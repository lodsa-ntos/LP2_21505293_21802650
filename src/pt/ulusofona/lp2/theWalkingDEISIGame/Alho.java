package pt.ulusofona.lp2.theWalkingDEISIGame;

public class Alho extends Equipamento{

    public Alho(int id, int idTipo, int xAtual, int yAtual) {
        super(id, idTipo, xAtual, yAtual);
    }

    @Override
    public int getiD() {
        return id = -6;
    }

    @Override
    public int getIdTipo() {
        return idTipo = 5;
    }

    @Override
    public int getXAtual() {
        return xAtual;
    }

    @Override
    public int getYAtual() {
        return yAtual;
    }

    @Override
    public String getTitulo() {
        return titulo = "Cabeça de Alho";
    }
}
