package pt.ulusofona.lp2.theWalkingDEISIGame;

public abstract class Equipamento {

    int id;
    int idTipo;
    String titulo;

    int xAtual;
    int yAtual;
    int xAnterior;
    int yAnterior;

    public Equipamento(int id, int idTipo, int xAtual, int yAtual) {
        this.id = id;
        this.idTipo = idTipo;
        this.xAtual = xAtual;
        this.yAtual = yAtual;
    }

    protected abstract int getiD();

    protected abstract int getIdTipo();

    protected abstract int getXAtual();

    protected abstract int getYAtual();

    protected abstract String getTitulo();

    public void setIdTipo(int idTipo) {
        if(idTipo == 0){
            titulo = "Escudo de madeira";
        } else if (idTipo == 1){
            titulo = "Espada samurai";
        }
    }

    @Override
    public String toString () {
        return id + " | " + titulo + " @ (" + xAtual + ", " + yAtual + ") ";
    }
}