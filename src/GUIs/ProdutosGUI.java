package GUIs;

import DAOs.DAOCds;
import DAOs.DAOGames;
import DAOs.DAOMarca;
import DAOs.DAOPlataformasgames;
import Entidades.Produtos;
import DAOs.DAOProdutos;
import DAOs.DAOSkates;
import Entidades.Cds;
import Entidades.Games;
import Entidades.Marca;
import Entidades.Plataformasgames;
import Entidades.Skates;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import javax.swing.BorderFactory;
import java.text.SimpleDateFormat;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.table.DefaultTableModel;
import tools.CaixaDeFerramentas;
import javax.swing.JCheckBox;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.util.Date;
import tools.CopiarArquivos;
import javax.swing.ImageIcon;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import tools.ManipulaArquivo;
import java.text.ParseException;

/**
 *
 * @author anamacowski 03/07/2023 - 01:04:27
 */
public class ProdutosGUI extends JDialog {
    
    Container cp;
    JPanel pnNorte = new JPanel();
    JPanel pnCentro = new JPanel();
    JPanel pnSul = new JPanel();
    JPanel pnLeste = new JPanel();
    JPanel pnOeste = new JPanel();

    //painéis das entidades que são o tipo do produto
    JPanel pnCds = new JPanel(new GridLayout(4, 2));
    JPanel pnGames = new JPanel(new GridLayout(6, 2));
    JPanel pnSkates = new JPanel(new GridLayout(3, 2));
    
    JButton btBuscar = new JButton("Buscar");
    JButton btAdicionar = new JButton("Adicionar");
    JButton btSalvar = new JButton("Salvar");
    JButton btAlterar = new JButton("Alterar");
    JButton btExcluir = new JButton("Excluir");
    JButton btListar = new JButton("Listar");
    JButton btCancelar = new JButton("Cancelar");
    String acao = "";

    //Checkbox para tipos de produto
    JCheckBox cbCds = new JCheckBox("CD");
    JCheckBox cbGames = new JCheckBox("Game");
    JCheckBox cbSkates = new JCheckBox("Skate");
    
    private JScrollPane scrollTabela = new JScrollPane();
    
    private JPanel pnAvisos = new JPanel(new GridLayout(1, 1));
    private JPanel pnListagem = new JPanel(new GridLayout(1, 1));
    private JPanel pnVazio = new JPanel(new GridLayout(6, 1));
    
    DefaultTableCellRenderer coluna1 = new DefaultTableCellRenderer();
    
    private CardLayout cardLayout;
    JLabel lbVazio = new JLabel("");

    //Produto - geral
    JLabel lbIdProduto = new JLabel("IdProduto");
    JTextField tfIdProduto = new JTextField(30);
    
    JLabel lbNomeProduto = new JLabel("NomeProduto");
    JTextField tfNomeProduto = new JTextField(60);
    
    JLabel lbQuantidadeProduto = new JLabel("QuantidadeProduto");
    JTextField tfQuantidadeProduto = new JTextField(30);

    //CRUD CDs
    JLabel lbGravadoraCd = new JLabel("Gravadora");
    JTextField tfGravadoraCd = new JTextField(60);
    
    JLabel lbAnoCd = new JLabel("Ano de lançamento");
    JTextField tfAnoCd = new JTextField(30);
    
    JLabel lbPrecoCd = new JLabel("Preço do CD");
    JTextField tfPrecoCd = new JTextField(30);
    
    DefaultComboBoxModel comboBoxModelPlat = new DefaultComboBoxModel();
    JComboBox cmbPlataformas = new JComboBox(comboBoxModelPlat);
    DefaultComboBoxModel comboBoxModelMarca = new DefaultComboBoxModel();
    
    JComboBox cmbBandaCd = new JComboBox(comboBoxModelMarca);
    JLabel lbBandaCd = new JLabel("Banda: ");
    
    

    //CRUD Games
    JLabel lbDesenvolvedorGm = new JLabel("Desenvolvedora");
    JTextField tfDesenvolvedorGm = new JTextField(60);
    
    JLabel lbAnoGm = new JLabel("Ano de lançamento");
    JTextField tfAnoGm = new JTextField(30);
    
