package pt.ulusofona.lp2.theWalkingDEISIGame;

public class Humano {

    int id;
    int idTipo;
    int idEquipa = 0;
    String nome;

    String tipo;
    String equipa;

    int xAtual;
    int yAtual;
    int xAnterior;
    int yAnterior;

    int nrHumanos = 0;

    public Humano() {
    }

    public Humano(int id, int idTipo, String nome, int xAtual, int yAtual) {
        this.id = id;
        this.idTipo = idTipo;
        this.nome = nome;
        this.xAtual = xAtual;
        this.yAtual = yAtual;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
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

    public void contarHumanos(int nrHumanos) {
        this.nrHumanos += nrHumanos;
    }

    public String getTipo(){
        if(this.idTipo == 0){
            return tipo = "Zombie";
        } else {
            return tipo = "Humano";
        }
    }

    // Devolve o id da Equipa da criatura.
    public int getIdEquipa() {
        return idEquipa;
    }

    // Metodo onde <Equipa> corresponde aos nomes (p.e. “Os Vivos” ou “Os Outros”).
    public String getEquipa() {
        if(this.idEquipa == 0){
            return equipa = "Os Vivos";
        } else {
            return equipa = "Os Outros";
        }
    }

    public void mudarCoordenadas (int novoX, int novoY){
        this.xAtual = novoX;
        this.yAtual = novoY;
    }

    public String getImagePNG() {
        return new String("hum.png");
    }

    @Override
    public String toString() {
        return id + " | " + tipo + " | " + equipa + " | " + nome + " | (" + xAtual + " , " + yAtual + ") " ;
    }
}