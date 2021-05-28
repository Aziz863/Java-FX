package sample;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Caixer {

    private int usuariActiu; // posició de l'usuari que s'ha connectat
    private String missatge; // guarda els missatges que es mostraran a l'usuari actiu

    // array amb la informació dels usuaris: nom, password, saldo
    private Client[] clients = {new Client("alopez", "aaa", 1900),
            new Client("blopez", "bbb", 100),
            new Client("clopez", "ccc", 5200)
    };
    // bitllets que disposa el caixer: només bitllets de 10, 20 i 50 euros
    private int[] bitllets = {50, 20, 10};

    // constructor
    public Caixer() {
        // posa l'usuari actiu a una posició incorrecta
        usuariActiu = -1;
        missatge = "Caixer operatiu";
    }

    // Getter atribut  missatge
    public String getMissatge() {
        return missatge;
    }

    /**
     * Comprova que el nom de l'usuari i el password siguin correctes
     * Després de n intents amb password incorrecte es bloqueja l'usuari.
     * Si està bloquejat ja no es podrà utilitzar el caixer!
     *
     * @param nom      nom de l'usuari que es comprova
     * @param password password de l'usuari que es vol comprovar
     * @return retorna cert si s'ha pogut validar correctament
     * En la variable missatge aquest mètode hi anota l'error que s'ha produït
     * En cas d'èxit es guarda en l'atribut usuariActiu la posició de l'usuari validat
     */
    boolean iniciaSessio(String nom, String password) {

        for (int i = 0; i < clients.length; i++) {
            if (clients[i].getNom().equals(nom)) {

                if (clients[i].isBloquejat()) {
                    missatge = "Usuari bloquejat";
                    return false;
                }
                if (clients[i].getPassword().equals(password)) {
                    missatge = "Nom d'usuari i password correcte";
                    usuariActiu = i;
                    return true;
                }
                clients[i].incrementaIntents();
                missatge = "Password incorrecte";
                return false;
            }
        }

        missatge = "Nom d'usuari incorrecte";
        return false;
    }


    /**
     * Segons una quantitat de diners per a retirar i si hi ha prou saldo
     * dedueix la quantitat retirada del saldo
     *
     * @param quantitat quantitat de diners a retirar
     * @return retorna cert si s'ha pogut extreure els diners.
     * En la variable missatge aquest mètode hi apunta les quantitats de bitllets que
     * es poden lliurar segons el tipus de bitllets disponibles
     */
    public boolean retirarDinersUsuari(double quantitat) {

        if (usuariActiu != -1) {
            missatge = quantitatsBitllets(quantitat);
            clients[usuariActiu].treureSaldo(quantitat);
            return true;
        }
        return false;
    }

    private String quantitatsBitllets(double quantitat) {
        String text = "";
        for (int i = 0; i < bitllets.length; i++) {
            int qbitllets = (int) quantitat / bitllets[i];
            text = text + " -Bitllets de " + bitllets[i] + ": " + qbitllets+"\n";
            quantitat = quantitat % bitllets[i];
        }

        return text;
    }

    /**
     * Afegeix al saldo de l'usuari actiu la quantitat indicada
     *
     * @param quantitat Quantitat de diners a ingressar
     */
    void ingressarDinersUsuari(double quantitat) {

        if (usuariActiu != -1) {
            clients[usuariActiu].incrementarSaldo(quantitat);
            missatge = "Diners ingressats";
        } else missatge = "No s'han pogut ingressar els diners";

    }

    /**
     * Retorna el nom de l'usuari actiu
     *
     * @return retorna "Error" en cas que no hi hagi usuari actiu
     */
    public String getNomUsuari() {

        if (usuariActiu != -1) {
            return clients[usuariActiu].getNom();
        }
        return "Error";

    }

    /**
     * Retorna el saldo de l'usuari actiu
     *
     * @return retorna la quantitat de diners de l'usuari actiu
     */
    public double consultaSaldoUsuari() {

        if (usuariActiu != -1) {
            return clients[usuariActiu].getSaldo();
        }
        return -1;

    }

    /**
     * Canvia el password de l'usuari actiu
     *
     * @param password Nova contrassenya
     */
    public void canviarClauUsuari(String password) {

        if (usuariActiu != -1) {
            clients[usuariActiu].setPassword(password);
        }
    }


}