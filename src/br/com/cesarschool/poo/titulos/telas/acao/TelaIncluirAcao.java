package br.com.cesarschool.poo.titulos.telas.acao;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import br.com.cesarschool.poo.titulos.entidades.Acao;
import br.com.cesarschool.poo.titulos.mediators.MediatorAcao;

public class TelaIncluirAcao {
    private JFrame tela;
    private JTextField textoId;
    private JTextField textoNome;
    private JTextField textoValor;
    private JTextField textoDataValidade;
    private JButton botIncluir;   
    private MediatorAcao mediatorAcao = MediatorAcao.getInstancia();

    public void setVisible(boolean b) {
        tela.setVisible(b);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                TelaIncluirAcao window = new TelaIncluirAcao();
                window.tela.setVisible(true);
            } catch (Exception e) {
                Logger.getLogger(TelaIncluirAcao.class.getName()).log(Level.SEVERE, null, e);
            }
        });
    }

    public TelaIncluirAcao() {
        initialize();
    }

    private void criarTela() {
        tela = new JFrame("Incluir Ação");
        tela.setBounds(100, 100, 556, 370);
        tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tela.getContentPane().setLayout(null);
    }

    private void initialize() {
        criarTela();

        int yPos = 40;
        int xLabel = 41;
        int xTextField = 183;
        int textFieldWidth = 122;
        int labelHeight = 20;
        int textFieldHeight = 26;
        int buttonHeight = 30;
        int buttonWidth = 90;
        int verticalSpacing = 36;

        JLabel labelId = new JLabel("Id");
        labelId.setBounds(xLabel, yPos, 121, labelHeight);
        tela.getContentPane().add(labelId);
        textoId = new JTextField();
        textoId.setBounds(xTextField, yPos, textFieldWidth, textFieldHeight);
        tela.getContentPane().add(textoId);
        yPos += verticalSpacing;

        JLabel labelNome = new JLabel("Nome");
        labelNome.setBounds(xLabel, yPos, 121, labelHeight);
        tela.getContentPane().add(labelNome);
        textoNome = new JTextField();
        textoNome.setBounds(xTextField, yPos, textFieldWidth, textFieldHeight);
        tela.getContentPane().add(textoNome);
        yPos += verticalSpacing;

        JLabel labelValor = new JLabel("Valor");
        labelValor.setBounds(xLabel, yPos, 121, labelHeight);
        tela.getContentPane().add(labelValor);
        textoValor = new JTextField();
        textoValor.setBounds(xTextField, yPos, textFieldWidth, textFieldHeight);
        tela.getContentPane().add(textoValor);
        yPos += verticalSpacing;

        JLabel labelDataValidade = new JLabel("Data de Validade");
        labelDataValidade.setBounds(xLabel, yPos, 121, labelHeight);
        tela.getContentPane().add(labelDataValidade);
        textoDataValidade = new JTextField();
        textoDataValidade.setBounds(xTextField, yPos, textFieldWidth, textFieldHeight);
        tela.getContentPane().add(textoDataValidade);
        yPos += verticalSpacing;

        botIncluir = new JButton("Incluir");
        botIncluir.setBounds(xLabel, yPos, buttonWidth, buttonHeight);
        tela.getContentPane().add(botIncluir);

        JButton botVoltar = new JButton("Voltar");
        botVoltar.setBounds(150, yPos, 100, buttonHeight);
        botVoltar.addActionListener(e -> {
            NavegacaoAcao navegacaoAcao = new NavegacaoAcao();
            navegacaoAcao.setVisible(true);
            tela.dispose();
        });
        tela.getContentPane().add(botVoltar);
        
        JButton botLimpar = new JButton("Limpar");
        botLimpar.setBounds(xLabel + 230, yPos, 90, 30);
        botLimpar.addActionListener(e -> {
            textoId.setText("");
            textoNome.setText("");
            textoValor.setText("");
            textoDataValidade.setText("");
        });

        tela.getContentPane().add(botLimpar);
        botIncluir.addActionListener(e -> {
            try {
                int id = Integer.parseInt(textoId.getText());
                String nome = textoNome.getText();
                LocalDate dataValidade = LocalDate.parse(textoDataValidade.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                double valor = Double.parseDouble(textoValor.getText());
                
                Acao acao = new Acao(id, nome, dataValidade, valor);
                String msg = mediatorAcao.incluir(acao);
                if (msg == null) {
                    JOptionPane.showMessageDialog(null, "Incluído com sucesso");
                } else {
                    JOptionPane.showMessageDialog(null, msg);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erro ao incluir: " + ex.getMessage());
            }
        });
    }
}