    JLabel lbPrecoGm = new JLabel("Preço do Jogo");
    JTextField tfPrecoGm = new JTextField(30);
    
    JLabel lbPlataformas = new JLabel("Plataformas: ");
    JCheckBox cbXboxX = new JCheckBox("Xbox Series X");
    JCheckBox cbNintendoS = new JCheckBox("Nintendo Switch");
    JCheckBox cbPS5 = new JCheckBox("Playstation 5");
    JCheckBox cbAtari2600 = new JCheckBox("Atari 2600");

    //verificar isso, relação de games pra plataformas é 1:n
    //JComboBox cmbPlataforma = new JComboBox(comboBoxModel);
    //JLabel lbPlataforma = new JLabel("Banda: ");
    //----------------------PRECISO DAS INFOS HERDADADAS DA TABELA PLATAFORMA----------------------------------------------
//CRUD Skates
    JLabel lbNivelSkt = new JLabel("Nível do Skate");
    JTextField tfNivelSkt = new JTextField(50);
    
    JLabel lbPrecoSkt = new JLabel("Preço do Skate");
    JTextField tfPrecoSkt = new JTextField(30);
    
    JComboBox cmbMarcaSkt = new JComboBox(comboBoxModelMarca);
    JLabel lbMarcaSkt = new JLabel("Marca: ");
    

    //Importações
    DAOProdutos daoProdutos = new DAOProdutos();
    Produtos produtos = new Produtos();
    
    DAOCds daoCds = new DAOCds();
    Cds cds = new Cds();
    
    DAOGames daoGames = new DAOGames();
    Games games = new Games();
    
    DAOSkates daoSkates = new DAOSkates();
    Skates skates = new Skates();
    
    DAOMarca daoMarca = new DAOMarca();
    Marca marca = new Marca();
    
    DAOPlataformasgames daoPlataformasgames = new DAOPlataformasgames();
    Plataformasgames plataformasgames = new Plataformasgames();
    
    String[] colunas = new String[]{"idProduto", "nomeProduto", "quantidadeProduto"};
    String[][] dados = new String[0][colunas.length];
    
    DefaultTableModel model = new DefaultTableModel(dados, colunas);
    JTable tabela = new JTable(model);
    
