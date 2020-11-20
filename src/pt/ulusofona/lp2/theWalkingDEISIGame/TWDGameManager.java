package pt.ulusofona.lp2.theWalkingDEISIGame;

import org.omg.CORBA.DynAnyPackage.Invalid;

/*Site dropProjet*/
// https://deisi.ulusofona.pt/drop-project/upload/lp2-2021-projecto-p1

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TWDGameManager {

    /*-----------------------------------------------------------------------------------------
    linhaAtual - Indica o inicio da linha do ficheiro a comecar a ser lida
    (numLinha/numColuna) - Correspondem ao número de linhas e ao número de colunas do mapa
    idEquipaAtual - Indica qual é o ID da equipa que começa o jogo
    nC - Indica o número de criaturas em jogo.
    nE - Indica o número de equipamentos em jogo.
    nrHumanosLidos - Indica o numero de humanos que irao ser lidos no mapa
    ------------------------------------------------------------------------------------------*/

    //Lista de Humanos
    ArrayList<Humano> humanos = new ArrayList<>();
    int nrHumanos = humanos.size();

    //Lista de Zombies
    ArrayList<Zombie> zombies = new ArrayList<>();
    int nrZombies = humanos.size();

    //Lista de Equipamento
    ArrayList<Equipamento> equipamentos = new ArrayList<>();

    int linhaAtual = 0;
    int numLinha = 0;
    int numColuna = 0;
    int idEquipaAtual = 0;
    int turnoAtual;
    int nC;
    int nE;

    boolean valido = true;

    public TWDGameManager() {
    }

    //Esta função faz a leitura do ficheiro de texto e carrega para a memória a informação relevante.
    public boolean startGame(File ficheiroInicial) {

        try {

            String nomeFicheiro = "mapa.txt";
            File ficheiroDados = new File(nomeFicheiro);

            Scanner leitor;

            leitor = new Scanner(ficheiroDados);

            String linha;

            // Primeira linha que indica as linhas e as colunas do mapa. Falta ler o Mapa e definir tamanho
            // ler uma linha do ficheiro
            // vai quebrando a string em varias substrings a partir do espaço vazio
            // "trim()" --> retira espaços a mais que estejam no inicio e no fim do texto (espaços padrao)
            linha = leitor.nextLine();
            String[] mapa = linha.split(" ");
            numLinha = Integer.parseInt(mapa[0].trim()); // guarda na primeira posicao do array o numLinha
            numColuna = Integer.parseInt(mapa[1].trim()); // guarda na primeira posicao do array o numColuna

            // Segunda linha que indica qual é o ID da equipa que começa o jogo.
            // ler uma linha do ficheiro
            linha = leitor.nextLine();
            idEquipaAtual = Integer.parseInt(linha);

            // Terceira linha que indica o número de criaturas em jogo.
            // ler uma linha do ficheiro
            linha = leitor.nextLine();
            nC = Integer.parseInt(linha);

            // imprime o numero de linhas e de colunas
            System.out.println(Arrays.toString(mapa).replace("[","").replace(",","").replace("]",""));

            // imprime o ID da equipa que começa o jogo
            System.out.println(idEquipaAtual);

            // imprime o número de criaturas em jogo.
            System.out.println(nC);

            int nLinhas = 0;
            nLinhas = nC;

            // enquanto o ficheiro tiver linhas não-lidas
            while (leitor.hasNextLine()) {
                if (linhaAtual != nLinhas) {
                    // lê uma linha do ficheiro até achar uma quebra de linha
                    linha = leitor.nextLine();

                    // vai quebrando a string em varias substrings a partir do caracter dois pontos (separador)
                    String[] dados = linha.split(":");

                    // Imprime as criaturas
                    System.out.println(linha);

                    // Converte as Strings lidas para os tipos esperados
                    // "trim()" --> retira espaços a mais que estejam no inicio e no fim do texto (espaços padrao)
                    int id = Integer.parseInt(dados[0].trim());
                    int idTipo = Integer.parseInt(dados[1].trim());
                    String nome = dados[2].trim();
                    int posX = Integer.parseInt(dados[3].trim());
                    int posY = Integer.parseInt(dados[4].trim());

                    //Verificar se o idTipo é zombie ou humano e adiciona na respetiva lista
                    if (idTipo == 0) {
                        Zombie zombie = new Zombie(id, idTipo, nome, posX, posY);
                        zombies.add(zombie); // adiciona zombie
                        zombie.setnrZombies(1); // incrementa se houver mais um
                        zombie.setTipo(idTipo); // chama o tipo de criatura e diz-me se é zombie ou humano
                    } else if (idTipo == 1) {
                        Humano humano = new Humano(id, idTipo, nome, posX, posY);
                        humanos.add(humano); // adiciona humano
                        humano.setNrHumanos(1); // incrementa se houver mais um
                        humano.setTipo(idTipo); // chama o tipo de criatura e diz-me se é zombie ou humano
                    }

                    linhaAtual++;

                } else if (linhaAtual < linha.length()) {
                    // Setima linha que indica o número de equipamentos em jogo.
                    // ler uma linha do ficheiro
                    linha = leitor.nextLine();
                    nE = Integer.parseInt(linha);

                    // imprime o número de equipamentos em jogo.
                    System.out.println(nE);
                    // enquanto o ficheiro tiver linhas não-lidas

                    int nEquipamentoslidos = 0;
                    nEquipamentoslidos = nE;

                    while (leitor.hasNextLine()) {

                        // lê uma linha do ficheiro até achar uma quebra de linha
                        linha = leitor.nextLine();

                        // vai quebrando a string em varias substrings a partir do caracter dois pontos (separador)
                        String[] novaFila = linha.split(":");

                        // Imprime os equipamentos
                        System.out.println(linha);

                        // Converte as Strings lidas para os tipos esperados
                        // "trim()" --> retira espaços a mais que estejam no inicio e no fim do texto (espaços padrao)
                        int id = Integer.parseInt(novaFila[0].trim());
                        int idTipo = Integer.parseInt(novaFila[1].trim());
                        int posX = Integer.parseInt(novaFila[2].trim());
                        int posY = Integer.parseInt(novaFila[3].trim());

                        //Verificar se o idTipo é zombie ou humano e adiciona na respetiva lista
                        if (idTipo == 0) {
                            Equipamento eq = new Equipamento(id, idTipo, posX, posY);
                            equipamentos.add(eq); // adiciona zombie
                            eq.setNrEquipamentos(1); // incrementa se houver mais um
                            eq.setIdTipo(idTipo); // chama o tipo de criatura e diz-me se é zombie ou humano
                        } else if (idTipo == 1) {
                            Equipamento eq1 = new Equipamento(id, idTipo, posX, posY);
                            equipamentos.add(eq1); // adiciona zombie
                            eq1.setNrEquipamentos(1); // incrementa se houver mais um
                            eq1.setIdTipo(idTipo); // chama o tipo de criatura e diz-me se é zombie ou humano
                        }
                    }
                }// else
            }

            leitor.close();


        } catch (FileNotFoundException exception) {
            System.out.println("Erro no " + exception.getMessage());
        }
        return true;
    }

    public int[] getWorldSize() {
        // FALTA MELHORAR
        int [] tam = new int[2];
        tam[0] = numLinha;
        tam[1] = numColuna;

        return tam;
    }

    public int getInitialTeam() {
        return  idEquipaAtual;
    }

    public List<Humano> getHumans() {
        return humanos;
    }

    public List<Zombie> getZombies() {
        return zombies;
    }

    public boolean move(int xO, int yO, int xD, int yD) {

        if (xD - 1 != xO && xD + 1 != xO && yD - 1 != yO && yD +1 != yO){
            valido = false;
        }

        if (!valido){
            return false;
        }

        // percorre a listahumanos... verifica e pega a posicao da criatura no mapa
        for (Humano h : humanos){
            if (h.xAtual == xO && h.yAtual == yO){
                //volta a posicao anterior
                h.xAnterior = h.xAtual;
                h.yAnterior = h.yAtual;

                //move uma posicao
                h.xAtual = xD;
                h.yAtual = yD;
            }
        }

        // percorre a lista zombies... verifica e pega a posicao da criatura no mapa
        for (Zombie z : zombies){
            if (z.xAtual == xO && z.yAtual == yO){
                //volta a posicao anterior
                z.xAnterior = z.xAtual;
                z.yAnterior = z.yAtual;

                //move uma posicao
                z.xAtual = xD;
                z.yAtual = yD;

            }
        }

        //Processa Turnos

        if (idEquipaAtual == 0) {
            idEquipaAtual = 1;
        } else {
            idEquipaAtual = 0;
        }

        return true;
    }

    public boolean gameIsOver() {

        return false;
    }

    public List<String> getAuthors() {

        List<String> creditos = new ArrayList<>();
        creditos.add("Lodney Santos " + "|" + " nº: " + "21505293");
        creditos.add("Jolina Guvulo " + "|" + " nº: " + "21802650");

        return creditos;
    }

    public int getCurrentTeamId() {
        return idEquipaAtual;
    }

    public int getElementId(int x, int y) {
        for (Humano h : humanos){
            if (h.xAtual == x && h.yAtual == y){
                System.out.println(h.getXAtual() + "," + h.getYAtual());
                return h.getId();
            } else {

            }
        }

        for (Zombie z : zombies){
            if (z.xAtual == x && z.yAtual == y){
                System.out.println(z.getXAtual() + "," + z.getYAtual());
                return z.getId();
            } else {

            }
        }

        /*for (Equipamento e : equipamentos){
            if (e.xAtual == x && e.yAtual == y){
                System.out.println(e.getXAtual() + "," + e.getYAtual());
                return e.getiD();
            } else {

            }
        } */

        return 0;
    }

    public List<String> getSurvivors() {

        List<String> resultados = new ArrayList<>();

        // (...)

        return resultados;
    }

    public boolean isDay() {


        return true;
    }

    public boolean hasEquipment(int creatureId, int equipmentTypeId) {

        return false;
    }
}