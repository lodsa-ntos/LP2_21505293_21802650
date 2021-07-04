package pt.ulusofona.lp2.theWalkingDEISIGame;

public class Equipamento {

    private int id;
    private int idTipo;
    private String titulo;

    private int xAtual;
    private int yAtual;
    private int countUsos;
    private int countProtecaoDoEscudo;
    private int nrSalvacoes = 0;
    private boolean escudoUsado;

    public Equipamento() {
    }

    public Equipamento(int id, int idTipo, int xAtual, int yAtual) {
        this.id = id;
        this.idTipo = idTipo;
        this.xAtual = xAtual;
        this.yAtual = yAtual;
        this.escudoUsado = false;
        if(idTipo == 0) {
            // Escudo de madeira
            // Equipamento Defensivo
            /* Protecao contra 1 ataque zombie = Defesa de 1 ataque (vivo) // Defesa de 2 ataques (Militar vivo) */
            countProtecaoDoEscudo = 1;
        } else if(idTipo == 2) {
            // Pistola
            // Equipamento Ofensivo
            /* Tem 3 balas */
            countUsos = 3;
        } else if(idTipo == 7) {
            // Lixivia
            // Equipamento Defensivo
            /* Necessário 3 litros de lixivia */
            countUsos = 3;
        }
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

    public int getXAtual() {
        return xAtual;
    }

    public void setXAtual(int xAtual) {
        this.xAtual = xAtual;
    }

    public int getYAtual() {
        return yAtual;
    }

    public void setYAtual(int yAtual) {
        this.yAtual = yAtual;
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

    public int getCountUsos() {
        return countUsos;
    }

    public int getCountProtecaoDoEscudo() {
        return countProtecaoDoEscudo;
    }

    public void aumentaProtecaoDoEscudo() {
        this.countProtecaoDoEscudo = 2;
    }

    public void diminuiCountUsos() {
        this.countUsos -= 1;
    }

    public void diminuiProtecaoDoEscudo() {this.countProtecaoDoEscudo -= 1;}

    public boolean isEscudoUsado() {
        return escudoUsado;
    }

    public void escudoFoiUsado() {
        this.escudoUsado = true;
    }

    public void incrementaNrSalvacoes() {
        this.nrSalvacoes++;
    }

    public int getNrSalvacoes() {
        return nrSalvacoes;
    }

    @Override
    public String toString () {
        return id + " | " + titulo + " @ (" + xAtual + ", " + yAtual + ") ";
    }

}