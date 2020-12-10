package pt.ulusofona.lp2.theWalkingDEISIGame;

import java.util.ArrayList;

public class Humano extends Creature{

    public Humano(int id, int idTipo, String nome, int xAtual, int yAtual) {
        super(id, idTipo, nome, xAtual, yAtual);

    }

    public int getId() {
        return id;
    }

    public int getIdEquipa() {
        return idEquipa;
    }

    public String getNome() {
        return nome;
    }

    public int getIdTipo() {
        return idTipo;
    }

    public void setxAtual(int xAtual) {
        this.xAtual = xAtual;
    }

    public int getXAtual() {
        return xAtual;
    }

    public int getYAtual() {
        return yAtual;
    }

    public void setyAtual(int yAtual) {
        this.yAtual = yAtual;
    }

    public ArrayList<Equipamento> getEquipamentos() {
        return equipamentos;
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

    // Metodo onde <Equipa> corresponde aos nomes (p.e. “Os Vivos” ou “Os Outros”).
    public void getEquipa() {
        if(this.idEquipa == 0){
            equipa = "Os Vivos";
        } else {
            equipa = "Os Outros";
        }
    }

    public String getImagePNG() {
        return new String("hum.png");
    }

    @Override
    public String toString() {
        if (equipamentos != null){
            return id + " | " + tipo + " | " + equipa + " | " + nome + " " + equipamentos.size() + " @ (" + xAtual + ", " + yAtual + ")";
        } else {
            return id + " | " + tipo + " | " + equipa + " | " + nome + " @ (" + xAtual + ", " + yAtual + ")";
        }
    }
}