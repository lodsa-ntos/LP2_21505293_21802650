package pt.ulusofona.lp2.theWalkingDEISIGame;

public abstract class Equipamento {

    int id;
    int idTipo;
    String titulo;

    int xAtual;
    int yAtual;
    int xAnterior;
    int yAnterior;

    public Equipamento() {
    }

    public Equipamento(int id, int idTipo, int xAtual, int yAtual) {
        this.id = id;
        this.idTipo = idTipo;
        this.xAtual = xAtual;
        this.yAtual = yAtual;
    }

    public abstract int getiD();

    public abstract int getIdTipo();

    public abstract int getXAtual();

    public abstract int getYAtual();

    public abstract String getTitulo();

    public void setIdTipo(int idTipo) {
        if(idTipo == 0) {
            titulo = "Escudo de madeira";
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

    @Override
    public String toString () {
        return id + " | " + titulo + " @ (" + xAtual + ", " + yAtual + ") ";
    }
}