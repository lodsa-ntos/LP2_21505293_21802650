package pt.ulusofona.lp2.theWalkingDEISIGame;

import java.util.ArrayList;

public class Cao extends Creature {

    public Cao(int id, int idTipo, String nome, int xAtual, int yAtual) {
        super(id, idTipo, nome, xAtual, yAtual);
    }

    @Override
    protected boolean processarCombateOfensivo(int xO, int yO, int xD, int yD, Creature creature, ArrayList<Creature> creatures) {

        /* CÃO A ATACAR */
        if (this.idTipo == 9) {

            // se o vivo atacar sem equipamento nao é valido
            if (this.equipamentos.size() == 0) {
                return false;
            }

            /* EQUIPAMENTOS OFENSIVOS */
            switch (this.equipamentos.get(0).getIdTipo()) {
                case 1: /* Interação com a Espada */
                case 6: /* Interação com a Estaca de madeira */
                case 10: { /* Interação com o capacete Beskar Helmet */

                    /* vamos destruir o zombie */
                    creatures.remove(creature);
                    TWDGameManager.zombiesDestruidos.add(creature);

                    /* incrementa o numero de zombies destruidos */
                    countZombiesDestruidos();
                    creature.setZombieIsDestroyed(true);
                    /* Depois do zombie ser destruido diminuimos a quantidade de zombies em jogo */
                    Creature.decrementCreaturesZombies();
                    /* E diminuimos a totalidade de criaturas em jogo */
                    Creature.decrementCreatures();

                    /* incrementa o numero de salvacao feita pelo equipamento */
                    this.equipamentos.get(0).incrementaNrSalvacoes();

                    this.setxAtual(creature.xAtual);
                    this.setyAtual(creature.yAtual);
                    return true;
                }

                case 2: { /* Interação com a Pistola */

                    if (this.getEquipamentosVivos().get(0).getCountUsos() == 0) {
                        //getEquipamentosVivos().get(0).isBroken();
                        creature.getEquipamentosVivos().remove(this.equipamentos.get(0));
                        return false;
                    }

                    if (creature.getIdTipo() != 4) {
                        this.equipamentos.get(0).diminuiCountUsos();/* ataque VS outros zombies, diminui uma bala*/

                        /* incrementa o numero de salvacao feita pelo equipamento */
                        this.equipamentos.get(0).incrementaNrSalvacoes();

                        /* vamos destruir o zombie */
                        creatures.remove(creature);
                        TWDGameManager.zombiesDestruidos.add(creature);

                        /* incrementa o numero de zombies destruidos */
                        countZombiesDestruidos();
                        creature.setZombieIsDestroyed(true);
                        /* Depois do zombie ser destruido diminuimos a quantidade de zombies em jogo */
                        Creature.decrementCreaturesZombies();
                        /* E diminuimos a totalidade de criaturas em jogo */
                        Creature.decrementCreatures();

                        this.setxAtual(creature.xAtual);
                        this.setyAtual(creature.yAtual);
                        return true;
                    } else {
                        /* A pistola não tem efeito contra Zombies Vampiros */
                        return false;
                    }
                }
            }
        }

        return false;
    }


    @Override
    public boolean moveDirecao(int xO, int yO, int xD, int yD, Creature creatureDestino) {
        return (Math.abs(xD - xO) > 0 && Math.abs(xD - xO) <= 2) && (Math.abs(yD - yO) > 0 && Math.abs(yD - yO) <= 2);
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
    public void setxAtual(int xAtual) {
        this.xAtual = xAtual;
    }

    @Override
    public void setyAtual(int yAtual) {
        this.yAtual = yAtual;
    }

    @Override
    public ArrayList<Equipamento> getEquipamentosVivos() {
        return equipamentos;
    }

    @Override
    public void setTipo(int idTipo) {
        if (idTipo == 9) {
            tipo = "Cão";
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
                idEquipa= -1;
                break;
        }
    }

    @Override
    public void transformaEmZombie(Creature creatureDestino) {
        /* O cão não se transforma */
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
        if (idTipo == 9) {
            return "dog.png";
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
        } else if (zombie.getCurrentTeamId() == 20 && isTransformado()){
            return id + " | " + tipo + " | " + equipa + " | " + nome + " " + equipamentosNoBolso + " @ (" + xAtual + ", " + yAtual + ")";
        } else if (equipa.equals("Os Vivos")){
            return id + " | " + tipo + " | " + equipa + " | " + nome + " " + equipamentosNoBolso + " @ (" + xAtual + ", " + yAtual + ")";
        } else if (equipa.equals("Os Outros")){
            return id + " | " + tipo + " | " + equipa + " | " + nome + " " + equipamentosNoBolso + " @ (" + xAtual + ", " + yAtual + ")";
        } else {
            return id + " | " + tipo + " | " + equipa + " | " + nome + " @ (" + xAtual + ", " + yAtual + ")";
        }
    }
}