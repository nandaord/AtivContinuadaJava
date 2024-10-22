package br.com.cesarschool.poo.titulos.telas.tituloDivida;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import br.com.cesarschool.poo.titulos.TelaPrincipal;

public class NavegacaoTituloDivida {

    private JFrame tela;

    private static final int LARGURA_JANELA = 450;
    private static final int ALTURA_JANELA = 450;
    private static final int LARGURA_BOTAO = 200;
    private static final int ALTURA_BOTAO = 30;

    public static void main(String[] args){
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
                NavegacaoTituloDivida janela = new NavegacaoTituloDivida();
                janela.tela.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public NavegacaoTituloDivida() {
        initialize();
    }

    public void setVisible(boolean visibilidade) {
        tela.setVisible(visibilidade);
    }

    private void initialize() {
        tela = new JFrame("Navegação Título Dívida");
        tela.setSize(LARGURA_JANELA, ALTURA_JANELA);
        tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tela.setLayout(null);
        tela.getContentPane().setBackground(new java.awt.Color(190, 190, 190));

        int posY = 30;
        int espacoBotao = 20;

        adicionarBotao("Incluir", posY, e -> abrirTelaIncluirTituloDivida());
        posY += ALTURA_BOTAO + espacoBotao;

        adicionarBotao("Alterar", posY, e -> abrirTelaAlterarTituloDivida());
        posY += ALTURA_BOTAO + espacoBotao;

        adicionarBotao("Buscar", posY, e -> abrirTelaBuscarTituloDivida());
        posY += ALTURA_BOTAO + espacoBotao;

        adicionarBotao("Excluir", posY, e -> abrirTelaExcluirTituloDivida());
        posY += ALTURA_BOTAO + espacoBotao;

        // Botão de voltar
        adicionarBotao("Voltar", posY, e -> {
            TelaPrincipal telaPrincipal = new TelaPrincipal();
            telaPrincipal.setVisible(true);
            tela.dispose();
        }, 100); // Largura personalizada para o botão "Voltar"
    }

    private void adicionarBotao(String titulo, int posY, java.awt.event.ActionListener acao) {
        adicionarBotao(titulo, posY, acao, LARGURA_BOTAO); // Usando largura padrão
    }

    private void adicionarBotao(String titulo, int posY, java.awt.event.ActionListener acao, int largura) {
        JButton botao = new JButton(titulo);
        botao.setBounds((LARGURA_JANELA - largura) / 2, posY, largura, ALTURA_BOTAO); // Centraliza os botões
        botao.addActionListener(acao);
        tela.getContentPane().add(botao);
    }

    private void abrirTelaAlterarTituloDivida() {
        TelaAlterarTituloDivida telaAlterarTituloDivida = new TelaAlterarTituloDivida();
        telaAlterarTituloDivida.setVisible(true);
        tela.setVisible(false);
    }

    private void abrirTelaBuscarTituloDivida() {
        TelaBuscarTituloDivida telaBuscarTituloDivida = new TelaBuscarTituloDivida();
        telaBuscarTituloDivida.setVisible(true);
        tela.setVisible(false);
    }

    private void abrirTelaExcluirTituloDivida() {
        TelaExcluirTituloDivida telaExcluirTituloDivida = new TelaExcluirTituloDivida();
        telaExcluirTituloDivida.setVisible(true);
        tela.setVisible(false);
    }

    private void abrirTelaIncluirTituloDivida() {
        TelaIncluirTituloDivida telaIncluirTituloDivida = new TelaIncluirTituloDivida();
        telaIncluirTituloDivida.setVisible(true);
        tela.setVisible(false);
    }
}
