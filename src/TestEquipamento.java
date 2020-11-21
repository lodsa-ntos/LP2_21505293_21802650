import org.junit.Test;
import pt.ulusofona.lp2.theWalkingDEISIGame.Equipamento;
import pt.ulusofona.lp2.theWalkingDEISIGame.Zombie;

import static org.junit.Assert.assertEquals;

public class TestEquipamento {

    public TestEquipamento() {
    }

    @Test
    public void test01GetImagePNG() {
        Equipamento equipamento = new Equipamento();
        String imageEsperada = "hum.png";
        String imageObtida = equipamento.getImagePNG();
        assertEquals (imageEsperada, imageObtida);

    }

    @Test
    public void test02GetIdEquipa() {
        Equipamento equipamento = new Equipamento(0, 0, 0, 3);
        int idEsperado = 2;
        int idObtido = equipamento.getiD();
        assertEquals(idEsperado, idObtido);
    }
}
