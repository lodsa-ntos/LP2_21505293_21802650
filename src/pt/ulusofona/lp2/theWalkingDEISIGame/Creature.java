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

    protected static HashMap<String, Integer> equipamentosDestruidosByZombies = new HashMap<>();

    public Creature(int id, int idTipo, String nome, int xAtual, int yAtual) {
        this.id = id;
        this.idTipo = idTipo;
        this.nome = nome;
        this.xAtual = xAtual;
        this.yAtual = yAtual;
    }

    abstract protected boolean processarCombateOfensivo(int xO, int yO, int xD, int yD, Creature creatureDestino, ArrayList<Creature> creatures);

    abstract protected boolean moveDirecao(int xO, int yO, int xD, int yD, Creature creatureDestino);

    abstract protected boolean moveDirecaoTurnosPares(int xO, int yO, int xD, int yD, Creature creatureDestino);

    abstract protected boolean moveDirecaoTurnosImpares(int xO, int yO, int xD, int yD, Creature creatureDestino);

    abstract public int getId();

    abstract public int getIdEquipa();

    abstract public String getNome();

    abstract public int getIdTipo();

    abstract public void setIdTipo(int idTipo);

    abstract public void setIdEquipa(int idEquipa);

    abstract public int getXAtual();

    abstract public int getYAtual();

    abstract public void setXAtual(int xAtual);

    abstract public void setYAtual(int yAtual);

    abstract public ArrayList<Equipamento> getEquipamentosVivos();

    abstract public void setTipo(int idTipo);

    abstract public void setEquipa(int idTipo);

    abstract public void transformaEmZombie(Creature creatureDestino);

    abstract public int getEquipamentosNoBolso();

    abstract public void countZombiesDestruidos();

    abstract public int getZombiesDestruidos();

    abstract public void incrementaCreaturesNoBolso();

    abstract public void incrementaEquipamentosNoBolso();

    abstract public void incrementaSemEquipamentoDepoisDeTransformado(int vivoTransformadoSemEquipamento);

    abstract public boolean isInSafeHaven();

    abstract public void inSafeHaven(boolean inSafeHaven);

    abstract public boolean isEnvenenado();

    abstract public void drankVeneno(boolean envenenado);

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