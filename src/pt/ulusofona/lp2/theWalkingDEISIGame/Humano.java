package pt.ulusofona.lp2.theWalkingDEISIGame;

import java.awt.*;

public class Humano {

    int id;
    int idTipo;
    int idEquipa;
    String nome;

    String tipo;
    String equipa;

    int xAtual;
    int yAtual;
    int xAnterior;
    int yAnterior;

    int nrHumanos = 0;

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

    public String getTipo() {
        return tipo;
    }

    public int getXAtual() {
        return xAtual;
    }

    public int getYAtual() {
        return yAtual;
    }

    public void setNrHumanos(int nrHumanos) {
        this.nrHumanos += nrHumanos;
    }

    public void setTipo(int idTipo){
        if(idTipo == 0){
            tipo = "Zombie";
        } else if (idTipo == 1){
            tipo = "Humano";
        } else{
            tipo = "Erro!! Tipo nao reconhecido!";
        }
    }

    // Devolve o id da Equipa da criatura.
    public int getIdEquipa() {
        return idEquipa;
    }

    // Metodo onde <Equipa> corresponde aos nomes (p.e. “Os Vivos” ou “Os Outros”).
    public void setEquipa(int idEquipa) {
        if(idEquipa == 0){
            equipa = "Os Vivos";
        } else if (idTipo == 1){
            equipa = "Os Outros";
        } else{
            equipa = "Erro!! Equipa nao reconhecida!";
        }
    }

    public void mudarCoordenadas (int novoX, int novoY){
        this.xAtual = novoX;
        this.yAtual = novoY;
    }

    public String getImagePNG() {
        return new String("hum.png");
    }

    @Override
    public String toString() {
        return id + " | " + tipo + " | " + equipa + " | " + nome + " | (" + xAtual + " , " + yAtual + ") " ;
    }
}
