package br.com.cesarschool.poo.titulos.telas.tituloDivida;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import java.util.logging.Logger;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import br.com.cesarschool.poo.titulos.entidades.TituloDivida;
import br.com.cesarschool.poo.titulos.mediators.MediatorTituloDivida;

public class TelaBuscarTituloDivida {
    private JFrame tela;
    private JTextField textoId, textoNome, textoTaxaJuros, textoDataValidade;
    private JButton botaoBuscar;
    private MediatorTituloDivida mediatorTituloDivida = MediatorTituloDivida.getInstance();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                TelaBuscarTituloDivida window = new TelaBuscarTituloDivida();
                window.tela.setVisible(true);
            } catch (Exception e) {
                Logger.getLogger(TelaBuscarTituloDivida.class.getName()).log(Level.SEVERE, null, e);
            }
        });
    }

    public TelaBuscarTituloDivida() {
        inicializar();
    }

    public void setVisible(boolean visibilidade) {
        tela.setVisible(visibilidade);
    }

    private void inicializar() {
        criarFrame();
        criarComponentes();
        criarBotoes();
    }

    private void criarFrame() {
        tela = new JFrame("Buscar Título Dívida");
        tela.setBounds(100, 100, 556, 370);
        tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tela.getContentPane().setLayout(null);
    }

    private void criarComponentes() {
        int yPos = 40;

        textoId = adicionarComponente("ID atual", yPos);
        yPos += 36;
        textoNome = adicionarComponente("Nome", yPos);
        desabilitarCampo(textoNome);
        yPos += 36;
        textoTaxaJuros = adicionarComponente("Valor", yPos);
        desabilitarCampo(textoTaxaJuros);
        yPos += 36;
        textoDataValidade = adicionarComponente("Data de Validade", yPos);
        desabilitarCampo(textoDataValidade);
    }

    private JTextField adicionarComponente(String labelText, int yPos) {
        int xLabel = 41, xTextField = 183;
        JLabel label = new JLabel(labelText);
        label.setBounds(xLabel, yPos, 121, 20);
        tela.getContentPane().add(label);
        JTextField textField = new JTextField();
        textField.setBounds(xTextField, yPos, 122, 26);
        tela.getContentPane().add(textField);
        return textField;
    }

    private void desabilitarCampo(JTextField campo) {
        campo.setEnabled(false);
        campo.setEditable(false);
    }

    private void criarBotoes() {
        int yPos = 184;

        adicionarBotao("Buscar", yPos, e -> buscarTitulo(),50);
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

    private void buscarTitulo() {
        try {
            int id = Integer.parseInt(textoId.getText());
            TituloDivida tituloDivida = mediatorTituloDivida.buscar(id);
            if (tituloDivida == null) {
                JOptionPane.showMessageDialog(null, "Erro ao buscar título");
            } else {
                preencherCampos(tituloDivida);
                JOptionPane.showMessageDialog(null, "Título encontrado com sucesso!");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Por favor, insira um ID válido.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar título: " + ex.getMessage());
        }
    }

    private void preencherCampos(TituloDivida tituloDivida) {
        textoNome.setText(tituloDivida.getNome());
        textoTaxaJuros.setText(String.valueOf(tituloDivida.getTaxaJuros()));
        textoDataValidade.setText(tituloDivida.getDataDeValidade().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
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
