package pt.ulusofona.lp2.theWalkingDEISIGame;

public class Adulto extends Creature {

    public Adulto(int id, int idTipo, String nome, int xAtual, int yAtual) {
        super(id, idTipo, nome, xAtual, yAtual);
    }

    @Override
    public boolean move(int xO, int yO, int xD, int yD) {
        return false;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public int getIdEquipa() {
        return idEquipa;
    }

    @Override
    public String getNome() {
        return nome;
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
}
