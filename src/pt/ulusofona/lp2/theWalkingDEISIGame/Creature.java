package pt.ulusofona.lp2.theWalkingDEISIGame;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class Creature {
    protected int id;
    protected int idTipo;
    protected int idEquipa;
    protected String nome;
    protected String tipo;
    protected String equipa;
    protected int xAtual;
    protected int yAtual;
    protected boolean isEnvenenado;
    protected boolean isInSafeHaven;
    protected boolean transformado;
    protected boolean isDestroyed;
    protected boolean deadPorEnvenenamento;
    protected boolean zombieIsRIP;
    protected int creaturesNoBolso = 0;
    protected int equipamentosNoBolso = 0;
    protected int zombiesDestruidos = 0;
    protected int countTransformacoesFeitas = 0;

    protected ArrayList<Equipamento> equipamentos = new ArrayList<>();
    protected ArrayList<Equipamento> destruidos = new ArrayList<>();

    protected static HashMap<String, Integer> equipamentosDestruidosByZombies = new HashMap<>();
    protected static int totalCreaturesEmJogo = 0;
    protected static int totalCreaturesVivas = 0;
    protected static int totalCreaturesZombies = 0;
    protected static int countTodosMenosOIdoso = 0;
    protected static int countTodosMenosZombieVampiro = 0;

    public Creature(int id, int idTipo, String nome, int xAtual, int yAtual) {
        this.id = id;
        this.idTipo = idTipo;
        this.nome = nome;
        this.xAtual = xAtual;
        this.yAtual = yAtual;

        totalCreaturesEmJogo++;

        if (idTipo == 0 || idTipo == 1 || idTipo == 2 || idTipo == 3 || idTipo == 4 || idTipo == 13) {
            totalCreaturesZombies++;
        } else {
            totalCreaturesVivas++;
        }

    }

    public static void incrementCreatures() { totalCreaturesEmJogo++; }
    public static void incrementCreaturesVivas() { totalCreaturesVivas++; }
    public static void incrementCreaturesZombies() { totalCreaturesZombies++; }

    public static void decrementCreatures() { totalCreaturesEmJogo--; }
    public static void decrementCreaturesVivas() { totalCreaturesVivas--; }
    public static void decrementCreaturesZombies() { totalCreaturesZombies--; }

    abstract protected boolean processarCombateOfensivo(int xO, int yO, int xD, int yD, Creature creatureDestino, ArrayList<Creature> creatures);

    abstract public boolean moveDirecao(int xO, int yO, int xD, int yD, Creature creatureDestino);

    abstract public boolean moveDirecaoTurnosPares(int xO, int yO, int xD, int yD, Creature creatureDestino);

    abstract public boolean moveDirecaoTurnosImpares(int xO, int yO, int xD, int yD, Creature creatureDestino);

    abstract public int getId();

    abstract public int getIdEquipa();

    abstract public String getNome();

    abstract public int getIdTipo();

    abstract public void setIdTipo(int idTipo);

    abstract public void setIdEquipa(int idEquipa);

    abstract public int getXAtual();

    abstract public int getYAtual();

    abstract public void setxAtual(int xAtual);

    abstract public void setyAtual(int yAtual);

    abstract public ArrayList<Equipamento> getEquipamentosVivos();

    abstract public void setTipo(int idTipo);

    abstract public void setEquipa(int idTipo);

    abstract public void transformaEmZombie(Creature creatureDestino);

    abstract public int getEquipamentosNoBolso();

    abstract public int countZombiesDestruidos();

    abstract public int getZombiesDestruidos();

    abstract public void incrementaCreaturesNoBolso();

    abstract public void incrementaEquipamentosNoBolso();

    abstract public void incrementaSemEquipamentoDepoisDeTransformado(int vivoTransformadoSemEquipamento);

    abstract public boolean isInSafeHaven();

    abstract public void inSafeHaven(boolean inSafeHaven);

    abstract public boolean isEnvenenado();

    abstract public void setEnvenenado(boolean envenenado);

    abstract public void countTransformacoesFeitasPorZombies();

    abstract public int getNumTransformacoesFeitasPorZombies();

    abstract public boolean isTransformado();

    abstract public void setTransformado (boolean criaturaTransformada);

    abstract public boolean zombieIsDestroyed();

    abstract public void setZombieIsDestroyed(boolean criaturaZombieDestruida);

    abstract public void setZombieIsRIP(boolean zombieArrivadeci);

    abstract public boolean humanDeadPorEnvenenamento();

    abstract public void setHumanDeadPorEnvenenamento(boolean criaturaIsDead);

    abstract public String getTipo();

    abstract public String getImagePNG();

    abstract public String toString();

}