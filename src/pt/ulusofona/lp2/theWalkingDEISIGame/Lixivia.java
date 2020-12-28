package pt.ulusofona.lp2.theWalkingDEISIGame;

public class Lixivia extends Equipamento{

    public Lixivia(int id, int idTipo, int xAtual, int yAtual) {
        super(id, idTipo, xAtual, yAtual);
        this.countUsos = 1;
    }

    @Override
    public int getID() {
        return id;
    }

    @Override
    public int getIdTipo() {
        return idTipo;
    }

    @Override
    public int getXAtual() {
        return xAtual;
    }

    @Override
    public int getYAtual() {
        return yAtual;
    }

    @Override
    public String getTitulo() {
        return titulo;
    }

    @Override
    public void diminuiCountUsos() {
        countUsos -= 0.3;
    }
}
