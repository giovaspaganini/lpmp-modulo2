/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartbusiness.controle;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import smartbusiness.modelo.CargoDAO;

/**
 * FXML Controller class
 *
 * @author Giovani Paganini <giovanipaganini@outlook.com>
 */
public class TelaCargoController implements Initializable {
    
    @FXML
    private TextArea taDescricao;

    @FXML
    private TextField tfNome;

    @FXML
    void cancelar(ActionEvent event) {
        limpaTela();
    }

    @FXML
    void salvar(ActionEvent event) {
        Cargo c = new Cargo(
                tfNome.getText(),
                taDescricao.getText()       
        );
        
        try {
            CargoDAO.create(c);
            limpaTela();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.
        }
    }
    
    private void limpaTela() {
        tfNome.clear();
        taDescricao.clear();
    }
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
