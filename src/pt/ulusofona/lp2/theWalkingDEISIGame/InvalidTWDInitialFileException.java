package pt.ulusofona.lp2.theWalkingDEISIGame;

public class InvalidTWDInitialFileException extends Exception {

    boolean creaturesDefinition;
    int nrCreatures;
    String linhaCreatures;

    public InvalidTWDInitialFileException(int nrCreatures, boolean creaturesDefinition, String linhaCreatures) {
        this.nrCreatures = nrCreatures;
        this.creaturesDefinition = creaturesDefinition;
        this.linhaCreatures = linhaCreatures;
    }

    // Este método deve devolver true se e só
    // se o ficheiro tiver pelo menos 2 criaturas
    public boolean validNrOfCreatures() {
        return nrCreatures >= 2;
    }

    // Este método deve devolver true se e só
    // se o ficheiro os dados completos para todas as
    // criaturas
    public boolean validCreatureDefinition() {
        return creaturesDefinition;
    }

    // Este método deve devolver a primeira linha que descreve uma criatura que tenha dados a
    //menos ou a mais
    public String getErroneousLine() {
        return linhaCreatures;
    }
}
