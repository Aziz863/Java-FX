package sample;

import java.util.Random;

public class GuessNumber {

    protected int numeroAleatori; // Número aleatori generat
    private int minValor; // valor mínim que pot tenir l'aleatori --final no canvia valor--
    private int maxValor; // valor màxim que pot tenir l'aleatori
    protected String missatge; // Guardarà el missatge que es  mostrarà a l'usuari cada cop que es comprovi un número
    private int intents = 0; //contador d'intents

    // Getters per als atributs que poden ser consultats des de fora de la classe
    public int getMaxValor() {
        return maxValor;
    }

    public int getMinValor() {
        return minValor;
    }

    public String getMissatge() {
        return missatge;
    }

    public int getIntents() {
        return intents;
    }

    public void setIntents() {
        intents = 0;
    }

    /**
     * Mètode generarAleatori
     *
     * @return retorna un número comprès entre les atributs minValor i maxValor
     */
    private int generarAleatori() {
        Random generador = new Random();
        int numero = generador.nextInt(this.maxValor - this.minValor) + this.minValor;
        return numero;
    }

    /**
     * Constructor per posar valors a minim i maxim
     * D'aquesta forma podem fer que endevinar el número ens consti més intents!
     */
    public GuessNumber(int minim, int maxim) {
        minValor = minim;
        maxValor = maxim;
        numeroAleatori = generarAleatori();
        missatge = "Endevina un número entre " + minValor + " i " + maxValor;
    }

    /**
     * Mètode comprova, mira si s'ha encertat el número passat per paràmetre
     *
     * @param numero: Número que volem comprovar si hem encertat o no.
     * @return Si no l'hem encertat retorna false. Si l'hem encertat retorna true.
     * En missatge s'hi guardarà "has guanyat, el número cercat era ..", "El numero que he pensat és més petit ..."
     * o "El núemero que he pensat  és més gran..."
     */
    public boolean comprova(int numero) {

        //Si encerta retorna true
        if (numero == numeroAleatori) {
            missatge = "Has encertat. \nClica RESTART per jugar de nou.";
            return true;
        }

        //Si falla, suma intents i retorna el missatge per pantalla, retorna fals
        intents++;
        if (numero > numeroAleatori) {
            missatge = "El número secret és més petit. \nIntents restants: " + (5 - intents);
        } else {
            missatge = "El número secret és més gran. \nIntents restants: " + (5 - intents);
        }

        return false;
    }

    /**
     * Mètode reiniciar()
     * Permet regenerar el número aleatori per si es vol tornar a jugar
     */
    public void reiniciar() {
        this.numeroAleatori = generarAleatori();
        missatge = "Joc Nou: He pensat un número entre " + minValor
                + " i " + maxValor;
    }

}