package pt.ulusofona.lp2.theWalkingDEISIGame;

public class EscudoMadeira extends Equipamento{

    public EscudoMadeira(int id, int idTipo, int xAtual, int yAtual) {
        super(id, idTipo, xAtual, yAtual);
        this.countUsos = 3;
    }

    @Override
    public int getID() {
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
