package GUIs;

import DAOs.DAOGames;
import DAOs.DAOPlataformasgames;
import Entidades.Games;
import Entidades.Plataformasgames;
import static com.sun.java.accessibility.util.AWTEventMonitor.addWindowListener;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import tools.CaixaDeFerramentas;



/**
 *
 * @author anamacowski 22/09/2023 - 11:45:47
 */
public class GamesGUI extends JDialog {

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
    JLabel lbProdutos_idProduto = new JLabel("Produtos_idProduto");
    JTextField tfProdutos_idProduto = new JTextField(30);
    JLabel lbIdPlataforma = new JLabel("IdPlataforma");
    JTextField tfIdPlataforma = new JTextField(30);
    JLabel lbDesenvolvedorGm = new JLabel("DesenvolvedorGm");
    JTextField tfDesenvolvedorGm = new JTextField(60);
    JLabel lbAnoGm = new JLabel("AnoGm");
    JTextField tfAnoGm = new JTextField(30);
    JLabel lbPrecoGm = new JLabel("PrecoGm");
    JTextField tfPrecoGm = new JTextField(30);
    JLabel lbVazio = new JLabel("");
    DAOGames daoGames = new DAOGames();
    Games games = new Games();
    String[] colunas = new String[]{"produtos_idProduto", "idPlataforma", "desenvolvedorGm", "anoGm", "precoGm"};
    String[][] dados = new String[0][colunas.length];

    DefaultTableModel model = new DefaultTableModel(dados, colunas);
    JTable tabela = new JTable(model);

