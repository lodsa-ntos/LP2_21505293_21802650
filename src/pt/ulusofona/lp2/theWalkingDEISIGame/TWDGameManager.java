package pt.ulusofona.lp2.theWalkingDEISIGame;

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
    int numLinha;
    int numColuna;
    int idEquipaAtual = 0;
    int turnoAtual;
    int nC;
    int nE;
    Humano criaturaAJogar = null;
    Zombie criaturaAJogar2 = null;

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

                        //Verificar se o idTipo é Escudo ou Espada e adiciona na respetiva lista
                        if (idTipo == 0) {
                            Equipamento eq = new Equipamento(id, idTipo, posX, posY);
                            equipamentos.add(eq); // adiciona equipamento
                            eq.setNrEquipamentos(1); // incrementa se houver mais um
                            eq.setIdTipo(idTipo); // chama o tipo de equipamento e diz-me se é Escudo ou Espada
                        } else if (idTipo == 1) {
                            Equipamento eq1 = new Equipamento(id, idTipo, posX, posY);
                            equipamentos.add(eq1); // adiciona equipamento
                            eq1.setNrEquipamentos(1); // incrementa se houver mais um
                            eq1.setIdTipo(idTipo); // chama o tipo de equipamento e diz-me se é Escudo ou Espada
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
        int [] tamanho = new int[2];
        tamanho[0] = numLinha;
        tamanho[1] = numColuna;

        return tamanho;
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

        Humano h1 = new Humano();

        // Coordenadas de origem fora do mapa
        if(xO < 0 && xO > numLinha){
            valido = false;
        }

        // Coordenadas de origem fora do mapa
        else if (yO < 0 && yO > numColuna){
            valido = false;
        }

        // Coordenadas de destino fora do mapa
        else if (xD < 0 && xD > numLinha){
            valido = false;
        }

        // Coordenadas de destino fora do mapa
        else if (yD < 0 && yD > numColuna){
            valido = false;
        }

        if (xD - 1 != xO && xD + 1 != xO && yD - 1 != yO && yD + 1 != yO){
            valido = false;
        }

        int nrTurno = 1;
        if(h1.getIdTipo() == 1 && nrTurno % 2 != 0){
            return false;
        }

        if (!valido){
            return false;
        }

        //movimenta
        for(Humano human: humanos){
            if(human.xAtual== xO && human.yAtual == yO){
                criaturaAJogar=human;

                human.xAtual = xD;
                human.yAtual = yD;
            }
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

        for (Equipamento e : equipamentos){
            if (e.xAtual == x && e.yAtual == y){
                System.out.println(e.getXAtual() + "," + e.getYAtual());
                return e.getiD();
            } else {

            }
        }

        return 0;
    }

    public List<String> getSurvivors() {

        List<String> resultados = new ArrayList<>();

        resultados.add("Nr. de turnos terminados: ");
        int nrTurnos = 1;
        resultados.add("Turnos: " + nrTurnos + "\n");

        Humano quantHumanos = new Humano();
        resultados.add("\n");
        resultados.add("OS VIVOS");
        resultados.add(quantHumanos.getId() + " | " + quantHumanos.getNome());

        Zombie quantidadeZ = new Zombie();
        resultados.add("\n");
        resultados.add(" OS OUTROS ");
        resultados.add(quantidadeZ.getId() + " | " + quantidadeZ.getNome()+"");

        return resultados;
    }

    public boolean isDay() {
        return true;
    }

    public boolean hasEquipment(int creatureId, int equipmentTypeId) {
        return true;
    }
}