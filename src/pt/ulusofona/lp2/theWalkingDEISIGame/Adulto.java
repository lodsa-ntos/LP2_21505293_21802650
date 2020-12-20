package pt.ulusofona.lp2.theWalkingDEISIGame;

public class Adulto extends Creature {

    public Adulto(int id, int idTipo, String nome, int xAtual, int yAtual) {
        super(id, idTipo, nome, xAtual, yAtual);
    }

    @Override
    public boolean move(int xO, int yO, int xD, int yD) {

        //percorre a lista... verifica o conjunto de humanos existentes e pega a posicao do mapa
        /*for (Creature vivo1: creatures) {
            if (vivo1.getIdEquipa() == idEquipaAtual &&
                    vivo1.getXAtual() == xO && vivo1.getYAtual() == yO) {

                for (Creature zombie: creatures) {
                    if (zombie.getXAtual() == xD && zombie.getYAtual() == yD) {
                        return false;
                    }
                }

                /*for (Equipamento eq: equipamentos) {
                    if (eq.getXAtual() == xD && eq.getYAtual() == yD){
                        //Move uma posicao
                        humano.setxAtual(xD);
                        humano.setyAtual(yD);
                        // verificar se o humano tem equipamentos
                        if (humano.getEquipamentos().size() == 0){
                            humano.getEquipamentos().add(eq);
                            // guarda como referencia a posicao original
                            eq.xAnterior = xD;
                            eq.yAnterior = yD;
                        } else {
                            // guardamos o equipamento existente na lista de equipamentos
                            Equipamento eqAntigo = humano.getEquipamentos().get(0);
                            // removemos esse equipamento e devolvemos na posicao original
                            humano.getEquipamentos().remove(0);
                            eqAntigo.xAtual = eqAntigo.xAnterior;
                            eqAntigo.yAtual = eqAntigo.yAnterior;
                            // depois de removido adiciona o novo
                            humano.getEquipamentos().add(eq);
                        }
                        // aumenta o numero de turnos
                        if (nrTurno % 2 == 0) {
                            idEquipaAtual = 0;
                            diurno = true;
                        } else {
                            idEquipaAtual = 1;
                            diurno = false;
                        }
                        nrTurno++;
                        return true;
                    }
                }*/

                // caso nao haja nenhum equipamento, zombie ou humano nessa posicao
                //Move uma posicao
                /*vivo1.setxAtual(xD);
                vivo1.setyAtual(yD);

                // aumenta o numero de turnos
                if (nrTurno == 0) {
                    idEquipaAtual = 10;
                    diurno = true;
                } else {
                    idEquipaAtual = 20;
                    diurno = false;
                }
                nrTurno++;
                return true;
            }
        }*/
        return false;
    }

}
