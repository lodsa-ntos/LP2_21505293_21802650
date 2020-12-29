package pt.ulusofona.lp2.theWalkingDEISIGame;

import java.util.ArrayList;

/*
    O deslocamento máximo é 2.
    Movem-se em turnos diurnos.
    Movem-se em todas as direçoes.

    Cao V = 9
 */

public class ZombieVampiro extends Creature{

    public ZombieVampiro(int id, int idTipo, String nome, int xAtual, int yAtual) {
        super(id, idTipo, nome, xAtual, yAtual);
    }

    @Override
    public boolean move(int xO, int yO, int xD, int yD,
                        Creature creatureDestino, ArrayList<Creature> creatures) {

        if (Math.abs(xD - xO) <= 2 || Math.abs(yD - yO) <= 2) {
            // verifica se o vivo tem equipamentos
            if (creatureDestino.equipamentos.size() == 0) {
                switch (creatureDestino.getIdTipo()) {
                    // crianca viva tranforma-se (->) em zombie crianca
                    case 5:
                        // adulto viva tranforma-se (->) em zombie adulto
                    case 6:
                        // militar viva tranforma-se (->) em zombie militar
                    case 7:
                        // idoso viva tranforma-se (->) em zombie idoso
                    case 8:
                        creatureDestino.setIdTipo(creatureDestino.getIdTipo() - 5);
                        creatureDestino.setIdEquipa(20);
                        return true;
                    // o cao nao se transforma
                    case 9:
                        return false;
                }
            } else {
                switch (creatureDestino.equipamentos.get(0).getIdTipo()) {
                    case 0:
                        // Escudo
                        if (!saltouPorCima(xO, yO, xD, yD, creatures)) {

                            // Quando militar defende, alteramos os estado de uso do escudo
                            if (creatureDestino.getIdTipo() == 7) {
                                creatureDestino.equipamentos.get(0).escudoFoiUsado();
                            }
                            creatureDestino.equipamentos.get(0).diminuiCountUsos();
                            // Caso o numero de usos for nulo então deixa de ter equipamento
                            if (creatureDestino.equipamentos.get(0).getCountUsos() == 0) {
                                creatureDestino.equipamentos.remove(0);
                            }
                            return true;
                        }
                        return false;
                    case 3:
                        // Escudo Tatico
                    case 4:
                        // Revista
                    case 5:
                        // cabeca de alho
                    case 10:
                        // Beskar helmet
                        return false;
                    case 7:
                        //lixivia
                        if (!saltouPorCima(xO, yO, xD, yD, creatures)) {

                            if (creatureDestino.equipamentos.get(0).getCountUsos() < 0.3) {
                                destroiEConverte(creatureDestino);
                                return true;
                            }
                        }
                        return false;
                    case 8:
                        // veneno
                            /*
                            IMCOMPLETO
                             */
                        return false;
                    case 9:
                        //antidoto
                             /*
                            IMCOMPLETO
                             */
                        return false;
                }
                destroiEConverte(creatureDestino);
            }
        }
        return false;
    }

    private boolean saltouPorCima(int xO, int yO, int xD, int yD, ArrayList<Creature> creatures ) {
        // verifica direcao
        String direcao = this.qualDirecao(xO, xD, yO, yD);
        int diff = 0;
        // se for horizontal significa que a diferenca do Y é o meio
        if (direcao.equals("horizontal")) {
            diff = Math.abs(yD - yO);
        } else if (direcao.equals("vertical")) {
            diff = Math.abs(xD - xO);
        } else if (direcao.equals("diagonal")) {
            diff = Math.abs(xD - xO);
        }

        // verifica se uma creatura ou equipamento esta naquela posicao
        for (Creature creature : creatures) {
            if (creature.getXAtual() == xO && creature.getYAtual() == diff) {
                return true;
            }
        }

        for (Equipamento equipamento : equipamentos) {
            if (equipamento.getXAtual() == xO && equipamento.getYAtual() == diff) {
                return true;
            }
        }
        return false;
    }

}
