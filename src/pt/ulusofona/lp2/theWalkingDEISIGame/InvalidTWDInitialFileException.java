package pt.ulusofona.lp2.theWalkingDEISIGame;

import java.util.Arrays;

public class InvalidTWDInitialFileException extends Exception {

    private boolean conteudoErrado;

    private int creatutesDefinition;
    private int dados;
    private int linhaCreatures;
    private int nrCreatures;

    public InvalidTWDInitialFileException( int nrCreatures) {
        this.nrCreatures = nrCreatures;
    }

    public InvalidTWDInitialFileException(int linhaCreatures, int creatutesDefinition, int dados) {
        this.linhaCreatures = linhaCreatures;
        this.creatutesDefinition = creatutesDefinition;
        this.dados = dados;
    }

    // Este método deve devolver true se e só
    // se o ficheiro tiver pelo menos 2 criaturas
    public boolean validNrOfCreatures() {
        if (nrCreatures < 2) {
            return true;
        } else {
            return true;
        }
    }

    // Este método deve devolver true se e só
    // se o ficheiro os dados completos para todas as
    // criaturas
    public boolean validCreatureDefinition() {
        return dados == 5;
    }

    // Este método deve devolver a primeira linha que descreve uma criatura que tenha dados a
    //menos ou a mais
    public String getErroneousLine() {
        if (dados > creatutesDefinition) {
            return "Dados a mais ( esperava criatura com: " + creatutesDefinition + " dados " + "mas obtive " + dados + " dados" +" )."
                    + " Na linha " + linhaCreatures + " os dados estam errados.";
        } else if (dados < creatutesDefinition){
            return "Dados a menos ( esperava criatura com " + creatutesDefinition + " dados " + "mas obtive " + dados + " dados" +" )."
                    + " Na linha " + linhaCreatures + " os dados estam errados.";
        } else {
            return "";
        }
    }

    public int getCreatutesDefinition() {
        return creatutesDefinition;
    }

    public int getDados() {
        return dados;
    }

    public int getLinhaCreatures() {
        return linhaCreatures;
    }

    public boolean isMaisQue2Creatures() {
        return conteudoErrado;
    }

    public int getNrCreatures() {
        return nrCreatures;
    }
}
