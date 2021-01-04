package pt.ulusofona.lp2.theWalkingDEISIGame;

import org.junit.Test;

import javax.print.attribute.standard.MediaSize;

import static org.junit.Assert.assertEquals;

public class TestIdoso {

    public TestIdoso() {

    }
        @Test
        public void test01GetImagePNG () {
            Idoso idoso = new Idoso(2,6,"Jackie chan",3,4);
            String imageEsperada = idoso.getImagePNG();
            String imageObtida = idoso.getImagePNG();
            assertEquals(imageEsperada, imageObtida);
        }
        @Test
        public void test02GetIdEquipa () {
            Idoso idoso = new Idoso(3, 0, "Paciente Zero", 4, 4);
            int idEsperado = 3;
            int idObtido = idoso.getId();
            assertEquals(idEsperado, idObtido);
        }
        @Test
        public void test03GetIdTipo () {
            Idoso idoso = new Idoso(3, 0, "Paciente Zero", 4, 4);
            int idEsperado = 0;
            int idObtido = idoso.idTipo;
            assertEquals(idEsperado, idObtido);
        }
    }
