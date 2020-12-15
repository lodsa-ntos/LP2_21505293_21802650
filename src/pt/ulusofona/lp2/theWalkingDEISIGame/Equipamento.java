package pt.ulusofona.lp2.theWalkingDEISIGame;

public class Equipamento {

    int id;
    int idTipo;
    String titulo;

    int xAtual;
    int yAtual;
    int xAnterior;
    int yAnterior;

    int nrEquipamentos = 0;

    public Equipamento() {
    }

    public Equipamento(int id, int idTipo, int xAtual, int yAtual) {
        this.id = id;
        this.idTipo = idTipo;
        this.xAtual = xAtual;
        this.yAtual = yAtual;
    }

    public int getiD() {
        return id;
    }

    public int getIdTipo() {
        return idTipo;
    }

    public int getXAtual() {
        return xAtual;
    }

    public int getYAtual() {
        return yAtual;
    }

    public String getTitulo() {
        return titulo;
    }

    public void contarEquipamentos(int nrEquipamentos) {
        this.nrEquipamentos += nrEquipamentos;
    }

    public void setIdTipo(int idTipo) {
        if(idTipo == 0){
            titulo = "Escudo de madeira";
        } else if (idTipo == 1){
            titulo = "Espada samurai";
        }
    }

    public String getImagePNG() {
        if (this.id == 1) {
            return new String("equipment.png");
        } else if (this.id == 2) {
            return new String("steak.png");
        } else {
            return null;
        }
    }

    @Override
    public String toString() {
        return id + " | " + titulo + " @ (" + xAtual + ", " + yAtual + ") ";
    }
}