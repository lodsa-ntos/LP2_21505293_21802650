import org.junit.Test;
import pt.ulusofona.lp2.theWalkingDEISIGame.Humano;

import static org.junit.Assert.assertEquals;

public class TestHumano {

    public TestHumano() {
    }

    @Test
    public void test01GetImagePNG() {
        Humano humano = new Humano();
        String imageEsperada = "hum.png";
        String imageObtida = humano.getImagePNG();
        assertEquals (imageEsperada, imageObtida);

    }


}
