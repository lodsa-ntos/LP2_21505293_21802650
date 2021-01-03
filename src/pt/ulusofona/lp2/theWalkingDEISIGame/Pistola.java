package pt.ulusofona.lp2.theWalkingDEISIGame;

public class Pistola extends Equipamento{

    public Pistola(int id, int idTipo, int xAtual, int yAtual) {
        super(id, idTipo, xAtual, yAtual);
        countUsos = 3;
    }

}
