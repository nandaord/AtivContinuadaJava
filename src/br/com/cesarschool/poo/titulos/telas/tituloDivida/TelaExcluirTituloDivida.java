package br.com.cesarschool.poo.titulos.telas.tituloDivida;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import java.util.logging.Logger;
import java.util.logging.Level;
import br.com.cesarschool.poo.titulos.mediators.MediatorTituloDivida;

public class TelaExcluirTituloDivida {
    private JFrame tela;
    private JTextField textoId;
    private JButton botaoExcluir;
    private MediatorTituloDivida mediatorTituloDivida = MediatorTituloDivida.getInstance();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                TelaExcluirTituloDivida window = new TelaExcluirTituloDivida();
                window.tela.setVisible(true);
            } catch (Exception e) {
                Logger.getLogger(TelaExcluirTituloDivida.class.getName()).log(Level.SEVERE, null, e);
            }
        });
    }

    public TelaExcluirTituloDivida() {
        initialize();
    }

    public void setVisible(boolean visibilidade) {
        tela.setVisible(visibilidade);
    }

    private void createFrame() {
        tela = new JFrame("Excluir Título Dívida");
        tela.setBounds(100, 100, 556, 370);
        tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tela.getContentPane().setLayout(null);
    }

    private void initialize() {
        createFrame();

        int yPos = 40;
        int xLabel = 41;
        int xTextField = 183;

        JLabel labelId = new JLabel("ID do título");
        labelId.setBounds(xLabel, yPos, 121, 20);
        tela.getContentPane().add(labelId);

        textoId = new JTextField();
        textoId.setBounds(xTextField, yPos, 122, 26);
        tela.getContentPane().add(textoId);
        yPos += 36;

        botaoExcluir = new JButton("Excluir");
        botaoExcluir.setBounds(xLabel, yPos, 90, 30);
        tela.getContentPane().add(botaoExcluir);

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(150, yPos, 90, 30);
        btnVoltar.addActionListener(e -> {
            NavegacaoTituloDivida navegacaoTituloDivida = new NavegacaoTituloDivida();
            navegacaoTituloDivida.setVisible(true);
            tela.dispose();
        });
        tela.getContentPane().add(btnVoltar);

        JButton btnLimpar = new JButton("Limpar");
        btnLimpar.setBounds(xLabel + 220, yPos, 100, 30);
        btnLimpar.addActionListener(e -> {
            textoId.setText("");
        });
        tela.getContentPane().add(btnLimpar);

        botaoExcluir.addActionListener(e -> {
            try {
                int id = Integer.parseInt(textoId.getText());
                String msg = mediatorTituloDivida.excluir(id);
                if (msg == null) {
                    JOptionPane.showMessageDialog(null, "Título excluído com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(null, "Erro ao excluir título: " + msg);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Por favor, insira um ID válido.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erro ao excluir título: " + ex.getMessage());
            }
        });
    }
}
