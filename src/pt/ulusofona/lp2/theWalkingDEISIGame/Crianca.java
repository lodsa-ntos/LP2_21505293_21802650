package pt.ulusofona.lp2.theWalkingDEISIGame;

import java.util.ArrayList;

import static pt.ulusofona.lp2.theWalkingDEISIGame.TWDGameManager.creatures;

public class Crianca extends Creature {

    public Crianca(int id, int idTipo, String nome, int xAtual, int yAtual) {
        super(id, idTipo, nome, xAtual, yAtual);
    }

    @Override
    public boolean move(int xO, int yO, int xD, int yD) {

        if (idTipo == 0) {
            for(Creature crianca : creatures) {
                if (Math.abs(xO - xD) <= 1 && Math.abs(yO - yD) <= 1) {

                }
            }
        }

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
