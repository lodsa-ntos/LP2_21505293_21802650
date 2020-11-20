package pt.ulusofona.lp2.theWalkingDEISIGame;

import javax.swing.*;
import java.io.IOException;

import static java.lang.Thread.sleep;

public class Zombie {

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

    public String getTipo() {
        return tipo;
    }

    public int getXAtual() {
        return xAtual;
    }

    public int getYAtual() {
        return yAtual;
    }

    public void setnrZombies (int nrZombies) {
        this.nrZombies += nrZombies;
    }

    // Metodo onde <Tipo> corresponde aos nomes (p.e. “Humano” ou “Zombie”).
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

    // Metodo que devolve o nome do ficheiro de imagem (formato PNG) que representa a criatura.
    public String getImagePNG() {
        return new String("zomb.png");
    }


    @Override
    public String toString() {
        return id + " | " + tipo + " | " + equipa + " | " + nome + " | (" + xAtual + " , " + yAtual + ") " ;
    }
}
