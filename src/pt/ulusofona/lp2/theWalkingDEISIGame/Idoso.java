package pt.ulusofona.lp2.theWalkingDEISIGame;

import java.util.ArrayList;

public class Idoso extends Creature {
  /*  public Idoso() {
    }*/

    public Idoso(int id, int idTipo, String nome, int xAtual, int yAtual) {
        super(id, idTipo, nome, xAtual, yAtual);
    }

    @Override
    public boolean move(int xO, int yO, int xD, int yD,
                        Creature creature, ArrayList<Creature> creatures) {
       /* if (idEquipa == 10 && xAtual == xO && yAtual == yO) {
            setxAtual(xD);
            setyAtual(yD);

        }*/
        return false;
    }
}

