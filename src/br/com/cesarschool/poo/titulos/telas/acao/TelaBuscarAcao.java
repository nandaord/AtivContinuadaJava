package br.com.cesarschool.poo.titulos.telas.acao;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import java.util.logging.Logger;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;

import br.com.cesarschool.poo.titulos.entidades.Acao;
import br.com.cesarschool.poo.titulos.mediators.MediatorAcao;

public class TelaBuscarAcao {
    private JFrame tela;
    private JTextField textoId;
    private JTextField textoNome;
    private JTextField textoValor;
    private JTextField textoDataValidade;
    private JButton botBuscar;
    private MediatorAcao mediatorAcao = MediatorAcao.getInstancia();

    public void setVisible(boolean b) {
        tela.setVisible(b);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                TelaBuscarAcao window = new TelaBuscarAcao();
                window.tela.setVisible(true);
            } catch (Exception e) {
                Logger.getLogger(TelaBuscarAcao.class.getName()).log(Level.SEVERE, null, e);
            }
        });
    }

    public TelaBuscarAcao() {
        initialize();
    }

    private void criarTela() {
        tela = new JFrame("Buscar Ação");
        tela.setBounds(100, 100, 556, 370);
        tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tela.getContentPane().setLayout(null);
    }

    private void initialize() {
        criarTela();

        int yPos = 40; // Posição inicial y
        int xLabel = 41; // Posição x para Labels
        int xTextField = 183; // Posição x para TextFields

        // COMPONENTE 1
        JLabel labelId = new JLabel("ID atual");
        labelId.setBounds(xLabel, yPos, 121, 20);
        tela.getContentPane().add(labelId);
        textoId = new JTextField();
        textoId.setBounds(xTextField, yPos, 122, 26);
        tela.getContentPane().add(textoId);
        yPos += 36;

        JLabel labelNome = new JLabel("Novo Nome");
        labelNome.setBounds(xLabel, yPos, 121, 20);
        tela.getContentPane().add(labelNome);
        textoNome = new JTextField();
        textoNome.setBounds(xTextField, yPos, 122, 26);
        textoNome.setEnabled(false);
        textoNome.setEditable(false);
        tela.getContentPane().add(textoNome);
        yPos += 36;

        // COMPONENTE 3
        JLabel labelValor = new JLabel("Novo Valor");
        labelValor.setBounds(xLabel, yPos, 121, 20);
        tela.getContentPane().add(labelValor);
        textoValor = new JTextField();
        textoValor.setBounds(xTextField, yPos, 122, 26);
        textoValor.setEnabled(false);
        textoValor.setEditable(false);
        tela.getContentPane().add(textoValor);
        yPos += 36;

        // COMPONENTE 4
        JLabel labelDataValidade = new JLabel("Data de Validade");
        labelDataValidade.setBounds(xLabel, yPos, 121, 20);
        tela.getContentPane().add(labelDataValidade);
        textoDataValidade = new JTextField();
        textoDataValidade.setBounds(xTextField, yPos, 122, 26);
        textoDataValidade.setEnabled(false);
        textoDataValidade.setEditable(false);
        tela.getContentPane().add(textoDataValidade);
        yPos += 36;

        // COMPONENTE 5
        botBuscar = new JButton("Buscar");
        botBuscar.setBounds(xLabel, yPos, 90, 30);
        tela.getContentPane().add(botBuscar);

        JButton botVoltar = new JButton("Voltar");
        botVoltar.setBounds(xLabel + 100, yPos, 90, 30);
        botVoltar.addActionListener(e -> {
            NavegacaoAcao navegacaoAcao = new NavegacaoAcao();
            navegacaoAcao.setVisible(true);
            tela.dispose(); // Fecha a Tela Buscar
        });
        tela.getContentPane().add(botVoltar);

        JButton botLimpar = new JButton("Limpar");
        botLimpar.setBounds(xLabel + 200, yPos, 100, 30);
        botLimpar.addActionListener(e -> {
            textoId.setText("");
            textoNome.setText("");
            textoValor.setText("");
            textoDataValidade.setText("");
        });
        tela.getContentPane().add(botLimpar);
        // Adicionando a ação do botão
        botBuscar.addActionListener(e -> {
            try {
                int id = Integer.parseInt(textoId.getText());

                // Tenta buscar a ação usando o mediador
                Acao acao = mediatorAcao.buscar(id);
                if (acao == null) {
                    JOptionPane.showMessageDialog(null, "Erro ao buscar ação");
                } else {
                    textoNome.setText(acao.getNome());
                    textoValor.setText(String.valueOf(acao.getValorUnitario()));
                    textoDataValidade.setText(acao.getDataValidade().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                    JOptionPane.showMessageDialog(null, "Ação encontrada com sucesso!");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erro ao buscar ação: " + ex.getMessage());
            }
        });
    }
}