    public ProdutosGUI() throws ParseException {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        CaixaDeFerramentas cf = new CaixaDeFerramentas();
        cp = getContentPane();
        cp.setLayout(new BorderLayout());
        setTitle("CRUD - Produtos");
        
        tabela.setEnabled(false);
        //tamanho da tabela
        tabela.setRowHeight(20);

        //cabeçalho
        tabela.getTableHeader().setBackground(Color.GRAY);
        tabela.getTableHeader().setForeground(new Color(0, 0, 0));

        //cor da linha da tabela
        tabela.setGridColor(new Color(0, 0, 0));
        
        cp.add(pnNorte, BorderLayout.NORTH);
        cp.add(pnCentro, BorderLayout.CENTER);
        cp.add(pnSul, BorderLayout.SOUTH);
        cp.add(pnLeste, BorderLayout.EAST);
        pnNorte.setBackground(new Color(56, 102, 65));
        pnCentro.setBorder(BorderFactory.createLineBorder(Color.black));
        
        pnNorte.setLayout(new FlowLayout(FlowLayout.LEFT));
        pnNorte.add(lbIdProduto);
        pnNorte.add(tfIdProduto);
        pnNorte.add(btBuscar);
        pnNorte.add(btAdicionar);
        pnNorte.add(btAlterar);
        pnNorte.add(btExcluir);
        pnNorte.add(btListar);
        pnNorte.add(btSalvar);
        pnNorte.add(btCancelar);
        
        btSalvar.setVisible(false);
        btAdicionar.setVisible(false);
        btAlterar.setVisible(false);
        btExcluir.setVisible(false);
        btCancelar.setVisible(false);
        
        tfNomeProduto.setEditable(false);
        tfQuantidadeProduto.setEditable(false);
        cbCds.setEnabled(false);
        cbGames.setEnabled(false);
        cbSkates.setEnabled(false);
        lbNomeProduto.setHorizontalAlignment(SwingConstants.CENTER);
        lbQuantidadeProduto.setHorizontalAlignment(SwingConstants.CENTER);
        
        cbCds.setHorizontalAlignment(SwingConstants.CENTER);
        cbGames.setHorizontalAlignment(SwingConstants.CENTER);
        cbSkates.setHorizontalAlignment(SwingConstants.CENTER);
        pnCentro.setLayout(new GridLayout(colunas.length + 1, 2));

        //Painel Produto
        pnCentro.setLayout(new GridLayout(1, 2));
        pnCentro.add(pnOeste);
        pnCentro.add(pnLeste);

        //Produto
        pnOeste.add(lbNomeProduto);
        pnOeste.add(tfNomeProduto);
        pnOeste.add(lbQuantidadeProduto);
        pnOeste.add(tfQuantidadeProduto);
        pnOeste.add(cbCds);
        pnOeste.add(cbGames);
        pnOeste.add(cbSkates);
        
        pnLeste.setLayout(new GridLayout(colunas.length, 1));
        pnLeste.add(pnSkates);
        pnLeste.add(pnGames);
        pnLeste.add(pnCds);

        /*Add paineis no painel leste
        pnLeste.setBorder(BorderFactory.createLineBorder(Color.black));
        pnLeste.add(pnCds);
        pnLeste.add(pnGames);
        pnLeste.add(pnSkates);*/
        //Cds
        pnCds.add(lbGravadoraCd);
        pnCds.add(tfGravadoraCd);
        pnCds.add(lbAnoCd);
        pnCds.add(tfAnoCd);
        pnCds.add(lbPrecoCd);
        pnCds.add(tfPrecoCd);
        pnCds.add(lbBandaCd);
        pnCds.add(cmbBandaCd);
        pnCds.setVisible(false);
        
        String[] listaMarca = daoMarca.listInOrderNomeStringsArray();
        for (String elemento : listaMarca) {
            cmbMarcaSkt.addItem(elemento);
        }

        //Games
        pnGames.add(lbDesenvolvedorGm);
        pnGames.add(tfDesenvolvedorGm);
        pnGames.add(lbAnoGm);
        pnGames.add(tfAnoGm);
        pnGames.add(lbPrecoGm);
        pnGames.add(tfPrecoGm);
        pnGames.add(lbPlataformas);
        pnGames.add(cbXboxX);
        pnGames.add(cbPS5);
        pnGames.add(cbNintendoS);
        pnGames.add(cbAtari2600);
        pnGames.setVisible(false);
        
        String[] listaPlataformas = daoPlataformasgames.listInOrderNomeStringsArray();
        for (String plat : listaPlataformas) {
            cmbPlataformas.addItem(plat);
        }

        //Skates
        pnSkates.add(lbNivelSkt);
        pnSkates.add(tfNivelSkt);
        pnSkates.add(lbPrecoSkt);
        pnSkates.add(tfPrecoSkt);
        pnSkates.add(lbMarcaSkt);
        pnSkates.add(cmbMarcaSkt);
        pnSkates.setVisible(false);
        
        cardLayout = new CardLayout();
        pnSul.setLayout(cardLayout);

        /*for (String elemento : listaMarca) {
            cmbMarca.addItem(elemento);
        }


        tfNome.setEditable(false);
        tfDataNascimento.setEditable(false);*/
        for (int i = 0; i < 5; i++) {
            pnVazio.add(new JLabel(" "));
        }
        pnSul.add(pnVazio, "vazio");
        pnSul.add(pnAvisos, "avisos");
        pnSul.add(pnListagem, "listagem");
        tabela.setEnabled(false);
        
        pnAvisos.add(new JLabel("Avisos"));

// listener Buscar
        btBuscar.addActionListener(new ActionListener() {
            CaixaDeFerramentas cdf = new CaixaDeFerramentas();
            
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(pnSul, "avisos");
                try {
                    produtos = daoProdutos.obter(Integer.valueOf(tfIdProduto.getText()));
                    if (produtos != null) {//achou o produtos na lista
                        //mostrar
                        btAdicionar.setVisible(false);
                        btAlterar.setVisible(true);
                        btExcluir.setVisible(true);
                        tfNomeProduto.setText(produtos.getNomeProduto());
                        tfNomeProduto.setEditable(false);
                        tfQuantidadeProduto.setText(String.valueOf(produtos.getQuantidadeProduto()));
                        tfQuantidadeProduto.setEditable(false);
                        
                        Cds cds = daoCds.obter(Integer.valueOf(tfIdProduto.getText()));
                        Games games = daoGames.obter(Integer.valueOf(tfIdProduto.getText()));
                        Skates skates = daoSkates.obter(Integer.valueOf(tfIdProduto.getText()));
                        
                        if (cds != null) {
                            cbCds.setSelected(true);
                            pnCds.setVisible(true);
                            tfGravadoraCd.setText(cds.getGravadoraCd());
                            tfGravadoraCd.setEditable(false);
                            tfAnoCd.setText(cdf.converteDeDateParaString(cds.getAnoCd()));
                            tfAnoCd.setEditable(false);
                            tfPrecoCd.setText(Double.toString(cds.getPrecoCd()));
                            tfPrecoCd.setEditable(false);
                            pnSkates.setVisible(false);
                            pnGames.setVisible(false);
                            pnCds.setVisible(true);
                            
                            int i = 0;
                            for (i = 0; i < listaMarca.length; i++) {
                                if (listaMarca[i].split(";")[0].equals(cds.getMarcaidMarca().toString().split(";")[0].concat("-").concat(cds.getMarcaidMarca().toString().split(";")[1]))) {
                                    break;
                                }
                            }
                            if (i < listaMarca.length) {
                                cmbBandaCd.setSelectedIndex(i);
                            }
                            
                            cmbBandaCd.setEnabled(false);
                        }
                        
                        if (games != null) {
                            cbGames.setSelected(true);
                            pnGames.setVisible(true);
                            tfDesenvolvedorGm.setText(games.getDesenvolvedorGm());
                            tfDesenvolvedorGm.setEditable(false);
                            tfAnoGm.setText(cdf.converteDeDateParaString(games.getAnoGm()));
                            tfAnoGm.setEditable(false);
                            tfPrecoGm.setText(Double.toString(games.getPrecoGm()));
                            tfPrecoCd.setEditable(false);
                            pnSkates.setVisible(false);
                            pnGames.setVisible(true);
                            pnCds.setVisible(false);
                            
                            int i = 0;
                            for (i = 0; i < listaPlataformas.length; i++) {
                                if (listaPlataformas[i].split(";")[0].equals(plataformasgames.getIdPlataforma().toString().split(";")[0].concat("-").concat(plataformasgames.getNomePlataforma().toString().split(";")[1]))) {
                                    break;
                                }
                            }
                            if (i < listaPlataformas.length) {
                                cmbPlataformas.setSelectedIndex(i);
                            }
                        }
                        
                        if (skates != null) {
                            System.out.println("aaa");
                            cbSkates.setSelected(true);
                            pnSkates.setVisible(true);
                            pnGames.setVisible(false);
                            pnCds.setVisible(false);
                            
                            tfNivelSkt.setText(skates.getNivelSkt());
                            tfNivelSkt.setEditable(false);
                            tfPrecoSkt.setText(String.valueOf(skates.getPrecoSkt()));
                            tfPrecoSkt.setEditable(false);
                            int i;
                            for (i = 0; i < listaMarca.length; i++) {
                                if (listaMarca[i].split(";")[0].equals(skates.getMarcaidMarca().toString().split(";")[0].concat("-").concat(skates.getMarcaidMarca().toString().split(";")[1]))) {
                                    break;
                                }
                            }
                            if (i < listaMarca.length) {
                                cmbMarcaSkt.setSelectedIndex(i);
                            }
                            
                            cmbMarcaSkt.setEnabled(false);
                        }
                    } else {//não achou na lista
                        //mostrar botão incluir
                        btAdicionar.setVisible(true);
                        btAlterar.setVisible(false);
                        btExcluir.setVisible(false);
                        tfNomeProduto.setText("");
                        tfNomeProduto.setEditable(false);
                        tfQuantidadeProduto.setText("");
                        tfQuantidadeProduto.setEditable(false);
                        cbSkates.setSelected(false);
                        cbGames.setSelected(false);
                        cbCds.setSelected(false);
                        pnSkates.setVisible(false);
                        pnGames.setVisible(false);
                        pnCds.setVisible(false);
                    }
                } catch (Exception ex) {
                    System.out.println(ex);
                    JOptionPane.showMessageDialog(cp, "Erro, Digite Novamente!", "Erro ao obter", JOptionPane.PLAIN_MESSAGE);
                }
                
            }
        });

// listener Adicionar
        btAdicionar.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                tfIdProduto.setEnabled(false);
                tfNomeProduto.requestFocus();
                tfNomeProduto.setEditable(true);
                tfQuantidadeProduto.setEditable(true);
                btAdicionar.setVisible(false);
                btSalvar.setVisible(true);
                btCancelar.setVisible(true);
                btBuscar.setVisible(false);
                btListar.setVisible(false);
                cbCds.setEnabled(true);
                cbGames.setEnabled(true);
                cbSkates.setEnabled(true);
                acao = "adicionar";
            }
        }
        );

