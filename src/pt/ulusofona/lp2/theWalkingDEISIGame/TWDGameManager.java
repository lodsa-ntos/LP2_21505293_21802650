package pt.ulusofona.lp2.theWalkingDEISIGame;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
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

    //Lista de Creatures
    static ArrayList<Creature> creatures = new ArrayList<>();

    //Lista de Equipamento
    ArrayList<Equipamento> equipamentos = new ArrayList<>();

    static int numLinha;
    static int numColuna;
    static int xPortas;
    static int yPortas;

    int linhaAtual = 0;
    int nrTurno = 0;
    int idEquipaAtual = 10;

    int nC;
    int nE;
    int nP;

    int nrDias = 6;
    int nrNoites = 6;

    boolean diurno = true;
    boolean valido = true;

    public TWDGameManager() {
    }

    //Esta função faz a leitura do ficheiro de texto e carrega para a memória a informação relevante.
    public boolean startGame(File ficheiroInicial) {

        creatures = new ArrayList<>(); // resent da lista de creatures.

        numLinha = 0; // resent variavel numLinha.
        numColuna = 0; // resent variavel numColuna.
        xPortas = 0; // resent variavel xPortas.
        yPortas = 0; // resent variavel yPortas.
        nrTurno = 0; // resent variavel turnos.

        try {

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

                if (linhaAtual < nLinhas) {
                    // lê uma linha do ficheiro até achar uma quebra de linha
                    linha = leitor.nextLine();

                    // vai quebrando a string em varias substrings a partir do caracter dois pontos (separador)
                    String[] dados = linha.split(":");

                    // Converte as Strings lidas para os tipos esperados
                    // "trim()" --> retira espaços a mais que estejam no inicio e no fim do texto (espaços padrao)
                    int id = Integer.parseInt(dados[0].trim());
                    int idTipo = Integer.parseInt(dados[1].trim());
                    String nome = dados[2].trim();
                    int posX = Integer.parseInt(dados[3].trim());
                    int posY = Integer.parseInt(dados[4].trim());

                    //Verificar se o idTipo é zombie ou humano e adiciona na lista de criaturas
                    switch(idTipo) {
                        case 0:
                            Creature criancaZombie = new Crianca(id, idTipo, nome, posX, posY);
                            creatures.add(criancaZombie); // adiciona zombie
                            criancaZombie.getTipo();
                            criancaZombie.getEquipaZombie();
                            System.out.println(criancaZombie.toString()); // imprime zombie
                            break;

                        case 1:
                            Creature adultoZombie = new Adulto(id, idTipo, nome, posX, posY);
                            creatures.add(adultoZombie); // adiciona zombie
                            adultoZombie.getTipo();
                            adultoZombie.getEquipaZombie();
                            System.out.println(adultoZombie.toString()); // imprime zombie
                            break;

                        case 2:
                            Creature militarZombie = new Militar(id, idTipo, nome, posX, posY);
                            creatures.add(militarZombie); // adiciona zombie
                            militarZombie.getTipo();
                            militarZombie.getEquipaZombie();
                            System.out.println(militarZombie.toString()); // imprime zombie
                            break;

                        case 3:
                            Creature idosoZombie = new Idoso(id, idTipo, nome, posX, posY);
                            creatures.add(idosoZombie); // adiciona zombie
                            idosoZombie.getTipo();
                            idosoZombie.getEquipaZombie();
                            System.out.println(idosoZombie.toString()); // imprime zombie
                            break;

                        case 4:
                            Creature zombieVamp = new ZombieVampiro(id, idTipo, nome, posX, posY);
                            creatures.add(zombieVamp); // adiciona zombie
                            zombieVamp.getTipo();
                            zombieVamp.getEquipaZombie();
                            System.out.println(zombieVamp.toString()); // imprime zombie
                            break;

                        case 5:
                            Creature criancaVivo = new Crianca(id, idTipo, nome, posX, posY);
                            creatures.add(criancaVivo); // adiciona humano
                            criancaVivo.getTipo();
                            criancaVivo.getEquipaVivos();
                            System.out.println(criancaVivo.toString()); // imprime humano
                            break;

                        case 6:
                            Creature adultoVivo = new Adulto(id, idTipo, nome, posX, posY);
                            creatures.add(adultoVivo); // adiciona humano
                            adultoVivo.getTipo();
                            adultoVivo.getEquipaVivos();
                            System.out.println(adultoVivo.toString()); // imprime humano
                            break;

                        case 7:
                            Creature militarVivo = new Militar(id, idTipo, nome, posX, posY);
                            creatures.add(militarVivo); // adiciona humano
                            militarVivo.getTipo();
                            militarVivo.getEquipaVivos();
                            System.out.println(militarVivo.toString()); // imprime humano
                            break;

                        case 8:
                            Creature idosoVivo = new Idoso(id, idTipo, nome, posX, posY);
                            creatures.add(idosoVivo); // adiciona humano
                            idosoVivo.getTipo();
                            idosoVivo.getEquipaVivos();
                            System.out.println(idosoVivo.toString()); // imprime humano
                            break;

                        case 9:
                            Creature cao = new Cao(id, idTipo, nome, posX, posY);
                            creatures.add(cao); // adiciona humano
                            cao.getTipo();
                            cao.getEquipaVivos();
                            System.out.println(cao.toString()); // imprime humano
                            break;
                    }

                    linhaAtual++;

                } else if (linhaAtual == nLinhas) { // verifica se as primeiras linhas ja foram lidas
                    // Setima linha que indica o número de equipamentos em jogo.
                    // ler uma linha do ficheiro
                    linha = leitor.nextLine();
                    nE = Integer.parseInt(linha);
                    System.out.println(nE);

                    int linhaPorta;
                    linhaPorta = nE;
                    linhaPorta += nLinhas;

                    // enquanto o ficheiro tiver linhas não-lidas depois da anterior, lê
                    while (leitor.hasNextLine()) {

                        if (linhaAtual < linhaPorta) {
                            // lê uma linha do ficheiro até achar uma quebra de linha
                            linha = leitor.nextLine();

                            // vai quebrando a string em varias substrings a partir do caracter dois pontos (separador)
                            String[] novaFila = linha.split(":");

                            // Converte as Strings lidas para os tipos esperados
                            // "trim()" --> retira espaços a mais que estejam no inicio e no fim do texto (espaços padrao)
                            int id = Integer.parseInt(novaFila[0].trim());
                            int idTipo = Integer.parseInt(novaFila[1].trim());
                            int posX = Integer.parseInt(novaFila[2].trim());
                            int posY = Integer.parseInt(novaFila[3].trim());

                            //Verificar se o idTipo é Escudo ou Espada e adiciona na respetiva lista
                            if (idTipo == 0) {
                                Equipamento escudo = new EscudoMadeira(id, idTipo, posX, posY);
                                equipamentos.add(escudo); // adiciona equipamento
                                escudo.setIdTipo(idTipo); // chama o tipo de equipamento e diz-me se é Escudo ou Espada
                                System.out.println(escudo.toString());
                            } else if (idTipo == 1) {
                                Equipamento espada = new EspadaHanzo(id, idTipo, posX, posY);
                                equipamentos.add(espada); // adiciona equipamento
                                espada.setIdTipo(idTipo); // chama o tipo de equipamento e diz-me se é Escudo ou Espada
                                System.out.println(espada.toString());
                            } else if (idTipo == 2) {
                                Equipamento pistola = new Pistola(id, idTipo, posX, posY);
                                equipamentos.add(pistola); // adiciona equipamento
                                pistola.setIdTipo(idTipo); // chama o tipo de equipamento e diz-me se é Escudo ou Espada
                                System.out.println(pistola.toString());
                            } else if (idTipo == 3) {
                                Equipamento escTatico = new EscudoTatico(id, idTipo, posX, posY);
                                equipamentos.add(escTatico); // adiciona equipamento
                                escTatico.setIdTipo(idTipo); // chama o tipo de equipamento e diz-me se é Escudo ou Espada
                                System.out.println(escTatico.toString());
                            } else if (idTipo == 4) {
                                Equipamento revista = new RevistaMaria(id, idTipo, posX, posY);
                                equipamentos.add(revista); // adiciona equipamento
                                revista.setIdTipo(idTipo); // chama o tipo de equipamento e diz-me se é Escudo ou Espada
                                System.out.println(revista.toString());
                            } else if (idTipo == 5) {
                                Equipamento cabecaAlho = new Alho(id, idTipo, posX, posY);
                                equipamentos.add(cabecaAlho); // adiciona equipamento
                                cabecaAlho.setIdTipo(idTipo); // chama o tipo de equipamento e diz-me se é Escudo ou Espada
                                System.out.println(cabecaAlho.toString());
                            } else if (idTipo == 6) {
                                Equipamento estaca = new EstacaMadeira(id, idTipo, posX, posY);
                                equipamentos.add(estaca); // adiciona equipamento
                                estaca.setIdTipo(idTipo); // chama o tipo de equipamento e diz-me se é Escudo ou Espada
                                System.out.println(estaca.toString());
                            } else if (idTipo == 7) {
                                Equipamento garrafaLixivia = new Lixivia(id, idTipo, posX, posY);
                                equipamentos.add(garrafaLixivia); // adiciona equipamento
                                garrafaLixivia.setIdTipo(idTipo); // chama o tipo de equipamento e diz-me se é Escudo ou Espada
                                System.out.println(garrafaLixivia.toString());
                            } else if (idTipo == 8) {
                                Equipamento veneno = new Veneno(id, idTipo, posX, posY);
                                equipamentos.add(veneno); // adiciona equipamento
                                veneno.setIdTipo(idTipo); // chama o tipo de equipamento e diz-me se é Escudo ou Espada
                                System.out.println(veneno.toString());
                            } else if (idTipo == 9) {
                                Equipamento antidoto = new Antidoto(id, idTipo, posX, posY);
                                equipamentos.add(antidoto); // adiciona equipamento
                                antidoto.setIdTipo(idTipo); // chama o tipo de equipamento e diz-me se é Escudo ou Espada
                                System.out.println(antidoto.toString());
                            } else if (idTipo == 10) {
                                Equipamento capacete = new BeskarHelmet(id, idTipo, posX, posY);
                                equipamentos.add(capacete); // adiciona equipamento
                                capacete.setIdTipo(idTipo); // chama o tipo de equipamento e diz-me se é Escudo ou Espada
                                System.out.println(capacete.toString());
                            }

                            linhaAtual++;

                        }  else if (linhaAtual == linhaPorta) {
                            linha = leitor.nextLine();
                            nP = Integer.parseInt(linha);

                            while (leitor.hasNextLine()) {
                                // lê uma linha do ficheiro até achar uma quebra de linha
                                linha = leitor.nextLine();

                                // vai quebrando a string em varias substrings a partir do caracter dois pontos (separador)
                                String[] porta = linha.split(":");

                                // Converte as Strings lidas para os tipos esperados
                                // "trim()" --> retira espaços a mais que estejam no inicio e no fim do texto (espaços padrao)
                                xPortas = Integer.parseInt(porta[0].trim()); // guarda na primeira posicao do array o x
                                yPortas = Integer.parseInt(porta[1].trim()); // guarda na segunda posicao do array o y
                            }
                        }
                    }
                }
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
            return idEquipaAtual = 10;
        } else {
            return idEquipaAtual = 20;
        }
    }

    public List<Creature> getCreatures(){
        return creatures;
    }

    public boolean move(int xO, int yO, int xD, int yD) {

        //VALIDAÇÕES PARA COORDENADAS DE DESTINO FORA DO MAPA
        if (xD < 0 || xD > numLinha) {
            valido = false; // estão fora do mapa
        } else if (yD < 0 || yD > numColuna) {
            valido = false; // estão fora do mapa
        }

        // verifica horizontalmente, verticalmente e diagonalmente
        else if (Math.abs(xD - xO) >= 2 || Math.abs(yD - yO) >= 2) {
            valido = false;
        }

        /*if (xD < 0 || yD < 0 || xD > numLinha - 1 || yD > numColuna - 1) {
            valido = false;
        }

        if (xD == xO && yD == yO) {
            valido = false;
        }*/

        if (!valido) {
            return false;
        }

        // CRIANÇA - VIVO
        for (Creature criancaVivo : creatures) {
                if (criancaVivo.getIdEquipa() == idEquipaAtual && criancaVivo.getIdTipo() == 5
                        && criancaVivo.getXAtual() == xO && criancaVivo.getYAtual() == yO) {

                    for (Equipamento eq : equipamentos) {
                        if (eq.getXAtual() == xD && eq.getYAtual() == yD) {
                            //Move uma posicao
                            criancaVivo.setxAtual(xD);
                            criancaVivo.setyAtual(yD);
                            // verificar se o humano tem equipamentos
                            if (criancaVivo.getEquipamentosVivos().size() == 0) {
                                criancaVivo.getEquipamentosVivos().add(eq);
                                // guarda como referencia a posicao original
                                eq.xAnterior = xD;
                                eq.yAnterior = yD;
                            } else {
                                // guardamos o equipamento existente na lista de equipamentos
                                Equipamento eqAntigo = criancaVivo.getEquipamentosVivos().get(0);
                                // removemos esse equipamento e devolvemos na posicao original
                                criancaVivo.getEquipamentosVivos().remove(0);
                                eqAntigo.xAtual = eqAntigo.xAnterior;
                                eqAntigo.yAtual = eqAntigo.yAnterior;
                                // depois de removido adiciona o novo
                                criancaVivo.getEquipamentosVivos().add(eq);
                            }
                        }
                    }
                    //Move uma posicao
                    criancaVivo.setxAtual(xD);
                    criancaVivo.setyAtual(yD);
                    return true;
                }
            }

        // ADULTO - VIVO
        for (Creature adultoVivo : creatures) {
            switch (adultoVivo.getId()){
                case 1:
                case 2:
                case 3:
                case 4:
                if (adultoVivo.getIdEquipa() == idEquipaAtual && adultoVivo.getIdTipo() == 6
                        && adultoVivo.getXAtual() == xO && adultoVivo.getYAtual() == yO ) {

                    for (Equipamento eq : equipamentos) {
                        if (eq.getXAtual() == xD && eq.getYAtual() == yD) {
                            //Move uma posicao
                            adultoVivo.setxAtual(xD);
                            adultoVivo.setyAtual(yD);
                            // verificar se o humano tem equipamentos
                            if (adultoVivo.getEquipamentosVivos().size() == 0) {
                                adultoVivo.getEquipamentosVivos().add(eq);
                                // guarda como referencia a posicao original
                                eq.xAnterior = xD;
                                eq.yAnterior = yD;
                            } else {
                                // guardamos o equipamento existente na lista de equipamentos
                                Equipamento eqAntigo = adultoVivo.getEquipamentosVivos().get(0);
                                // removemos esse equipamento e devolvemos na posicao original
                                adultoVivo.getEquipamentosVivos().remove(0);
                                eqAntigo.xAtual = eqAntigo.xAnterior;
                                eqAntigo.yAtual = eqAntigo.yAnterior;
                                // depois de removido adiciona o novo
                                adultoVivo.getEquipamentosVivos().add(eq);
                            }
                        }
                    }
                    // caso nao haja nenhum equipamento, nessa posicao
                    //Move uma posicao // INCOMPLETO SAO DOIS MOVIMENTOS
                    adultoVivo.setxAtual(xD);
                    adultoVivo.setyAtual(yD);
                    nrTurno++;

                    if (nrTurno == 0) {
                        idEquipaAtual = 10;
                        diurno = true;
                    } else if (nrTurno == 1){
                        idEquipaAtual = 10;
                        diurno = true;
                    } else if (nrTurno == 2){
                        idEquipaAtual = 20;
                        diurno = false;
                    } else if (nrTurno == 3){
                        idEquipaAtual = 20;
                        diurno = false;
                    } else if (nrTurno == 4){
                        idEquipaAtual = 10;
                        diurno = true;
                    } else if (nrTurno == 5){
                        idEquipaAtual = 10;
                        diurno = true;
                    } else if (nrTurno == 6) {
                        idEquipaAtual = 20;
                        diurno = false;
                    } else if (nrTurno == 7) {
                        idEquipaAtual = 20;
                        diurno = false;
                    } else if (nrTurno == 8){
                        idEquipaAtual = 10;
                        diurno = true;
                    } else if (nrTurno == 9){
                        idEquipaAtual = 10;
                        diurno = true;
                    } else if (nrTurno == 10) {
                        idEquipaAtual = 20;
                        diurno = false;
                    } else if (nrTurno == 11) {
                        idEquipaAtual = 20;
                        diurno = false;
                    } else if (nrTurno == 12){
                        idEquipaAtual = 10;
                        diurno = true;
                    } else if (nrTurno == 13){
                        idEquipaAtual = 10;
                        diurno = true;
                    }
                    return true;
                }
            }
        }

        return false;
    }

    public boolean gameIsOver() {
        return (nrTurno/2)  >= nrDias || (nrTurno/2) >= nrNoites;
    }

    public List<String> getAuthors() {
        List<String> creditos = new ArrayList<>();
        creditos.add("Lodney Santos " + "|" + " nº: " + "21505293");
        creditos.add("Jolina Guvulo " + "|" + " nº: " + "21802650");
        return creditos;
    }

    public int getCurrentTeamId() {
        if (diurno){
            return idEquipaAtual;
        } else {
            return idEquipaAtual = 20;
        }
    }

    public int getElementId(int x, int y) {
        for (Creature c: creatures){
            if (c.getXAtual() == x && c.getYAtual() == y) {
                return c.getId();
            }
        }

        for (Equipamento e : equipamentos){
            if (e.getXAtual() == x && e.getYAtual() == y){
                return e.getiD();
            }
        }

        return 0;
    }

    public List<String> getGameResults() {
        int countHumano = 0;
        int countZombie = 0;

        List<String> resultados = new ArrayList<>();

        resultados.add("Nr. de turnos terminados: ");
        resultados.add("Turnos: " + nrTurno + "\n");

       /*for (Humano humano : humanos) {
            if (humano.getId() >= 1 || humano.getId() >= 2) {
                countHumano++;
                resultados.add("\n");
                resultados.add("OS VIVOS");
                resultados.add(humano.getId() + " | " + humano.getNome());
            }
        }
        for (Zombie zombie : zombies) {
            if (zombie.getId() >= 3) {
                countZombie++;
                resultados.add("\n");
                resultados.add(" OS OUTROS ");
                resultados.add(zombie.getId() + " | " + zombie.getNome() + "");
            }
        }*/

        return resultados;
    }

    public boolean isDay() {
        return diurno;
    }

    public int getEquipmentId(int creatureId) {
        // verifica se o criatura tem o equipamento
        for (Creature creature: creatures) {
            if (creature.getId() == creatureId) {
                for (Equipamento equipamento : creature.getEquipamentosVivos()) {
                    if (equipamento.getIdTipo() == getEquipmentTypeId(0) || equipamento.getiD() == getEquipmentTypeId(1)) {
                        return equipamento.getiD();
                    }
                }
            }
        }
        // se nenhum tiver retorna 0
        return 0;
    }

    public List<Integer> getIdsInSafeHaven() {

        ArrayList<Integer> safe = new ArrayList<>();

        for (Creature creature : creatures){
            if (creature.getXAtual() == xPortas && creature.getYAtual() == yPortas){
                safe.add(creature.getId());
            }
        }
        return safe;
    }

    public boolean isDoorToSafeHaven(int x, int y) {
        return xPortas == x && yPortas == y;
    }

    public int getEquipmentTypeId(int equipmentId){
        for (Equipamento equipamento: equipamentos){
            if (equipamento.getiD() == equipmentId){
                return equipmentId;
            }
        }
        return 0;
    }

    public String getEquipmentInfo(int equipmentId) {

        String nomeTipo;
        int info;
        double infoLixivia;

            switch (equipmentId) {
                // PARA 3 EQUIPAENTOS TEMOS QUE DAR O NOME + INFO
                case -1:
                    nomeTipo = "Escudo de Madeira";
                    info = 1;
                    return nomeTipo + " | " + info;

                case -2:
                    nomeTipo = "Espada Hattori Hanzo";
                    return nomeTipo;

                case -3:
                    nomeTipo = "Pistola Walther PPK";
                    info = 3;
                    return nomeTipo + " | " + info;

                case -4:
                    nomeTipo = "Escudo Tático";
                    return nomeTipo;

                case -5:
                    nomeTipo = "Revista Maria";
                    return nomeTipo;

                case -6:
                    nomeTipo = "Cabeça de alho";
                    return nomeTipo;

                case -7:
                    nomeTipo = "Estaca de Madeira";
                    return nomeTipo;

                case -8:
                    nomeTipo = "Garrafa de lixívia";
                    infoLixivia = 0.3;
                    return nomeTipo + " | " + infoLixivia;

                case -9:
                    nomeTipo = "Veneno";
                    return nomeTipo;

                case -10:
                    nomeTipo = "Antídoto";
                    return nomeTipo;
            }
        return null;
    }

    public boolean saveGame(File fich) {

        // retorna o separador de linha, ou seja será a quebra de linha quando chegar a final de uma linha lida
        String nextLine = System.lineSeparator();

        try {
            FileWriter salvarFich = new FileWriter(fich);

            salvarFich.write(numLinha + " " + numColuna);
            salvarFich.write(nextLine);
            salvarFich.write(idEquipaAtual + "");
            salvarFich.write(nextLine);
            salvarFich.write(nC + "");
            salvarFich.write(nextLine);

            for(Creature criatura : creatures) {
                salvarFich.write(criatura.getId() + " : " + criatura.getIdTipo()+ " : " + criatura.getNome() + " : "
                        + criatura.getXAtual() + " : " + criatura.getYAtual());

                salvarFich.write(nextLine);
            }

            salvarFich.write(nE + "");
            salvarFich.write(nextLine);

            for(Equipamento objeto : equipamentos) {
                salvarFich.write(objeto.getiD() + " : " + objeto.getIdTipo()+ " : " + objeto.getXAtual() + " : " + objeto.getYAtual());

                salvarFich.write(nextLine);
            }

            salvarFich.write(nP + "");
            salvarFich.write(nextLine);
            salvarFich.write(xPortas + " : " + yPortas);

            /* IMCOMPLETO */

            salvarFich.close();

            return true;

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            return false;
        }
    }

    public boolean loadGame(File fich) {
        return false;
    }

    public String[] popCultureExtravaganza() {
        return new String[0];
    }

    public boolean hasEquipment(int creatureId, int equipmentTypeId){
        return false;
    }

    public List<Zombie> getZombies(){
        return null;
    }

    public List<Humano> getHumans(){
        return null;
    }

    public List<String> getSurvivors(){
        return null;
    }
}