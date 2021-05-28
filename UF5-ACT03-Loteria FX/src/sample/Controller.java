package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.Arrays;

public class Controller {

    //TextField
    @FXML
    private TextField aposta_1;
    @FXML
    private TextField aposta_2;
    @FXML
    private TextField aposta_3;
    @FXML
    private TextField aposta_4;
    @FXML
    private TextField aposta_5;
    @FXML
    private TextField aposta_6;
    @FXML
    private TextField resultat_1;
    @FXML
    private TextField resultat_2;
    @FXML
    private TextField resultat_3;
    @FXML
    private TextField resultat_4;
    @FXML
    private TextField resultat_5;
    @FXML
    private TextField resultat_6;

    //Button
    @FXML
    private Button button_sorteig;
    @FXML
    private Button button_comprovar;

    //Label
    @FXML
    private Label label_resultat;

    //Iniciem la classe loteria
    Loteria loteria = new Loteria();

    /**
     * Creem els números aleatoris amb el mètode sortejar de la classe Loteria
     * Deshabilitem el botó de realitzar sorteig i habilitem el de comprovar
     * Guardem les apostes de cada TextField en un array
     * Verifiquem les apostes i retornem el missatge pel label de sota
     */
    public void iniciar_sorteig() {
        loteria.sortejar();

      //Intentem guardar els textField en una array, si no són enters ens dona un error
        try {
            //Array per guardar apostes
            int[] apostes = {
                    Integer.parseInt(aposta_1.getText()),
                    Integer.parseInt(aposta_2.getText()),
                    Integer.parseInt(aposta_3.getText()),
                    Integer.parseInt(aposta_4.getText()),
                    Integer.parseInt(aposta_5.getText()),
                    Integer.parseInt(aposta_6.getText())};

            //Crida el mètode per verificar apostes
            loteria.setApostes(apostes);
            label_resultat.setText(loteria.missatge);

            //Si es fan les apostes correctamtn amagem el botó de sorteig i activem el de comprovar
            if (loteria.setApostes(apostes)) {
                button_sorteig.setDisable(true);
                button_comprovar.setDisable(false);
            }
        } catch (NumberFormatException e) {
            label_resultat.setText("Error. Introdueix 6 nombres enters");
        }
    }

    /**
     * Si el booleá setApostes és correcte, retornem el resultat al label
     */
    public void comprovar() {

        //Creem array per guardar els números generats aleatoriament
        int[] resultat_aleatoris = loteria.getAleatoris();

        //Mostrar en cada TextField de resultat un número aleatori de l'array
        resultat_1.setText(Integer.toString(resultat_aleatoris[0]));
        resultat_2.setText(Integer.toString(resultat_aleatoris[1]));
        resultat_3.setText(Integer.toString(resultat_aleatoris[2]));
        resultat_4.setText(Integer.toString(resultat_aleatoris[3]));
        resultat_5.setText(Integer.toString(resultat_aleatoris[4]));
        resultat_6.setText(Integer.toString(resultat_aleatoris[5]));

        //Imprimim al label de sota el resultat
        label_resultat.setText("Has encertat: " + loteria.numeroEncerts());
    }

}
