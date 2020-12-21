package pt.ulusofona.lp2.theWalkingDEISIGame;

public class Antidoto extends Equipamento{

    public Antidoto(int id, int idTipo, int xAtual, int yAtual) {
        super(id, idTipo, xAtual, yAtual);
    }

    @Override
    public int getiD() {
        return id = -10;
    }

    @Override
    public int getIdTipo() {
        return idTipo = 9;
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
        return titulo = "Antídoto";
    }
}
