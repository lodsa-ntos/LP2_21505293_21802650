package pt.ulusofona.lp2.theWalkingDEISIGame;

import java.util.ArrayList;

public abstract class Creature {
    int id;
    int idTipo;
    int idEquipa = 10;
    int idEquipaZombie = 20;
    String nome;

    String tipo;
    String equipa;
    boolean isAlive;

    int xAtual;
    int yAtual;
    int xAnterior;
    int yAnterior;

    int nrHumanos = 0;
    int nrZombies = 0;
    protected ArrayList<Equipamento> equipamentos = new ArrayList<>();
    protected ArrayList<Equipamento> destruidos = new ArrayList<>();

    public Creature(int id, int idTipo, String nome, int xAtual, int yAtual) {
        this.id = id;
        this.idTipo = idTipo;
        this.nome = nome;
        this.xAtual = xAtual;
        this.yAtual = yAtual;
    }

    public boolean move(int xO, int yO, int xD, int yD) {
        return false;
    }

        // Devolve o ID da criatura.
    public abstract int getId();

    public abstract int getIdEquipa();

    public abstract String getNome();

    public abstract int getIdTipo();

    public abstract int getXAtual();

    public abstract int getYAtual();

    protected abstract ArrayList<Equipamento> getEquipamentos();

    public void setxAtual(int xAtual) {}

    public void setyAtual(int yAtual) {}

    public void contarHumanos(int nrHumanos) {}

    public void contarZombies (int nrZombies) {}

    // Metodo onde <Tipo> corresponde aos nomes (p.e. “Humano” ou “Zombie”).
    public void getTipo(){
        if(this.idTipo == 1){
            tipo = "Adulto (Zombie)";
        } else if(this.idTipo == 6) {
            tipo = "Adulto (Vivo)";
        } else {
            tipo = "Cão";
        }
    }

    // Metodo onde <Equipa> corresponde aos nomes (p.e. “Os Vivos” ou “Os Outros”).
    public void getEquipa() {
        if(this.idEquipa == 10){
            equipa = "Os Vivos";
        }
    }

    public void getEquipaZ(){
        if (this.idEquipaZombie == 20){
            equipa = "Os Outros";
        }
    }


    // Metodo que devolve o nome do ficheiro de imagem (formato PNG) que representa a criatura.
    public String getImagePNG() {
        if(this.id == 1){
            return new String("idoso.png");
        } else if(this.id == 2) {
            return new String("jackie.png");
        } else if(this.id == 3) {
            return new String("alice.png");
        } else if(this.id == 4) {
            return new String("ash.png");
        } else if(this.id == 5) {
            return new String("dog.png");
        } else if(this.id == 6) {
            return new String("zomb.png");
        } else {
            return null;
        }
    }

    @Override
    public String toString() {
        if (equipa.equals("Os Vivos")){
            return id + " | " + tipo + " | " + equipa + " | " + nome + " " + equipamentos.size() + " @ (" + xAtual + ", " + yAtual + ")";
        } else if (equipa.equals("Os Outros")){
            return id + " | " + tipo + " | " + equipa + " | " + nome + " " + destruidos.size() + " @ (" + xAtual + ", " + yAtual + ")";
        } else {
            return id + " | " + tipo + " | " + equipa + " | " + nome + " @ (" + xAtual + ", " + yAtual + ")";
        }
    }

}