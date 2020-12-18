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

    public void setxAtual(int xAtual) {}

    public void setyAtual(int yAtual) {}

    protected ArrayList<Equipamento> getEquipamentosVivos(){
        return equipamentos;
    }

    protected ArrayList<Equipamento> getEquipamentosZombies(){
        return destruidos;
    }

    // Metodo onde <Tipo> corresponde aos nomes (p.e. “Humano” ou “Zombie”).
    protected void getTipo(){
        if(this.idTipo == 0){
            tipo = "Criança (Zombie)";
        } else if (this.idTipo == 1) {
            tipo = "Adulto (Zombie)";
        } else if (this.idTipo == 2) {
            tipo = "Militar (Zombie)";
        } else if (this.idTipo == 3) {
            tipo = "Idoso (Zombie)";
        } else if (this.idTipo == 4) {
            tipo = "Zombie Vampiro";
        } else if (this.idTipo == 5) {
            tipo = "Criança (Vivo)";
        } else if (this.idTipo == 6) {
            tipo = "Adulto (Vivo)";
        } else if (this.idTipo == 7) {
            tipo = "Militar (Vivo)";
        } else if (this.idTipo == 8) {
            tipo = "Idoso (Vivo)";
        } else if (this.idTipo == 9) {
            tipo = "Cão";
        }
    }

    // Metodo onde <Equipa> corresponde aos nomes (p.e. “Os Vivos” ou “Os Outros”).
    public void getEquipaVivos() {
        if(this.idEquipa == 10){
            equipa = "Os Vivos";
        }
    }

    public void getEquipaZombie(){
        if (this.idEquipaZombie == 20){
            equipa = "Os Outros";
        }
    }

    // Metodo que devolve o nome do ficheiro de imagem (formato PNG) que representa a criatura.
    public String getImagePNG() {
        switch (id){
            case 1:
                return new String("idoso.png");
            case 2:
                return new String("jackie.png");
            case 3:
                return new String("alice.png");
            case 4:
                return new String("ash.png");
            case 5:
                return new String("dog.png");
            case 6:
                return new String("zomb.png");
            default:
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