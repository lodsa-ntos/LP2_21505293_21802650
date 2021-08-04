package pt.ulusofona.lp2.theWalkingDEISIGame;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.junit.Assert.*;

public class TestsDoJogo {

    public TestsDoJogo() {}

    @Test
    public void test01StartGame() throws InvalidTWDInitialFileException, FileNotFoundException {
        TWDGameManager mapaGigante = new TWDGameManager();

        try { /* Para todas as criatutas e equipamentos */

            mapaGigante.startGame(new File("ficheirosParaTestes/mapaGigante.txt"));
            assertEquals(String.valueOf(true), 2, mapaGigante.getWorldSize().length);

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
            assertFalse("", mapaGigante.gameIsOver());

            String pathToSaveGame = "ficheirosParaTestes/saveGameMapaGigante.txt";
            assertTrue(String.valueOf(true), mapaGigante.saveGame(new File(pathToSaveGame)));

        } catch (FileNotFoundException | InvalidTWDInitialFileException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test02MovimentosCoelho() throws InvalidTWDInitialFileException, FileNotFoundException {
        TWDGameManager runCoelho = new TWDGameManager();

        try {
            runCoelho.startGame(new File("ficheirosParaTestes/movimentosCoelho.txt"));
            assertEquals(String.valueOf(true), 2, runCoelho.getWorldSize().length);

            // Turno 0 - PAR
            assertEquals("Vivo a jogar: ", 10, runCoelho.getCurrentTeamId());
            assertTrue(String.valueOf(true), runCoelho.isDay());
            assertTrue("Coelho move : ", runCoelho.move(2, 1, 4, 1));
            assertEquals("", 0, runCoelho.getElementId(2,0)); // casa antiga fica vazia
            assertEquals("", 2, runCoelho.getElementId(4,1)); // Coelho na nova casa

            // Turno 1 - IMPAR
            assertEquals("Zombie a jogar: ", 20, runCoelho.getCurrentTeamId());
            assertTrue(String.valueOf(true), runCoelho.isDay());
            assertFalse("Coelho move : ", runCoelho.move(3, 4, 3, 1));
            assertEquals("", 3, runCoelho.getElementId(3,4)); // casa antiga nao fica vazia
            assertEquals("", 0, runCoelho.getElementId(3,1)); // Coelho na nova casa

            // Turno 1 - IMPAR
            assertEquals("Zombie a jogar: ", 20, runCoelho.getCurrentTeamId());
            assertTrue(String.valueOf(true), runCoelho.isDay());
            assertTrue("Coelho move : ", runCoelho.move(3, 4, 3, 2));
            assertEquals("", 0, runCoelho.getElementId(3,4)); // casa antiga não fica
            assertEquals("", 3, runCoelho.getElementId(3,2)); // Coelho nao vai para a nova casa

            // Turno 2 - PAR
            assertEquals("Vivo a jogar: ", 10, runCoelho.getCurrentTeamId());
            assertFalse(String.valueOf(false), runCoelho.isDay());
            assertTrue("Coelho move : ", runCoelho.move(2, 2, 2, 3));
            assertEquals("", 0, runCoelho.getElementId(2,2)); // casa antiga fica vazia
            assertEquals("", 1, runCoelho.getElementId(2,3)); // Coelho na nova casa

            // Turno 3 - IMPAR
            assertEquals("Zombie a jogar: ", 20, runCoelho.getCurrentTeamId());
            assertFalse(String.valueOf(false), runCoelho.isDay());
            assertTrue("Coelho move : ", runCoelho.move(4, 4, 4, 3));
            assertEquals("", 0, runCoelho.getElementId(4,4)); // casa antiga fica vazia
            assertEquals("", 4, runCoelho.getElementId(4,3)); // Coelho na nova casa

            // Turno 4 - PAR
            assertEquals("Vivo a jogar: ", 10, runCoelho.getCurrentTeamId());
            assertTrue(String.valueOf(true), runCoelho.isDay());
            assertFalse("Coelho move para casa com equipamento: Devia retornar falso ", runCoelho.move(2, 3, 1, 3));
            assertEquals("", 1, runCoelho.getElementId(2,3)); // casa antiga nao fica vazia
            assertEquals("", -2, runCoelho.getElementId(1,3)); // Coelho nao vai para a nova casa
            assertEquals("", 0, runCoelho.getEquipmentId(3)); // Nao apanhou a espada com id = -2
            assertEquals("", 0, runCoelho.getEquipmentTypeId(-1));

        } catch (FileNotFoundException | InvalidTWDInitialFileException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test03MilitarVsEquipamento() throws InvalidTWDInitialFileException, FileNotFoundException {
        TWDGameManager militarSmash = new TWDGameManager();

        try {
            militarSmash.startGame(new File("ficheirosParaTestes/militarVSEquipamento.txt"));

            assertEquals(String.valueOf(true), 2, militarSmash.getWorldSize().length);

            // Turno 0
            assertEquals("Vivo a jogar: ", 10, militarSmash.getCurrentTeamId());
            assertTrue(String.valueOf(true), militarSmash.isDay()); // DIA
            assertTrue("Militar Vivo move : ", militarSmash.move(1, 1, 1, 0));
            assertEquals("", 0, militarSmash.getElementId(1,2)); // casa antiga fica vazia
            assertEquals("", 2, militarSmash.getElementId(1,0)); // Militar Vivo na nova casa

            // Turno 1
            assertEquals("Zombie a jogar: ", 20, militarSmash.getCurrentTeamId());
            assertTrue(String.valueOf(true), militarSmash.isDay()); // DIA
            assertTrue("Militar Zombie move para casa com equipamento : ", militarSmash.move(4, 2, 3, 2));
            assertEquals("", 0, militarSmash.getElementId(4,2)); // casa antiga fica vazia
            assertEquals("", 1, militarSmash.getElementId(3,2)); // Militar Vivo na nova casa
            assertFalse("", militarSmash.saltarPorCima(4,2,3,2)); // Não faz salto por cima
            assertEquals("militarSmash.move(4, 2, 3, 2)", 0, militarSmash.getEquipmentId(1)); // Eq. destruido
            assertEquals("", 0, militarSmash.getEquipmentTypeId(-1));
            assertEquals("", 0, militarSmash.getElementId(4,2)); // casa vazia
            assertEquals("", 1, militarSmash.getElementId(3,2));

            for (Creature m : militarSmash.getCreatures()) {
                int idMilitar = m.getId();
                if (idMilitar == 1) {
                    assertEquals("1 | Militar (Zombie) | Os Outros | Freddy M. 1 @ (3, 2)", m.toString());
                }
            }
            assertTrue("", militarSmash.criaturasMaisEquipamentosApanharam().contains("1:Freddy M.:1"));

            // Turno 2
            assertEquals("Vivo a jogar: ", 10, militarSmash.getCurrentTeamId());
            assertFalse(String.valueOf(false), militarSmash.isDay()); // NOITE
            assertTrue("Militar Vivo move : ", militarSmash.move(1, 0, 1, 1));
            assertEquals("", 0, militarSmash.getElementId(1,0)); // casa antiga fica vazia
            assertEquals("", 2, militarSmash.getElementId(1,1)); // Militar Vivo na nova casa

            // Turno 3
            assertEquals("Zombie a jogar: ", 20, militarSmash.getCurrentTeamId());
            assertFalse(String.valueOf(false), militarSmash.isDay()); // NOITE
            assertTrue("Militar Zombie move para nova casa : ", militarSmash.move(3, 2, 2, 2));
            assertEquals("", 0, militarSmash.getElementId(3,2)); // casa antiga fica vazia
            assertEquals("", 1, militarSmash.getElementId(2,2)); // Militar Vivo na nova casa
            assertEquals("militarSmash.move(3, 2, 2, 2)", 0, militarSmash.getEquipmentId(1)); // Eq. destruido
            assertEquals("", 0, militarSmash.getEquipmentTypeId(-1)); // Eq. destruido
            assertEquals("", 0, militarSmash.getElementId(3,2)); // casa vazia
            assertEquals("", 1, militarSmash.getElementId(2,2));

            for (Creature m : militarSmash.getCreatures()) {
                int idMilitar = m.getId();
                if (idMilitar == 1) {
                    assertEquals("1 | Militar (Zombie) | Os Outros | Freddy M. 1 @ (2, 2)", m.toString());
                }
            }

        } catch (FileNotFoundException | InvalidTWDInitialFileException e) {
            e.printStackTrace();
        }
    }
}
