/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIs;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 *
 * @author aninh
 */
public class MenuGUI extends JFrame {

    private Container cp;
    private JButton btProdutos = new JButton("Produtos");
    private JButton btMarcas = new JButton("Marcas");
    private JButton btPlataformasgames = new JButton("Plataformas de jogos");
   

    public MenuGUI() {
        cp = getContentPane();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("Menu");

        cp.setLayout(new GridLayout(3, 1));

        cp.add(btProdutos);
        cp.add(btMarcas);
        cp.add(btPlataformasgames);
       

        btProdutos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ProdutosGUI produtosGUI = new ProdutosGUI();
                } catch (ParseException ex) {
                    Logger.getLogger(MenuGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        });
        
        btMarcas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    MarcaGUI marcaGUI = new MarcaGUI();
                } catch (ParseException ex) {
                    Logger.getLogger(MenuGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        });
        
        btPlataformasgames.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    PlataformasGamesGUI plataformasGamesGUI = new PlataformasGamesGUI();
                } catch (ParseException ex) {
                    Logger.getLogger(MenuGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        });
        
       
        setSize(350, 200);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}

