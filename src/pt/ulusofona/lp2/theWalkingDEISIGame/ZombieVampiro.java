package pt.ulusofona.lp2.theWalkingDEISIGame;

import java.util.ArrayList;

public class ZombieVampiro extends Creature {

    public ZombieVampiro(int id, int idTipo, String nome, int xAtual, int yAtual) {
        super(id, idTipo, nome, xAtual, yAtual);
    }

    @Override
    protected boolean processarCombateOfensivo(int xO, int yO, int xD, int yD, Creature creature, ArrayList<Creature> creatures) {
        return false;
    }

    @Override
    public boolean moveDirecao(int xO, int yO, int xD, int yD, Creature creatureDestino) {

        /* Proibir tentativa de salto 0,4 -> 1,2 */
        if (Math.abs(xO - xD) >= 2 && Math.abs(yO - yD) == 1 || Math.abs(xD - xO) == 1 && Math.abs(yD - yO) >= 2) {
            return false;
        }

        return Math.abs(xO - xD) <= 2 && Math.abs(yO - yD) <= 2;
    }

    @Override
    public boolean moveDirecaoTurnosPares(int xO, int yO, int xD, int yD, Creature creatureDestino) {
        return false;
    }

    @Override
    public boolean moveDirecaoTurnosImpares(int xO, int yO, int xD, int yD, Creature creatureDestino) {
        return false;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public int getIdEquipa() {
        return idEquipa;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public int getIdTipo() {
        return idTipo;
    }

    @Override
    public void setIdTipo(int idTipo) {
        this.idTipo = idTipo;
    }

    @Override
    public void setIdEquipa(int idEquipa) {
        this.idEquipa = idEquipa;
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
    public void setXAtual(int xAtual) {
        this.xAtual = xAtual;
    }

    @Override
    public void setYAtual(int yAtual) {
        this.yAtual = yAtual;
    }

    @Override
    public ArrayList<Equipamento> getEquipamentosVivos() {
        return equipamentos;
    }

    @Override
    public void setTipo(int idTipo) {
        if (idTipo == 4) {
            tipo = "Zombie Vampiro";
        } else {
            tipo = "";
        }
    }

    @Override
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
                idEquipa = -1;
                break;
        }
    }

    @Override
    public void transformaEmZombie(Creature creatureDestino) {
        int creatureIdTipo = creatureDestino.getIdTipo();
        switch (creatureDestino.getIdTipo()) {
            case 5:
            case 6:
            case 7:
            case 8:
                creatureDestino.setTipo(creatureIdTipo - 5);
                creatureDestino.setEquipa(creatureIdTipo - 5);
                creatureDestino.setIdTipo(creatureIdTipo - 5);
                creatureDestino.setIdEquipa(20);
                break;
            case 12:
                creatureDestino.setTipo(creatureIdTipo + 1);
                creatureDestino.setEquipa(creatureIdTipo + 1);
                creatureDestino.setIdTipo(creatureIdTipo + 1);
                creatureDestino.setIdEquipa(20);
                break;
        }
    }
    @Override
    public int getEquipamentosNoBolso() {
        return equipamentosNoBolso;
    }

    @Override
    public int countZombiesDestruidos() {
        return zombiesDestruidos++;
    }

    @Override
    public int getZombiesDestruidos() {
        return zombiesDestruidos;
    }

    @Override
    public void incrementaCreaturesNoBolso() {
        this.creaturesNoBolso++;
    }

    @Override
    public void incrementaEquipamentosNoBolso() {
        this.equipamentosNoBolso++;
    }

    @Override
    public void incrementaSemEquipamentoDepoisDeTransformado(int vivoTransformadoSemEquipamento) {
        equipamentosNoBolso = vivoTransformadoSemEquipamento;
    }

    @Override
    public boolean isInSafeHaven() {
        return isInSafeHaven;
    }

    @Override
    public void inSafeHaven(boolean inSafeHaven) {
        isInSafeHaven = inSafeHaven;
    }

    @Override
    public boolean isEnvenenado() {
        return isEnvenenado;
    }

    @Override
    public void setEnvenenado(boolean envenenado) {
        isEnvenenado = envenenado;
    }

    @Override
    public void countTransformacoesFeitasPorZombies() {
        countTransformacoesFeitas++;
    }

    @Override
    public int getNumTransformacoesFeitasPorZombies() {
        return countTransformacoesFeitas;
    }

    @Override
    public boolean isTransformado() {
        return transformado;
    }

    @Override
    public void setTransformado(boolean criaturaTransformada) {
        transformado = criaturaTransformada;
    }

    @Override
    public boolean zombieIsDestroyed() {
        return isDestroyed;
    }

    @Override
    public void setZombieIsDestroyed(boolean criaturaZombieDestruida) {
        isDestroyed = criaturaZombieDestruida;
    }

    @Override
    public void setZombieIsRIP(boolean zombieArrivadeci) {
        zombieIsRIP = zombieArrivadeci;
    }

    @Override
    public boolean humanDeadPorEnvenenamento() {
        return deadPorEnvenenamento;
    }

    @Override
    public void setHumanDeadPorEnvenenamento(boolean criaturaIsDead) {
        deadPorEnvenenamento = criaturaIsDead;
    }

    @Override
    public String getTipo() {
        return tipo;
    }

    @Override
    public String getImagePNG() {
        if (idTipo == 4) {
            return "zombieVampiro.png";
        }
        return null;
    }

    @Override
    public String toString() {
        TWDGameManager zombie = new TWDGameManager();

        if (isInSafeHaven()){
            return id + " | " + tipo + " | " + equipa + " | " + nome + " " + equipamentosNoBolso + " @ A salvo";
        } else if (zombieIsDestroyed() || humanDeadPorEnvenenamento()){
            return id + " | " + tipo + " | " + equipa + " | " + nome + " " + 0 + " @ (RIP)";
        }  else if (equipa.equals("Os Vivos") || equipa.equals("Os Outros") || (zombie.getCurrentTeamId() == 20 && isTransformado())){
            return id + " | " + tipo + " | " + equipa + " | " + nome + " " + equipamentosNoBolso + " @ (" + xAtual + ", " + yAtual + ")";
        }  else {
            return id + " | " + tipo + " | " + equipa + " | " + nome + " @ (" + xAtual + ", " + yAtual + ")";
        }
    }
}