// listener Salvar
        btSalvar.addActionListener(
                new ActionListener() {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            CaixaDeFerramentas cdf = new CaixaDeFerramentas();
            CopiarArquivos copiarArquivos = new CopiarArquivos();
            
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                if (acao.equals("adicionar")) {
                    produtos = new Produtos();
                }
                try {
                    //Produtos
                    produtos.setIdProduto(Integer.valueOf(tfIdProduto.getText()));
                    produtos.setNomeProduto(tfNomeProduto.getText());
                    produtos.setQuantidadeProduto(Integer.valueOf(tfQuantidadeProduto.getText()));

                    //Cds
                    if (cbCds.isSelected()) {
                        if (acao.equals("adicionar")) {
                            cds = new Cds();
                        }
                        cds.setProdutosidProduto(Integer.valueOf(tfIdProduto.getText()));
                        sdf.setLenient(false);
                        Date datac = sdf.parse(tfAnoCd.getText());
                        cds.setAnoCd(datac);
                        cds.setGravadoraCd(tfGravadoraCd.getText());
                        cds.setPrecoCd(Double.parseDouble(tfPrecoCd.getText()));
                        cds.setMarcaidMarca(daoMarca.obter(Integer.valueOf(cmbBandaCd.getSelectedItem().toString().split("-")[0])));

                        //Games    
                    } else if (cbGames.isSelected()) {
                        if (acao.equals("adicionar")) {
                            games = new Games();
                        }
                        games.setProdutosidProduto(Integer.valueOf(tfIdProduto.getText()));
                        sdf.setLenient(false);
                        Date datag = sdf.parse(tfAnoGm.getText());
                        games.setAnoGm(datag);
                        games.setDesenvolvedorGm(tfDesenvolvedorGm.getText());
                        games.setPrecoGm(Double.parseDouble(tfPrecoGm.getText()));
                        games.setIdPlataforma(daoPlataformasgames.obter(Integer.valueOf(cmbPlataformas.getSelectedItem().toString().split("-")[0])));
                    } //Skates
                    else if (cbSkates.isSelected()) {
                        if (acao.equals("adicionar")) {
                            skates = new Skates();
                        }
                        skates.setProdutosidProduto(Integer.valueOf(tfIdProduto.getText()));
                        skates.setNivelSkt(tfNivelSkt.getText());
                        skates.setPrecoSkt(Double.parseDouble(tfPrecoSkt.getText()));
                        skates.setMarcaidMarca(daoMarca.obter(Integer.valueOf(cmbMarcaSkt.getSelectedItem().toString().split("-")[0])));
                    }
                    
                    if (acao.equals("adicionar")) {
                        daoProdutos.inserir(produtos);
                        
                        if (cbCds.isSelected() && cds.getProdutosidProduto() != null) {
                            daoCds.inserir(cds);
                        }
                        if (cbGames.isSelected() && games.getProdutosidProduto() != null) {
                            daoGames.inserir(games);
                        }
                        if (cbSkates.isSelected() && skates.getProdutosidProduto() != null) {
                            daoSkates.inserir(skates);
                        }
                        
                    } else {
                        if (cbCds.isSelected() == false && daoCds.obter(Integer.valueOf(tfIdProduto.getText())) != null) {
                            daoCds.remover(daoCds.obter(produtos.getIdProduto()));
                        } else if (daoCds.obter(Integer.valueOf(tfIdProduto.getText())) != null) {
                            daoCds.atualizar(cds);
                        } else {
                            daoCds.inserir(cds);
                        }
                        if (cbSkates.isSelected() == false && daoSkates.obter(Integer.valueOf(tfIdProduto.getText())) != null) {
                            daoSkates.remover(daoSkates.obter(produtos.getIdProduto()));
                        } else if (daoSkates.obter(Integer.valueOf(tfIdProduto.getText())) != null) {
                            daoSkates.atualizar(skates);
                        } else {
                            daoSkates.inserir(skates);
                        }
                        if (cbGames.isSelected() == false && daoGames.obter(Integer.valueOf(tfIdProduto.getText())) != null) {
                            daoGames.remover(daoGames.obter(produtos.getIdProduto()));
                        }
                        daoProdutos.atualizar(produtos);
                    }
                    btSalvar.setVisible(false);
                    btCancelar.setVisible(false);
                    btBuscar.setVisible(true);
                    btListar.setVisible(true);
                    tfIdProduto.setEditable(true);
                    tfIdProduto.requestFocus();
                    tfIdProduto.setText("");
                    tfNomeProduto.setEditable(false);
                    tfNomeProduto.setText("");
                    tfQuantidadeProduto.setEditable(false);
                    tfQuantidadeProduto.setText("");
                    cbCds.setSelected(false);
                    cbCds.setEnabled(false);
                    cbSkates.setSelected(false);
                    cbSkates.setEnabled(false);
                    cbGames.setSelected(false);
                    cbGames.setEnabled(false);
                } catch (Exception ex) {
                    System.out.println(ex);
                    JOptionPane.showMessageDialog(cp, "Erro, Digite Novamente!", "Erro ao obter", JOptionPane.PLAIN_MESSAGE);
                    
                }
            }
        }
        );

