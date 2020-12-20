package pt.ulusofona.lp2.theWalkingDEISIGame;

import java.util.ArrayList;

public class ZombieVampiro extends Creature{

    public ZombieVampiro(int id, int idTipo, String nome, int xAtual, int yAtual) {
        super(id, idTipo, nome, xAtual, yAtual);
    }

    @Override
    public boolean move(int xO, int yO, int xD, int yD) {
        return false;
    }

    private ArrayList<Equipamento> getEquipamentos() {
        return destruidos;
    }
}
