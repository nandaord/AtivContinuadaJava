package br.com.cesarschool.poo.titulos.telas.tituloDivida;

import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Logger;
import java.util.logging.Level;
import br.com.cesarschool.poo.titulos.entidades.TituloDivida;
import br.com.cesarschool.poo.titulos.mediators.MediatorTituloDivida;

public class TelaIncluirTituloDivida {
    private JFrame tela;
    private JTextField textoId, textoNome, textoTaxaJuros, textoDataValidade;
    private JButton botaoIncluir;
    private MediatorTituloDivida mediatorAcao = MediatorTituloDivida.getInstance();

    public static void main(String[] args) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("Não foi possível definir o look and feel.");
        }
        SwingUtilities.invokeLater(() -> {
            try {
                TelaIncluirTituloDivida window = new TelaIncluirTituloDivida();
                window.tela.setVisible(true);
            } catch (Exception e) {
                Logger.getLogger(TelaIncluirTituloDivida.class.getName()).log(Level.SEVERE, null, e);
            }
        });
    }

    public TelaIncluirTituloDivida() {
        inicializar();
    }

    public void setVisible(boolean visibilidade) {
        tela.setVisible(visibilidade);
    }

    private void criarTela() {
        tela = new JFrame("Incluir Título Dívida");
        tela.setBounds(100, 100, 556, 370);
        tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tela.getContentPane().setLayout(null);
        tela.getContentPane().setBackground(new java.awt.Color(190, 190, 190));
    }

    private void inicializar() {
        criarTela();

        int yPos = 40;
        int xLabel = 41;
        int xTextField = 183;

        addLabel("Id", xLabel, yPos);
        textoId = addTextField(xTextField, yPos);
        yPos += 36;

        addLabel("Nome", xLabel, yPos);
        textoNome = addTextField(xTextField, yPos);
        yPos += 36;

        addLabel("Taxa de Juros", xLabel, yPos);
        textoTaxaJuros = addTextField(xTextField, yPos);
        yPos += 36;

        addLabel("Data de Validade", xLabel, yPos);
        textoDataValidade = addTextField(xTextField, yPos);
        yPos += 36;

        botaoIncluir = new JButton("Incluir");
        botaoIncluir.setBounds(xLabel, yPos, 90, 30);
        tela.getContentPane().add(botaoIncluir);

        JButton botaoVoltar = new JButton("Voltar");
        botaoVoltar.setBounds(xLabel + 100, yPos, 100, 30);
        botaoVoltar.addActionListener(e -> {
            new NavegacaoTituloDivida().setVisible(true);
            tela.dispose();
        });
        tela.getContentPane().add(botaoVoltar);

        JButton botaoLimpar = new JButton("Limpar");
        botaoLimpar.setBounds(xLabel + 210, yPos, 100, 30);
        botaoLimpar.addActionListener(e -> {
            textoId.setText("");
            textoNome.setText("");
            textoTaxaJuros.setText("");
            textoDataValidade.setText("");
        });
        tela.getContentPane().add(botaoLimpar);

        botaoIncluir.addActionListener(e -> incluirTitulo());
    }

    private JLabel addLabel(String text, int x, int y) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, 121, 20);
        tela.getContentPane().add(label);
        return label;
    }

    private JTextField addTextField(int x, int y) {
        JTextField textField = new JTextField();
        textField.setBounds(x, y, 78, 26);
        tela.getContentPane().add(textField);
        return textField;
    }

    private void incluirTitulo() {
        try {
            int id = Integer.parseInt(textoId.getText());
            String nome = textoNome.getText();
            LocalDate dataValidade = LocalDate.parse(textoDataValidade.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            double valor = Double.parseDouble(textoTaxaJuros.getText());

            TituloDivida acao = new TituloDivida(id, nome, dataValidade, valor);
            String msg = mediatorAcao.incluir(acao);
            JOptionPane.showMessageDialog(null, msg != null ? msg : "Incluído com sucesso");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Erro: valor inválido - " + e.getMessage());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao incluir: " + ex.getMessage());
        }
    }
}
