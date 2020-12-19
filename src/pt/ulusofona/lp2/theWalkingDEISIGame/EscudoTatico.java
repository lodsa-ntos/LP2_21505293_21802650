package pt.ulusofona.lp2.theWalkingDEISIGame;

public class EscudoTatico extends Equipamento{

    public EscudoTatico(int id, int idTipo, int xAtual, int yAtual) {
        super(id, idTipo, xAtual, yAtual);
    }

    @Override
    public int getiD() {
        return id;
    }

    @Override
    public int getIdTipo() {
        return idTipo;
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
        return titulo;
    }
}
