package pt.ulusofona.lp2.theWalkingDEISIGame;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import static org.junit.Assert.*;

public class TestDoJogo {

    public TestDoJogo() {}

    @Test
    public void test01StartGame() throws InvalidTWDInitialFileException, FileNotFoundException {
        TWDGameManager mapaGigante = new TWDGameManager();

        try { /* Para todas as criatutas e equipamentos */

            mapaGigante.startGame(new File("ficheirosParaTestes/mapaGigante.txt"));
            assertEquals(String.valueOf(true), 2, mapaGigante.getWorldSize().length);
            List<Creature> creatureList = mapaGigante.getCreatures();

            // Turno 1
            assertEquals("Vivo a jogar: ", 10, mapaGigante.getCurrentTeamId());
            assertTrue(String.valueOf(true), mapaGigante.isDay());
            assertTrue("Militar move para casa com equipamento: ", mapaGigante.move(2, 0, 3, 0));
            assertEquals("", 0, mapaGigante.getElementId(2,0)); // casa antiga fica vazia
            assertEquals("", 3, mapaGigante.getElementId(3,0)); // Militar na casa do equipamento
            assertEquals("", -1, mapaGigante.getEquipmentId(3)); // Apanhou o escudo com id = -1
            assertEquals("", 0, mapaGigante.getEquipmentTypeId(-1));

            // Turno 2
            assertEquals("Zombie a jogar: ", 20, mapaGigante.getCurrentTeamId());
            assertTrue(String.valueOf(true), mapaGigante.isDay());
            mapaGigante.move(2, 4, 2, 5); // Zombie move

            // Turno 3
            assertEquals("Vivo a jogar: ", 10, mapaGigante.getCurrentTeamId());
            assertFalse(String.valueOf(false), mapaGigante.isDay());
            assertTrue("move(4, 0, 7, 0) devolveu false", mapaGigante.move(3, 0, 4, 0));
            assertEquals("", 0, mapaGigante.getElementId(3,0));
            assertEquals("", 3, mapaGigante.getElementId(4,0)); // Militar na nova casa
            assertEquals("Militar com novo equipamento", -1, mapaGigante.getEquipmentId(3));

            // Turno 4
            assertEquals("Zombie a jogar: ", 20, mapaGigante.getCurrentTeamId());
            assertFalse(String.valueOf(false), mapaGigante.isDay());
            mapaGigante.move(2, 5, 1, 5); // Zombie move

            // Turno 5 move(4, 0, 7, 0)
            assertEquals("Vivo a jogar: ", 10, mapaGigante.getCurrentTeamId());
            assertTrue(String.valueOf(true), mapaGigante.isDay());
            assertTrue("move(4, 0, 7, 0)", mapaGigante.move(4, 0, 7, 0));
            assertEquals("", -1, mapaGigante.getElementId(4,0));
            assertEquals("", 3, mapaGigante.getElementId(7,0)); // Militar na nova casa
            assertEquals("Militar com novo equipamento", -2, mapaGigante.getEquipmentId(3));
            assertEquals("", "Escudo de Madeira | 2", mapaGigante.getEquipmentInfo(-2));
            assertEquals("",false, mapaGigante.gameIsOver());



        } catch (FileNotFoundException | InvalidTWDInitialFileException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test01MovimentosCoelho() throws InvalidTWDInitialFileException, FileNotFoundException {
        TWDGameManager runCoelho = new TWDGameManager();

        try {
            runCoelho.startGame(new File("ficheirosParaTestes/movimentosCoelho.txt"));
            assertEquals(String.valueOf(true), 2, runCoelho.getWorldSize().length);

            // Turno 1
            assertEquals("Vivo a jogar: ", 10, runCoelho.getCurrentTeamId());
            assertTrue(String.valueOf(true), runCoelho.isDay());
            assertTrue("Coelho move : ", runCoelho.move(2, 1, 4, 1));
            assertEquals("", 0, runCoelho.getElementId(2,0)); // casa antiga fica vazia
            assertEquals("", 2, runCoelho.getElementId(4,1)); // Coelho na nova casa

        } catch (FileNotFoundException | InvalidTWDInitialFileException e) {
            e.printStackTrace();
        }
    }
}
