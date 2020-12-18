package pt.ulusofona.lp2.theWalkingDEISIGame;

import pt.ulusofona.lp2.theWalkingDEISIGame.Creature;
import pt.ulusofona.lp2.theWalkingDEISIGame.Equipamento;

import java.util.ArrayList;

public class Militar extends Creature {

    public Militar(int id, int idTipo, String nome, int xAtual, int yAtual) {
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
