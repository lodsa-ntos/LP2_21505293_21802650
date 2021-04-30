package pt.ulusofona.lp2.theWalkingDEISIGame;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class TWDGameManager {

    /*------------------------------------------The Walking DEISI----------------------------------------------
    Tema:
          Este projecto tem como objetivo o desenvolvimento de uma aplicação (programa) em linguagem Java.

          No bairro do Freddy M. passa-se algo de muito estranho. Algumas pessoas têm desaparecido
          sem deixar rasto. O Freddy resolveu investigar e descobriu que está no meio de um surto de zombies.
          O Freddy pretende salvar os seus vizinhos, mas não sabe qual é a melhor forma de o fazer.

          A simulação irá consistir na aplicação de várias regras que definem os movimentos dos
          humanos (e seus animais de companhia) ao longo de vários dias, e várias noites e tem como
          objetivo tentar planear os movimentos que permitam salvar o maior número de companheiros.

    ----------------------------------------------------------------------------------------------------------*/

    //Lista de Creatures
    private static ArrayList<Creature> creatures = new ArrayList<>();
    //Lista para o safeHaven
    private static ArrayList<Creature> safe = new ArrayList<>();
    //Lista de criaturas envenenadas
    private static ArrayList<Creature> criaturasEnvenenadas = new ArrayList<>();
    //Lista de Equipamento
    private static ArrayList<Equipamento> equipamentos = new ArrayList<>();
    //Lista de zombies destruidos
    static ArrayList<Creature> zombiesDestruidos = new ArrayList<>();
    //Lista de Equipamento encontrados no caminho
    static ArrayList<Equipamento> equipamentosEncontrados = new ArrayList<>();
    //Lista de Equipamento encontrados no caminho
    private static ArrayList<Porta> portasEmJogo = new ArrayList<>();

    private static int numLinha;
    private static int numColuna;
    private int xPortas;
    private int yPortas;
    private int linhaAtual = 0;
    private int idEquipaInicial;
    private int idEquipaAtual;
    private int nC;
    private int nE;
    private int nP;
    private int nrTurno = 0;
    private int nrTurnoDoVeneno = 0;
    private int turnoDeTransformacoes = 0;
    private static boolean diurno = true;

    public TWDGameManager() {
    }

    public void startGame(File ficheiroInicial) throws InvalidTWDInitialFileException, FileNotFoundException {

        /* Cada vez que o ficheiro é lido o programa vai fazer o reset das variaveis para o valor inicial */
        incrementarReset();

        String[] dados;

        try {

            FileInputStream initialFile = new FileInputStream(ficheiroInicial);

            Scanner leitor;

            leitor = new Scanner(initialFile);

            String linha;

            // Primeira linha que indica as linhas e as colunas do mapa.
            // ler uma linha do ficheiro
            linha = leitor.nextLine();

            // vai quebrando a string em varias substrings a partir do espaco vazio
            String[] mapa = linha.split(" ");

            if (getWorldSize().length == 2) {
                numLinha = Integer.parseInt(mapa[0].trim()); // guarda na primeira posicao do array o numLinha
                numColuna = Integer.parseInt(mapa[1].trim()); // guarda na segunda posicao do array o numColuna
            }

            // Segunda linha que indica qual é o ID da equipa que começa o jogo.
            // ler uma linha do ficheiro
            linha = leitor.nextLine();
            idEquipaInicial = Integer.parseInt(linha);
            idEquipaAtual = idEquipaInicial;

            // Terceira linha que indica o número de criaturas em jogo.
            // ler uma linha do ficheiro
            linha = leitor.nextLine();
            nC = Integer.parseInt(linha);

            /* Obter o valor de numero de criaturas e a partir desse valor contar o numero de linhas de criaturas */
            int nLinhas;
            nLinhas = nC;

            if (nC < 2) {
                throw new InvalidTWDInitialFileException(nC, true, "");
            }

            /* enquanto o ficheiro tiver linhas não-lidas */
            while (leitor.hasNextLine()) {

                if (linhaAtual < nLinhas) {
                    // lê uma linha do ficheiro até achar uma quebra de linha
                    linha = leitor.nextLine();

                    // vai quebrando a string em varias substrings a partir do caracter dois pontos (separador)
                    dados = linha.split(":");

                    if (dados.length == 5) {

                        // Converte as Strings lidas para os tipos esperados
                        // "trim()" --> retira espaços a mais que estejam no inicio e no fim do texto (espaços padrao)
                        int id = Integer.parseInt(dados[0].trim());
                        int idTipo = Integer.parseInt(dados[1].trim());
                        String nome = dados[2].trim();
                        int posX = Integer.parseInt(dados[3].trim());
                        int posY = Integer.parseInt(dados[4].trim());

                        //Verificar se o idTipo é zombie ou humano e adiciona na lista de criaturas
                        switch (idTipo) {
                            case 0: /* Crianca Zombie */
                            case 5: /* Crianca VIvo */
                                Creature crianca = new Crianca(id, idTipo, nome, posX, posY);
                                creatures.add(crianca); // adiciona crianca
                                crianca.setTipo(idTipo);
                                crianca.setEquipa(idTipo);
                                crianca.getNrCriaturasZombies();
                                System.out.println(crianca.toString()); // imprime crianca
                                break;

                            case 1: /* Adulto Zombie */
                            case 6: /* Adulto Vivo */
                                Creature adulto = new Adulto(id, idTipo, nome, posX, posY);
                                creatures.add(adulto); // adiciona aduLto
                                adulto.setTipo(idTipo);
                                adulto.setEquipa(idTipo);
                                adulto.getNrCriaturasZombies();
                                System.out.println(adulto.toString()); // imprime adulto
                                break;

                            case 2: /* Militar Zombie */
                            case 7: /* Militar Vivo */
                                Creature militar = new Militar(id, idTipo, nome, posX, posY);
                                creatures.add(militar); // adiciona militar
                                militar.setTipo(idTipo);
                                militar.setEquipa(idTipo);
                                militar.getNrCriaturasZombies();
                                System.out.println(militar.toString()); // imprime militar
                                break;

                            case 3: /* Idoso Zombie */
                            case 8: /* Idoso Vivo */
                                Creature idoso = new Idoso(id, idTipo, nome, posX, posY);
                                creatures.add(idoso); // adiciona idoso
                                idoso.setTipo(idTipo);
                                idoso.setEquipa(idTipo);
                                idoso.getNrCriaturasZombies();
                                System.out.println(idoso.toString()); // imprime idoso
                                break;

                            case 4: /* Zombie Vampiro */
                                Creature zombieVamp = new ZombieVampiro(id, idTipo, nome, posX, posY);
                                creatures.add(zombieVamp); // adiciona zombie vampiro
                                zombieVamp.setTipo(idTipo);
                                zombieVamp.setEquipa(idTipo);
                                System.out.println(zombieVamp.toString()); // imprime zombie vampiro
                                break;

                            case 9: /* Cao */
                                Creature cao = new Cao(id, idTipo, nome, posX, posY);
                                creatures.add(cao); // adiciona cao
                                cao.setTipo(idTipo);
                                cao.setEquipa(idTipo);
                                System.out.println(cao.toString()); // imprime cao
                                break;

                            case 12: /* Coelho Vivo */
                            case 13: /* Coelho Zombie */
                                Creature coelho = new Coelho(id, idTipo, nome, posX, posY);
                                creatures.add(coelho); // adiciona coelho
                                coelho.setTipo(idTipo);
                                coelho.setEquipa(idTipo);
                                System.out.println(coelho.toString()); // imprime coelho
                                break;

                            default:
                                System.out.println("Erro! Criatura não adicionada!");
                                break;
                        }
                    } else {
                        throw new InvalidTWDInitialFileException(nC, false, linha);
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

                    // enquanto o ficheiro tiver linhas não-lidas depois da anterior, lê as linhas com equipamentos
                    while (leitor.hasNextLine()) {

                        if (linhaAtual < linhaPorta) {
                            // lê uma linha do ficheiro até achar uma quebra de linha
                            linha = leitor.nextLine();

                            // vai quebrando a string em varias substrings a partir do caracter dois pontos (separador)
                            String[] novaFila = linha.split(":");

                            if (novaFila.length == 4) {

                                // Converte as Strings lidas para os tipos esperados
                                // "trim()" --> retira espaços a mais que estejam no inicio e no fim do texto (espaços padrao)
                                int id = Integer.parseInt(novaFila[0].trim());
                                int idTipo = Integer.parseInt(novaFila[1].trim());
                                int posX = Integer.parseInt(novaFila[2].trim());
                                int posY = Integer.parseInt(novaFila[3].trim());

                                //Verificar o idTipo e adiciona na lista
                                if (idTipo == 0 || idTipo == 1 || idTipo == 2 || idTipo == 3 || idTipo == 4 || idTipo == 5
                                        || idTipo == 6 || idTipo == 7 || idTipo == 8 || idTipo == 9 || idTipo == 10) {
                                    Equipamento allEquipments = new Equipamento(id, idTipo, posX, posY);
                                    equipamentos.add(allEquipments); // adiciona equipamento
                                    allEquipments.setIdTipo(idTipo); // chama o tipo de equipamento
                                    System.out.println(allEquipments.toString());
                                }
                            } else {
                                throw new InvalidTWDInitialFileException(nE, false, linha);
                            }

                            linhaAtual++;

                        } else if (linhaAtual == linhaPorta) {
                            linha = leitor.nextLine();
                            nP = Integer.parseInt(linha);
                            System.out.println(nP);

                            while (leitor.hasNextLine()) {
                                // lê uma linha do ficheiro até achar uma quebra de linha
                                linha = leitor.nextLine();

                                // vai quebrando a string em varias substrings a partir do caracter dois pontos (separador)
                                String[] filaDasPortas = linha.split(":");

                                if (filaDasPortas.length == 2) {

                                    // Converte as Strings lidas para os tipos esperados
                                    // "trim()" --> retira espaços a mais que estejam no inicio e no fim do texto (espaços padrao)
                                    xPortas = Integer.parseInt(filaDasPortas[0].trim()); // guarda na primeira posicao do array o x
                                    yPortas = Integer.parseInt(filaDasPortas[1].trim()); // guarda na segunda posicao do array o y

                                    Porta portaSafeHaven = new Porta(xPortas, yPortas);
                                    portasEmJogo.add(portaSafeHaven);
                                    System.out.println(portaSafeHaven.toString());

                                } else {
                                    throw new InvalidTWDInitialFileException(nP, false, linha);
                                }
                            }
                        }
                    }
                }
            }

            leitor.close();

        } catch (FileNotFoundException ex) {
            System.out.println("\n" + "Erro.: " + ficheiroInicial.getName() + " não encontrado.");
        }
    }

    public int[] getWorldSize() {
        int [] tamanho = new int[2];
        tamanho[0] = numLinha;
        tamanho[1] = numColuna;
        return tamanho;
    }

    public int getInitialTeam() {
        return idEquipaInicial;
    }

    public List<Creature> getCreatures(){
        return creatures;
    }

    // TODO falta implementar os movimentos do coelho
    public boolean move(int xO, int yO, int xD, int yD) {

        if (!gameIsOver()) {
            //VALIDAÇÕES PARA COORDENADAS DE DESTINO FORA DO MAPA
            if (xD < 0 || xD > numLinha) {
                return false; // estão fora do mapa

            } else if (yD < 0 || yD > numColuna) {
                return false; // estão fora do mapa
            }

            boolean encontrouEquip = false;
            boolean nrTurnosImpares = Math.abs(xO - xD) <= 2 && Math.abs(yO - yD) <= 2;
            boolean nrTurnosPares = Math.abs(xO - xD) <= 3 && Math.abs(yO - yD) <= 3;

            for (Creature creatureOrigem : creatures) {
                if (creatureOrigem.getIdEquipa() == idEquipaAtual &&
                        creatureOrigem.getXAtual() == xO && creatureOrigem.getYAtual() == yO) {

                    /* Verifica se é da equipa dos VIVOS que está a tentar entrar no safeHaven */
                    if (creatureOrigem.getIdEquipa() == 10) {
                        if (!saltarPorCima(xO, yO, xD, yD) && creatureOrigem.getIdTipo() != 5 && creatureOrigem.getIdTipo() != 8) {
                            return false;
                        }
                        /* Se existir uma porta safeHaven */
                        if (isDoorToSafeHaven(xD, yD)) {
                            /* Vamos colocar o vivo lá dentro */
                            creatureOrigem.inSafeHaven(true);
                            /* Vamos remove-lo do mapa */
                            //creatureOrigem.id = 0;
                            creatures.get(creatures.indexOf(creatureOrigem)).inSafeHaven(true);
                            /* E adiciona-mos o vivo na lista do safeHaven */
                            safe.add(creatureOrigem);
                            creatureOrigem.setxAtual(xD);
                            creatureOrigem.setyAtual(yD);
                            incrementarTurno();
                            return true;
                        }
                    }

                    // COMBATE = VIVO NA DEFENSIVA
                    for (Creature creatureDestino : creatures) {
                        // Se o elemento de uma equipa cair em cima de um outro da mesma equipa
                        // retorna falso
                        if (creatureDestino.getXAtual() == xD && creatureDestino.getYAtual() == yD) {
                            if (creatureDestino.getIdEquipa() == idEquipaAtual) {
                                return false;
                            } else {

                                /* Zombie VS Vivo sem equipamento */
                                if (creatureOrigem.getIdEquipa() == 20) {

                                    if (creatureOrigem.getIdTipo() == 0 || creatureOrigem.getIdTipo() == 1 ||
                                            creatureOrigem.getIdTipo() == 2 || creatureOrigem.getIdTipo() == 3 ||
                                            creatureOrigem.getIdTipo() == 4) {

                                        // O cão não se transforma
                                        if (creatureDestino.getIdTipo() == 9) {
                                            return false;
                                        }

                                        /* verifica se o vivo tem equipamentos // se nao tiver nenhum equipamento */
                                        if (creatureDestino.getEquipamentosVivos().size() == 0) {
                                            switch (creatureDestino.getIdTipo()) {
                                                case 5:
                                                case 6:
                                                case 7:
                                                case 8:
                                                    /* Vivo tranforma-se (->) em Zombie */
                                                    creatureOrigem.transformaEmZombie(creatureDestino);
                                                    creatureDestino.setTransformado(true);
                                                    creatureOrigem.countTransformacoesFeitasPorZombies();
                                                    incrementarTurno();
                                                    return true;
                                            }

                                        } else {

                                            /* Zombie VS Vivo com equipamentos defensivos */
                                            switch (creatureDestino.getEquipamentosVivos().get(0).getIdTipo()) {
                                                case 0:
                                                    /* Interação com o Escudo de madeira */

                                                    /* Quando militar defende, alteramos os estado de uso do escudo de madeira */
                                                    if (creatureDestino.getIdTipo() == 7) {
                                                        creatureDestino.getEquipamentosVivos().get(0).escudoFoiUsado();
                                                    }

                                                    /* diminuimos ... */
                                                    creatureDestino.getEquipamentosVivos().get(0).diminuiCountUsos();

                                                    creatureDestino.equipamentos.get(0).incrementaNrSalvacoes();

                                                    if (creatureDestino.equipamentos.get(0).getCountUsos() == 0) {
                                                        // destrui-mos o equipamento
                                                        creatureDestino.getEquipamentosVivos().remove(creatureDestino.equipamentos.get(0));
                                                    }

                                                    incrementarTurno();
                                                    return true;

                                                case 1:
                                                    /* Interação com a Espada */
                                                    if (creatureDestino.getIdTipo() == 5) {
                                                        if (creatureOrigem.getIdTipo() != 0) {
                                                            /* vamos transformar o vivo em zombie */
                                                            creatureOrigem.transformaEmZombie(creatureDestino);
                                                            creatureDestino.setTransformado(true);
                                                            creatureOrigem.countTransformacoesFeitasPorZombies();
                                                            /* e destrui-mos o equipamento */
                                                            creatureDestino.getEquipamentosVivos().remove(creatureDestino.equipamentos.get(0));
                                                        } else {
                                                            /* vamos destruir o zombie */
                                                            creatures.remove(creatureOrigem);
                                                            zombiesDestruidos.add(creatureOrigem);
                                                            /* incrementa o numero de zombies destruidos */
                                                            creatureDestino.countZombiesDestruidos();
                                                            creatureDestino.setZombieIsDestroyed(true);
                                                        }
                                                        incrementarTurno();
                                                        return true;
                                                    }

                                                    if (creatureDestino.getIdTipo() == 6 || creatureDestino.getIdTipo() == 7 ||
                                                            creatureDestino.getIdTipo() == 8) {
                                                        /* vamos destruir o zombie */
                                                        creatures.remove(creatureOrigem);
                                                        zombiesDestruidos.add(creatureOrigem);

                                                        /* incrementa o numero de zombies destruidos */
                                                        creatureDestino.countZombiesDestruidos();
                                                        creatureDestino.setZombieIsDestroyed(true);
                                                        incrementarTurno();
                                                        return true;
                                                    }

                                                case 2:
                                                    /* Interação com a Pistola */
                                                    if (creatureDestino.getIdTipo() == 5 || creatureDestino.getIdTipo() == 6
                                                            || creatureDestino.getIdTipo() == 7 || creatureDestino.getIdTipo() == 8) {

                                                        if (creatureDestino.equipamentos.get(0).getCountUsos() == 0) {
                                                            creatureDestino.equipamentos.get(0).isBroken();
                                                            /* A pistola não tem efeito contra Zombies Vampiros */
                                                            /* vamos transformar o vivo em zombie */
                                                            creatureOrigem.transformaEmZombie(creatureDestino);
                                                            creatureDestino.setTransformado(true);
                                                            creatureOrigem.countTransformacoesFeitasPorZombies();
                                                            /* e destrui-mos o equipamento*/
                                                            creatureDestino.getEquipamentosVivos().remove(creatureDestino.equipamentos.get(0));
                                                            incrementarTurno();
                                                            return true;
                                                        }

                                                        if (creatureOrigem.getIdTipo() != 4) {
                                                            /* diminui uma bala */
                                                            creatureDestino.equipamentos.get(0).diminuiCountUsos();

                                                            /* incrementa o numero de salvacao feita pelo equipamento */
                                                            creatureDestino.equipamentos.get(0).incrementaNrSalvacoes();

                                                            /* vamos destruir o zombie */
                                                            creatures.remove(creatureOrigem);
                                                            zombiesDestruidos.add(creatureOrigem);

                                                            /* incrementa o numero de zombies destruidos */
                                                            creatureDestino.countZombiesDestruidos();
                                                            creatureDestino.setZombieIsDestroyed(true);
                                                            incrementarTurno();
                                                            return true;

                                                        } else if (creatureOrigem.getIdTipo() == 4 && !isDay()) {
                                                            /* A pistola não tem efeito contra Zombies Vampiros */
                                                            /* vamos transformar o vivo em zombie */
                                                            creatureOrigem.transformaEmZombie(creatureDestino);
                                                            creatureDestino.setTransformado(true);
                                                            creatureOrigem.countTransformacoesFeitasPorZombies();
                                                            /* e destrui-mos o equipamento*/
                                                            creatureDestino.getEquipamentosVivos().remove(creatureDestino.equipamentos.get(0));
                                                            incrementarTurno();
                                                            return true;
                                                        }
                                                    }

                                                case 3:
                                                    /* Interação com o Escudo Tático */
                                                    /* Protecao contra varios ataques */
                                                    creatureDestino.equipamentos.get(0).incrementaNrSalvacoes();
                                                    incrementarTurno();
                                                    return false;

                                                case 4:
                                                    /* Interação com a Revista Maria */
                                                    /* Protege contra ataques de zombies idosos */
                                                    if (creatureOrigem.getIdTipo() == 3) {
                                                        creatureDestino.equipamentos.get(0).incrementaNrSalvacoes();
                                                        return false;
                                                    } else {
                                                        /* Não protege de outros zombies */
                                                        /* Vivo tranforma-se (->) em Zombie */
                                                        creatureOrigem.transformaEmZombie(creatureDestino);
                                                        creatureDestino.setTransformado(true);
                                                        creatureOrigem.countTransformacoesFeitasPorZombies();
                                                        creatureDestino.getEquipamentosVivos().remove(creatureDestino.equipamentos.get(0));
                                                        incrementarTurno();
                                                        return true;
                                                    }

                                                case 5:
                                                    /* Interação com a Cabeça de alho */
                                                    /* Protege contra ataques de zombies Vampiros */
                                                    if (creatureOrigem.getIdTipo() == 4) {
                                                        creatureDestino.equipamentos.get(0).incrementaNrSalvacoes();
                                                        return false;
                                                    } else {
                                                        /* Não protege de outros zombies */
                                                        /* Vivo tranforma-se (->) em Zombie */
                                                        creatureOrigem.transformaEmZombie(creatureDestino);
                                                        creatureDestino.setTransformado(true);
                                                        creatureOrigem.countTransformacoesFeitasPorZombies();
                                                        creatureDestino.getEquipamentosVivos().remove(creatureDestino.equipamentos.get(0));
                                                        incrementarTurno();
                                                        return true;
                                                    }

                                                case 6:
                                                    /* Interação com a Estaca de madeira */
                                                    if (creatureDestino.getIdTipo() == 5 || creatureDestino.getIdTipo() == 6
                                                            || creatureDestino.getIdTipo() == 7 || creatureDestino.getIdTipo() == 8) {
                                                        /* vamos destruir o zombie */
                                                        creatures.remove(creatureOrigem);
                                                        zombiesDestruidos.add(creatureOrigem);

                                                        /* incrementa o numero de zombies destruidos */
                                                        creatureDestino.countZombiesDestruidos();
                                                        creatureDestino.setZombieIsDestroyed(true);

                                                        /* incrementa o numero de salvacao feita pelo equipamento */
                                                        creatureDestino.equipamentos.get(0).incrementaNrSalvacoes();
                                                        /* deixa de ter equipamento */
                                                        incrementarTurno();
                                                        return true;
                                                    }

                                                case 7:
                                                    /* Interação com a Lixivia */
                                                    creatureDestino.equipamentos.get(0).incrementaNrSalvacoes();
                                                    incrementarTurno();
                                                    return false;
                                                case 8:
                                                case 9:
                                                case 10:
                                                    /* Interação com o veneno */
                                                    /* Interação com o antidoto */
                                                    /* Interação com o capacete Beskar Helmet */
                                                    creatureDestino.equipamentos.get(0).incrementaNrSalvacoes();
                                                    incrementarTurno();
                                                    return false;
                                            }
                                        }
                                    }
                                }

                                /* processa o combate com equipamento ofensivo */
                                /* VIVO vs ZOMBIE */
                                boolean movimentoValido = creatureOrigem.processarOCombate(xO, yO, xD, yD, creatureDestino,
                                        creatures);

                                if (!movimentoValido) {
                                    return false;
                                } else {
                                    creatureDestino.incrementaCreaturesNoBolso();
                                    break;
                                }
                            }
                        }
                    }

                    /* AÇÃO = APANHAR E LARGAR OU DESTRUIR EQUIPAMENTO */
                    /* caso na posicao destino nao tiver uma criatura
                     verifica se é um equipamento */
                    if (creatureOrigem.getIdEquipa() == 10) {
                        if (creatureOrigem.moveDirecao(xO, yO, xD, yD, creatureOrigem)) {
                            for (Equipamento eq : equipamentos) {
                                if (eq.getxAtual() == xD && eq.getyAtual() == yD) {
                                    // verificar se o humano tem equipamentos
                                    if (creatureOrigem.getEquipamentosVivos().size() == 0) {
                                        // se for militar e escudo de madeira, entao a protecao aumenta
                                        if (creatureOrigem.getIdTipo() == 7 && eq.getIdTipo() == 0) {
                                            // verifica se foi a primeira vez usado
                                            if (!eq.isEscudoUsado()) {
                                                eq.aumentaCountUsos();
                                            }
                                        }

                                        /* Apenas podem apanhar o antidoto os 'vivos' que estejam envenenados */
                                        if (!creatureOrigem.isEnvenenado()) {
                                            if (eq.getIdTipo() == 9) {
                                                return false;
                                            }
                                        }

                                        if (creatureOrigem.getIdTipo() == 8 && isDay() == false) {
                                            return false;
                                        }

                                    } else {
                                        // guardamos o equipamento existente na lista de equipamentos
                                        Equipamento eqAntigo = creatureOrigem.getEquipamentosVivos().get(0);
                                        equipamentos.add(eqAntigo);
                                        // removemos esse equipamento e devolvemos na posicao original
                                        creatureOrigem.getEquipamentosVivos().remove(0);

                                    }
                                    // depois de removido adiciona o novo
                                    creatureOrigem.getEquipamentosVivos().add(eq);
                                    // Acrescenta o equipamento no bolso
                                    creatureOrigem.incrementaEquipamentosNoBolso();

                                    if (creatureOrigem.getEquipamentosVivos().get(0).getIdTipo() == 8) {
                                        System.out.println("\n" + creatureOrigem.getTipo() + " está envenenado." + "\n"
                                                + "Warning: Se o 'Vivo' estiver envenenado durante 3 turnos, morre. " +
                                                "\n" + "Tem mais uma jogada, tente encontrar o antidoto.");
                                    }

                                    if (creatureOrigem.getEquipamentosVivos().get(0).getIdTipo() == 9) {
                                        System.out.println("\n" + creatureOrigem.getTipo() + " conseguiu o antídoto a tempo, " +
                                                "está curado. Encontre o safeHaven");
                                    }

                                    /* Se a criatura quiser apanhar outro equipamento */
                                    switch (creatureOrigem.getEquipamentosVivos().get(0).getIdTipo()) {
                                        case 0:
                                        case 1:
                                        case 2:
                                        case 3:
                                        case 4:
                                        case 5:
                                        case 6:
                                        case 7:
                                        case 9:
                                        case 10:
                                            /* Se estiver a carregar um frasco veneno , a criatura tem de largar o frasco vazio */
                                            if (creatureOrigem.getEquipamentosVivos().get(0).getIdTipo() == 8) {
                                                // guardamos o equipamento existente na lista de equipamentos
                                                Equipamento eqAntigo = creatureOrigem.getEquipamentosVivos().get(0);
                                                equipamentos.add(eqAntigo);
                                                // removemos esse equipamento e devolvemos na posicao original
                                                creatureOrigem.getEquipamentosVivos().remove(0);

                                                // depois de removido adiciona o novo
                                                creatureOrigem.getEquipamentosVivos().add(eq);
                                                // Acrescenta o equipamento no bolso
                                                creatureOrigem.incrementaEquipamentosNoBolso();
                                            }
                                            break;
                                    }

                                    /* Se a criatura quiser apanhar outro equipamento */
                                    switch (creatureOrigem.getEquipamentosVivos().get(0).getIdTipo()) {
                                        case 0:
                                        case 1:
                                        case 2:
                                        case 3:
                                        case 4:
                                        case 5:
                                        case 6:
                                        case 7:
                                        case 8:
                                        case 10:
                                            /* Se estiver a carregar um frasco antidoto , a criatura tem de largar o frasco vazio */
                                            if (creatureOrigem.getEquipamentosVivos().get(0).getIdTipo() == 9) {
                                                // guardamos o equipamento existente na lista de equipamentos
                                                Equipamento eqAntigo = creatureOrigem.getEquipamentosVivos().get(0);
                                                equipamentos.add(eqAntigo);
                                                // removemos esse equipamento e devolvemos na posicao original
                                                creatureOrigem.getEquipamentosVivos().remove(0);

                                                // depois de removido adiciona o novo
                                                creatureOrigem.getEquipamentosVivos().add(eq);
                                                // Acrescenta o equipamento no bolso
                                                creatureOrigem.incrementaEquipamentosNoBolso();
                                            }
                                            break;
                                    }

                                    encontrouEquip = true;
                                    break;
                                }
                            }
                        } else {
                            return false;
                        }
                        
                        // se for da equipa dos zombies // e se for para cima do equipamento // vamos destrui-lo
                    } else if (creatureOrigem.getIdEquipa() == 20 && creatureOrigem.getIdTipo() != 4) {
                        if (creatureOrigem.moveDirecao(xO, yO, xD, yD, creatureOrigem)) {
                            for (Equipamento eq : equipamentos) {
                                if (eq.getxAtual() == xD && eq.getyAtual() == yD) {

                                    if (eq.getIdTipo() == 8) {
                                        /* Veneno não pode ser destruido */
                                        return false;
                                    }

                                    // Adiciona nos equipamentos destruidos
                                    // Destroi os equipamento
                                    // Move uma posicao
                                    creatureOrigem.destruidos.add(eq);

                                    /* Removemos o equipamento */
                                    equipamentos.remove(eq);

                                    /* Contamos os equipamentos destruidos */
                                    creatureOrigem.countEquipamentosDestruidos++;

                                    encontrouEquip = true;
                                    break;
                                }
                            }
                        } else {
                            return false;
                        }
                    }

                    /* Interação com o Veneno e Antidoto */
                    if (creatureOrigem.getIdEquipa() == 10) {
                        for (Equipamento eq : equipamentos) {
                            if (!encontrouEquip) {
                                if (eq.getxAtual() == xO && eq.getyAtual() == yO) {
                                    /* Se o vivo apanhar um veneno */
                                    if (eq.getIdTipo() == 8) {
                                        //nrTurnoDoVeneno++;
                                        /* dizemos que o vivo está envenenado */
                                        creatureOrigem.setEnvenenado(true);

                                        if (nrTurnoDoVeneno > 1) {
                                            if (creatureOrigem.isEnvenenado()) {
                                                if (creatureOrigem.getIdTipo() == 8) {
                                                    if (isDay() == true) {
                                                        nrTurnoDoVeneno = 0;
                                                        creatures.remove(creatureOrigem);
                                                        criaturasEnvenenadas.add(creatureOrigem);
                                                        creatureOrigem.setHumanDeadPorEnvenenamento(true);
                                                        incrementarTurno();
                                                        return true;
                                                    } else {
                                                        return false;
                                                    }
                                                } else if (creatureOrigem.getIdTipo() != 8) {
                                                    nrTurnoDoVeneno = 0;
                                                    creatures.remove(creatureOrigem);
                                                    criaturasEnvenenadas.add(creatureOrigem);
                                                    creatureOrigem.setHumanDeadPorEnvenenamento(true);
                                                    incrementarTurno();
                                                    return true;
                                                }

                                            } else {
                                                return false;
                                            }
                                        }
                                        /* Se apanhar o antidoto, fica curado */
                                    } else if (eq.getIdTipo() == 9) {
                                            nrTurnoDoVeneno = 0;
                                            creatureOrigem.setEnvenenado(false);
                                        }
                                    }
                                }
                            }
                        }

                    // se encontrou equipamento removemos da lista anterior e colocamos
                    // numa outra lista de forma a que quando o "vivo" saia da posicao que apanhou o equipamento
                    // o equipamento desapareca do mapa
                    for (Equipamento eq : equipamentos) {
                        if (creatureOrigem.getIdEquipa() == 10 && creatureOrigem.getIdTipo() != 8) {
                            if (!encontrouEquip) {
                                if (eq.getxAtual() == xO && eq.getyAtual() == yO) {
                                    eq.encontrouEquipamento(true);
                                    equipamentos.get(equipamentos.indexOf(eq)).encontrouEquipamento(true);
                                    equipamentosEncontrados.add(eq);
                                    eq.setxAtual(-1);
                                    eq.setyAtual(-1);
                                    creatureOrigem.setxAtual(xD);
                                    creatureOrigem.setyAtual(yD);
                                    incrementarTurno();
                                    return true;
                                }
                            }
                        }
                    }

                    /* Movimentação a partir do idoso */
                    if (creatureOrigem.getIdEquipa() == 10) {
                        /* Idosos humanos só se movem em turnos diurnos */
                        if (creatureOrigem.getIdTipo() == 8) {
                            if (isDay() == true) {
                                if (creatureOrigem.moveDirecao(xO, yO, xD, yD, creatureOrigem)) {
                                    creatureOrigem.setxAtual(xD);
                                    creatureOrigem.setyAtual(yD);
                                    for (Equipamento eq : equipamentos) {
                                        /* caso o idoso encontre o equipamento deve-o apanhar */
                                        if (!encontrouEquip) {
                                            /* quando se mover para fora dessa casa, deve-o largar */
                                            creatureOrigem.getEquipamentosVivos().remove(eq);
                                            creatureOrigem.setxAtual(xD);
                                            creatureOrigem.setyAtual(yD);
                                            incrementarTurno();
                                            return true;
                                        }
                                    }
                                    incrementarTurno();
                                    return true;
                                }
                            } else {
                                return false;
                            }

                        } else if (creatureOrigem.getIdTipo() != 8) {

                            if (!saltarPorCima(xO, yO, xD, yD) && creatureOrigem.getIdTipo() != 5 && creatureOrigem.getIdTipo() != 8) {
                                return false;
                            }

                            if (creatureOrigem.getIdTipo() == 5 && !creatureOrigem.moveDirecao(xO, yO, xD, yD, creatureOrigem)) {
                                return false;
                            }

                            if (creatureOrigem.getIdTipo() == 6 && !creatureOrigem.moveDirecao(xO, yO, xD, yD, creatureOrigem)) {
                                return false;
                            }

                            if (creatureOrigem.getIdTipo() == 7 && !creatureOrigem.moveDirecao(xO, yO, xD, yD, creatureOrigem)) {
                                return false;
                            }

                            if (creatureOrigem.getIdTipo() == 9 && !creatureOrigem.moveDirecao(xO, yO, xD, yD, creatureOrigem)) {
                                return false;
                            }

                            if (creatureOrigem.getIdTipo() == 12 && !creatureOrigem.moveDirecao(xO, yO, xD, yD, creatureOrigem)) {
                                return false;
                            }

                            creatureOrigem.setxAtual(xD);
                            creatureOrigem.setyAtual(yD);
                            incrementarTurno();
                            return true;

                        }
                        return false;
                    }

                    /* Movimentação a partir do Zombie Vampiro */
                    if (creatureOrigem.getIdEquipa() == 20) {
                        /* Zombie Vampiro só se movem em turnos nocturnos */
                        if (creatureOrigem.getIdTipo() == 4 && !isDay()) {
                            if (creatureOrigem.moveDirecao(xO, yO, xD, yD, creatureOrigem)) {
                                creatureOrigem.setxAtual(xD);
                                creatureOrigem.setyAtual(yD);
                                for (Equipamento eq : equipamentos) {
                                    /* caso o zombie vampiro encontre o equipamento deve-o destruir */
                                    if (!encontrouEquip) {
                                        if (eq.getxAtual() == xO && eq.getyAtual() == yO) {
                                            /* Se for zombie vamp vs alho retorna falso */
                                            if (creatureOrigem.getIdTipo() == 4 && eq.getIdTipo() == 5) {
                                                return false;
                                            }
                                            /* Removemos o equipamento */
                                            equipamentos.remove(eq);
                                            /* Adiciona-o nos equipamentos destruidos */
                                            creatureOrigem.destruidos.add(eq);
                                            creatureOrigem.setxAtual(xD);
                                            creatureOrigem.setyAtual(yD);
                                            incrementarTurno();
                                            return true;
                                        }
                                    }
                                }
                                incrementarTurno();
                                return true;
                            }

                            /* Se forem outros zombies */
                        } else if (creatureOrigem.getIdTipo() != 4) {

                            if (!saltarPorCima(xO, yO, xD, yD) && creatureOrigem.getIdTipo() != 5 && creatureOrigem.getIdTipo() != 8) {
                                return false;
                            }

                            if (creatureOrigem.getIdTipo() == 0 && !creatureOrigem.moveDirecao(xO, yO, xD, yD, creatureOrigem)) {
                                return false;
                            }

                            if (creatureOrigem.getIdTipo() == 1 && !creatureOrigem.moveDirecao(xO, yO, xD, yD, creatureOrigem)) {
                                return false;
                            }

                            if (creatureOrigem.getIdTipo() == 2 && !creatureOrigem.moveDirecao(xO, yO, xD, yD, creatureOrigem)) {
                                return false;
                            }

                            if (creatureOrigem.getIdTipo() == 3 && !creatureOrigem.moveDirecao(xO, yO, xD, yD, creatureOrigem)) {
                                return false;
                            }

                            if (creatureOrigem.getIdTipo() == 13 && !creatureOrigem.moveDirecao(xO, yO, xD, yD, creatureOrigem)) {
                                return false;
                            }

                            creatureOrigem.setxAtual(xD);
                            creatureOrigem.setyAtual(yD);
                            incrementarTurno();
                            return true;
                        }
                        return false;
                    }

                   /* TODO arranjar solução para o deslocamento do coelho */

                    incrementarTurno();
                    return true;
                }
            }
        }
        return false;
    }

    protected boolean saltarPorCima(int xO, int yO, int xD, int yD) {
        /* verifica direcao em que a critura está a tentar saltar por cima */
        String direcao = moverTodasDirecoes(xO, xD, yO, yD);
        int diffX = 0;
        int diffY = 0;

        int meioX = 0;
        int meioY = 0;
        /* se for horizontal significa que a diferenca do Y é o meio */
        switch (direcao) {
            case "horizontal":
                diffX = Math.abs(xD - xO);
                meioY = yO;
                if (xD < xO) {
                    meioX = xD + (diffX / 2);
                } else {
                    meioX = xO + (diffX / 2);
                }

                break;

            case "vertical":
                diffY = Math.abs(yD - yO);
                meioX = xO;

                if (yD < yO) {
                    meioY = yD + (diffY / 2);
                } else {
                    meioY = yO + (diffY / 2);
                }

                break;

            case "diagonal":
                diffX = Math.abs(xD - xO);
                diffY = Math.abs(yD - yO);

                if (xD < xO) {
                    meioX = xD + (diffX / 2);
                } else {
                    meioX = xO + (diffX / 2);
                }

                if (yD < yO) {
                    meioY = yD + (diffY / 2);
                } else {
                    meioY = yO + (diffY / 2);
                }

                break;
        }

        /* Verificar o deslocamento da criatura que está a ser movida
        * Verificar se ao mover existe uma criatura ou equipamento no meio */
        if (xD - xO > 1 && yO - yD > 1 || xO - xD > 1 && yD - yO > 1 || xO - xD > 1 && yO - yD > 1
                || xD - xO > 1 && yD - yO > 1 || xD - xO > 1 && Math.abs(yD - yO) == 0 || xO - xD > 1 && Math.abs(yD - yO) == 0
                || yD - yO > 1 && Math.abs(xD - xO) == 0 || yO - yD > 1 && Math.abs(xD - xO) == 0) {
            // verifica se uma criatura ou equipamento esta naquela posicao
            for (Creature creature : creatures) {
                if (creature.getXAtual() == meioX && creature.getYAtual() == meioY) {
                    return false;
                }
            }


            for (Equipamento equipamento : TWDGameManager.equipamentos) {
                if (equipamento.getxAtual() == meioX && equipamento.getyAtual() == meioY) {
                    return false;
                }
            }
        }

        return true;
    }

    protected String moverTodasDirecoes(int xO, int xD, int yO, int yD) {
        if (Math.abs(xD - xO) > 1 && Math.abs(yD - yO) == 0 ) {
            return "horizontal";
        } else if (Math.abs(xD - xO) == 0 && Math.abs(yD - yO) > 1) {
            return "vertical";
        } else if (Math.abs(xD - xO) > 1 && Math.abs(yD - yO) > 1) {
            return "diagonal";
        }

        return "";
    }

    public boolean gameIsOver() {
        int nrMaxDiaENoite = 6;
        int numeroVivosEmJogo = 0;
        int criancaEmJogo = 0;
        int adultoEmJogo = 0;
        int militarEmJogo = 0;
        int caoEmJogo = 0;
        int idosoEmJogo = 0;
        int countTransformacao = 0;

        /* Sem vivos em jogo */
        for (Creature creatureOrigem : creatures) {
            if (creatureOrigem.getIdEquipa() == 10) {
                /* Se existirem "Vivos" e não passaram para o SafeHaven ou não foram transformado em Zombie */
                if (!creatureOrigem.isInSafeHaven() && !creatureOrigem.isTransformado()
                        && !creatureOrigem.isEnvenenado()) {
                    /* conta os "Vivos" que ainda estão em Jogo*/
                    numeroVivosEmJogo++;
                    if (creatureOrigem.getIdTipo() == 5){
                        criancaEmJogo++;
                    }
                    if (creatureOrigem.getIdTipo() == 6){
                        adultoEmJogo++;
                    }
                    if (creatureOrigem.getIdTipo() == 7){
                        militarEmJogo++;
                    }
                    if (creatureOrigem.getIdTipo() == 8){
                        idosoEmJogo++;
                    }
                    if (creatureOrigem.getIdTipo() == 9) {
                        caoEmJogo++;
                    }
                }
            }
        }

        /* Apenas idosos vivos em jogo no turno nocturno */
        if (getCurrentTeamId() == 10) {
            if (!isDay()) {
                return criancaEmJogo == 0 && adultoEmJogo == 0 && militarEmJogo == 0 && caoEmJogo == 0;
            } else {
                return false;
            }
        }

        int numeroZombiesEmJogo = 0;
        int criancaZombieEmJogo = 0;
        int adultoZombieEmJogo = 0;
        int militarZombieEmJogo = 0;
        int idosoZombieEmJogo = 0;
        int zombieVampEmJogo = 0;

        /* Sem zombies em jogo */
        for (Creature creatureOrigem : creatures) {
            if (creatureOrigem.getIdEquipa() == 20) {
                /* Se existirem 'zombies' e ainda não foram destruidos */
                if (!creatureOrigem.zombieIsDestroyed()) {
                    /* conta os "zombies" que ainda estão em Jogo*/
                    numeroZombiesEmJogo++;
                    if (creatureOrigem.getIdTipo() == 0){
                        criancaZombieEmJogo++;
                    }
                    if (creatureOrigem.getIdTipo() == 1){
                        adultoZombieEmJogo++;
                    }
                    if (creatureOrigem.getIdTipo() == 2){
                        militarZombieEmJogo++;
                    }
                    if (creatureOrigem.getIdTipo() == 3){
                        idosoZombieEmJogo++;
                    }
                    if (creatureOrigem.getIdTipo() == 4) {
                        zombieVampEmJogo++;
                    }
                }
            }
        }

        /* Apenas Zombie Vampiro em jogo no turno diurno */
        if (getCurrentTeamId() == 20) {
            if (isDay()) {
                return criancaZombieEmJogo == 0 && adultoZombieEmJogo == 0 && militarZombieEmJogo == 0 && idosoZombieEmJogo == 0;
            } else {
                return false;
            }
        }

        /* //TODO se houver transformacao o jogo continua ...
              se não houver tranformacao o jogo termina no turno 12 */
        for (Creature creatureOrigem : creatures) {
            if (creatureOrigem.getIdEquipa() == 10) {
                if (creatureOrigem.isTransformado()) {
                    nrTurno--;
                }
            }
        }


        // O jogo termina se tiverem passados 3 dias e 3 noites ou se nao exitirem mais "Vivos" ou 'Zombies' em Jogo
        return ((nrTurno/2) >= nrMaxDiaENoite) || numeroVivosEmJogo == 0 || numeroZombiesEmJogo == 0;
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
        for (Creature c: creatures){
            if (c.getXAtual() == x && c.getYAtual() == y && !c.isInSafeHaven()) {
                return c.getId();
            }
        }

        for (Equipamento e : equipamentos){
            if (e.getxAtual() == x && e.getyAtual() == y && !e.encontrouEquipamento()){
                return e.getId();
            }
        }

        return 0;
    }

    public List<String> getGameResults() {

        List<String> resultados = new ArrayList<>();

        resultados.add("Nr. de turnos terminados:");
        resultados.add(String.valueOf(nrTurno));
        resultados.add("");

        resultados.add("Ainda pelo bairro: ");
        resultados.add("");

        resultados.add("OS VIVOS");
        for (Creature creatureVivos: creatures){
            if (creatureVivos.getIdEquipa() == 10) {
                if(!creatureVivos.isInSafeHaven() && !creatureVivos.isTransformado() && !creatureVivos.isEnvenenado()) /*TODO falta saber se não foram transformadas em zombies{*/ {
                    resultados.add(creatureVivos.getId() + " " + creatureVivos.getNome());
                }
            }
        }

        resultados.add("");
        resultados.add("OS OUTROS");
        for (Creature creatureZombies : creatures){
            if (creatureZombies.getIdEquipa() == 20) {
                resultados.add(creatureZombies.getId() + " (antigamente conhecido como " + creatureZombies.getNome() + ")");
            }
        }

        resultados.add("");
        resultados.add("Num safe haven:");
        resultados.add("");

        resultados.add("OS VIVOS");
        for (Creature creatureVivos: safe ) {
            if (creatureVivos.getIdEquipa() == 10) {
                if (creatureVivos.isInSafeHaven()) {
                    resultados.add(creatureVivos.getId() + " " + creatureVivos.getNome());
                }
            }
        }

        resultados.add("");
        resultados.add("Envenenados / Destruidos");

        resultados.add("");
        resultados.add("OS VIVOS");
        for (Creature creatureVivos: criaturasEnvenenadas) {
            if (creatureVivos.getIdEquipa() == 10) {
                if(creatureVivos.isEnvenenado()) {
                    resultados.add(creatureVivos.getId() + " " + creatureVivos.getNome());
                }
            }
        }

        resultados.add("");
        resultados.add("OS OUTROS");
        for (Creature creatureZombies : zombiesDestruidos){
            if (creatureZombies.getIdEquipa() == 20) {
                resultados.add(creatureZombies.getId() + " (antigamente conhecido como " + creatureZombies.getNome() + ")");
            }
        }

        return resultados;
    }

    public boolean isDay() {
        switch (nrTurno) {
            case 0:
            case 1:
            case 4:
            case 5:
            case 8:
            case 9:
                diurno = true;
                break;
            case 2:
            case 3:
            case 6:
            case 7:
            case 10:
            case 11:
                diurno = false;
                break;
        }
        return diurno;
    }

    public int getEquipmentId(int creatureId) {
        /* verifica se o criatura tem o equipamento */
        for (Creature creature: creatures) {
            if (creature.getId() == creatureId) {
                if (creature.getEquipamentosVivos().size() !=0) {
                    return creature.getEquipamentosVivos().get(0).getId();
                }
            }
        }
        /* se nao tiver retorna 0 */
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
        /* Todas as portas guardadas na lista ...
        * se estiverem na posicao (x,y), returnamos true e mostramos as portas */
        for (Porta door : portasEmJogo) {
            if (door.getxAtual() == x && door.getyAtual() == y){
                return true;
            }
        }
        return false;
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

    /* TODO falta implementar como gravar informacao completa do jogo */
    public boolean saveGame(File fich) {

        /* retorna o separador de linha, ou seja será a quebra de linha quando chegar a final de uma linha lida */
        String nextLine = System.lineSeparator();

        try {
            FileWriter salvarFich = new FileWriter(fich.getAbsoluteFile());

            salvarFich.write(numLinha + " " + numColuna);
            salvarFich.write(nextLine);
            salvarFich.write(getCurrentTeamId() + "");
            salvarFich.write(nextLine);

            salvarFich.write(getCreatures().size() + "");
            salvarFich.write(nextLine);

            for(Creature criatura : getCreatures()) {
                    salvarFich.write(criatura.getId() + " : " + criatura.getIdTipo() + " : " + criatura.getNome() + " : "
                            + criatura.getXAtual() + " : " + criatura.getYAtual());

                    salvarFich.write(nextLine);
            }

            salvarFich.write(equipamentos.size() + "");
            salvarFich.write(nextLine);

            for(Equipamento objeto : equipamentos) {
                salvarFich.write(objeto.getId() + " : " + objeto.getIdTipo()+ " : " + objeto.getxAtual() + " : "
                        + objeto.getyAtual());

                salvarFich.write(nextLine);
            }

            salvarFich.write(portasEmJogo.size() + "");
            salvarFich.write(nextLine);

            for(Porta p : portasEmJogo) {
                salvarFich.write(p.getxAtual() + " : " + p.getyAtual());
            }

            salvarFich.close();
            System.out.println("\n" + "Jogo guardado com sucesso ...");
            return true;

        } catch (IOException e) {
            System.out.println("Erro.: Não foi possível o guardar o jogo no ficheiro " + fich.getName() );
            return false;
        }
    }

    public boolean loadGame(File fich) {

        try {
            startGame(fich);
            System.out.println("\n" + "Jogo carregado com sucesso ...");
            return true;

        } catch (IOException | InvalidTWDInitialFileException e) {
            System.out.println("Erro.: Não foi possível carregar o jogo guardado do ficheiro " + fich.getName());
            return false;
        }
    }

    public String[] popCultureExtravaganza() {

        String[] resposta = new String[14];

        resposta[0] = "Resident Evil";
        resposta[1] = "Evil Dead";
        resposta[2] = "I Am Legend";
        resposta[3] = "I Am Legend";
        resposta[4] = "Dragon Ball";
        resposta[5] = "World War Z";
        resposta[6] = "Mandalorians";
        resposta[7] = "1972";
        resposta[8] = "Kill Bill";
        resposta[9] = "1978";
        resposta[10] = "Bond, James Bond.";
        resposta[11] = "Lost";
        resposta[12] = "Chocho";
        resposta[13] = "Farrokh Bulsara";

        return resposta;
    }

    public Map<String, List<String>> getGameStatistics() {

        HashMap<String, List<String>> estatisticaDoJogo = new HashMap<>();

        estatisticaDoJogo.put("os3ZombiesMaisTramados", zombiesTramado());
        estatisticaDoJogo.put("os3VivosMaisDuros", vivosDuros());
        estatisticaDoJogo.put("tiposDeEquipamentoMaisUteis", equipamentosUteis());
        estatisticaDoJogo.put("tiposDeZombiesESeusEquipamentosDestruidos", tiposZombiesEEquipamentosDestruidos());
        estatisticaDoJogo.put("criaturasMaisEquipadas", criaturasMaisEquipamentosApanharam());

        return estatisticaDoJogo;
    }

    /* <IDCriatura>:<Nome>:<NrTransformações> */
    /* Quais os 3 zombies que mais vivos transformaram? */
    private List<String> zombiesTramado() {

        List<String> creturesZombies;

        creturesZombies = getCreatures().stream()
                   /* Filtrar apenas os vivos */
                   .filter((zombiesSemPiedade) -> zombiesSemPiedade.getIdEquipa() == 20)
                   /* Filtrar zombies que tiverem pelo menos 1 ou mais transformacoes */
                   .filter((t) -> t.getNumTransformacoesFeitasPorZombies() >= 1)
                   /* Ordena por ordem decrescente */
                   .sorted ((z2, z1) -> z1.getNumTransformacoesFeitasPorZombies() - z2.getNumTransformacoesFeitasPorZombies())
                   /* Top 3 */
                   .limit(3)
                   /* Transforma os elementos em string */
                   .map( (c) -> c.getId() + ":" + c.getNome() + ":" + c.getNumTransformacoesFeitasPorZombies() + "\n")
                   /* Transforma o resultado final em lista */
                   .collect(toList());

        return creturesZombies;
    }

    /* <IDCriatura>:<Nome>:<NrDestruicoes> */
    /* Quais os 3 vivos que mais zombies destruiram */
    private List<String> vivosDuros() {

        List<String> creaturesVivos;

        creaturesVivos = getCreatures().stream()
                /* Filtrar apenas os vivos */
                .filter((vivosSemMedo) -> vivosSemMedo.getIdEquipa() == 10)
                /* Filtrar os vivos que têm pelo menos um zombie destruidos */
                .filter((destruidos) -> destruidos.getZombiesDestruidos() >= 1)
                /* Ordena por ordem descendente */
                .sorted ((v2, v1) -> v1.getZombiesDestruidos() - v2.getZombiesDestruidos())
                /* Top 3 */
                .limit(3)
                /* Transforma os elementos em string */
                .map( (c) -> c.getId() + ":" + c.getNome() + ":" + c.getZombiesDestruidos() + "\n")
                /* Transforma o resultado final em lista */
                .collect(toList());

        return creaturesVivos;
    }

    /* <ID>:<NrSalvacoes> */
    /* Quais os equipamentos que mais safaram os vivos (of/def)? */
    private List<String> equipamentosUteis() {

        List<String> equipamentoQueSafaram;

        equipamentoQueSafaram = equipamentos.stream()
                /* filtrar apenas os equipamentos que salvaram */
                .filter((eq) -> eq.getNrSalvacoes() > 0)
                // ordena por ordem ascendente
                .sorted((eq2,eq1) -> eq1.getNrSalvacoes() - eq2.getNrSalvacoes())
                /* Transforma o resultado em strings */
                .map((eq)-> eq.getId() +":"+ eq.getNrSalvacoes())
                /* transforma o resultado final em formato de lista */
                .collect(toList());

        return equipamentoQueSafaram;
    }

    /* TODO falta implementar o desempate e o numero de equipamentos */
    /* <Nome do Tipo>:<ID_Zombies>:<NrEquipamentos> */
    /* Qual o total de equipamentos destruidos por cada tipo de zombie? */
    private List<String> tiposZombiesEEquipamentosDestruidos() {

        List<String> zombieScore;

        zombieScore = getCreatures().stream()
                /* filtrar as criaturas zombies */
                .filter((zo) -> zo.getIdEquipa() == 20)
                /* filtrar os zombies que não foram destruidos */
                .filter((zomb) -> !zomb.zombieIsDestroyed())
                /* recolher os nomes do zombies e contar o numeros de zombies do mesmo tipo em jogo
                e converter num conjunto de dados */
                .collect(Collectors.groupingBy(Creature::getTipo, Collectors.counting()))
                /* O entrySet vai retornar o que está contido no collect, um set contido no mapa
                 e permitir obter chaves e valores contidos nesse map */
                .entrySet().stream()
                /* Ordenar do tipo que mais destruiu para o tipo que menos destruiu */
                .sorted((e1,e2)-> (int)(e2.getValue() - e1.getValue()))
                /* Transforma o resultado em strings */
                .map((z) -> z.getKey() +":"+ z.getValue())
                /* transforma o resultado final em formato de lista */
                .collect(toList());

    return zombieScore;

    }

    /* <IDCriatura>:<Nome>:<NrEquipamentos> */
    /* Quais as 5 criaturas que mais equipamentos apanharam/destruitam */
    private List<String> criaturasMaisEquipamentosApanharam() {

        List<String> osEquipados;

        osEquipados = getCreatures().stream()
                /* filtrar as criaturas que estão em jogo */
                .filter((criaturas) -> !criaturas.isInSafeHaven() && !criaturas.isTransformado()
                && !criaturas.isEnvenenado() && !criaturas.zombieIsDestroyed())
                /* Ordenar por ordem decrescente do numero de equipamentos */
                .sorted ((v2, v1) -> v1.getEquipamentosNoBolso() - v2.getEquipamentosNoBolso())
                /* Mostrar apenas 5 criaturas que mais equipamentos apanharam/destruiram */
                .limit(5)
                /* Transforma o resultado em strings */
                .map( (c) -> c.getId() + ":" + c.getNome() + ":" + c.getEquipamentosNoBolso())
                /* transforma o resultado final em formato de lista */
                .collect(toList());

        return osEquipados;
    }

    public void incrementarTurno() {
        nrTurnoDoVeneno++;
        nrTurno++;

        switch (idEquipaAtual) {
            case 10:
                idEquipaAtual = 20;
                break;
            case 20:
                idEquipaAtual = 10;
                break;
        }
    }

    public void incrementarReset() {

        creatures = new ArrayList<>(); // resent da lista de creatures.
        equipamentos = new ArrayList<>(); // reset da lista de equipamentos
        safe = new ArrayList<>(); // reset da lista safeHaven
        criaturasEnvenenadas = new ArrayList<>(); // reset da lista de criaturas envenedadas
        equipamentosEncontrados = new ArrayList<>(); // reset da lista de equipamentos encontrados
        zombiesDestruidos = new ArrayList<>(); // reset da lista de zombies destruidos
        portasEmJogo = new ArrayList<>();

        numLinha = 0; // reset variavel numLinha.
        numColuna = 0; // reset variavel numColuna.
        xPortas = 0; // reset variavel xPortas safeHaven.
        yPortas = 0; // reset variavel yPortas safeHaven.
        nrTurno = 0; // reset variavel turnos do jogo.
        nrTurnoDoVeneno = 0; // reset variavel de turnos quando o vivo apanha o equipamento veneno
        turnoDeTransformacoes = 0; // reset para reducao de turnos de forma a dar continuidade ao jogo
    }
}