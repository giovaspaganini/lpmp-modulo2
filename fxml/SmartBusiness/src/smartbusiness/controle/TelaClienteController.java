/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartbusiness.controle;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import smartbusiness.modelo.ClienteDAO;

/**
 * FXML Controller class
 *
 * @author L
 */
public class TelaClienteController implements Initializable {

    @FXML
    private TextField tfNome;
    @FXML
    private TextField tfCPF;
    @FXML
    private TextField tfLogradouro;
    @FXML
    private TextField tfBairro;
    @FXML
    private TextField tfCidade;
    @FXML
    private TextField tfEstado;
    @FXML
    private TableView<EnderecoCliente> tvEnderecos;
    @FXML
    private TableColumn<EnderecoCliente, String> tcLogradouro;
    @FXML
    private TableColumn<EnderecoCliente, String> tcBairro;
    @FXML
    private TableColumn<EnderecoCliente, String> tcCidade;
    @FXML
    private TableColumn<EnderecoCliente, String> tcEstado;
    @FXML
    private ListView<Cliente> lvClientes;
    @FXML
    private Button btInserir;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            ObservableList clientes = FXCollections.observableArrayList(ClienteDAO.retrieveAll());
            lvClientes.setItems(clientes);
                    
        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(TelaClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
        //essa tecnica faz uma ligacao entre o atributo do objeto EnderecoCliente logradouro e a celula dessa coluna
        tcLogradouro.
            setCellValueFactory(
                   new PropertyValueFactory<>("logradouro"));

        tcBairro.
            setCellValueFactory(
               new PropertyValueFactory<>("bairro"));

        
        tcCidade.
            setCellValueFactory(
               new PropertyValueFactory<>("cidade"));

        tcEstado.
            setCellValueFactory(
               new PropertyValueFactory<>("estado"));

        
    }    

    @FXML
    private void salvarEndereco(ActionEvent event) {
        
        if (tvEnderecos.getSelectionModel().isEmpty()){
            EnderecoCliente aux = 
            new EnderecoCliente(
                    tfLogradouro.getText(),
                    tfBairro.getText(),
                    tfCidade.getText(),
                    tfEstado.getText(),
                    "","");

            tvEnderecos.getItems().add(aux);
        }else{
           EnderecoCliente eaux = tvEnderecos.getSelectionModel().getSelectedItem();
           
           eaux.setLogradouro(tfLogradouro.getText());
           eaux.setBairro(tfBairro.getText());
           eaux.setCidade(tfCidade.getText());
           eaux.setEstado(tfEstado.getText());
           
           tvEnderecos.getSelectionModel().clearSelection();
           tvEnderecos.refresh();
        }
        limpaEndereco();
                
    }

    @FXML
    private void salvar(ActionEvent event) {
        Cliente c;
        if (lvClientes.getSelectionModel().isEmpty()){
            c = new Cliente(
                    tfNome.getText(),
                    tfCPF.getText());
            
        } else {
            c = lvClientes.getSelectionModel().getSelectedItem();
        }
        
        for (EnderecoCliente eaux : tvEnderecos.getItems()){
            if (eaux.getPk_endereco() == 0){
                c.getEndereco().add(eaux);
            }
               
        }

        try {
            if (c.getPk()==0){
               ClienteDAO.create(c);
            } else {
               ClienteDAO.update(c);
            }
            
            limpaTela();
            lvClientes.getItems().add(c);
            lvClientes.refresh();
            
        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(TelaClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void cancelar(ActionEvent event) {
    }

    @FXML
    private void inserir(ActionEvent event) {
        lvClientes.getSelectionModel().clearSelection();
        limpaTela();
        btInserir.setDisable(true);
    }
    
    private void limpaEndereco(){
        tfLogradouro.clear();
        tfCidade.clear();
        tfBairro.clear();
        tfEstado.clear();
    }
    
    private void limpaTela(){
        tfNome.clear();
        tfCPF.clear();
        limpaEndereco();
        tvEnderecos.getItems().clear();
    }

    @FXML
    private void alteraClienteSelecionado(MouseEvent event) {
        Cliente c = lvClientes.getSelectionModel().getSelectedItem();
        
        tfNome.setText(c.getNome());
        tfCPF.setText(c.getCpf());
        
        tvEnderecos.setItems(
           FXCollections.observableArrayList(c.getEndereco())
        );
    }

    @FXML
    private void alteraEndereco(MouseEvent event) {
        tfLogradouro.setText(
            tvEnderecos.
            getSelectionModel().
            getSelectedItem().getLogradouro());
        
            tfBairro.setText(
            tvEnderecos.
            getSelectionModel().
            getSelectedItem().getBairro());
            
            tfCidade.setText(
            tvEnderecos.
            getSelectionModel().
            getSelectedItem().getCidade());

            tfEstado.setText(
            tvEnderecos.
            getSelectionModel().
            getSelectedItem().getEstado());
    }
    
}
