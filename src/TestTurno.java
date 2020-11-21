import org.junit.Test;
import pt.ulusofona.lp2.theWalkingDEISIGame.Humano;
import pt.ulusofona.lp2.theWalkingDEISIGame.Zombie;

import static org.junit.Assert.assertEquals;

public class TestTurno {
    public TestTurno() {
    }

    @Test
    public void test01TurnoJogar() {
        Zombie zombie = new Zombie();
        int idEsperado = 0;
        int idObtido = zombie.getXAtual();
        assertEquals(idEsperado, idObtido);

    }
}
