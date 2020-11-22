package pt.ulusofona.lp2.theWalkingDEISIGame;

import java.util.ArrayList;

public class Zombie {

    int id;
    int idTipo;
    int idEquipa = 1;
    String nome;
    ArrayList<Equipamento> destruidos = new ArrayList<>();

    String tipo;
    String equipa;
    int turnos;

    int xAtual;
    int yAtual;
    int xAnterior;
    int yAnterior;

    int nrZombies = 0;

    public Zombie() {
    }

    public Zombie (int id, int idTipo, String nome, int xAtual, int yAtual) {
        this.id = id;
        this.idTipo = idTipo;
        this.nome = nome;
        this.xAtual = xAtual;
        this.yAtual = yAtual;
    }

    // Devolve o ID da criatura.
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public int getXAtual() {
        return xAtual;
    }

    public int getYAtual() {
        return yAtual;
    }

    public void contarZombies (int nrZombies) {
        this.nrZombies += nrZombies;
    }

    // Metodo onde <Tipo> corresponde aos nomes (p.e. “Humano” ou “Zombie”).
    public String getTipo(){
        if(this.idTipo == 0){
            return tipo = "Zombie";
        } else {
            return tipo = "Humano";
        }
    }

    // Devolve o id da Equipa da criatura.
    public int getIdEquipa() {
        return this.idEquipa;
    }

    // Metodo onde <Equipa> corresponde aos nomes (p.e. “Os Vivos” ou “Os Outros”).
    public String getEquipa() {
        if(this.idEquipa == 0){
            return equipa = "Os Vivos";
        } else {
            return equipa = "Os Outros";
        }
    }

    public int getNrTurnos() {
        return turnos;
    }

    // Metodo que devolve o nome do ficheiro de imagem (formato PNG) que representa a criatura.
    public String getImagePNG() {
        return new String("zomb.png");
    }

    @Override
    public String toString() {
        if (destruidos != null){
            return id + " | " + tipo + " | " + equipa + " | " + nome + " " + destruidos.size() + " @ (" + xAtual + ", " + yAtual + ")";
        } else {
            return id + " | " + tipo + " | " + equipa + " | " + nome + " sem equipamento destruido " + "@ (" + xAtual + ", " + yAtual + ")";
        }
    }
}