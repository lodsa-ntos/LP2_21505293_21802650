package pt.ulusofona.lp2.theWalkingDEISIGame;

import java.util.ArrayList;

public class Crianca extends Creature {

    public Crianca(int id, int idTipo, String nome, int xAtual, int yAtual) {
        super(id, idTipo, nome, xAtual, yAtual);
    }

    @Override
    protected boolean processarCombateOfensivo(int xO, int yO, int xD, int yD, Creature creature, ArrayList<Creature> creatures) {

        TWDGameManager nrTurnosOfensivos = new TWDGameManager();

        /* CRIANCA VIVO A ATACAR */
            if (this.idTipo == 5) {

                // se o vivo atacar sem equipamento nao é valido
                if (this.equipamentos.size() == 0) {
                    return false;
                }

                /*EQUIPAMENTOS OFENSIVOS*/
                switch (this.equipamentos.get(0).getIdTipo()) {
                    case 1: { /* Interação com a Espada */
                        if (creature.getIdTipo() == 0) {
                            /* crianca viva com espada VS zombie crianca == processa o combate */
                            /* vamos destruir o zombie crianca e posicionar a crianca viva naquela posicao */
                            creatures.remove(creature);
                            TWDGameManager.zombiesDestruidos.add(creature);

                            /* incrementa o numero de zombies destruidos */
                            countZombiesDestruidos();
                            creature.setZombieIsDestroyed(true);
                            creature.setZombieIsRIP(true);

                            /* incrementa o numero de salvacao feita pelo equipamento */
                            this.equipamentos.get(0).incrementaNrSalvacoes();

                            this.setxAtual(creature.xAtual);
                            this.setyAtual(creature.yAtual);
                            this.getEquipamentosVivos().remove(this.equipamentos.get(0));
                            nrTurnosOfensivos.incrementarTurno();
                            return true;
                        } else {
                            /* criança viva com uma espada VS zombie que nao seja crianca == jogada invalida */
                            return false;
                        }
                    }

                    case 2: { /* Interação com a Pistola */

                        if (getEquipamentosVivos().get(0).getCountUsos() == 0) {
                            //getEquipamentosVivos().get(0).isBroken();
                            creature.getEquipamentosVivos().remove(this.equipamentos.get(0));
                            return false;
                        }

                        if (creature.getIdTipo() != 4) {

                            /* ataque VS outros zombies, diminui uma bala */
                            this.equipamentos.get(0).diminuiCountUsos();

                            /* incrementa o numero de salvacao feita pelo equipamento */
                            this.equipamentos.get(0).incrementaNrSalvacoes();

                            /* vamos destruir o zombie */
                            creatures.remove(creature);
                            TWDGameManager.zombiesDestruidos.add(creature);

                            /* incrementa o numero de zombies destruidos */
                            countZombiesDestruidos();
                            creature.setZombieIsDestroyed(true);
                            if (creature.zombieIsDestroyed()) {
                                System.out.println(creature.toString());
                            }

                            this.setxAtual(creature.xAtual);
                            this.setyAtual(creature.yAtual);
                            nrTurnosOfensivos.incrementarTurno();
                            return true;
                        } else {
                            /* A pistola não tem efeito contra Zombies Vampiros */
                            return false;
                        }
                    }

                    case 6: /* Interação com a Estaca de madeira */
                    case 10: { /* Interação com o capacete Beskar Helmet */
                        /* vamos destruir o zombie */
                        creatures.remove(creature);
                        TWDGameManager.zombiesDestruidos.add(creature);

                        /* incrementa o numero de zombies destruidos */
                        countZombiesDestruidos();
                        creature.setZombieIsDestroyed(true);

                        /* incrementa o numero de salvacao feita pelo equipamento */
                        this.equipamentos.get(0).incrementaNrSalvacoes();

                        this.setxAtual(creature.xAtual);
                        this.setyAtual(creature.yAtual);
                        nrTurnosOfensivos.incrementarTurno();
                        return true;
                    }
                }
            }

        return false;
    }

    @Override
    public boolean moveDirecao(int xO, int yO, int xD, int yD, Creature creatureDestino) {

        if (Math.abs(xD - xO) > 0 && Math.abs(yD - yO) > 0) {
            return false;
        }

        return Math.abs(xO - xD) <= 1 && Math.abs(yO - yD) <= 1;
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
    public void setxAtual(int xAtual) {
        this.xAtual = xAtual;
    }

    @Override
    public int getYAtual() {
        return yAtual;
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
        switch (idTipo) {
            case 0:
                tipo = "Criança (Zombie)";
                break;
            case 5:
                tipo = "Criança (Vivo)";
                break;
            default:
                tipo = "";
                break;
        }
    }

    @Override
    public String getTipo() {
        return tipo;
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
    public String getImagePNG() {
        switch (idTipo){
            case 0:
                return "zombieCrianca.png";
            case 5:
                return "criancaViva.png";
        }
        return null;
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
    public int getNrCriaturasZombies() {
        if (tipo.equals("Criança (Zombie)") || tipo.equals("Adulto (Zombie)") || tipo.equals("Militar (Zombie)")
                || tipo.equals("Idoso (Zombie)") || tipo.equals("Zombie Vampiro")) {
            countZombiesIguais++;
        }

        return countZombiesIguais;
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
