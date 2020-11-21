import org.junit.Test;
import pt.ulusofona.lp2.theWalkingDEISIGame.Zombie;

import static org.junit.Assert.assertEquals;

public class TestZombie {

    public TestZombie() {
    }

    @Test
    public void test01GetImagePNG() {
        Zombie zombie = new Zombie();
        String imageEsperada = "zomb.png";
        String imageObtida = zombie.getImagePNG();
        assertEquals (imageEsperada, imageObtida);

    }

    @Test
    public void test02GetIdEquipa() {
        Zombie zombie = new Zombie(3, 0, "Paciente Zero", 4, 4);
        int idEsperado = 3;
        int idObtido = zombie.getId();
        assertEquals(idEsperado, idObtido);
    }

    @Test
    public void test03GetIdTipo() {
        Zombie zombie = new Zombie();
        zombie.setTipo(0);
        String idEsperado = "Zombie";
        String idObtido = zombie.getTipo();
        assertEquals(idEsperado, idObtido);
    }
}
