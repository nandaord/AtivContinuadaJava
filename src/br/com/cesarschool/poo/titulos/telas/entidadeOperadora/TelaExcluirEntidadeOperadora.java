package br.com.cesarschool.poo.titulos.telas.entidadeOperadora;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import br.com.cesarschool.poo.titulos.mediators.MediatorEntidadeOperadora;

public class TelaExcluirEntidadeOperadora {
    private JFrame tela;
    private JTextField textoId;
    private JButton botExcluir; 
    private MediatorEntidadeOperadora mediatorEntidadeOperadora = MediatorEntidadeOperadora.getInstancia();
    
    private static final int ESPACO_VERTICAL = 36;
    private static final int LARGURA_TEXTFIELD = 122;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                TelaExcluirEntidadeOperadora window = new TelaExcluirEntidadeOperadora();
                window.tela.setVisible(true); 
            } catch (Exception e) {
                Logger.getLogger(TelaExcluirEntidadeOperadora.class.getName()).log(Level.SEVERE, null, e);
            }
        });
    }

    public TelaExcluirEntidadeOperadora() {
        initialize();
    }

    public void setVisible(boolean visibilidade) {
        tela.setVisible(visibilidade); 
    }

    private void criarTela() { 
        tela = new JFrame("Excluir Entidade Operadora");
        tela.setBounds(100, 100, 556, 370);
        tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tela.getContentPane().setLayout(null);
    }

    private void initialize() {
        criarTela(); 
        
        int yPos = 40; 
        int xLabel = 41; 
        int xTextField = 183; 

        // COMPONENTE 1
        JLabel labelId = new JLabel("ID da Entidade Operadora");
        labelId.setBounds(xLabel, yPos, 121, 20);
        tela.getContentPane().add(labelId);
        
        textoId = new JTextField();
        textoId.setBounds(xTextField, yPos, LARGURA_TEXTFIELD, 26);
        tela.getContentPane().add(textoId); 
        yPos += ESPACO_VERTICAL; 

        // COMPONENTE 2
        botExcluir = new JButton("Excluir"); 
        botExcluir.setBounds(xLabel, yPos, 90, 30);
        tela.getContentPane().add(botExcluir);

        JButton botVoltar = new JButton("Voltar");
        botVoltar.setBounds(150, yPos, 100, 30);
        botVoltar.addActionListener(e -> {
            NavegacaoEntidadeOperadora navegacaoEntidadeOperadora = new NavegacaoEntidadeOperadora();
            navegacaoEntidadeOperadora.setVisible(true);
            tela.dispose(); 
        });
        tela.getContentPane().add(botVoltar); 
        
        JButton botLimpar = new JButton("Limpar");
        botLimpar.setBounds(xLabel + 230, yPos, 100, 30);
        botLimpar.addActionListener(e -> {
            textoId.setText("");
        });
        tela.getContentPane().add(botLimpar); 
        
        botExcluir.addActionListener(e -> { 
            try {
                int id = Integer.parseInt(textoId.getText());
                int confirmacao = JOptionPane.showConfirmDialog(tela,
                    "Tem certeza que deseja excluir?", 
                    "Confirmação", 
                    JOptionPane.YES_NO_OPTION);
                
                if (confirmacao == JOptionPane.YES_OPTION) {
                    String msg = mediatorEntidadeOperadora.excluir(id);
                    if (msg == null) {
                        JOptionPane.showMessageDialog(null, "Entidade Operadora excluída com sucesso!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Erro ao excluir Entidade Operadora: " + msg);
                    }
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erro ao excluir Entidade Operadora: " + ex.getMessage());
            }
        });
    }
}
