package pt.ulusofona.lp2.theWalkingDEISIGame;

public class Lixivia extends Equipamento{

    public Lixivia(int id, int idTipo, int xAtual, int yAtual) {
        super(id, idTipo, xAtual, yAtual);
    }

    @Override
    public int getiD() {
        return id = -8;
    }

    @Override
    public int getIdTipo() {
        return idTipo = 7;
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
        return titulo = "Garrafa de Lixívia (1 litro)";
    }
}
