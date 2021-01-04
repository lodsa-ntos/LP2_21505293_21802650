package pt.ulusofona.lp2.theWalkingDEISIGame;

import java.util.ArrayList;

public abstract class Creature {
    protected int id;
    protected int idTipo;
    protected int idEquipa;
    protected String nome;

    private String tipo;
    private String equipa;

    protected int xAtual;
    protected int yAtual;
    protected int xAnterior;
    protected int yAnterior;
    protected boolean envenenado;

    protected ArrayList<Equipamento> equipamentos = new ArrayList<>();
    protected ArrayList<Equipamento> destruidos = new ArrayList<>();
    protected ArrayList<Creature> envenenados = new ArrayList<>();

    public Creature(int id, int idTipo, String nome, int xAtual, int yAtual) {
        this.id = id;
        this.idTipo = idTipo;
        this.nome = nome;
        this.xAtual = xAtual;
        this.yAtual = yAtual;
    }

    protected abstract boolean move(int xO, int yO, int xD, int yD,
                                 Creature creature, ArrayList<Creature> creatures);

    protected abstract boolean move(int xO, int yO, int xD, int yD);

    protected boolean saltouPorCima(int xO, int yO, int xD, int yD, ArrayList<Creature> creatures) {
        // verifica direcao
        String direcao = this.qualDirecao(xO, xD, yO, yD);
        int diffX = 0;
        int diffY = 0;

        int meioX = 0;
        int meioY = 0;
        // se for horizontal significa que a diferenca do Y é o meio
        switch (direcao) {
            case "horizontal":
                diffX = Math.abs(xD - xO);
                meioY = yO;
                if (xD < xO) {
                    meioX = xD + (diffX / 2);
                } else {
                    meioX = xO + (diffX / 2);
                }

                break;
            case "vertical":
                diffY = Math.abs(yD - yO);
                meioX = xO;

                if (yD < yO) {
                    meioY = yD + (diffY / 2);
                } else {
                    meioY = yO + (diffY / 2);
                }
                break;
            case "diagonal":
                diffX = Math.abs(xD - xO);
                diffY = Math.abs(yD - yO);

                if (xD < xO) {
                    meioX = xD + (diffX / 2);
                } else {
                    meioX = xO + (diffX / 2);
                }

                if (yD < yO) {
                    meioY = yD + (diffY / 2);
                } else {
                    meioY = yO + (diffY / 2);
                }

                break;
        }

        // verifica se uma creatura ou equipamento esta naquela posicao
        for (Creature creature : creatures) {
            if (creature.getXAtual() == meioX && creature.getYAtual() == meioY) {
                return true;
            }
        }

        for (Equipamento equipamento : TWDGameManager.equipamentos) {
            if (equipamento.getxAtual() == meioX && equipamento.getyAtual() == meioY) {
                return true;
            }
        }
        return false;
    }

    // Devolve o ID da criatura.
    public int getId() {
        return id;
    }

    public int getIdEquipa() {
        return idEquipa;
    }

    public String getNome() {
        return nome;
    }

    public int getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(int idTipo) {
        this.idTipo = idTipo;
    }

    public void setIdEquipa(int idEquipa) {
        this.idEquipa = idEquipa;
    }

    public int getXAtual() {
        return xAtual;
    }

    public int getYAtual() {
        return yAtual;
    }

    public void setxAtual(int xAtual) {
        this.xAtual = xAtual;
    }

    public void setyAtual(int yAtual) {
        this.yAtual = yAtual;
    }

    public ArrayList<Equipamento> getEquipamentosVivos(){
        return equipamentos;
    }

    public ArrayList<Equipamento> getEquipamentosZombies(){
        return destruidos;
    }

    // Metodo onde <Tipo> corresponde aos nomes (p.e. “Humano” ou “Zombie”).
    protected void setTipo(int idTipo){
        switch (idTipo) {
            case 0:
                tipo = "Criança (Zombie)";
                break;
            case 1:
                tipo = "Adulto (Zombie)";
                break;
            case 2:
                tipo = "Militar (Zombie)";
                break;
            case 3:
                tipo = "Idoso (Zombie)";
                break;
            case 4:
                tipo = "Zombie Vampiro";
                break;
            case 5:
                tipo = "Criança (Vivo)";
                break;
            case 6:
                tipo = "Adulto (Vivo)";
                break;
            case 7:
                tipo = "Militar (Vivo)";
                break;
            case 8:
                tipo = "Idoso (Vivo)";
                break;
            case 9:
                tipo = "Cão";
                break;
            default:
                tipo = "";
                break;
        }
    }

    // Metodo onde <Equipa Vivos> corresponde ao nome (p.e. “Os Vivos”).
    // Metodo onde <Equipa Zombie> corresponde ao nome (p.e. “Os Outros”).
    public void setEquipa(int idTipo) {
        switch (idTipo) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
                equipa = "Os Outros";
                idEquipa = 20;
                break;
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
                equipa = "Os Vivos";
                idEquipa = 10;
                break;
            default:
                equipa = "";
                idEquipa= -1;
                break;
        }
    }

    protected void destroiEConverte(Creature creatureDestino) {
        creatureDestino.equipamentos.remove(0);
        if (creatureDestino.getIdTipo() == 5 || creatureDestino.getIdTipo() == 6 ||
                creatureDestino.getIdTipo() == 7 || creatureDestino.getIdTipo() == 8) {
            creatureDestino.setIdTipo(creatureDestino.getIdTipo() - 5);
            creatureDestino.setIdEquipa(20);
        }
    }

    protected String qualDirecao(int xO, int xD, int yO, int yD) {
        if (Math.abs(xD - xO) != 0 && Math.abs(yD - yO) == 0 ) {
            return "horizontal";
        } else if (Math.abs(xD - xO) == 0 && Math.abs(yD - yO) != 0 ) {
            return "vertical";
        } else if (Math.abs(xD - xO) == Math.abs(yD - yO)) {
            return "diagonal";
        }
        return "";
    }

    // Metodo que devolve o nome do ficheiro de imagem (formato PNG) que representa a criatura.
    public String getImagePNG(){
        switch (idTipo){
            case 0:
                return "zombieCrianca.png";
            case 1:
                return "zombieAdulto.png";
            case 2:
                return "zombieMilitar.png";
            case 3:
                return "zomb.png";
            case 4:
                return "zombieVampiro.png";
            case 5:
                return "crianca.png";
            case 6:
            case 7:
            case 8:
                return "human.png";
            case 9:
                return "dog.png";
        }
        return null;
    }

    @Override
    public String toString() {
        if (equipa.equals("Os Vivos")){
            return id + " | " + tipo + " | " + equipa + " | " + nome + " " + equipamentos.size() + " @ (" + xAtual + ", " + yAtual + ")";
        } else if (equipa.equals("Os Outros")){
            return id + " | " + tipo + " | " + equipa + " | " + nome + " " + destruidos.size() + " @ (" + xAtual + ", " + yAtual + ")";
        } else {
            return id + " | " + tipo + " | " + equipa + " | " + nome + " @ (" + xAtual + ", " + yAtual + ")";
        }
    }

}