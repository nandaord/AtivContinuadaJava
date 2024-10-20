package br.com.cesarschool.poo.titulos.telas.entidadeOperadora;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import java.util.logging.Logger;

import java.util.logging.Level;

import br.com.cesarschool.poo.titulos.entidades.EntidadeOperadora;
import br.com.cesarschool.poo.titulos.mediators.MediatorEntidadeOperadora;

public class TelaBuscarEntidadeOperadora {
    private JFrame tela; 
    private JTextField textoId;
    private JTextField textoNome;
    private JTextField textoAutorizacaoAcao;
    private JTextField textoSaldoAcao;
    private JTextField textoSaldoTituloDivida;
    private JButton botBuscar; 

    private MediatorEntidadeOperadora mediatorEntidadeOperadora = MediatorEntidadeOperadora.getInstancia();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                TelaBuscarEntidadeOperadora window = new TelaBuscarEntidadeOperadora();
                window.tela.setVisible(true);
            } catch (Exception e) {
                Logger.getLogger(TelaBuscarEntidadeOperadora.class.getName()).log(Level.SEVERE, null, e);
            }
        });
    }

    public TelaBuscarEntidadeOperadora() {
        initialize();
    }

    public void setVisible(boolean visibilidade) {
        tela.setVisible(visibilidade); 
    }

    private void criarTela() { 
        tela = new JFrame("Buscar Entidade Operadora");
        tela.setBounds(100, 100, 556, 370);
        tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tela.getContentPane().setLayout(null);
    }

    private void initialize() {
        criarTela();

        int yPos = 40; // Posição inicial y
        int xLabel = 41; // Posição x para Labels
        int xTextField = 183; // Posição x para TextFields
        int espacoY = 36; // Espaçamento vertical
        int alturaLabel = 20; // Altura para Labels
        int alturaTextField = 26; // Altura para TextFields

        // COMPONENTE 1
        JLabel labelId = new JLabel("ID");
        labelId.setBounds(xLabel, yPos, 121, alturaLabel);
        tela.getContentPane().add(labelId); 
        
        textoId = new JTextField();
        textoId.setBounds(xTextField, yPos, 78, alturaTextField);
        tela.getContentPane().add(textoId);
        yPos += espacoY;

        // COMPONENTE 2
        JLabel labelNome = new JLabel("Nome");
        labelNome.setBounds(xLabel, yPos, 121, alturaLabel);
        tela.getContentPane().add(labelNome);
        
        textoNome = new JTextField();
        textoNome.setBounds(xTextField, yPos, 78, alturaTextField);
        tela.getContentPane().add(textoNome); 
        yPos += espacoY;

        // COMPONENTE 3
        JLabel labelAutorizacaoAcao = new JLabel("Autorização ação");
        labelAutorizacaoAcao.setBounds(xLabel, yPos, 121, alturaLabel);
        tela.getContentPane().add(labelAutorizacaoAcao);
        
        textoAutorizacaoAcao = new JTextField();
        textoAutorizacaoAcao.setBounds(xTextField, yPos, 78, alturaTextField);
        tela.getContentPane().add(textoAutorizacaoAcao);
        yPos += espacoY;

        // COMPONENTE 4
        JLabel labelSaldoAcao = new JLabel("Saldo ação");
        labelSaldoAcao.setBounds(xLabel, yPos, 121, alturaLabel);
        tela.getContentPane().add(labelSaldoAcao); 
        
        textoSaldoAcao = new JTextField();
        textoSaldoAcao.setBounds(xTextField, yPos, 78, alturaTextField);
        tela.getContentPane().add(textoSaldoAcao); 
        yPos += espacoY;

        // COMPONENTE 5
        JLabel labelSaldoTituloDivida = new JLabel("Saldo título dívida");
        labelSaldoTituloDivida.setBounds(xLabel, yPos, 121, alturaLabel);
        tela.getContentPane().add(labelSaldoTituloDivida); 
        
        textoSaldoTituloDivida = new JTextField();
        textoSaldoTituloDivida.setBounds(xTextField, yPos, 78, alturaTextField);
        tela.getContentPane().add(textoSaldoTituloDivida); 
        yPos += espacoY;

        // COMPONENTE 6
        botBuscar = new JButton("Buscar");
        botBuscar.setBounds(xLabel, yPos, 90, 30);
        tela.getContentPane().add(botBuscar);

        // Adicionando a ação do botão
        botBuscar.addActionListener(e -> {
            try {
                int id = Integer.parseInt(textoId.getText());
                EntidadeOperadora entidadeOperadora = mediatorEntidadeOperadora.buscar(id);
                
                if (entidadeOperadora != null) {
                    textoNome.setText(entidadeOperadora.getNome());
                    textoAutorizacaoAcao.setText(String.valueOf(entidadeOperadora.isAutorizadoAcao()));
                    textoSaldoAcao.setText(String.valueOf(entidadeOperadora.getSaldoAcao()));
                    textoSaldoTituloDivida.setText(String.valueOf(entidadeOperadora.getSaldoTituloDivida()));
                } else {
                    JOptionPane.showMessageDialog(null, "Entidade não encontrada");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erro ao buscar: " + ex.getMessage());
            }
        });
    }
}
