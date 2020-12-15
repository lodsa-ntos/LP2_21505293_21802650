package pt.ulusofona.lp2.theWalkingDEISIGame;

import java.util.ArrayList;

public class Humano extends Creature{

    public Humano(int id, int idTipo, String nome, int xAtual, int yAtual) {
        super(id, idTipo, nome, xAtual, yAtual);

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

    @Override
    public ArrayList<Equipamento> getEquipamentos() {
        return equipamentos;
    }

    @Override
    public String toString() {
        if (equipamentos != null){
            return id + " | " + tipo + " | " + equipa + " | " + nome + " " + equipamentos.size() + " @ (" + xAtual + ", " + yAtual + ")";
        } else {
            return id + " | " + tipo + " | " + equipa + " | " + nome + " @ (" + xAtual + ", " + yAtual + ")";
        }
    }

    public void contarHumanos(int nrHumanos) {
        this.nrHumanos += nrHumanos;
    }

    public void getTipo(){
        if(this.idTipo == 0){
            tipo = "Zombie";
        } else {
            tipo = "Humano";
        }
    }

    public void getEquipa() {
        if(this.idEquipa == 0){
            equipa = "Os Vivos";
        } else {
            equipa = "Os Outros";
        }
    }
}