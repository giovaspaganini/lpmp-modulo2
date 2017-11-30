/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartbusiness.controle;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import smartbusiness.modelo.CargoDAO;

/**
 * FXML Controller class
 *
 * @author Giovani Paganini <giovanipaganini@outlook.com>
 */
public class TelaCargoController implements Initializable {
    
   @FXML
    private TextField tfNome;

    @FXML
    private TextArea taDescricao;
    @FXML
    private ListView<Cargo> lvCargos;
    @FXML
    private Button btInserir;

    @FXML
    void cancelar(ActionEvent event) {
        limpaTela();
    }

    @FXML
    void salvar(ActionEvent event) {
        if (tfNome.getText().isEmpty()){
            throw new RuntimeException("Preencha os Campos obrigatórios");
        }
        
        boolean novo = lvCargos.getSelectionModel().getSelectedIndex()==-1;
        Cargo c = null;
        if (novo){
            c = new Cargo(
               tfNome.getText(),
               taDescricao.getText()        
            );
        } else c = lvCargos.getSelectionModel().getSelectedItem();
        
        try {
            if (novo){
                CargoDAO.create(c);
                lvCargos.getItems().add(c);
            } else {
                c.setNome(tfNome.getText());
                c.setDescricao(taDescricao.getText());
                CargoDAO.update(c);                
            }
            limpaTela();
        } catch (SQLException ex) {
            System.out.println("Essa é a msg de ERRO: " + ex.getMessage());
            Logger.getLogger(TelaCargoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void limpaTela(){
        tfNome.clear();
        taDescricao.clear();
        btInserir.setDisable(false);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            ArrayList<Cargo> cargos = CargoDAO.retrieveAll();
            
            lvCargos.getItems().addAll(cargos);
            
        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(TelaCargoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }    

    @FXML
    private void alteraCargoSelecionado(MouseEvent event) {
        Cargo aux = lvCargos.getSelectionModel().getSelectedItem();
        
        tfNome.setText(aux.getNome());
        taDescricao.setText(aux.getDescricao());        
    }

    @FXML
    private void inserir(ActionEvent event) {
        lvCargos.getSelectionModel().clearSelection();
        limpaTela();
        btInserir.setDisable(true);
    }    
    
}
