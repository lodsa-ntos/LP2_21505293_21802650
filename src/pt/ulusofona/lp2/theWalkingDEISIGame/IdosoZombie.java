package pt.ulusofona.lp2.theWalkingDEISIGame;

import java.util.ArrayList;

public class IdosoZombie extends Creature{
    public IdosoZombie(int id, int idTipo, String nome, int xAtual, int yAtual) {
        super(id, idTipo, nome, xAtual, yAtual);
    }

    @Override
    protected boolean move(int xO, int yO, int xD, int yD, Creature creature, ArrayList<Creature> creatures) {
        return false;
    }

    @Override
    protected boolean move(int xO, int yO, int xD, int yD) {
        return false;
    }
}
