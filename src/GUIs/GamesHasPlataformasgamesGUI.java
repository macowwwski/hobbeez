package GUIs;

import Entidades.GamesHasPlataformasgames;
import DAOs.DAOGamesHasPlataformasgames;
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
 * @author anamacowski 03/10/2023 - 18:15:11
 */
public class GamesHasPlataformasgamesGUI extends JDialog {

    Container cp;
    JPanel pnNorte = new JPanel();
    JPanel pnCentro = new JPanel();
    JPanel pnSul = new JPanel();
    JPanel pnLeste = new JPanel(new BorderLayout());
    JPanel pnLesteA = new JPanel(new FlowLayout(FlowLayout.CENTER));
    JButton btBuscar = new JButton("Buscar");
    JButton btAdicionar = new JButton("Adicionar");
    JButton btSalvar = new JButton("Salvar");
    JButton btAlterar = new JButton("Alterar");
    JButton btExcluir = new JButton("Excluir");
    JButton btListar = new JButton("Listar");
    JButton btCancelar = new JButton("Cancelar");
    String acao = "";
    private JScrollPane scrollTabela = new JScrollPane();

    private JPanel pnAvisos = new JPanel(new GridLayout(1, 1));
    private JPanel pnListagem = new JPanel(new GridLayout(1, 1));
    private JPanel pnVazio = new JPanel(new GridLayout(6, 1));

    DefaultTableCellRenderer coluna1 = new DefaultTableCellRenderer();
    private CardLayout cardLayout;

//////////////////// - mutável - /////////////////////////
    JLabel lbGames_produtos_idProduto = new JLabel("Games_produtos_idProduto");
    JTextField tfGames_produtos_idProduto = new JTextField(30);
    JLabel lbPlataformasgames_idPlataforma = new JLabel("Plataformasgames_idPlataforma");
    JTextField tfPlataformasgames_idPlataforma = new JTextField(30);
    JLabel lbStatus = new JLabel("Status");
    JTextField tfStatus = new JTextField(1);
    JLabel lbVazio = new JLabel("");
    DAOGamesHasPlataformasgames daoGamesHasPlataformasgames = new DAOGamesHasPlataformasgames();
    GamesHasPlataformasgames gamesHasPlataformasgames = new GamesHasPlataformasgames();
    String[] colunas = new String[]{"games_produtos_idProduto", "plataformasgames_idPlataforma", "status"};
    String[][] dados = new String[0][colunas.length];

    DefaultTableModel model = new DefaultTableModel(dados, colunas);
    JTable tabela = new JTable(model);

    public GamesHasPlataformasgamesGUI() throws ParseException {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        CaixaDeFerramentas cf = new CaixaDeFerramentas();
        cp = getContentPane();
        cp.setLayout(new BorderLayout());
        setTitle("CRUD - GamesHasPlataformasgames");

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
        pnNorte.setBackground(new Color(56, 102, 65));
        pnCentro.setBorder(BorderFactory.createLineBorder(Color.black));

        pnNorte.setLayout(new FlowLayout(FlowLayout.LEFT));
        pnNorte.add(lbGames_produtos_idProduto);
        pnNorte.add(tfGames_produtos_idProduto);
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
        tfPlataformasgames_idPlataforma.setEditable(false);
        tfStatus.setEditable(false);
        pnCentro.setLayout(new GridLayout(colunas.length - 1, 2));
        lbPlataformasgames_idPlataforma.setHorizontalAlignment(SwingConstants.CENTER);
        lbStatus.setHorizontalAlignment(SwingConstants.CENTER);

        pnCentro.add(lbPlataformasgames_idPlataforma);
        pnCentro.add(tfPlataformasgames_idPlataforma);
        pnCentro.add(lbStatus);
        pnCentro.add(tfStatus);
        cardLayout = new CardLayout();
        pnSul.setLayout(cardLayout);

        for (int i = 0; i < 5; i++) {
            pnVazio.add(new JLabel(" "));
        }
        pnSul.add(pnVazio, "vazio");
        pnSul.add(pnAvisos, "avisos");
        pnSul.add(pnListagem, "listagem");
        tabela.setEnabled(false);

        pnAvisos.add(new JLabel("Avisos"));

// localizar game e plataforma
        
        
// listener Buscar
        btBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(pnSul, "avisos");
                try {
                    gamesHasPlataformasgames = daoGamesHasPlataformasgames.obter(Integer.valueOf(tfGames_produtos_idProduto.getText()));
                    if (gamesHasPlataformasgames != null) {//achou o gamesHasPlataformasgames na lista
                        //mostrar
                        btAdicionar.setVisible(false);
                        btAlterar.setVisible(true);
                        btExcluir.setVisible(true);
                        tfPlataformasgames_idPlataforma.setText(String.valueOf(gamesHasPlataformasgames.getPlataformasgames_idPlataforma()));
                        tfPlataformasgames_idPlataforma.setEditable(false);
                        tfStatus.setText(String.valueOf(gamesHasPlataformasgames.getStatus()));
                        tfStatus.setEditable(false);
                    } else {//não achou na lista
                        //mostrar botão incluir
                        btAdicionar.setVisible(true);
                        btAlterar.setVisible(false);
                        btExcluir.setVisible(false);
                        tfPlataformasgames_idPlataforma.setText("");
                        tfPlataformasgames_idPlataforma.setEditable(false);
                        tfStatus.setText("");
                        tfStatus.setEditable(false);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(cp, "Erro, Digite Novamente!", "Erro ao obter", JOptionPane.PLAIN_MESSAGE);
                }
            }
        });

