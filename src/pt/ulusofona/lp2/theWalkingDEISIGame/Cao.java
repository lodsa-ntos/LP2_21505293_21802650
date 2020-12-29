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
            if (this.equipamentos.size() == 0) return false;

            // verificar se o vivo tem um equipamento
            switch (equipamentos.get(0).getIdTipo()) {
                // Espada
                case 1:
                    // Estaca de Madeira
                case 6:
                    // Beskar Helmet
                case 10: {
                    creatures.remove(creatureDestino);
                    this.setxAtual(creatureDestino.xAtual);
                    this.setyAtual(creatureDestino.yAtual);
                    return true;
                }
                // Pistola
                case 2: {
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
            }
        }
        return false;
    }
}