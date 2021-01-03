package pt.ulusofona.lp2.theWalkingDEISIGame;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
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
    private static ArrayList<Creature> creatures = new ArrayList<>();

    //Lista de Equipamento
    static ArrayList<Equipamento> equipamentos = new ArrayList<>();

    //Lista para o safeHaven
    private static ArrayList<Creature> safe = new ArrayList<>();

    private static int numLinha;
    private static int numColuna;
    private static int xPortas;
    private static int yPortas;
    private static int nrTurno = 0;

    private int linhaAtual = 0;
    private int idEquipaAtual = 10;

    private int nC;
    private int nE;
    private int nP;

    private static int nrDias = 6;
    private static int nrNoites = 6;

    private static boolean diurno = true;

    public TWDGameManager() {
    }

    //Esta função faz a leitura do ficheiro de texto e carrega para a memória a informação relevante.
    public boolean startGame(File ficheiroInicial) {

        creatures = new ArrayList<>(); // resent da lista de creatures.
        equipamentos = new ArrayList<>(); // reset da lista de equipamentos
        safe = new ArrayList<>(); // reset da lista safeHaven

        numLinha = 0; // reset variavel numLinha.
        numColuna = 0; // reset variavel numColuna.
        xPortas = 0; // reset variavel xPortas safeHaven.
        yPortas = 0; // reset variavel yPortas safeHaven.
        nrTurno = 0; // reset variavel turnos.

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
                        case 5:
                            Creature crianca = new Crianca(id, idTipo, nome, posX, posY);
                            creatures.add(crianca); // adiciona crianca
                            crianca.setTipo(idTipo);
                            crianca.setEquipa(idTipo);
                            System.out.println(crianca.toString()); // imprime crianca
                            break;

                        case 1:
                        case 6:
                            // adiciona aduto
                            // imprime adulto
                            Creature adulto = new Adulto(id, idTipo, nome, posX, posY);
                            creatures.add(adulto);
                            adulto.setTipo(idTipo);
                            adulto.setEquipa(idTipo);
                            System.out.println(adulto.toString());
                            break;

                        case 2:
                        case 7:
                            Creature militar = new Militar(id, idTipo, nome, posX, posY);
                            creatures.add(militar); // adiciona militar
                            militar.setTipo(idTipo);
                            militar.setEquipa(idTipo);
                            System.out.println(militar.toString()); // imprime militar
                            break;

                        case 3:
                        case 8:
                            Creature idoso = new Idoso(id, idTipo, nome, posX, posY);
                            creatures.add(idoso); // adiciona idoso
                            idoso.setTipo(idTipo);
                            idoso.setEquipa(idTipo);
                            System.out.println(idoso.toString()); // imprime idoso
                            break;

                        case 4:
                            Creature zombieVamp = new ZombieVampiro(id, idTipo, nome, posX, posY);
                            creatures.add(zombieVamp); // adiciona zombie
                            zombieVamp.setTipo(idTipo);
                            zombieVamp.setEquipa(idTipo);
                            System.out.println(zombieVamp.toString()); // imprime zombie
                            break;

                        case 9:
                            Creature cao = new Cao(id, idTipo, nome, posX, posY);
                            creatures.add(cao); // adiciona humano
                            cao.setTipo(idTipo);
                            cao.setEquipa(idTipo);
                            System.out.println(cao.toString()); // imprime humano
                            break;

                        default:
                            System.out.println("Erro! Criatura não adicionada!");
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

        // Os Vivos
        if (nrTurno == 0) {
            return idEquipaAtual = 10;
        } else if (nrTurno == 1) {
            return idEquipaAtual = 10;
        }

        // Os Outros
        else if (nrTurno == 2) {
            return idEquipaAtual = 20;
        } else if (nrTurno == 3) {
            return idEquipaAtual = 20;
        }

        // Os Vivos
        else if (nrTurno == 4) {
            return idEquipaAtual = 10;
        } else if (nrTurno == 5) {
            return idEquipaAtual = 10;
        }

        // Os Outros
        else if (nrTurno == 6) {
            return idEquipaAtual = 20;
        } else if (nrTurno == 7) {
            return idEquipaAtual = 20;
        }

        //Os Vivos
        else if (nrTurno == 8) {
            return idEquipaAtual = 10;
        } else if (nrTurno == 9) {
            return idEquipaAtual = 10;
        }

        // Os Outros
        else if (nrTurno == 10) {
            return idEquipaAtual = 20;
        } else if (nrTurno == 11) {
            return idEquipaAtual = 20;
        }

        return 0;
    }

    public List<Creature> getCreatures(){
        return creatures;
    }

    public boolean move(int xO, int yO, int xD, int yD) {

        //VALIDAÇÕES PARA COORDENADAS DE DESTINO FORA DO MAPA
        if (xD < 0 || xD > numLinha) {
            return false; // estão fora do mapa

        } else if (yD < 0 || yD > numColuna) {
            return false; // estão fora do mapa
        }

        boolean encontrouCriatura = false;
        boolean encontrouEquip = false;
        for (Creature creatureOrigem : creatures) {

            if (creatureOrigem.getIdEquipa() == idEquipaAtual &&
                    creatureOrigem.getXAtual() == xO && creatureOrigem.getYAtual() == yO) {

                // Verifica se vai para uma safe heaven
                // caso for, remove no mapa, guarda a criatura e retorna true
                if (isDoorToSafeHaven(xD, yD)) {
                    if (creatureOrigem.getIdEquipa() == 10) {
                        safe.add(creatureOrigem);
                        creatures.remove(creatureOrigem);
                    } else {
                        return false;
                    }
                }

                // Quando vai contra outra criatura
                for (Creature creatureDestino: creatures) {
                    // Se o elemento de uma equipa cair em cima de um outro da mesma equipa
                    // retorna falso
                    if (creatureDestino.getXAtual() == xD && creatureDestino.getYAtual() == yD) {
                        if (creatureDestino.getIdEquipa() == idEquipaAtual) {
                            return false;
                        } else {
                            // processa o combate
                            boolean movimentoValido = creatureOrigem.move(xO, yO, xD, yD, creatureDestino,
                                    creatures);
                            if (!movimentoValido) {
                                return false;
                            } else {
                                encontrouCriatura = true;
                                break;
                            }
                        }
                    }
                }

                // caso na posicao destino nao tiver uma criatura
                // verifica se é um equipamento
                if (creatureOrigem.getIdEquipa() == 10) {
                    for (Equipamento eq : equipamentos) {
                        if (eq.getxAtual() == xD && eq.getyAtual() == yD) {
                            // verificar se o humano tem equipamentos
                            if (creatureOrigem.getEquipamentosVivos().size() == 0) {
                                // se for militar e escudo de madeira entao a protecao aumenta
                                if (creatureOrigem.getIdTipo() == 7 && eq.getIdTipo() == 0) {
                                    // verifica se foi a primeira vez usado
                                    if (!eq.isEscudoUsado()) {
                                        eq.aumentaCountUsos();
                                    }
                                }
                                creatureOrigem.getEquipamentosVivos().add(eq);
                                //equipamentos.remove(eq);
                                // guarda como referencia a posicao original
                                eq.xAnterior = xD;
                                eq.yAnterior = yD;

                                //Move uma posicao
                                creatureOrigem.setxAtual(xD);
                                creatureOrigem.setyAtual(yD);

                            } else {
                                // guardamos o equipamento existente na lista de equipamentos
                                Equipamento eqAntigo = creatureOrigem.getEquipamentosVivos().get(0);
                                equipamentos.add(eqAntigo);
                                // removemos esse equipamento e devolvemos na posicao original
                                creatureOrigem.getEquipamentosVivos().remove(0);
                                eqAntigo.xAtual = eqAntigo.xAnterior;
                                eqAntigo.yAtual = eqAntigo.yAnterior;
                                // depois de removido adiciona o novo
                                creatureOrigem.getEquipamentosVivos().add(eq);
                                creatureOrigem.setxAtual(xD);
                                creatureOrigem.setyAtual(yD);
                            }
                            encontrouEquip = true;
                            break;
                        }
                    }
                } else if (creatureOrigem.getIdEquipa() == 20) {
                    // se for da equipa dos zombies
                    for (Equipamento eq : equipamentos) {
                        if (eq.getxAtual() == xD && eq.getyAtual() == yD) {
                            // se for zombie vamp vs alho retorna falso
                            if (creatureOrigem.getIdTipo() == 4 && eq.getIdTipo() == 5) {
                                return false;
                            }
                            // Adiciona nos equipamentos destruidos
                            // Destroi os equipamento
                            //Move uma posicao
                            creatureOrigem.destruidos.add(eq);
                            equipamentos.remove(eq); // problema?

                            creatureOrigem.setxAtual(xD);
                            creatureOrigem.setyAtual(yD);

                            encontrouEquip = true;
                        }
                    }
                }

                // Caso nao encontar nenhuma criatura, equipamento ou safeheaven
                if (!encontrouCriatura && !encontrouEquip) {
                    if (!creatureOrigem.saltouPorCima(xO, yO, xD, yD, creatures)) {
                        creatureOrigem.move(xO, yO, xD, yD);
                        creatureOrigem.setxAtual(xD);
                        creatureOrigem.setyAtual(yD);
                    } else {
                        return false;
                    }
                }

                nrTurno++;

                // Dia
                if (nrTurno == 0 || nrTurno == 1) {
                    idEquipaAtual = 10;
                    diurno = true;
                }

                // Noite
                else if (nrTurno == 2 || nrTurno == 3) {
                    idEquipaAtual = 20;
                    diurno = false;
                }

                // Dia
                else if (nrTurno == 4 || nrTurno == 5) {
                    idEquipaAtual = 10;
                    diurno = true;
                }

                //Noite
                else if (nrTurno == 6 || nrTurno == 7) {
                    idEquipaAtual = 20;
                    diurno = false;
                }

                // Dia
                else if (nrTurno == 8 || nrTurno == 9) {
                    idEquipaAtual = 10;
                    diurno = true;
                }

                // Noite
                else if (nrTurno == 10 || nrTurno == 11) {
                    idEquipaAtual = 20;
                    diurno = false;
                }

               // System.out.println(creatures);
                 return true;
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
            return idEquipaAtual = 10;
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
            if (e.getxAtual() == x && e.getyAtual() == y){
                return e.getId();
            }
        }

        return 0;
    }

    public List<String> getGameResults() {

        List<String> resultados = new ArrayList<>();

        resultados.add("Nr. de turnos terminados: ");
        resultados.add(nrTurno + "\n");
        resultados.add("\n");

        resultados.add("Ainda pelo bairro: ");
        resultados.add("\n");

        resultados.add("OS VIVOS");
        for (Creature vivos: creatures){
            switch (vivos.getIdTipo()){
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                    resultados.add(vivos.getId() + " : " + vivos.getNome());
            }
        }

        resultados.add("\n");
        resultados.add("OS OUTROS");
        for (Creature others : creatures){
            switch (others.getIdTipo()){
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                    resultados.add(others.getId() + " : " + others.getNome());
            }
        }

        resultados.add("\n");
        resultados.add("No safe haven: ");
        resultados.add("" + safe);

        resultados.add("\n");
        resultados.add("OS VIVOS");
        //TODO por implementar

        resultados.add("\n");
        resultados.add("Envenenados / Destruidos");

        resultados.add("\n");
        resultados.add("OS VIVOS");
        //TODO por implementar

        resultados.add("\n");
        resultados.add("OS OUTROS");
        //TODO por implementar

        return resultados;
    }

    public boolean isDay() {
        return diurno;
    }

    public int getEquipmentId(int creatureId) {
        // verifica se o criatura tem o equipamento
        for (Creature creature: creatures) {
            if (creature.getId() == creatureId) {
                if (creature.getEquipamentosVivos().size() !=0) {
                    return creature.getEquipamentosVivos().get(0).getId();
                }
            }
        }
        // se nao tiver retorna 0
        return 0;
    }

    public List<Integer> getIdsInSafeHaven() {

        ArrayList<Integer> creaturesSafeHeavenID = new ArrayList<>();
        for (Creature creature : safe){
            creaturesSafeHeavenID.add(creature.getId());
        }
        return creaturesSafeHeavenID;
    }

    public boolean isDoorToSafeHaven(int x, int y) {
        return xPortas == x && yPortas == y;
    }

    public int getEquipmentTypeId(int equipmentId){

        for (Equipamento equipamento: equipamentos){
            if (equipmentId == equipamento.getId()){
                return equipamento.getIdTipo();
            }
        }
        return 0;
    }

    public String getEquipmentInfo(int equipmentId) {

        for (Equipamento equipamento: equipamentos) {
            if (equipamento.getId() == equipmentId) {
                // Se os equipamentos forem escudo de madeira, pistola ou lixivia
                // <Nome Tipo> | <Info>
                if (equipamento.getIdTipo() == 0 || equipamento.getIdTipo() == 2) {
                    return equipamento.getTitulo() + " | " + equipamento.getCountUsos();
                } else if (equipamento.getIdTipo() == 7) {
                    return equipamento.getTitulo() + " | " + equipamento.getCountUsos();
                }
                // Caso nao for, retorna apenas <Nome Tipo>
                return equipamento.getTitulo();
            }
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
                salvarFich.write(objeto.getId() + " : " + objeto.getIdTipo()+ " : " + objeto.getxAtual() + " : " + objeto.getyAtual());

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

        String[] resposta = new String[14];
        resposta[0] = "Resident Evil";
        resposta[1] = "Evil Dead";
        resposta[2] = "A Noite Devorou o Mundo";
        resposta[3] = "Invasão Zumbi";
        resposta[4] = "..";
        resposta[5] = "Resident Evil 2";
        resposta[6] = "The Mandalorian";
        resposta[7] = "1972";
        resposta[8] = "Kill Bill";
        resposta[9] = "1978";
        resposta[10] = "James Bond";
        resposta[11] = "..";
        resposta[12] = "Cabeça de alho chocho";
        resposta[13] = "Farrokn Bulsara";

        return resposta;
    }




    /* FUNCOES PRIMEIRA PARTE */

    public boolean hasEquipment(int creatureId, int equipmentTypeId){
        // verifica se o humano tem o equipamento
        for (Creature creature: creatures) {
            if (creature.getId() == creatureId) {
                switch (creature.getIdTipo()){
                    case 0:
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                        for (Equipamento equipamento: creature.destruidos) {
                            if (equipamento.idTipo == equipmentTypeId) {
                                return true;
                            }
                        }

                    case 5:
                    case 6:
                    case 7:
                    case 8:
                    case 9:
                        for (Equipamento equipamento: creature.equipamentos) {
                            if (equipamento.idTipo == equipmentTypeId) {
                                return true;
                            }
                        }
                }
            }
        }
        // se nenhum tiver retorna falso
        return false;
    }

    public List<String> getSurvivors(){
        return null;
    }
}