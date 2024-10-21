package br.com.cesarschool.poo.titulos.telas.acao;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import br.com.cesarschool.poo.titulos.mediators.MediatorAcao;

public class TelaExcluirAcao {
    private JFrame tela;
    private JTextField textoId;
    private JButton botExcluir;
    private MediatorAcao mediatorAcao = MediatorAcao.getInstance();

    public void setVisible(boolean b) {
        tela.setVisible(b);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                TelaExcluirAcao window = new TelaExcluirAcao();
                window.tela.setVisible(true);
            } catch (Exception e) {
                Logger.getLogger(TelaExcluirAcao.class.getName()).log(Level.SEVERE, null, e);
            }
        });
    }

    public TelaExcluirAcao() {
        initialize();
    }

    private void criarTela() {
        tela = new JFrame("Excluir Ação");
        tela.setBounds(100, 100, 556, 370);
        tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tela.getContentPane().setLayout(null);
    }

    private void initialize() {
        criarTela();
        
        int yPos = 40;
        int xLabel = 41;

        JLabel labelId = new JLabel("ID da ação");
        labelId.setBounds(xLabel, yPos, 121, 20);
        tela.getContentPane().add(labelId);

        xLabel += 110;
        textoId = new JTextField();
        textoId.setBounds(xLabel, yPos, 122, 26);
        tela.getContentPane().add(textoId);
        yPos += 36;

        botExcluir = new JButton("Excluir");
        botExcluir.setBounds(xLabel, yPos, 90, 30);
        tela.getContentPane().add(botExcluir);

        JButton botVoltar = new JButton("Voltar");
        botVoltar.setBounds(xLabel + 100, yPos, 90, 30);
        botVoltar.addActionListener(e -> {
            NavegacaoAcao navegacaoAcao = new NavegacaoAcao();
            navegacaoAcao.setVisible(true);
            tela.dispose();
        });
        tela.getContentPane().add(botVoltar);
        
        JButton botLimpar = new JButton("Limpar");
        botLimpar.setBounds(xLabel + 200, yPos, 90, 30);
        botLimpar.addActionListener(e -> {
            textoId.setText("");
        });
        tela.getContentPane().add(botLimpar);

        botExcluir.addActionListener(e -> {
            try {
                int id = Integer.parseInt(textoId.getText());            
                String msg = mediatorAcao.excluir(id);
                if (msg == null) {
                    JOptionPane.showMessageDialog(null, "Ação excluída com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(null, "Erro ao excluir ação: " + msg);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Por favor, insira um ID válido.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erro ao excluir ação: " + ex.getMessage());
            }
        });
    }
}
