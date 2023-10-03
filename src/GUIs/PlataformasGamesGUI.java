package GUIs;

import Entidades.Plataformasgames;
import DAOs.DAOPlataformasgames;
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
 * @author anamacowski 06/07/2023 - 21:45:07
 */
public class PlataformasGamesGUI extends JDialog {

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
    JLabel lbIdPlataforma = new JLabel("IdPlataforma");
    JTextField tfIdPlataforma = new JTextField(30);
    JLabel lbNomePlataforma = new JLabel("NomePlataforma");
    JTextField tfNomePlataforma = new JTextField(60);
    JLabel lbVazio = new JLabel("");
    DAOPlataformasgames daoPlataformasGames = new DAOPlataformasgames();
    Plataformasgames plataformasGames = new Plataformasgames();
    String[] colunas = new String[]{"idPlataforma", "nomePlataforma"};
    String[][] dados = new String[0][colunas.length];

    DefaultTableModel model = new DefaultTableModel(dados, colunas);
    JTable tabela = new JTable(model);

    public PlataformasGamesGUI() throws ParseException {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        CaixaDeFerramentas cf = new CaixaDeFerramentas();
        cp = getContentPane();
        cp.setLayout(new BorderLayout());
        setTitle("CRUD - PlataformasGames");

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
        pnNorte.add(lbIdPlataforma);
        pnNorte.add(tfIdPlataforma);
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
        tfNomePlataforma.setEditable(false);
        pnCentro.setLayout(new GridLayout(colunas.length - 1, 2));
        lbNomePlataforma.setHorizontalAlignment(SwingConstants.CENTER);

        pnCentro.add(lbNomePlataforma);
        pnCentro.add(tfNomePlataforma);
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
                    plataformasGames = daoPlataformasGames.obter(Integer.valueOf(tfIdPlataforma.getText()));
                    if (plataformasGames != null) {//achou o plataformasGames na lista
                        //mostrar
                        btAdicionar.setVisible(false);
                        btAlterar.setVisible(true);
                        btExcluir.setVisible(true);
                        tfNomePlataforma.setText(String.valueOf(plataformasGames.getNomePlataforma()));
                        tfNomePlataforma.setEditable(false);
                    } else {//não achou na lista
                        //mostrar botão incluir
                        btAdicionar.setVisible(true);
                        btAlterar.setVisible(false);
                        btExcluir.setVisible(false);
                        tfNomePlataforma.setText("");
                        tfNomePlataforma.setEditable(false);
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
                tfIdPlataforma.setEnabled(false);
                tfNomePlataforma.requestFocus();
                tfNomePlataforma.setEditable(true);
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
                    plataformasGames = new Plataformasgames();
                }
                try {
                    plataformasGames.setIdPlataforma(Integer.valueOf(tfIdPlataforma.getText()));
                    plataformasGames.setNomePlataforma(tfNomePlataforma.getText());
                    if (acao.equals("adicionar")) {
                        daoPlataformasGames.inserir(plataformasGames);
                    } else {
                        daoPlataformasGames.atualizar(plataformasGames);
                    }
                    btSalvar.setVisible(false);
                    btCancelar.setVisible(false);
                    btBuscar.setVisible(true);
                    btListar.setVisible(true);
                    tfIdPlataforma.setEnabled(true);
                    tfIdPlataforma.setEditable(true);
                    tfIdPlataforma.requestFocus();
                    tfIdPlataforma.setText("");
                    tfNomePlataforma.setEnabled(false);
                    tfNomePlataforma.setEditable(false);
                    tfNomePlataforma.setText("");
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
                tfIdPlataforma.setEditable(false);
                tfNomePlataforma.requestFocus();
                tfNomePlataforma.setEditable(true);
                btSalvar.setVisible(true);
                btCancelar.setVisible(true);
                btListar.setVisible(false);
                tfIdPlataforma.setEnabled(true);
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
                tfIdPlataforma.setEnabled(true);
                tfIdPlataforma.setEditable(true);
                tfIdPlataforma.requestFocus();
                tfIdPlataforma.setText("");
                tfNomePlataforma.setText("");
                tfNomePlataforma.setEditable(false);
                btAlterar.setVisible(false);
                if (response == JOptionPane.YES_OPTION) {
                    daoPlataformasGames.remover(plataformasGames);
                }
            }
        });

// listener Listar
        btListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Plataformasgames> listaPlataformasGames = daoPlataformasGames.list();
                String[] colunas = new String[]{"IdPlataforma", "NomePlataforma"};
                String[][] dados = new String[listaPlataformasGames.size()][colunas.length];
                String aux[];
                for (int i = 0; i < listaPlataformasGames.size(); i++) {
                    aux = listaPlataformasGames.get(i).toString().split(";");
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
                tfNomePlataforma.setEditable(false);//cor do background e da letra de cada coluna
                coluna1.setHorizontalAlignment(SwingConstants.CENTER);
                tabela.getColumnModel().getColumn(0).setCellRenderer(coluna1);
                tabela.getColumnModel().getColumn(1).setCellRenderer(coluna1);
            }
        });

// listener Cancelar
        btCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btCancelar.setVisible(false);
                tfIdPlataforma.setText("");
                tfIdPlataforma.requestFocus();
                tfIdPlataforma.setEnabled(true);
                tfIdPlataforma.setEditable(true);
                tfNomePlataforma.setText("");
                tfNomePlataforma.setEditable(false);
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
