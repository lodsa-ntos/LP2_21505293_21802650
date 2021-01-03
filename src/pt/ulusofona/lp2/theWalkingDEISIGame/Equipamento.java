package pt.ulusofona.lp2.theWalkingDEISIGame;

public abstract class Equipamento {

    protected int id;
    protected int idTipo;
    protected String titulo;

    protected int xAtual;
    protected int yAtual;
    protected int xAnterior;
    protected int yAnterior;

    protected double countUsos;
    protected boolean escudoUsado;

    public Equipamento() {
    }

    public Equipamento(int id, int idTipo, int xAtual, int yAtual) {
        this.id = id;
        this.idTipo = idTipo;
        this.xAtual = xAtual;
        this.yAtual = yAtual;
        countUsos = 0;
    }

    public int getId() {
        return id;
    }

    public int getIdTipo() {
        return idTipo;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getxAtual() {
        return xAtual;
    }

    public int getyAtual() {
        return yAtual;
    }

    public int getxAnterior() {
        return xAnterior;
    }

    public int getyAnterior() {
        return yAnterior;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setIdTipo(int idTipo) {
        if(idTipo == 0) {
            titulo = "Escudo de Madeira";
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

    public double getCountUsos() {
        return countUsos;
    }

    public void aumentaCountUsos() {
        this.countUsos += 1;
    }

    public void diminuiCountUsos() {
        this.countUsos -= 1;
    }

    public boolean isEscudoUsado() {
        return escudoUsado;
    }

    public void escudoFoiUsado() {
        this.escudoUsado = true;
    }

    @Override
    public String toString () {
        return id + " | " + titulo + " @ (" + xAtual + ", " + yAtual + ") ";
    }
}