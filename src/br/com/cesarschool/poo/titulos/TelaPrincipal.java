package br.com.cesarschool.poo.titulos;

import javax.swing.*;
import br.com.cesarschool.poo.titulos.telas.acao.NavegacaoAcao;
import br.com.cesarschool.poo.titulos.telas.entidadeOperadora.NavegacaoEntidadeOperadora;
import br.com.cesarschool.poo.titulos.telas.tituloDivida.NavegacaoTituloDivida;
import br.com.cesarschool.poo.titulos.telas.transacao.TelaOperacao;

public class TelaPrincipal {

    private JFrame tela;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                TelaPrincipal janela = new TelaPrincipal();
                janela.tela.setVisible(true);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro ao inicializar a aplicação: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    public TelaPrincipal() {
        initialize();
    }

    public void setVisible(boolean visibilidade) {
        tela.setVisible(visibilidade);
    }

    private void initialize() {
        tela = new JFrame("Tela Inicial");
        tela.setSize(600, 500); // Aumenta o tamanho da janela
        tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tela.setLayout(null);
        tela.setLocationRelativeTo(null);  // Centraliza a janela na tela

        int buttonWidth = 200; // Largura do botão
        int buttonHeight = 30; // Altura do botão
        int buttonX = (tela.getWidth() - buttonWidth) / 2; // Coordenada X para centralizar o botão

        // botão de navegação ação
        JButton botAcao = new JButton("Navegação Ação");
        botAcao.setBounds(buttonX, 30, buttonWidth, buttonHeight);
        botAcao.addActionListener(e -> abrirNavegacaoAcao());
        tela.getContentPane().add(botAcao);

        // botão de título dívida
        JButton botCadastro = new JButton("Navegação Título Dívida");
        botCadastro.setBounds(buttonX, 80, buttonWidth, buttonHeight);
        botCadastro.addActionListener(e -> abrirNavegacaoTituloDivida());
        tela.getContentPane().add(botCadastro);

        // botão de entidade operadora
        JButton botEntidade = new JButton("Navegação Entidade Operadora");
        botEntidade.setBounds(buttonX, 130, buttonWidth, buttonHeight);
        botEntidade.addActionListener(e -> abrirEntidadeOperadora());
        tela.getContentPane().add(botEntidade);

        // botão de operação
        JButton botOperacao = new JButton("Navegação Operação");
        botOperacao.setBounds(buttonX, 180, buttonWidth, buttonHeight);
        botOperacao.addActionListener(e -> abrirOperacao());
        tela.getContentPane().add(botOperacao);

        // botão de sair
        JButton botSair = new JButton("Sair");
        botSair.setBounds(buttonX, 230, buttonWidth, buttonHeight);
        botSair.addActionListener(e -> System.exit(0)); // Fecha a aplicação
        tela.getContentPane().add(botSair);
    }

    private void abrirNavegacaoAcao() {
        NavegacaoAcao navegacaoAcao = new NavegacaoAcao();
        navegacaoAcao.setVisible(true);
        tela.setVisible(false);
    }

    private void abrirNavegacaoTituloDivida() {
        NavegacaoTituloDivida navegacaoTituloDivida = new NavegacaoTituloDivida();
        navegacaoTituloDivida.setVisible(true);
        tela.setVisible(false);
    }

    private void abrirEntidadeOperadora() {
        NavegacaoEntidadeOperadora navegacaoEntidadeOperadora = new NavegacaoEntidadeOperadora();
        navegacaoEntidadeOperadora.setVisible(true);
        tela.setVisible(false);
    }

    private void abrirOperacao() {
        TelaOperacao telaOperacao = new TelaOperacao();
        telaOperacao.setVisible(true);
        tela.setVisible(false);
    }
}
