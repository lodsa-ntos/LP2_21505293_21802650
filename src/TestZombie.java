import org.junit.Test;
import pt.ulusofona.lp2.theWalkingDEISIGame.Zombie;

import static org.junit.Assert.assertEquals;

public class TestZombie {

    public TestZombie() {
    }

    @Test
    public void test01GetImagePNG() {
        Zombie zombie = new Zombie();
        String imageEsperada = "hum.png";
        String imageObtida = zombie.getImagePNG();
        assertEquals (imageEsperada, imageObtida);

    }

    @Test
    public void test02GetIdEquipa() {
        Zombie zombie = new Zombie(2, 1, "Freddy M.", 3, 4);
        int idEsperado = 2;
        int idObtido = zombie.getId();
        assertEquals(idEsperado, idObtido);
    }
}