    public GamesGUI() throws ParseException {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        CaixaDeFerramentas cf = new CaixaDeFerramentas();
        cp = getContentPane();
        cp.setLayout(new BorderLayout());
        setTitle("CRUD - Games");

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
        pnNorte.add(lbProdutos_idProduto);
        pnNorte.add(tfProdutos_idProduto);
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
        tfIdPlataforma.setEditable(false);
        tfDesenvolvedorGm.setEditable(false);
        tfAnoGm.setEditable(false);
        tfPrecoGm.setEditable(false);
        pnCentro.setLayout(new GridLayout(colunas.length - 1, 2));
        lbIdPlataforma.setHorizontalAlignment(SwingConstants.CENTER);
        lbDesenvolvedorGm.setHorizontalAlignment(SwingConstants.CENTER);
        lbAnoGm.setHorizontalAlignment(SwingConstants.CENTER);
        lbPrecoGm.setHorizontalAlignment(SwingConstants.CENTER);

        pnCentro.add(lbIdPlataforma);
        pnCentro.add(tfIdPlataforma);
        pnCentro.add(lbDesenvolvedorGm);
        pnCentro.add(tfDesenvolvedorGm);
        pnCentro.add(lbAnoGm);
        pnCentro.add(tfAnoGm);
        pnCentro.add(lbPrecoGm);
        pnCentro.add(tfPrecoGm);
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

// listener Buscar
        btBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(pnSul, "avisos");
                try {
                    games = daoGames.obter(Integer.valueOf(tfProdutos_idProduto.getText()));
                    if (games != null) {//achou o games na lista
                        //mostrar
                        btAdicionar.setVisible(false);
                        btAlterar.setVisible(true);
                        btExcluir.setVisible(true);
                        tfIdPlataforma.setText(String.valueOf(games.getIdPlataforma()));
                        tfIdPlataforma.setEditable(false);
                        tfDesenvolvedorGm.setText(String.valueOf(games.getDesenvolvedorGm()));
                        tfDesenvolvedorGm.setEditable(false);
                        tfAnoGm.setText(new SimpleDateFormat("dd/MM/yyyy").format(games.getAnoGm()));
                        tfPrecoGm.setText(String.valueOf(games.getPrecoGm()));
                        tfPrecoGm.setEditable(false);
                    } else {//não achou na lista
                        //mostrar botão incluir
                        btAdicionar.setVisible(true);
                        btAlterar.setVisible(false);
                        btExcluir.setVisible(false);
                        tfIdPlataforma.setText("");
                        tfIdPlataforma.setEditable(false);
                        tfDesenvolvedorGm.setText("");
                        tfDesenvolvedorGm.setEditable(false);
                        tfAnoGm.setText("");
                        tfAnoGm.setEditable(false);
                        tfPrecoGm.setText("");
                        tfPrecoGm.setEditable(false);
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
                tfProdutos_idProduto.setEnabled(false);
                tfIdPlataforma.requestFocus();
                tfIdPlataforma.setEditable(true);
                tfDesenvolvedorGm.setEditable(true);
                tfAnoGm.setEditable(true);
                tfPrecoGm.setEditable(true);
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
                    games = new Games();
                }
                try {
                    games.setProdutosidProduto(Integer.valueOf(tfProdutos_idProduto.getText()));
                    
                    DAOPlataformasgames daoPlataformasgames = new DAOPlataformasgames();
                    Plataformasgames plataformasgames = daoPlataformasgames.obter(Integer.valueOf(tfIdPlataforma.getText()));
                    games.setIdPlataforma(plataformasgames);
                    
                    games.setDesenvolvedorGm(tfDesenvolvedorGm.getText());
                    sdf.setLenient(false);
                    Date data = sdf.parse(tfAnoGm.getText());
                    games.setAnoGm(data);
                    games.setAnoGm(cf.converteDeStringParaDate(tfAnoGm.getText()));
                    games.setPrecoGm(Double.valueOf(tfPrecoGm.getText()));
                    if (acao.equals("adicionar")) {
                        daoGames.inserir(games);
                    } else {
                        daoGames.atualizar(games);
                    }
                    btSalvar.setVisible(false);
                    btCancelar.setVisible(false);
                    btBuscar.setVisible(true);
                    btListar.setVisible(true);
                    tfProdutos_idProduto.setEnabled(true);
                    tfProdutos_idProduto.setEditable(true);
                    tfProdutos_idProduto.requestFocus();
                    tfProdutos_idProduto.setText("");
                    tfIdPlataforma.setEnabled(false);
                    tfIdPlataforma.setEditable(false);
                    tfIdPlataforma.setText("");
                    tfDesenvolvedorGm.setEnabled(false);
                    tfDesenvolvedorGm.setEditable(false);
                    tfDesenvolvedorGm.setText("");
                    tfAnoGm.setEnabled(false);
                    tfAnoGm.setEditable(false);
                    tfAnoGm.setText("");
                    tfPrecoGm.setEnabled(false);
                    tfPrecoGm.setEditable(false);
                    tfPrecoGm.setText("");
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
                tfProdutos_idProduto.setEditable(false);
                tfIdPlataforma.requestFocus();
                tfIdPlataforma.setEditable(true);
                tfDesenvolvedorGm.setEditable(true);
                tfAnoGm.setEditable(true);
                tfPrecoGm.setEditable(true);
                btSalvar.setVisible(true);
                btCancelar.setVisible(true);
                btListar.setVisible(false);
                tfProdutos_idProduto.setEnabled(true);
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
                tfProdutos_idProduto.setEnabled(true);
                tfProdutos_idProduto.setEditable(true);
                tfProdutos_idProduto.requestFocus();
                tfProdutos_idProduto.setText("");
                tfIdPlataforma.setText("");
                tfIdPlataforma.setEditable(false);
                tfDesenvolvedorGm.setText("");
                tfDesenvolvedorGm.setEditable(false);
                tfAnoGm.setText("");
                tfAnoGm.setEditable(false);
                tfPrecoGm.setText("");
                tfPrecoGm.setEditable(false);
                btAlterar.setVisible(false);
                if (response == JOptionPane.YES_OPTION) {
                    daoGames.remover(games);
                }
            }
        });

// listener Listar
        btListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Games> listaGames = daoGames.list();
                String[] colunas = new String[]{"Produtos_idProduto", "IdPlataforma", "DesenvolvedorGm", "AnoGm", "PrecoGm"};
                String[][] dados = new String[listaGames.size()][colunas.length];
                String aux[];
                for (int i = 0; i < listaGames.size(); i++) {
                    aux = listaGames.get(i).toString().split(";");
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
                tfIdPlataforma.setEditable(false);
                tfDesenvolvedorGm.setEditable(false);
                tfAnoGm.setEditable(false);
                tfPrecoGm.setEditable(false);//cor do background e da letra de cada coluna
                coluna1.setHorizontalAlignment(SwingConstants.CENTER);
                tabela.getColumnModel().getColumn(0).setCellRenderer(coluna1);
                tabela.getColumnModel().getColumn(1).setCellRenderer(coluna1);
                tabela.getColumnModel().getColumn(2).setCellRenderer(coluna1);
                tabela.getColumnModel().getColumn(3).setCellRenderer(coluna1);
                tabela.getColumnModel().getColumn(4).setCellRenderer(coluna1);
            }
        });

// listener Cancelar
        btCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btCancelar.setVisible(false);
                tfProdutos_idProduto.setText("");
                tfProdutos_idProduto.requestFocus();
                tfProdutos_idProduto.setEnabled(true);
                tfProdutos_idProduto.setEditable(true);
                tfIdPlataforma.setText("");
                tfIdPlataforma.setEditable(false);
                tfDesenvolvedorGm.setText("");
                tfDesenvolvedorGm.setEditable(false);
                tfAnoGm.setText("");
                tfAnoGm.setEditable(false);
                tfPrecoGm.setText("");
                tfPrecoGm.setEditable(false);
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
