package pt.ulusofona.lp2.theWalkingDEISIGame;

import java.util.ArrayList;

public class Militar extends Creature {

    public Militar(int id, int idTipo, String nome, int xAtual, int yAtual) {
        super(id, idTipo, nome, xAtual, yAtual);
    }

    @Override
    protected boolean processarOCombate(int xO, int yO, int xD, int yD, Creature creature, ArrayList<Creature> creatures) {

        /* MILITAR VIVO A ATACAR */
        if (this.idTipo == 7) {

            // se o vivo atacar sem equipamento nao é valido
            if (this.equipamentos.size() == 0) {
                return false;
            }

            /*EQUIPAMENTOS OFENSIVOS*/
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

                    /* incrementa o numero de salvacao feita pelo equipamento */
                    this.equipamentos.get(0).incrementaNrSalvacoes();

                    this.setxAtual(creature.xAtual);
                    this.setyAtual(creature.yAtual);
                    return true;
                }

                case 2: { /* Interação com a Pistola */

                    if (getEquipamentosVivos().get(0).getCountUsos() == -1) {
                        //getEquipamentosVivos().get(0).isBroken();
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
        return Math.abs(xO - xD) <= 3 && Math.abs(yO - yD) <= 3;
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
    public ArrayList<Equipamento> getEquipamentosZombies() {
        return destruidos;
    }

    @Override
    public void setTipo(int idTipo) {
        switch (idTipo) {
            case 2:
                tipo = "Militar (Zombie)";
                break;
            case 7:
                tipo = "Militar (Vivo)";
                break;
            default:
                tipo = "";
                break;
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
        }
    }

    @Override
    public int getCreaturesNoBolso() {
        return creaturesNoBolso;
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
    public void incrementaZombiesDestruidos() {
        this.zombiesDestruidos++;
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
    public int countEquipamentosZombies() {
        return countEquipamentosDestruidos++;
    }

    @Override
    public int getCountEquipamentosDestruidos() {
        return countEquipamentosDestruidos;
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
        switch (idTipo){
            case 2:
                return "zombieMilitar.png";
            case 7:
                return "human.png";
        }
        return null;
    }

    @Override
    public String toString() {
        if (isInSafeHaven()){
            return id + " | " + tipo + " | " + equipa + " | " + nome + " " + equipamentos.size() + " @ A salvo";
        } else if (isTransformado()){
            return id + " | " + tipo + " | " + equipa + " | " + nome + " " + equipamentos.size() + " @ (RIP)";
        } else if (humanDeadPorEnvenenamento()) {
            return id + " | " + tipo + " | " + equipa + " | " + nome + " " + equipamentos.size() + " @ (RIP)";
        } else if (zombieIsDestroyed()){
            return id + " | " + tipo + " | " + equipa + " | " + nome + " " + destruidos.size() + " @ (RIP)";
        } else if (equipa.equals("Os Vivos")){
            return id + " | " + tipo + " | " + equipa + " | " + nome + " " + equipamentos.size() + " @ (" + xAtual + ", " + yAtual + ")";
        } else if (equipa.equals("Os Outros")){
            return id + " | " + tipo + " | " + equipa + " | " + nome + " " + destruidos.size() + " @ (" + xAtual + ", " + yAtual + ")";
        } else {
            return id + " | " + tipo + " | " + equipa + " | " + nome + " @ (" + xAtual + ", " + yAtual + ")";
        }
    }

}
