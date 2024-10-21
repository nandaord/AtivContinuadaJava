package br.com.cesarschool.poo.titulos.telas.tituloDivida;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import java.util.logging.Logger;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;

import br.com.cesarschool.poo.titulos.entidades.TituloDivida;
import br.com.cesarschool.poo.titulos.mediators.MediatorTituloDivida;

public class TelaAlterarTituloDivida {
    private JFrame tela;
    private JTextField textoId, textoNome, textoTaxaJuros, textoDataValidade;
    private MediatorTituloDivida mediatorTituloDivida = MediatorTituloDivida.getInstance();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                TelaAlterarTituloDivida window = new TelaAlterarTituloDivida();
                window.tela.setVisible(true);
            } catch (Exception e) {
                Logger.getLogger(TelaAlterarTituloDivida.class.getName()).log(Level.SEVERE, null, e);
            }
        });
    }

    public TelaAlterarTituloDivida() {
        inicializar();
    }

    public void setVisible(boolean visibilidade) {
        tela.setVisible(visibilidade);
    }

    private void inicializar() {
        criarTela();
        criarComponentes();
        criarBotoes();
    }

    private void criarTela() {
        tela = new JFrame("Alterar Título de Dívida");
        tela.setBounds(100, 100, 556, 370);
        tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tela.getContentPane().setLayout(null);
    }

    private void criarComponentes() {
        int yPos = 40;

        textoId = adicionarComponente("ID atual", yPos);
        yPos += 36;
        textoNome = adicionarComponente("Novo Nome", yPos);
        yPos += 36;
        textoTaxaJuros = adicionarComponente("Nova taxa de juros", yPos);
        yPos += 36;
        textoDataValidade = adicionarComponente("Data de Validade", yPos);
    }

    private JTextField adicionarComponente(String labelText, int yPos) {
        int xLabel = 41, xTextField = 183;
        JLabel label = new JLabel(labelText);
        label.setBounds(xLabel, yPos, 121, 20);
        tela.getContentPane().add(label);
        JTextField textField = new JTextField();
        textField.setBounds(xTextField, yPos, 78, 26);
        tela.getContentPane().add(textField);
        return textField;
    }

    private void criarBotoes() {
        int yPos = 184;

        adicionarBotao("Alterar", yPos, e -> alterarTituloDivida(),50);
        adicionarBotao("Voltar", yPos, e -> voltar(), 180);
        adicionarBotao("Limpar", yPos, e -> limparCampos(), 310);
    }

    private void adicionarBotao(String titulo, int yPos, java.awt.event.ActionListener acao) {
        adicionarBotao(titulo, yPos, acao, 90);
    }

    private void adicionarBotao(String titulo, int yPos, java.awt.event.ActionListener acao, int xOffset) {
        int xLabel = 41;
        JButton botao = new JButton(titulo);
        botao.setBounds(xLabel + xOffset, yPos, 90, 30);
        botao.addActionListener(acao);
        tela.getContentPane().add(botao);
    }

    private void alterarTituloDivida() {
        try {
            int id = Integer.parseInt(textoId.getText());
            String nome = textoNome.getText();
            LocalDate dataValidade = LocalDate.parse(textoDataValidade.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            double taxaJuros = Double.parseDouble(textoTaxaJuros.getText());

            TituloDivida tituloDivida = new TituloDivida(id, nome, dataValidade, taxaJuros);
            String msg = mediatorTituloDivida.alterar(tituloDivida);

            JOptionPane.showMessageDialog(null, msg == null ? "Título alterado com sucesso!" : msg);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao alterar: " + ex.getMessage());
        }
    }

    private void voltar() {
        NavegacaoTituloDivida navegacaoTituloDivida = new NavegacaoTituloDivida();
        navegacaoTituloDivida.setVisible(true);
        tela.dispose();
    }

    private void limparCampos() {
        textoId.setText("");
        textoNome.setText("");
        textoTaxaJuros.setText("");
        textoDataValidade.setText("");
    }
}
