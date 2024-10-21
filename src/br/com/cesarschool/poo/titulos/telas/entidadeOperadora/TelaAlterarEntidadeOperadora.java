package br.com.cesarschool.poo.titulos.telas.entidadeOperadora;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import br.com.cesarschool.poo.titulos.entidades.EntidadeOperadora;
import br.com.cesarschool.poo.titulos.mediators.MediatorEntidadeOperadora;

public class TelaAlterarEntidadeOperadora {
    private JFrame tela; 
    private JTextField textoId;
    private JTextField textoNome;
    private JTextField textoSaldoAcao;
    private JTextField textoSaldoTituloDivida;
    private JButton botAlterar;
    private MediatorEntidadeOperadora mediatorEntidadeOperadora = MediatorEntidadeOperadora.getInstance();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                TelaAlterarEntidadeOperadora window = new TelaAlterarEntidadeOperadora();
                window.tela.setVisible(true);
            } catch (Exception e) {
                Logger.getLogger(TelaAlterarEntidadeOperadora.class.getName()).log(Level.SEVERE, null, e);
            }
        });
    }

    public TelaAlterarEntidadeOperadora() {
        initialize();
    }

    public void setVisible(boolean visibilidade) {
        tela.setVisible(visibilidade);
    }

    private void criarTela() { 
        tela = new JFrame("Alterar Entidade Operadora");
        tela.setBounds(100, 100, 556, 370);
        tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tela.getContentPane().setLayout(null);
    }

    private void initialize() {
        criarTela(); 
        int yPos = 40; 
        int xLabel = 41; 
        int xTextField = 183;
        int espacoY = 36;
        int alturaLabel = 20;
        int alturaTextField = 26;
        int alturaBotao = 30;

        // COMPONENTE 1
        JLabel labelId = new JLabel("ID atual");
        labelId.setBounds(xLabel, yPos, 121, alturaLabel);
        tela.getContentPane().add(labelId); 
        
        textoId = new JTextField();
        textoId.setBounds(xTextField, yPos, 78, alturaTextField);
        tela.getContentPane().add(textoId);
        yPos += espacoY;

        // COMPONENTE 2
        JLabel labelNome = new JLabel("Novo Nome");
        labelNome.setBounds(xLabel, yPos, 121, alturaLabel);
        tela.getContentPane().add(labelNome); 
        
        textoNome = new JTextField();
        textoNome.setBounds(xTextField, yPos, 78, alturaTextField);
        tela.getContentPane().add(textoNome); 
        yPos += espacoY;

        // COMPONENTE 3
        JLabel labelAutorizadoAcao = new JLabel("Nova autorização ação");
        labelAutorizadoAcao.setBounds(xLabel, yPos, 121, alturaLabel);
        tela.getContentPane().add(labelAutorizadoAcao);
        
        JComboBox<String> comboAutorizadoAcao = new JComboBox<>(new String[]{"true", "false"});
        comboAutorizadoAcao.setBounds(xTextField, yPos, 78, alturaTextField);
        tela.getContentPane().add(comboAutorizadoAcao);
        yPos += espacoY;

        // COMPONENTE 4
        JLabel labelSaldoAcao = new JLabel("Novo Saldo ação");
        labelSaldoAcao.setBounds(xLabel, yPos, 121, alturaLabel);
        tela.getContentPane().add(labelSaldoAcao); 
        
        textoSaldoAcao = new JTextField();
        textoSaldoAcao.setBounds(xTextField, yPos, 78, alturaTextField);
        tela.getContentPane().add(textoSaldoAcao);
        yPos += espacoY;

        // COMPONENTE 5
        JLabel labelSaldoTituloDivida = new JLabel("Novo saldo título dívida");
        labelSaldoTituloDivida.setBounds(xLabel, yPos, 121, alturaLabel);
        tela.getContentPane().add(labelSaldoTituloDivida);
        
        textoSaldoTituloDivida = new JTextField();
        textoSaldoTituloDivida.setBounds(xTextField, yPos, 78, alturaTextField);
        tela.getContentPane().add(textoSaldoTituloDivida); 
        yPos += espacoY;

        // COMPONENTE 6
        botAlterar = new JButton("Alterar");
        botAlterar.setBounds(xLabel, yPos, 90, alturaBotao);
        tela.getContentPane().add(botAlterar);
        
        JButton botVoltar = new JButton("Voltar"); 
        botVoltar.setBounds(xLabel + 100, yPos, 90, alturaBotao);
        botVoltar.addActionListener(e -> {
            NavegacaoEntidadeOperadora navegacaoEntidadeOperadora = new NavegacaoEntidadeOperadora();
            navegacaoEntidadeOperadora.setVisible(true);
            tela.dispose();
        });
        tela.getContentPane().add(botVoltar);

        JButton botLimpar = new JButton("Limpar");
        botLimpar.setBounds(xLabel + 200, yPos, 100, 30);
        botLimpar.addActionListener(e -> {
            textoId.setText("");
            textoNome.setText("");
            textoSaldoAcao.setText("");
            textoSaldoTituloDivida.setText("");
        });
        tela.getContentPane().add(botLimpar);

        // Adicionando a ação do botão
        botAlterar.addActionListener(e -> { 
            try {
                int id = Integer.parseInt(textoId.getText());
                String nome = textoNome.getText();
                boolean autorizadoAcao = Boolean.parseBoolean(comboAutorizadoAcao.getSelectedItem().toString());
                double saldoTituloDivida = Double.parseDouble(textoSaldoTituloDivida.getText());
                double saldoAcao = Double.parseDouble(textoSaldoAcao.getText());
                
                // Cria o objeto EntidadeOperadora aqui
                EntidadeOperadora entidadeOperadora = new EntidadeOperadora(id, nome, autorizadoAcao);

                String msg = mediatorEntidadeOperadora.alterar(entidadeOperadora);
                if (msg == null) {
                    JOptionPane.showMessageDialog(null, "Entidade alterada com sucesso");
                } else {
                    JOptionPane.showMessageDialog(null, msg);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erro ao alterar: " + ex.getMessage());
            }
        });
    }
}
