/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartbusiness.visao;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import smartbusiness.modelo.CargoDAO;
import smartbusiness.negocio.Cargo;

/**
 *
 * @author L
 */
public class TelaCargo extends JFrame{

    private JTextField tfNome = new JTextField(10);
    private JLabel lbMsg = new JLabel(); 
    private JComboBox<String> cbSexo = new JComboBox<>();
    private JComboBox<Cargo> cbCargos;
    
    private JCheckBox cxMaioridade = new JCheckBox("Sou maior de 18 anos");
    private JRadioButton rbZonaRural = new JRadioButton("Zona Rural");
    private JRadioButton rbZonaUrbana = new JRadioButton("Zona Urbana");
    private JRadioButton rbZonaCosmopolitana = new JRadioButton("Zona Cosmopolitana");    
    
    private JTextArea taSeuComentario = new JTextArea(5, 30);
        
    public TelaCargo() throws HeadlessException, SQLException {
        super("Cadatro/Alteração de Cargos");
        this.setLayout(new FlowLayout());
        
        Container p = this.getContentPane();
        
//        cbSexo.addItem("Masculino");
//          cbSexo.addItem("Feminino");
         
        cbCargos = new JComboBox<Cargo>();
        
        ArrayList<Cargo> aux = CargoDAO.retrieveAll(4000,6000);
        for (Cargo c:aux){
            cbCargos.addItem(c);
        }
        
        p.add(cbCargos);

        

        
        p.add(new JLabel("Nome:"));

        p.add(tfNome);
        
        p.add(cbSexo);
        
        p.add(cxMaioridade);
        
       //inserção dos componentes no conteiner (painel)
        p.add(rbZonaRural);
        p.add(rbZonaUrbana);
        p.add(rbZonaCosmopolitana);
        
        ButtonGroup bgZona = new ButtonGroup();
        //agrupamento lógico, não aparece na tela
        bgZona.add(rbZonaRural);
        bgZona.add(rbZonaUrbana);
        bgZona.add(rbZonaCosmopolitana);
        
        p.add(taSeuComentario);
        
        JButton btnOk = new JButton("Me Aperte!");
        p.add(btnOk);
               
        p.add(lbMsg);
        
        btnOk.addActionListener(
           new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 lbMsg.setText(
                         Double.toString(((Cargo)cbCargos.getSelectedItem()).getGratificacao()));
                 
                 
            }
        }
        
        
        
        );
        
        
        
        
        this.setSize(400,400);      
        this.setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE               
        );
        
        
        //this.setVisible(true);
    }
        
}
