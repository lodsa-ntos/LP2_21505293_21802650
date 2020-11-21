import org.junit.Test;
import pt.ulusofona.lp2.theWalkingDEISIGame.Humano;
import pt.ulusofona.lp2.theWalkingDEISIGame.Zombie;

import static org.junit.Assert.assertEquals;

public class TestTurno {
    public TestTurno() {
    }

    @Test
    public void test01GetImagePNG() {
        Zombie zombie = new Zombie(2, 1, "Freddy M.", 3, 4);
        int idEsperado = 2;
        int idObtido = zombie.getId();
        assertEquals(idEsperado, idObtido);

    }

    @Test
    public void test02GetIdEquipa() {
        Humano humano = new Humano(2, 1, "Freddy M.", 3, 4);
        int idEsperado = 2;
        int idObtido = humano.getId();
        assertEquals(idEsperado, idObtido);
    }
}
