/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartbusiness.controle;

import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
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
import smartbusiness.modelo.VendaDAO;

public class TelaVendaController implements Initializable {

    @FXML
    private TextField tfNumeroVenda;

    @FXML
    private TextField tfDataVenda;

    @FXML
    private TextField tfProduto;

    @FXML
    private TextField tfQtd;

    @FXML
    private TextField tfValor;

    @FXML
    private TableView<VendaItem> tvVendasItens;

    @FXML
    private TableColumn<VendaItem, Integer> tcProduto;

    @FXML
    private TableColumn<VendaItem, Integer> tcQtd;

    @FXML
    private TableColumn<VendaItem, Double> tcValor;

    @FXML
    private ListView<Venda> lvVendas;

    @FXML
    private Button btInserir;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            ObservableList vendas = FXCollections.observableArrayList(VendaDAO.retrieveAll());
            lvVendas.setItems(vendas);

        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(TelaVendaController.class.getName()).log(Level.SEVERE, null, ex);
        }

        tcProduto.setCellValueFactory(new PropertyValueFactory<>("produto"));
        tcQtd.setCellValueFactory(new PropertyValueFactory<>("qtd"));
        tcValor.setCellValueFactory(new PropertyValueFactory<>("valorUnitario"));

    }

    @FXML
    private void alteraVenda(MouseEvent event) {
        tfProduto.setText(Integer.toString(
                tvVendasItens.
                getSelectionModel().
                getSelectedItem().getFkProduto()));

        tfQtd.setText(Integer.toString(
                tvVendasItens.
                getSelectionModel().
                getSelectedItem().getQtd()));

        tfValor.setText(Double.toString(tvVendasItens.
                getSelectionModel().
                getSelectedItem().getValorUnitario()));

    }

    @FXML
    private void alteraVendaSelecionada(MouseEvent event) {
        Venda v = lvVendas.getSelectionModel().getSelectedItem();
        
        tfNumeroVenda.setText(Integer.toString(v.getNumero()));
        tfDataVenda.setText(v.getData().toString());
        
        tvVendasItens.setItems(
           FXCollections.observableArrayList(v.getItens())
        );

    }

    @FXML
    private void cancelar(ActionEvent event) {

    }

    @FXML
    private void inserir(ActionEvent event) {

        lvVendas.getSelectionModel().clearSelection();
        limpaTela();
        btInserir.setDisable(true);

    }

    @FXML
    private void salvar(ActionEvent event) {

        Venda v;
        if (lvVendas.getSelectionModel().isEmpty()) {
            v = new Venda(
                    tfNumeroVenda.getText(),
                    tfDataVenda.getText());

        } else {
            v = lvVendas.getSelectionModel().getSelectedItem();
        }

        for (VendaItem eaux : tvVendasItens.getItems()) {
            if (eaux.getPk() == 0) {
                v.getItens().add(eaux);
            }

        }
        try {
            if (v.getPk() == 0) {
                VendaDAO.create(v);
            } else {
                VendaDAO.update(v);
            }

            limpaTela();
            lvVendas.getItems().add(v);
            lvVendas.refresh();

        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(TelaVendaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void limpaTela() {
        tfNumeroVenda.clear();
        tfNumeroVenda.clear();
        limpaVenda();
        tvVendasItens.getItems().clear();

    }

    @FXML
    private void salvarVenda(ActionEvent event) {
        
        
    }

    private void limpaVenda() {
        tfProduto.clear();
        tfQtd.clear();
        tfValor.clear();
    }

}