// listener Alterar
        btAlterar.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                btBuscar.setVisible(false);
                btAlterar.setVisible(false);
                tfIdProduto.setEditable(false);
                tfNomeProduto.requestFocus();
                tfNomeProduto.setEditable(true);
                tfQuantidadeProduto.setEditable(true);
                btSalvar.setVisible(true);
                btCancelar.setVisible(true);
                btListar.setVisible(false);
                tfIdProduto.setEnabled(true);
                btExcluir.setVisible(false);
                cbCds.setEnabled(true);
                cbGames.setEnabled(true);
                cbSkates.setEnabled(true);
                if (cbCds.isSelected()) {
                    tfAnoCd.setEditable(true);
                    tfGravadoraCd.setEditable(true);
                    tfPrecoCd.setEditable(true);
                    cmbBandaCd.setEnabled(true);

                    //Games    
                } else if (cbGames.isSelected()) {
                    tfAnoGm.setEditable(true);
                    tfDesenvolvedorGm.setEditable(true);
                    tfPrecoGm.setEditable(true);
                    cmbPlataformas.setEnabled(true);
                } //Skates
                else if (cbSkates.isSelected()) {
                    tfNivelSkt.setEditable(true);
                    tfPrecoSkt.setEditable(true);
                    cmbMarcaSkt.setEnabled(true);
                }
                acao = "alterar";
                
            }
        }
        );

