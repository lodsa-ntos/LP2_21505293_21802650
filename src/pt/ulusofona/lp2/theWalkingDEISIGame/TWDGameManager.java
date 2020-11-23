package pt.ulusofona.lp2.theWalkingDEISIGame;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class TWDGameManager {

    /*-----------------------------------------------------------------------------------------
    Listas:
        - humanos > representa a quantidade de humanos existentes no mapa
        - zombies > representa a quantidade de zombies existentes no mapa
        - equipamentos > representa a quantidade de equipamentos existentes no mapa

    linhaAtual - Indica o inicio da linha do ficheiro a comecar a ser lida
    (numLinha/numColuna) - Correspondem ao número de linhas e ao número de colunas do mapa
    idEquipaAtual - Indica qual é o ID da equipa que começa o jogo
    nC - Indica o número de criaturas em jogo.
    nE - Indica o número de equipamentos em jogo.
    nrTurno - Indica os turnos de jogadas do jogo e alteracao do dia para a noite
    valido - Indica os movimentos validos das criaturas no jogo
    ------------------------------------------------------------------------------------------*/

    //Lista de Humanos
    static ArrayList<Humano> humanos = new ArrayList<>();

    //Lista de Zombies
    static ArrayList<Zombie> zombies = new ArrayList<>();

    //Lista de Equipamento
    ArrayList<Equipamento> equipamentos = new ArrayList<>();

    int linhaAtual = 0;
    static int numLinha;
    static int numColuna;
    int idEquipaAtual = 0;
    int nrTurno = 0;
    int nC;
    int nE;
    boolean diurno = true;


    boolean valido = true;

    public TWDGameManager() {
    }

    //Esta função faz a leitura do ficheiro de texto e carrega para a memória a informação relevante.
    public boolean startGame(File ficheiroInicial) {

        humanos = new ArrayList<>();
        zombies = new ArrayList<>();
        numLinha = 0;
        numColuna = 0;
        nrTurno = 0;

        try {

            //String nomeFicheiro = "mapa.txt";
            //File ficheiroDados = new File(nomeFicheiro);

            Scanner leitor;

            leitor = new Scanner(ficheiroInicial);

            String linha;

            // Primeira linha que indica as linhas e as colunas do mapa.
            // ler uma linha do ficheiro
            linha = leitor.nextLine();

            // vai quebrando a string em varias substrings a partir do espaco vazio
            String[] mapa = linha.split(" ");

            // verifica se o ficheiro cumpre com as regras
            if(mapa.length > 2) {
                return false;
            }
            numLinha = Integer.parseInt(mapa[0].trim()); // guarda na primeira posicao do array o numLinha
            numColuna = Integer.parseInt(mapa[1].trim()); // guarda na segunda posicao do array o numColuna

            // Segunda linha que indica qual é o ID da equipa que começa o jogo.
            // ler uma linha do ficheiro
            linha = leitor.nextLine();
            idEquipaAtual = Integer.parseInt(linha);

            // Terceira linha que indica o número de criaturas em jogo.
            // ler uma linha do ficheiro
            linha = leitor.nextLine();
            nC = Integer.parseInt(linha);

            int nLinhas;
            nLinhas = nC;

            // enquanto o ficheiro tiver linhas não-lidas
            while (leitor.hasNextLine()) {
                // linhaAtual != nLinhas
                if (linhaAtual < nLinhas) {
                    // lê uma linha do ficheiro até achar uma quebra de linha
                    linha = leitor.nextLine();

                    // vai quebrando a string em varias substrings a partir do caracter dois pontos (separador)
                    String[] dados = linha.split(":");

                    // Imprime as linhas com criaturas
                    // System.out.println(linha);

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
                        zombie.contarZombies(1); // incrementa se houver mais um
                        zombie.getTipo(); // chama o tipo de criatura e diz-me se é zombie ou humano
                        zombie.getEquipa(); // iz-me se pertence aos "Os Vivos" ou aos "Os Outros"
                        System.out.println(zombie.toString()); // imprime zombie
                    } else if (idTipo == 1) {
                        Humano humano = new Humano(id, idTipo, nome, posX, posY);
                        humanos.add(humano); // adiciona humano
                        humano.contarHumanos(1); // incrementa se houver mais um
                        humano.getTipo(); // chama o tipo de criatura e diz-me se é zombie ou humano
                        humano.getEquipa(); // diz-me se pertence aos "Os Vivos" ou aos "Os Outros"
                        System.out.println(humano.toString()); // imprime humanos
                    }

                    linhaAtual++;

                } else if (linhaAtual == nLinhas) { // verifica se as primeiras linhas ja foram lidas
                    // Setima linha que indica o número de equipamentos em jogo.
                    // ler uma linha do ficheiro
                    linha = leitor.nextLine();
                    nE = Integer.parseInt(linha);

                    while (leitor.hasNextLine()) {

                        // lê uma linha do ficheiro até achar uma quebra de linha
                        linha = leitor.nextLine();

                        // vai quebrando a string em varias substrings a partir do caracter dois pontos (separador)
                        String[] novaFila = linha.split(":");

                        // Imprime linhas com os equipamentos
                        // System.out.println(linha);

                        // Converte as Strings lidas para os tipos esperados
                        // "trim()" --> retira espaços a mais que estejam no inicio e no fim do texto (espaços padrao)
                        int id = Integer.parseInt(novaFila[0].trim());
                        int idTipo = Integer.parseInt(novaFila[1].trim());
                        int posX = Integer.parseInt(novaFila[2].trim());
                        int posY = Integer.parseInt(novaFila[3].trim());

                        //Verificar se o idTipo é Escudo ou Espada e adiciona na respetiva lista
                        if (idTipo == 0 || idTipo == 1) {
                            Equipamento eq = new Equipamento(id, idTipo, posX, posY);
                            equipamentos.add(eq); // adiciona equipamento
                            eq.setNrEquipamentos(1); // incrementa se houver mais um
                            eq.setIdTipo(idTipo); // chama o tipo de equipamento e diz-me se é Escudo ou Espada
                            System.out.println(eq.toString());
                        }
                    }
                }// else
            }

            leitor.close();

        } catch (FileNotFoundException exception) {
            System.out.println("Erro no " + exception.getMessage());
            return false;
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
        if (nrTurno % 2 == 0) {
            return idEquipaAtual = 0;
        } else {
            return idEquipaAtual = 1;
        }
    }

    public List<Humano> getHumans() {
        return humanos;
    }

    public List<Zombie> getZombies() {
        return zombies;
    }

    public boolean move(int xO, int yO, int xD, int yD) {

        // Devolve falso se o movimento for incompativel com o tipo de movimentação ou coordenadas estão fora do Mapa
        //if (xO < 0|| xO > numLinha || yO < 0 || yO > numColuna) {
           // return false;
      //  }
        //VALIDAÇÕES PARA COORDENADAS DE DESTINO FORA DO MAPA
        if (xD < 0 || xD > numLinha){
            valido = false; // estão fora do mapa
        }

        else if (yD < 0 || yD > numColuna){
            valido = false; // estão fora do mapa
        }

        // xD - 1 != xO && xD + 1 != xO && yD - 1 != yO && yD + 1 != yO
        // verifica horizontalmente, verticalmente e diagonalmente
        else if ( Math.abs(xD - xO) >= 2 || Math.abs(yD - yO) >= 2){
            valido = false;
        }

        if (!valido){
           return false;
        }

        //percorre a lista... verifica o conjunto de humanos existentes e pega a posicao do mapa
        for (Humano humano : humanos) {
            if (humano.getXAtual() == xO && humano.getYAtual() == yO ) {

                for (Zombie zombie: zombies) {
                    if (zombie.xAtual == xD && zombie.yAtual == yD) {
                        return false;
                    }
                }

                for (Humano humano2: humanos) {
                    if (humano2.getXAtual() == xD && humano2.getYAtual() == yD) {
                        return false;
                    }
                }

                for (Equipamento eq: equipamentos) {
                    if (eq.xAtual == xD && eq.yAtual == yD){
                        //Move uma posicao
                        humano.setxAtual(xD);
                        humano.setyAtual(yD);
                        // verificar se o humano tem equipamentos
                        if (humano.getEquipamentos().size() == 0){
                            humano.getEquipamentos().add(eq);
                            // guarda como referencia a posicao original
                            eq.xAnterior = xD;
                            eq.yAnterior = yD;
                        } else {
                            // guardamos o equipamento existente na lista de equipamentos
                            Equipamento eqAntigo = humano.getEquipamentos().get(0);
                            // removemos esse equipamento e devolvemos na posicao original
                            humano.getEquipamentos().remove(0);
                            eqAntigo.xAtual = eqAntigo.xAnterior;
                            eqAntigo.yAtual = eqAntigo.yAnterior;
                            // depois de removido adiciona o novo
                            humano.getEquipamentos().add(eq);
                        }

                        if (nrTurno % 2 == 0) {
                            idEquipaAtual = 0;
                            diurno = true;
                        } else {
                            idEquipaAtual = 1;
                            diurno = false;
                        }
                        // aumenta o nmr de turnos
                        nrTurno++;
                        return true;
                    }
                }

                // caso nao haja nenhum equipamento, zombie ou humano nessa posicao
                //Move uma posicao
                humano.setxAtual(xD);
                humano.setyAtual(yD);
                // aumenta o nmr de turnos

                if (nrTurno % 2 == 0) {
                    idEquipaAtual = 0;
                    diurno = true;
                } else {
                    idEquipaAtual = 1;
                    diurno = false;
                }
                nrTurno++;
            }
        }
        return false;
    }

    public boolean gameIsOver() {
        return nrTurno == 12;
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
        System.out.println("X: "+ x + "\nY: " + y);
        for (Humano h : humanos){
            if (h.getXAtual() == x && h.getYAtual() == y) {
                return h.getId();
            }
        }

        for (Zombie z : zombies){
            if (z.xAtual == x && z.yAtual == y) {
                return z.getId();
            }
        }

        for (Equipamento e : equipamentos){
            if (e.xAtual == x && e.yAtual == y){
                return e.getiD();
            }
        }

        return 0;
    }

    public List<String> getSurvivors() {

        List<String> resultados = new ArrayList<>();

        resultados.add("Nr. de turnos terminados: ");
        resultados.add("Turnos: " + nrTurno + "\n");

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
        return diurno;
    }

    public boolean hasEquipment(int creatureId, int equipmentTypeId) {

        // verifica se o humano tem o equipamento
        for (Humano humano: humanos) {
            if (humano.getId() == creatureId) {
                for (Equipamento equipamento : humano.getEquipamentos()) {
                    if (equipamento.idTipo == equipmentTypeId) {
                        return true;
                    }
                }
            }
        }

        ////se nenhum tiver retorna falso
        return false;
    }
}