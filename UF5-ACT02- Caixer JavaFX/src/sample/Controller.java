package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class Controller {

    //Panels
    @FXML
    private Pane pane_sup;
    @FXML
    private Pane pane_inf;

    //TextField
    @FXML
    private TextField text_nom;
    @FXML
    private TextField text_pass;
    @FXML
    private TextField text_total;

    //Button Opcions
    @FXML
    private Button button_validar;
    @FXML
    private Button button_saldo;
    @FXML
    private Button button_ingres;
    @FXML
    private Button button_retirar;
    @FXML
    private Button button_exit;

    //Button Bitllets
    @FXML
    private Button button_10;
    @FXML
    private Button button_20;
    @FXML
    private Button button_50;

    //Label
    @FXML
    private Label label_main;
    @FXML
    private Label label_return;
    @FXML
    private Label label_bitllet;

    //Crea objecte caixer
    Caixer caixer = new Caixer();

    //Variable per guardar saldo acumulat amb els botons i contador de cada bitllet
    Double saldo_acumulat = 0.0;
    int bitllet10 = 0;
    int bitllet20 = 0;
    int bitllet50 = 0;

    /**
     * Al iniciar amagem el panel inferior i mostres el superior, així ens serà útil per al botó "sortir"
     */
    @FXML
    public void initialize() {
        pane_inf.setVisible(false);
        pane_sup.setVisible(true);
    }

    /**
     * Iniciar sessió amb el botó Validar
     */
    public void validar() {
        //Guardem el contingut dels TextField en una variable
        String nom = text_nom.getText();
        String contrasenya = text_pass.getText();

        //Passem el text del nom i la contrasenya pel mètode de iniciar sessió
        boolean correcte = caixer.iniciaSessio(nom, contrasenya);
        label_return.setText(caixer.getMissatge());

        //Si iniciem sessió correctament es mostra la part inferior i amaga la superior
        if (correcte) {
            pane_inf.setVisible(true);
            pane_sup.setVisible(false);
            label_main.setText("Benvingut " + caixer.getNomUsuari());
        }
    }

    /**
     * Primer botó, consulta saldo.
     * Mostrem el saldo en el label concatenant el double a un String sense contingut
     * per no haber de convertir el double en un String
     */
    public void consultaSaldo() {
        double saldo = caixer.consultaSaldoUsuari();
        label_main.setText("" + saldo);
    }

    /**
     * Sumem un bitllet al contador i 10€ als diners que retirarem o ingressarem
     */
    public void sumar10() {
        bitllet10++;
        saldo_acumulat = saldo_acumulat + 10;

        //Mostres pel TextField la quantitat de diners acumulada

        text_total.setText("" + saldo_acumulat);
    }

    /**
     * Sumem un bitllet al contador i 20€ als diners que retirarem o ingressarem
     */
    public void sumar20() {
        bitllet20++;
        saldo_acumulat = saldo_acumulat + 20;

        //Mostres pel TextField la quantitat de diners acumulada

        text_total.setText("" + saldo_acumulat);

    }

    /**
     * Sumem un bitllet al contador i 50€ als diners que retirarem o ingressarem
     */
    public void sumar50() {
        bitllet50++;
        saldo_acumulat = saldo_acumulat + 50;

        //Mostres pel TextField la quantitat de diners acumulada
        text_total.setText("" + saldo_acumulat);
    }

    /**
     * Retirem els diners acumulats amb el mètode del Caixer
     * Reiniciem tots els valors del saldo i bitllets a 0
     * Mostrem per pantalla la quantitat de bitllets
     */
    public void treure_diners() {
        caixer.retirarDinersUsuari(saldo_acumulat);
        label_main.setText("50 " + bitllet50 + "\n20 " + bitllet20 + "\n10 " + bitllet10);
        saldo_acumulat = 0.0;
        bitllet10 = 0;
        bitllet20 = 0;
        bitllet50 = 0;
    }

    /**
     * Ingressem els diners acumulats amb el mètode del Caixer
     * Reiniciem tots els valors del saldo i bitllets a 0
     * Mostrem per pantalla la quantitat de bitllets
     */
    public void ingressar_diners() {
        caixer.ingressarDinersUsuari(saldo_acumulat);
        label_main.setText("50 " + bitllet50 + "\n20 " + bitllet20 + "\n10 " + bitllet10);
        saldo_acumulat = 0.0;
        bitllet10 = 0;
        bitllet20 = 0;
        bitllet50 = 0;

    }

}
