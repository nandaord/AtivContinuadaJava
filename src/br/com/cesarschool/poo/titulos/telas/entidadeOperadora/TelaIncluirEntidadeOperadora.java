package br.com.cesarschool.poo.titulos.telas.entidadeOperadora; 

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import java.util.logging.Logger;
import java.util.logging.Level;

import br.com.cesarschool.poo.titulos.entidades.EntidadeOperadora;
import br.com.cesarschool.poo.titulos.mediators.MediatorEntidadeOperadora;

public class TelaIncluirEntidadeOperadora {
    private JFrame tela; 
    private JTextField textoId;
    private JTextField textoNome;
    private JComboBox<String> comboAutorizadoAcao;
    private JTextField textoSaldoAcao;
    private JTextField textoSaldoTituloDivida;
    private JButton botIncluir; 
    private MediatorEntidadeOperadora mediatorEntidadeOperadora = MediatorEntidadeOperadora.getInstance();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                TelaIncluirEntidadeOperadora window = new TelaIncluirEntidadeOperadora();
                window.tela.setVisible(true); 
            } catch (Exception e) {
                Logger.getLogger(TelaIncluirEntidadeOperadora.class.getName()).log(Level.SEVERE, null, e);
            }
        });
    }

    public TelaIncluirEntidadeOperadora() {
        initialize();
    }

    public void setVisible(boolean visibilidade) {
        tela.setVisible(visibilidade); 
    }

    private void criarTela() { 
        tela = new JFrame("Incluir Entidade Operadora"); 
        tela.setBounds(100, 100, 556, 370);
        tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tela.getContentPane().setLayout(null);
    }

    private void initialize() {
        criarTela(); 

        int yPos = 40; 
        int xLabel = 41; 
        int xTextField = 183; 
        int fieldWidth = 78; 
        int labelHeight = 20; 
        int textFieldHeight = 26; 
        int buttonHeight = 30; 
        int buttonWidth = 90; 
        int verticalSpacing = 36; 
        int horizontalSpacing = 21; 

        JLabel labelId = new JLabel("ID");
        labelId.setBounds(xLabel, yPos, 121, labelHeight);
        tela.getContentPane().add(labelId); 
        textoId = new JTextField();
        textoId.setBounds(xTextField, yPos, fieldWidth, textFieldHeight);
        tela.getContentPane().add(textoId); 
        yPos += verticalSpacing; 

        JLabel labelNome = new JLabel("Nome");
        labelNome.setBounds(xLabel, yPos, 121, labelHeight);
        tela.getContentPane().add(labelNome); 
        textoNome = new JTextField();
        textoNome.setBounds(xTextField, yPos, fieldWidth, textFieldHeight);
        tela.getContentPane().add(textoNome); 
        yPos += verticalSpacing;

        JLabel labelAutorizadoAcao = new JLabel("Autorizado ação");
        labelAutorizadoAcao.setBounds(xLabel, yPos, 121, labelHeight);
        tela.getContentPane().add(labelAutorizadoAcao); 

        String[] options = {"true", "false"};
        comboAutorizadoAcao = new JComboBox<>(options);
        comboAutorizadoAcao.setBounds(xTextField, yPos, fieldWidth, textFieldHeight);
        tela.getContentPane().add(comboAutorizadoAcao); 

        yPos += verticalSpacing;

        JLabel labelSaldoAcao = new JLabel("Saldo ação");
        labelSaldoAcao.setBounds(xLabel, yPos, 121, labelHeight);
        tela.getContentPane().add(labelSaldoAcao); 
        textoSaldoAcao = new JTextField();
        textoSaldoAcao.setBounds(xTextField, yPos, fieldWidth, textFieldHeight);
        tela.getContentPane().add(textoSaldoAcao); 
        yPos += verticalSpacing;

        JLabel labelSaldoTituloDivida = new JLabel("Saldo título dívida");
        labelSaldoTituloDivida.setBounds(xLabel, yPos, 121, labelHeight);
        tela.getContentPane().add(labelSaldoTituloDivida); 
        textoSaldoTituloDivida = new JTextField();
        textoSaldoTituloDivida.setBounds(xTextField, yPos, fieldWidth, textFieldHeight);
        tela.getContentPane().add(textoSaldoTituloDivida); 
        yPos += verticalSpacing;

        botIncluir = new JButton("Incluir");
        botIncluir.setBounds(xLabel, yPos, buttonWidth, buttonHeight);
        tela.getContentPane().add(botIncluir); 

        JButton botVoltar = new JButton("Voltar");
        botVoltar.setBounds(xLabel + buttonWidth + horizontalSpacing, yPos, buttonWidth, buttonHeight);
        botVoltar.addActionListener(e -> {
            NavegacaoEntidadeOperadora navegacaoEntidadeOperadora = new NavegacaoEntidadeOperadora();
            navegacaoEntidadeOperadora.setVisible(true);
            tela.dispose(); 
        });

        tela.getContentPane().add(botVoltar);
        JButton botLimpar = new JButton("Limpar");
        botLimpar.setBounds(xLabel + 220, yPos, 100, 30);
        botLimpar.addActionListener(e -> {
            textoId.setText("");
            textoNome.setText("");
            comboAutorizadoAcao.setSelectedIndex(0);
            textoSaldoAcao.setText("");
            textoSaldoTituloDivida.setText("");
        });
        tela.getContentPane().add(botLimpar);

        botIncluir.addActionListener(e -> {
            try {
                int id = Integer.parseInt(textoId.getText());
                String nome = textoNome.getText();
                boolean autorizadoAcao = Boolean.parseBoolean(comboAutorizadoAcao.getSelectedItem().toString());

                double saldoTituloDivida = Double.parseDouble(textoSaldoTituloDivida.getText());
                double saldoAcao = Double.parseDouble(textoSaldoAcao.getText());

                EntidadeOperadora entidadeOperadora = new EntidadeOperadora(id, nome, autorizadoAcao);

                entidadeOperadora.ajustarSaldoAcao(saldoAcao);
                entidadeOperadora.ajustarSaldoTituloDivida(saldoTituloDivida);

                String msg = mediatorEntidadeOperadora.incluir(entidadeOperadora);
                if (msg == null) {
                    JOptionPane.showMessageDialog(null, "Entidade Operadora incluída com sucesso");
                } else {
                    JOptionPane.showMessageDialog(null, msg);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erro ao incluir: " + ex.getMessage());
            }
        });
    }
}
