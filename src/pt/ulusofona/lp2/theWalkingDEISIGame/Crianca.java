package pt.ulusofona.lp2.theWalkingDEISIGame;

/*
    O deslocamento máximo é 1.
    Movem-se em qualquer turno.
    Movem-se na horizontal e na vertical.

    Criança Z = 0
    Criança V = 5

 */

import java.util.ArrayList;

public class Crianca extends Creature {

    public Crianca(int id, int idTipo, String nome, int xAtual, int yAtual) {
        super(id, idTipo, nome, xAtual, yAtual);
    }

    @Override
    public boolean move(int xO, int yO, int xD, int yD,
                        Creature creatureDestino, ArrayList<Creature> creatures) {

        // verifica horizontalmente
        // verifica verticalmente
        if (Math.abs(xD - xO) == 1 && Math.abs(yD - yO) == 0 ||
                Math.abs(xD - xO) == 0 && Math.abs(yD - yO) == 1) {

            // Caso for vivo
            if (this.idTipo == 5) {
                // se o vivo atacar sem equip nao é valido
                if (this.equipamentos.size() == 0) {
                    return false;
                }

                // verificar se o vivo tem um equipamento
                switch (equipamentos.get(0).getIdTipo()) {
                    // Espada
                    case 1: {
                        // Se selecionar uma criança viva com uma espada e atacar um outro zombie nao crianca
                        // retorna falso
                        if (creatureDestino.idTipo == 0) {
                            // vamos destruir o zombie crianca e posicionar a crianca naquela posicao
                            creatures.remove(creatureDestino);
                            this.setxAtual(creatureDestino.xAtual);
                            this.setyAtual(creatureDestino.yAtual);
                            return true;
                        } else {
                            return false;
                        }
                    }
                    // Pistola
                    case 2: {
                        // A pistola não tem efeito contra Zombies Vampiros
                        if (creatureDestino.getIdTipo() != 4) {
                            // diminui uma bala
                            this.equipamentos.get(0).diminuiCountUsos();
                            if (this.equipamentos.get(0).getCountUsos() == 0) {
                                this.equipamentos.remove(0);
                            }
                            creatures.remove(creatureDestino);
                            this.setxAtual(creatureDestino.xAtual);
                            this.setyAtual(creatureDestino.yAtual);
                            return true;
                        } else {
                            return false;
                        }
                    }
                    // Estaca de Madeira
                    case 6:
                        // Beskar Helmet
                    case 10: {
                        creatures.remove(creatureDestino);
                        this.setxAtual(creatureDestino.xAtual);
                        this.setyAtual(creatureDestino.yAtual);
                        return true;
                    }
                    default:
                        return false;
                }

            } else if (this.idTipo == 0) {
                // caso for zombie
                // verifica se o vivo tem equipamentos
                if (creatureDestino.equipamentos.size() == 0) {
                    // caso sem equip
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
                    // caso com equip defensivo
                    switch (creatureDestino.equipamentos.get(0).getIdTipo()) {
                        case 0:
                            // Escudo
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
                        case 3:
                            // Escudo Tatico
                        case 10:
                            // Beskar helmet
                            return false;
                        case 4:
                            // Revista
                        case 5:
                            // cabeca de alho
                            destroiEConverte(creatureDestino);
                            return true;
                        case 7:
                            //lixivia
                            if (creatureDestino.equipamentos.get(0).getCountUsos() < 0.3) {
                                destroiEConverte(creatureDestino);
                                return true;
                            }
                            return false;
                        case 8:
                            // veneno
                            /*
                            IMCOMPLETO
                             */
                        case 9:
                        //antidoto
                             /*
                            IMCOMPLETO
                             */
                            return false;
                    }
                    // caso com equip ofensivo
                    destroiEConverte(creatureDestino);
                }
            }
        }
        return false;
    }

}
