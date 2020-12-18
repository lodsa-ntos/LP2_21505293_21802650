package pt.ulusofona.lp2.theWalkingDEISIGame;

import java.util.ArrayList;

public abstract class Zombie extends Creature {

    public Zombie(int id, int idTipo, String nome, int xAtual, int yAtual) {
        super(id, idTipo, nome, xAtual, yAtual);
    }

        @Override
    public int getId() {
        return id;
    }

    @Override
    public int getIdEquipa() {
        return idEquipaZombie = 20;
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

    @Override
    public ArrayList<Equipamento> getEquipamentos() {
        return destruidos;
    }

    public void contarZombies (int nrZombies) {
        this.nrZombies += nrZombies;
    }
}