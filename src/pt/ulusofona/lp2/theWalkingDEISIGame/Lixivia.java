package pt.ulusofona.lp2.theWalkingDEISIGame;

public class Lixivia extends Equipamento{

    public Lixivia(int id, int idTipo, int xAtual, int yAtual) {
        super(id, idTipo, xAtual, yAtual);
        this.countUsos = 3;
    }

    @Override
    public void diminuiCountUsos() {
        countUsos -= 3;
    }
}
