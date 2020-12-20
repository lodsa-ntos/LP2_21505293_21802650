package pt.ulusofona.lp2.theWalkingDEISIGame;

public class Crianca extends Creature {

    public Crianca(int id, int idTipo, String nome, int xAtual, int yAtual) {
        super(id, idTipo, nome, xAtual, yAtual);
    }

    @Override
    public boolean move(int xO, int yO, int xD, int yD) {
        return false;
    }
}
