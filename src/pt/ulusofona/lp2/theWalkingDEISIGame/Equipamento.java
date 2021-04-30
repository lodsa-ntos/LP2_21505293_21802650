package pt.ulusofona.lp2.theWalkingDEISIGame;

import java.util.ArrayList;
import java.util.Collection;

public class Equipamento {

    private int id;
    private int idTipo;
    private String titulo;

    private int xAtual;
    private int yAtual;
    private int countUsos;
    private int nrSalvacoes = 0;
    private boolean escudoUsado;
    protected boolean isEquipamento;
    protected boolean isEquipmentZombie;

    ArrayList<Equipamento> equipamentosQueSafaram = new ArrayList<>();

    public Equipamento() {
    }

    public Equipamento(int id, int idTipo, int xAtual, int yAtual) {
        this.id = id;
        this.idTipo = idTipo;
        this.xAtual = xAtual;
        this.yAtual = yAtual;
        this.escudoUsado = false;
        if(idTipo == 0) {
            //Escudo de madeira
            // Equipamento Defensivo
            /*Protecao contra 1 ataque zombie*/
            countUsos = 1;
        } else if(idTipo == 2) {
            //Pistola
            // Equipamento Ofensivo
            /*Tem 3 balas (permite matar 3 balas)*/
            countUsos = 3;
        } else if(idTipo == 7) {
            //Lixivia
            // Equipamento Defensivo
            /*Protecao contra ataques zombies (Necessário 3 litros de lixivia)*/
            countUsos = 3;
        } else if(idTipo == 8) {
            //Veneno
            // Equipamento Defensivo
            /*Protecao durante 2 turnos*/
            /*Se o "Vivo" estiver envenenado durante 3 turnos, morre*/
            countUsos = 3;
        }
    }

    public int getId() {
        return id;
    }

    public int getIdTipo() {
        return idTipo;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getxAtual() {
        return xAtual;
    }

    public void setxAtual(int xAtual) {
        this.xAtual = xAtual;
    }

    public int getyAtual() {
        return yAtual;
    }

    public void setyAtual(int yAtual) {
        this.yAtual = yAtual;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setIdTipo(int idTipo) {
        if(idTipo == 0) {
            titulo = "Escudo de Madeira";
        } else if (idTipo == 1) {
            titulo = "Espada Hattori Hanzo";
        } else if (idTipo == 2) {
            titulo = "Pistola Walther PPK";
        } else if (idTipo == 3) {
            titulo = "Escudo Tático";
        } else if (idTipo == 4) {
            titulo = "Revista Maria";
        } else if (idTipo == 5) {
            titulo = "Cabeça de Alho";
        } else if (idTipo == 6) {
            titulo = "Estaca de Madeira";
        } else if (idTipo == 7) {
            titulo = "Garrafa de Lixívia (1 litro)";
        } else if (idTipo == 8) {
            titulo = "Veneno";
        } else if (idTipo == 9) {
            titulo = "Antídoto";
        } else if (idTipo == 10) {
            titulo = "Beskar Helmet";
        }
    }

    public int getCountUsos() {
        return countUsos;
    }

    public void aumentaCountUsos() {
        this.countUsos += 1;
    }

    public void diminuiCountUsos() {
        this.countUsos -= 1;
    }

    public void isBroken() {this.countUsos = 0;}

    public boolean isEscudoUsado() {
        return escudoUsado;
    }

    public void escudoFoiUsado() {
        this.escudoUsado = true;
    }

    public ArrayList<Equipamento> getEquipamentosSafos() {
        return equipamentosQueSafaram;
    }

    public void incrementaNrSalvacoes() {
        this.nrSalvacoes++;
    }

    public int getNrSalvacoes() {
        return nrSalvacoes;
    }

    public boolean encontrouEquipamento() {
        return isEquipamento;
    }

    public void encontrouEquipamento(boolean isEquipamento) {
        this.isEquipamento = isEquipamento;
    }

    public boolean foundEquipmentZombie() {
        return isEquipmentZombie;
    }

    public void foundEquipmentZombie(boolean isEquipmentZombie) {
        this.isEquipmentZombie = isEquipmentZombie;
    }

    @Override
    public String toString () {
        return id + " | " + titulo + " @ (" + xAtual + ", " + yAtual + ") ";
    }

}