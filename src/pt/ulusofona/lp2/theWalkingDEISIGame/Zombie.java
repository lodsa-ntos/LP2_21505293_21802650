package pt.ulusofona.lp2.theWalkingDEISIGame;

import java.util.ArrayList;

public class Zombie extends Creature {

    public Zombie(int id, int idTipo, String nome, int xAtual, int yAtual) {
        super(id, idTipo, nome, xAtual, yAtual);
    }

    // Devolve o ID da criatura.
    public int getId() {
        return id;
    }

    public int getIdEquipa() {
        return idEquipa;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public int getIdTipo() {
        return idTipo;
    }

    public int getXAtual() {
        return xAtual;
    }

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

    // Metodo onde <Tipo> corresponde aos nomes (p.e. “Humano” ou “Zombie”).
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

    // Metodo que devolve o nome do ficheiro de imagem (formato PNG) que representa a criatura.
    @Override
    public String getImagePNG() {
        return new String("zomb.png");
    }

    @Override
    public String toString() {
        if (destruidos != null){
            return id + " | " + tipo + " | " + equipa + " | " + nome + " " + destruidos.size() + " @ (" + xAtual + ", " + yAtual + ")";
        } else {
            return id + " | " + tipo + " | " + equipa + " | " + nome + "@ (" + xAtual + ", " + yAtual + ")";
        }
    }
}