package pt.ulusofona.lp2.theWalkingDEISIGame;

import java.util.ArrayList;

public class Humano {

    int id;
    int idTipo;
    int idEquipa = 0;
    String nome;

    String tipo;
    String equipa;

    int xAtual;
    int yAtual;
    int xAnterior;
    int yAnterior;

    int nrHumanos = 0;
    ArrayList<Equipamento> equipamentos = new ArrayList<>();

    public Humano() {
    }

    public Humano(int id, int idTipo, String nome, int xAtual, int yAtual) {
        this.id = id;
        this.idTipo = idTipo;
        this.nome = nome;
        this.xAtual = xAtual;
        this.yAtual = yAtual;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public int getIdTipo() {
        return idTipo;
    }

    public int getXAtual() {
        return xAtual;
    }

    public int getYAtual() {
        return yAtual;
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

    // Devolve o id da Equipa da criatura.
    public int getIdEquipa() {
        return idEquipa;
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