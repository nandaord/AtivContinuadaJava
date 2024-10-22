package br.com.cesarschool.poo.titulos.telas.entidadeOperadora;

import javax.swing.*;
import java.util.logging.Logger;
import java.util.logging.Level;

import br.com.cesarschool.poo.titulos.entidades.EntidadeOperadora;
import br.com.cesarschool.poo.titulos.mediators.MediatorEntidadeOperadora;

public class TelaBuscarEntidadeOperadora{

    private JFrame tela;
    private JTextField textoId, textoNome, textoAutorizadoAcao, textoSaldoAcao, textoSaldoTituloDivida;
    private JButton botaoBuscar;
    private MediatorEntidadeOperadora mediatorEntidadeOperadora = MediatorEntidadeOperadora.getInstance();

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
                TelaBuscarEntidadeOperadora window = new TelaBuscarEntidadeOperadora();
                window.tela.setVisible(true);
            } catch (Exception e) {
                Logger.getLogger(TelaBuscarEntidadeOperadora.class.getName()).log(Level.SEVERE, null, e);
            }
        });
    }

    public TelaBuscarEntidadeOperadora() {
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
        tela = new JFrame("Buscar Entidade Operadora");
        tela.setBounds(100, 100, 556, 370);
        tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tela.getContentPane().setLayout(null);
        tela.getContentPane().setBackground(new java.awt.Color(190, 190, 190));
    }

    private void criarComponentes() {
        int yPos = 40;

        textoId = adicionarComponente("ID atual", yPos);
        yPos += 36;
        textoNome = adicionarComponente("Nome", yPos);
        desabilitarCampo(textoNome);
        yPos += 36;

        textoAutorizadoAcao = adicionarComponente("Autorizado Ação", yPos);
        desabilitarCampo(textoAutorizadoAcao);
        yPos += 36;
        textoSaldoAcao = adicionarComponente("Saldo Ação", yPos);
        desabilitarCampo(textoSaldoAcao);
        yPos += 36;

        textoSaldoTituloDivida = adicionarComponente("Saldo Entidade Operadora", yPos);
        desabilitarCampo(textoSaldoTituloDivida);
        yPos += 36;

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
        int yPos = 220;

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
            EntidadeOperadora entidadeOperadora = mediatorEntidadeOperadora.buscar(id);
            if (entidadeOperadora == null) {
                JOptionPane.showMessageDialog(null, "Erro ao buscar entidade operadora");
            } else {
                preencherCampos(entidadeOperadora);
                JOptionPane.showMessageDialog(null, "Entidade Operadora encontrada com sucesso!");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Por favor, insira um ID válido.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar entidade operadora: " + ex.getMessage());
        }
    }

    private void preencherCampos(EntidadeOperadora entidadeOperadora) {
        textoNome.setText(entidadeOperadora.getNome());
        textoAutorizadoAcao.setText(Boolean.toString(entidadeOperadora.getAutorizadoAcao()));
        textoSaldoAcao.setText(Double.toString(entidadeOperadora.getSaldoAcao()));
        textoSaldoTituloDivida.setText(Double.toString(entidadeOperadora.getSaldoTituloDivida()));
    }

    private void voltar() {
        NavegacaoEntidadeOperadora navegacaoEntidadeOperadora = new NavegacaoEntidadeOperadora();
        navegacaoEntidadeOperadora.setVisible(true);
        tela.dispose();
    }

    private void limparCampos() {
        textoId.setText("");
        textoNome.setText("");
        textoAutorizadoAcao.setText("");
        textoSaldoAcao.setText("");
        textoSaldoTituloDivida.setText("");
    }


}
