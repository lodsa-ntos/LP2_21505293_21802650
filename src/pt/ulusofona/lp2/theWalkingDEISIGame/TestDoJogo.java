package pt.ulusofona.lp2.theWalkingDEISIGame;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import static org.junit.Assert.*;

public class TestDoJogo {

    public TestDoJogo() {}

    @Test
    public void test01StartGame() {
        TWDGameManager mapaGigante = new TWDGameManager();

        try { /* Para todas as criatutas e equipamentos*/
            mapaGigante.startGame(new File("ficheirosParaTestes/mapaGigante.txt"));
            assertEquals(String.valueOf(true), 2,mapaGigante.getWorldSize().length);
            assertEquals(String.valueOf(true), mapaGigante.getInitialTeam(), 10);
            assertEquals(String.valueOf(true), mapaGigante.getCurrentTeamId(),10);
            assertFalse(mapaGigante.move(0, 0, 0, 1));
            assertTrue(mapaGigante.move(3, 3, 2, 3));
            assertTrue(mapaGigante.move(4,4,3,4));
            assertFalse(mapaGigante.saltarPorCima(3, 3, 6, 6));
            assertTrue(mapaGigante.move(5,5,6,6));
            assertTrue(mapaGigante.move(8,2,6,4));

            List<Creature> creatureList = mapaGigante.getCreatures();
            assertEquals(String.valueOf(true), 10, creatureList.size());

            int [] tamanho = mapaGigante.getWorldSize();
            assertEquals(String.valueOf(true), 2, tamanho.length);

        } catch (InvalidTWDInitialFileException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test02IdosoNaNoite() {
        TWDGameManager idosoNoturno = new TWDGameManager();

        try {
            idosoNoturno.startGame(new File("ficheirosParaTestes/idosoNaNoite.txt"));

            if (idosoNoturno.isDay()) {
                assertEquals(String.valueOf(true), idosoNoturno.getCurrentTeamId(),10);
                assertTrue(idosoNoturno.move(2, 1, 0, 3));
                assertTrue(idosoNoturno.move(3, 4, 2, 4));
                assertFalse(idosoNoturno.move(3, 3, 4, 3)); /* idoso não pode andar de noite */
            }

        } catch (InvalidTWDInitialFileException | FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
