package pt.ulusofona.lp2.theWalkingDEISIGame;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestEquipamento {

    public TestEquipamento () {
    }

    @Test
    public void test02GetIdEquipa() {
        //Equipamento equipamento = new Equipamento(2, 0, 0, 3);
        EscudoMadeira escudoMadeira = new EscudoMadeira(2,0,0,3);
        int idEsperado = 2;
        int idObtido = escudoMadeira.getiD();
        assertEquals(idEsperado, idObtido);
    }

    @Test
    public void test02GetIdTipo() {
        EscudoMadeira escudoMadeira = new EscudoMadeira(1,0,0,3);
        escudoMadeira.setIdTipo(1);
        String tipoEsperado = "Espada samurai";
        String tipoObtido = escudoMadeira.getTitulo();
        assertEquals(tipoEsperado, tipoObtido);
    }
}