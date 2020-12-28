package pt.ulusofona.lp2.theWalkingDEISIGame;

import java.util.ArrayList;

public class Cao extends Creature {

    public Cao(int id, int idTipo, String nome, int xAtual, int yAtual) {
        super(id, idTipo, nome, xAtual, yAtual);
    }

    @Override
    public boolean move(int xO, int yO, int xD, int yD,
                        Creature creature, ArrayList<Creature> creatures) {
        return false;
    }

}