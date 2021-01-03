package pt.ulusofona.lp2.theWalkingDEISIGame;

public class EscudoMadeira extends Equipamento{

    public EscudoMadeira(int id, int idTipo, int xAtual, int yAtual) {
        super(id, idTipo, xAtual, yAtual);
        this.countUsos = 1;
        this.escudoUsado = false;
    }


}
