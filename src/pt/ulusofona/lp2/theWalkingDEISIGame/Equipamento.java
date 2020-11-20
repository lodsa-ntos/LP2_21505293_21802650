package pt.ulusofona.lp2.theWalkingDEISIGame;

public class Equipamento {
    int id;
    int idTipo;
    String titulo;
    String descricao;

    int xAtual;
    int yAtual;
    int xAnterior;
    int yAnterior;

    int nrEquipamentos = 0;
    String erro;

    public Equipamento(int id, int idTipo, int xAtual, int yAtual) {
        this.id = id;
        this.idTipo = idTipo;
        this.xAtual = xAtual;
        this.yAtual = yAtual;
    }

    public int getiD() {
        return id;
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

    public String getDescricao() {
        return descricao;
    }

    public void setNrEquipamentos(int nrEquipamentos) {
        this.nrEquipamentos += nrEquipamentos;
    }

    public void setIdTipo(int idTipo) {
        if(idTipo == 0){
            titulo = "Escudo de madeira";
            descricao = "Permite obter proteção contra 1 ataque de zombies.";
        } else if (idTipo == 1){
            titulo = "Espada samurai";
            descricao = "Serve para decapitar e/ou desmembrar zombies.";
        } else{
            erro = "Erro!! Tipo nao reconhecido!";
        }
    }

    public String getImagePNG() {
        return null;
    }

    @Override
    public String toString() {
        return "Equipamento: " + id  + " | " + idTipo + " | " + xAtual + " | " + yAtual;
    }
}