// listener Excluir
        btExcluir.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                
                int response = JOptionPane.showConfirmDialog(cp, "Confirme a exclusão?", "Confirm",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                
                btExcluir.setVisible(false);
                tfIdProduto.setEnabled(true);
                tfIdProduto.setEditable(true);
                tfIdProduto.requestFocus();
                tfIdProduto.setText("");
                tfNomeProduto.setText("");
                tfNomeProduto.setEditable(false);
                tfQuantidadeProduto.setText("");
                tfQuantidadeProduto.setEditable(false);
                btAlterar.setVisible(false);
                if (response == JOptionPane.YES_OPTION) {
                    daoProdutos.remover(produtos);
                }
            }
        }
        );
        
        

// listener Listar
        btListar.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                List<Produtos> listaProdutos = daoProdutos.list();
                String[] colunas = new String[]{"IdProduto", "NomeProduto", "QuantidadeProduto"};
                String[][] dados = new String[listaProdutos.size()][colunas.length];
                String aux[];
                for (int i = 0; i < listaProdutos.size(); i++) {
                    aux = listaProdutos.get(i).toString().split(";");
                    for (int j = 0; j < colunas.length; j++) {
                        dados[i][j] = aux[j];
                    }
                }
                cardLayout.show(pnSul, "listagem");
                scrollTabela.setPreferredSize(tabela.getPreferredSize());
                pnListagem.add(scrollTabela);
                scrollTabela.setViewportView(tabela);
                model.setDataVector(dados, colunas);
                
                btAlterar.setVisible(false);
                btExcluir.setVisible(false);
                btAdicionar.setVisible(false);
                
                tfNomeProduto.setEditable(false);
                tfQuantidadeProduto.setEditable(false);//cor do background e da letra de cada 

                if (cbCds.isSelected()) {
                    tfAnoCd.setEditable(false);
                    tfGravadoraCd.setEditable(false);
                    tfPrecoCd.setEditable(false);
                    cmbBandaCd.setEnabled(false);

                    //Games    
                } else if (cbGames.isSelected()) {
                    tfAnoGm.setEditable(false);
                    tfDesenvolvedorGm.setEditable(false);
                    tfPrecoGm.setEditable(false);
                    cmbPlataformas.setEnabled(false);
                } //Skates
                else if (cbSkates.isSelected()) {
                    tfNivelSkt.setEditable(false);
                    tfPrecoSkt.setEditable(false);
                    cmbMarcaSkt.setEnabled(false);
                }
                
                coluna1.setHorizontalAlignment(SwingConstants.CENTER);
                tabela.getColumnModel().getColumn(0).setCellRenderer(coluna1);
                tabela.getColumnModel().getColumn(1).setCellRenderer(coluna1);
                tabela.getColumnModel().getColumn(2).setCellRenderer(coluna1);
            }
        }
        );

