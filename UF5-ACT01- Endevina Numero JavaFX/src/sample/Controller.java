package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Controller {

    @FXML
    private Label main_label;
    @FXML
    private TextField text;
    @FXML
    private Button enter_button;
    @FXML
    private Label return_label;
    @FXML
    private Button reset_button;

    //Genera un número de la classe GuessNumber i el guarda el la variable joc
    private GuessNumber joc = new GuessNumber(1, 10);

    //Al iniciar mostra el primer label i amaga el segon
    @FXML
    public void initialize() {
        main_label.setText("Endevina un numero entre " + joc.getMinValor() + " i " + joc.getMaxValor() + "\nTens 5 intents, sort!");
        return_label.setVisible(false);
    }

    /**
     * Mètode que s'executa al fer clcick al botó
     */
    public void ferClick() {

        //Guardem el contingut del TextField amb id=text en la variable text_intro
        String text_intro = text.getText();

        /**
         * Si el text és enter el guardem el la variable num, el passem per paràmetre en el mètode comprova.
         * Fem visible el segon label i mostrem el missatge.
         * Netejem la zona del textField.
         * Si acabem els intents, ens ho mostra per pantalla.
         */
        try {
            int num = Integer.valueOf(text_intro);
            joc.comprova(num);
            return_label.setVisible(true);
            return_label.setText(joc.getMissatge());
            text.setText("");
            if (joc.getIntents() >= 5) return_label.setText("5 intents, has perdut. \nClica RESTART per jugar de nou.");

        } catch (Exception e) {
            return_label.setText("Ha de ser enter");
        }

    }

    /**
     * Mètode reiniciar()
     * Permet regenerar el número aleatori per si es vol tornar a jugar
     */
    public void reiniciar() {
        joc.reiniciar();
        //Tornem a amagar el segon label
        return_label.setVisible(false);
        //Reiniciem els intents a 0 amb el mètode setIntents.
        joc.setIntents();
    }

}
