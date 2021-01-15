package pt.ulusofona.lp2.theWalkingDEISIGame;

import java.util.Arrays;

public class InvalidTWDInitialFileException extends Exception {

    String[] dados;
    boolean maisQue2Creatures = true;

    public InvalidTWDInitialFileException(String[] dados) {
        super("Numero erraodo de componentes: " + dados.length);
    }

    public InvalidTWDInitialFileException(boolean validCreature) {
        super("Erro: ficheiro com menos do que 2 criaturas");
        maisQue2Creatures = validCreature;
    }

    // Este método deve devolver true se e só
    // se o ficheiro tiver pelo menos 2 criaturas
    public boolean validNrOfCreatures() {
        return maisQue2Creatures;
    }

    // Este método deve devolver true se e só
    // se o ficheiro tiver os dados completos para todas as
    //criaturas
    public boolean validCreatureDefinition() {
        return dados.length == 5;
    }

    // Este método deve devolver a primeira linha que descreve uma criatura que tenha dados a
    //menos ou a mais
    public String getErroneousLine() {
        return Arrays.toString(dados);
    }

}