// listener Cancelar
        btCancelar.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                btCancelar.setVisible(false);
                tfIdProduto.setText("");
                tfIdProduto.requestFocus();
                tfIdProduto.setEnabled(true);
                tfIdProduto.setEditable(true);
                tfNomeProduto.setText("");
                tfNomeProduto.setEditable(false);
                tfQuantidadeProduto.setText("");
                tfQuantidadeProduto.setEditable(false);
                btBuscar.setVisible(true);
                btListar.setVisible(true);
                btSalvar.setVisible(false);
                btCancelar.setVisible(false);
                cbCds.setSelected(false);
                cbCds.setEnabled(false);
                cbGames.setSelected(false);
                cbGames.setEnabled(false);
                cbSkates.setSelected(false);
                cbSkates.setEnabled(false);
                pnSkates.setVisible(false);
                pnCds.setVisible(false);
                pnGames.setVisible(false);
            }
        }
        );
        
        cbCds.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                if (cbCds.isSelected()) {
                    pnCds.setVisible(true);
                    cbSkates.setSelected(false);
                    cbGames.setSelected(false);
                    pnSkates.setVisible(false);
                    pnGames.setVisible(false);
                } else {
                    pnCds.setVisible(false);
                }
            }
        }
        );
        
        cbGames.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                if (cbGames.isSelected()) {
                    pnGames.setVisible(true);
                    cbSkates.setSelected(false);
                    cbCds.setSelected(false);
                    pnCds.setVisible(false);
                    pnSkates.setVisible(false);
                } else {
                    pnGames.setVisible(false);
                }
            }
        }
        );
        
        cbSkates.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                if (cbSkates.isSelected()) {
                    pnSkates.setVisible(true);
                    cbCds.setSelected(false);
                    cbGames.setSelected(false);
                    pnCds.setVisible(false);
                    pnGames.setVisible(false);
                    
                } else {
                    pnSkates.setVisible(false);
                }
            }
        }
        );

// listener ao fechar o programa
        addWindowListener(
                new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e
            ) {
                dispose();
            }
        }
        );
        
        setModal(
                true);
        pack();
        
        setLocationRelativeTo(
                null);//centraliza na tela
        setVisible(
                true);
    }//fim do contrutor de GUI
} //fim da classe