// listener Adicionar
        btAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tfGames_produtos_idProduto.setEnabled(false);
                tfPlataformasgames_idPlataforma.requestFocus();
                tfPlataformasgames_idPlataforma.setEditable(true);
                tfStatus.setEditable(true);
                btAdicionar.setVisible(false);
                btSalvar.setVisible(true);
                btCancelar.setVisible(true);
                btBuscar.setVisible(false);
                btListar.setVisible(false);
                acao = "adicionar";
            }
        });

// listener Salvar
        btSalvar.addActionListener(new ActionListener() {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            @Override
            public void actionPerformed(ActionEvent e) {
                if (acao.equals("adicionar")) {
                    gamesHasPlataformasgames = new GamesHasPlataformasgames();
                }
                try {
                    gamesHasPlataformasgames.setGames_produtos_idProduto(Integer.valueOf(tfGames_produtos_idProduto.getText()));
                    gamesHasPlataformasgames.setPlataformasgames_idPlataforma(Integer.valueOf(tfPlataformasgames_idPlataforma.getText()));
                    gamesHasPlataformasgames.setStatus(tfStatus.getText());
                    if (acao.equals("adicionar")) {
                        daoGamesHasPlataformasgames.inserir(gamesHasPlataformasgames);
                    } else {
                        daoGamesHasPlataformasgames.atualizar(gamesHasPlataformasgames);
                    }
                    btSalvar.setVisible(false);
                    btCancelar.setVisible(false);
                    btBuscar.setVisible(true);
                    btListar.setVisible(true);
                    tfGames_produtos_idProduto.setEnabled(true);
                    tfGames_produtos_idProduto.setEditable(true);
                    tfGames_produtos_idProduto.requestFocus();
                    tfGames_produtos_idProduto.setText("");
                    tfPlataformasgames_idPlataforma.setEnabled(false);
                    tfPlataformasgames_idPlataforma.setEditable(false);
                    tfPlataformasgames_idPlataforma.setText("");
                    tfStatus.setEnabled(false);
                    tfStatus.setEditable(false);
                    tfStatus.setText("");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(cp, "Erro, Digite Novamente!", "Erro ao obter", JOptionPane.PLAIN_MESSAGE);

                }
            }
        });

// listener Alterar
        btAlterar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btBuscar.setVisible(false);
                btAlterar.setVisible(false);
                tfGames_produtos_idProduto.setEditable(false);
                tfPlataformasgames_idPlataforma.requestFocus();
                tfPlataformasgames_idPlataforma.setEditable(true);
                tfStatus.setEditable(true);
                btSalvar.setVisible(true);
                btCancelar.setVisible(true);
                btListar.setVisible(false);
                tfGames_produtos_idProduto.setEnabled(true);
                btExcluir.setVisible(false);
                acao = "alterar";

            }
        });

// listener Excluir
        btExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int response = JOptionPane.showConfirmDialog(cp, "Confirme a exclusão?", "Confirm",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                btExcluir.setVisible(false);
                tfGames_produtos_idProduto.setEnabled(true);
                tfGames_produtos_idProduto.setEditable(true);
                tfGames_produtos_idProduto.requestFocus();
                tfGames_produtos_idProduto.setText("");
                tfPlataformasgames_idPlataforma.setText("");
                tfPlataformasgames_idPlataforma.setEditable(false);
                tfStatus.setText("");
                tfStatus.setEditable(false);
                btAlterar.setVisible(false);
                if (response == JOptionPane.YES_OPTION) {
                    daoGamesHasPlataformasgames.remover(gamesHasPlataformasgames);
                }
            }
        });

// listener Listar
        btListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<GamesHasPlataformasgames> listaGamesHasPlataformasgames = daoGamesHasPlataformasgames.list();
                String[] colunas = new String[]{"Games_produtos_idProduto", "Plataformasgames_idPlataforma", "Status"};
                String[][] dados = new String[listaGamesHasPlataformasgames.size()][colunas.length];
                String aux[];
                for (int i = 0; i < listaGamesHasPlataformasgames.size(); i++) {
                    aux = listaGamesHasPlataformasgames.get(i).toString().split(";");
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
                tfPlataformasgames_idPlataforma.setEditable(false);
                tfStatus.setEditable(false);//cor do background e da letra de cada coluna
                coluna1.setHorizontalAlignment(SwingConstants.CENTER);
                tabela.getColumnModel().getColumn(0).setCellRenderer(coluna1);
                tabela.getColumnModel().getColumn(1).setCellRenderer(coluna1);
                tabela.getColumnModel().getColumn(2).setCellRenderer(coluna1);
            }
        });

// listener Cancelar
        btCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btCancelar.setVisible(false);
                tfGames_produtos_idProduto.setText("");
                tfGames_produtos_idProduto.requestFocus();
                tfGames_produtos_idProduto.setEnabled(true);
                tfGames_produtos_idProduto.setEditable(true);
                tfPlataformasgames_idPlataforma.setText("");
                tfPlataformasgames_idPlataforma.setEditable(false);
                tfStatus.setText("");
                tfStatus.setEditable(false);
                btBuscar.setVisible(true);
                btListar.setVisible(true);
                btSalvar.setVisible(false);
                btCancelar.setVisible(false);
            }
        });

// listener ao fechar o programa
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        setModal(true);
        pack();
        setLocationRelativeTo(null);//centraliza na tela
        setVisible(true);
    }//fim do contrutor de GUI
} //fim da classe
