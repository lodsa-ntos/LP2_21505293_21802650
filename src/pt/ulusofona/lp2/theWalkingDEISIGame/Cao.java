package pt.ulusofona.lp2.theWalkingDEISIGame;

import java.util.ArrayList;

/*
    O deslocamento máximo é 2.
    Movem-se em qualquer turno.
    Movem-se apenas nas quatro direcções colaterais (p.e. Noroeste, Nordeste, etc)

    Cao V = 9
 */

public class Cao extends Creature {

    public Cao(int id, int idTipo, String nome, int xAtual, int yAtual) {
        super(id, idTipo, nome, xAtual, yAtual);
    }

    @Override
    public boolean move(int xO, int yO, int xD, int yD,
                        Creature creatureDestino, ArrayList<Creature> creatures) {

        int diffX = Math.abs(xD - xO);
        int diffY = Math.abs(yD - yO);
        if (diffX == diffY && diffX <= 2) {

            // Caso for vivo ATAQUE
            if (this.equipamentos.size() == 0) {
                return false;
            }

            // verificar se o vivo tem um equipamento
            switch (equipamentos.get(0).getIdTipo()) {
                // Espada
                case 1:
                    // Estaca de Madeira
                case 6:
                    // Beskar Helmet
                case 10: {
                    if (saltouPorCima(xO, yO, xD, yD, creatures)) {

                        creatures.remove(creatureDestino);
                        this.setxAtual(creatureDestino.xAtual);
                        this.setyAtual(creatureDestino.yAtual);
                        return true;
                    }
                    return false;
                }
                // Pistola
                case 2: {
                    if (saltouPorCima(xO, yO, xD, yD, creatures)) {

                        // A pistola não tem efeito contra Zombies Vampiros
                        if (creatureDestino.idTipo != 4) {
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
                    return false;
                }
            }
        }
        return false;
    }

    @Override
    public boolean move(int xO, int yO, int xD, int yD) {
        int diffX = Math.abs(xD - xO);
        int diffY = Math.abs(yD - yO);
        return diffX == diffY && diffX <= 2;
    }

    private boolean saltouPorCima(int xO, int yO, int xD, int yD, ArrayList<Creature> creatures ) {
        // verifica direcao
        String direcao = this.qualDirecao(xO, xD, yO, yD);
        int diff = 0;
        // se for horizontal significa que a diferenca do Y é o meio
        switch (direcao) {
            case "horizontal":
                diff = Math.abs(yD - yO);
                break;
            case "vertical":
            case "diagonal":
                diff = Math.abs(xD - xO);
                break;
        }

        // verifica se uma creatura ou equipamento esta naquela posicao
        for (Creature creature : creatures) {
            if (creature.getXAtual() == xO && creature.getYAtual() == diff) {
                return false;
            }
        }

        for (Equipamento equipamento : equipamentos) {
            if (equipamento.getxAtual() == xO && equipamento.getyAtual() == diff) {
                return false;
            }
        }
        return true;
    }
}