package pt.ulusofona.lp2.theWalkingDEISIGame;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestEquipamento {

    public TestEquipamento () {
    }

    @Test
    public void test01GetImagePNG() {
        Equipamento equipamento = new Equipamento();
        String imageEsperada = "equipment.png";
        String imageObtida = equipamento.getImagePNG();
        assertEquals (imageEsperada, imageObtida);

    }

    @Test
    public void test02GetIdEquipa() {
        Equipamento equipamento = new Equipamento(2, 0, 0, 3);
        int idEsperado = 2;
        int idObtido = equipamento.getiD();
        assertEquals(idEsperado, idObtido);
    }

    @Test
    public void test02GetIdTipo() {
        Equipamento equipamento = new Equipamento();
        equipamento.setIdTipo(1);
        String tipoEsperado = "Espada samurai";
        String tipoObtido = equipamento.getTitulo();
        assertEquals(tipoEsperado, tipoObtido);
    }
}