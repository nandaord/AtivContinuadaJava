package br.com.cesarschool.poo.titulos;

import javax.swing.*;
import br.com.cesarschool.poo.titulos.telas.acao.NavegacaoAcao;
import br.com.cesarschool.poo.titulos.telas.entidadeOperadora.NavegacaoEntidadeOperadora;
import br.com.cesarschool.poo.titulos.telas.tituloDivida.NavegacaoTituloDivida;
import br.com.cesarschool.poo.titulos.telas.transacao.TelaOperacao;

public class TelaPrincipal {

    private JFrame tela;

    public static void main(String[] args){
        SwingUtilities.invokeLater(() -> {
            try {
                TelaPrincipal janela = new TelaPrincipal();
                janela.tela.setVisible(true);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro ao inicializar a aplicação: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    public TelaPrincipal(){
        initialize();
    }

    public void setVisible(boolean visibilidade){
        tela.setVisible(visibilidade);
    }

    private void initialize(){
        tela = new JFrame("Tela Inicial");
        tela.setSize(450, 400);
        tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tela.setLayout(null);
        tela.setLocationRelativeTo(null);  // Centraliza a janela na tela

        // botão de navegação ação
        JButton botAcao = new JButton("Navegação Ação");
        botAcao.setBounds(100, 30, 200, 30);
        botAcao.addActionListener(e -> abrirNavegacaoAcao());
        tela.getContentPane().add(botAcao);

        // botão de título dívida
        JButton botCadastro = new JButton("Navegação Título Dívida");
        botCadastro.setBounds(100, 80, 200, 30);
        botCadastro.addActionListener(e -> abrirNavegacaoTituloDivida());
        tela.getContentPane().add(botCadastro);

        // botão de entidade operadora
        JButton botEntidade = new JButton("Navegação Entidade Operadora");
        botEntidade.setBounds(100, 130, 200, 30);
        botEntidade.addActionListener(e -> abrirEntidadeOperadora());
        tela.getContentPane().add(botEntidade);

        // botão de operação
        JButton botOperacao = new JButton("Navegação Operação");
        botOperacao.setBounds(100, 180, 200, 30);
        botOperacao.addActionListener(e -> abrirOperacao());
        tela.getContentPane().add(botOperacao);

        // botão de sair
        JButton botSair = new JButton("Sair");
        botSair.setBounds(100, 230, 200, 30);
        botSair.addActionListener(e -> System.exit(0)); // Fecha a aplicação
        tela.getContentPane().add(botSair);
    }

    private void abrirNavegacaoAcao(){
        NavegacaoAcao navegacaoAcao = new NavegacaoAcao();
        navegacaoAcao.setVisible(true);
        tela.setVisible(false);
    }

    private void abrirNavegacaoTituloDivida(){
        NavegacaoTituloDivida navegacaoTituloDivida = new NavegacaoTituloDivida();
        navegacaoTituloDivida.setVisible(true);
        tela.setVisible(false);
    }

    private void abrirEntidadeOperadora(){
        NavegacaoEntidadeOperadora navegacaoEntidadeOperadora = new NavegacaoEntidadeOperadora();
        navegacaoEntidadeOperadora.setVisible(true);
        tela.setVisible(false);
    }

    private void abrirOperacao(){
        TelaOperacao telaOperacao = new TelaOperacao();
        telaOperacao.setVisible(true);
        tela.setVisible(false);
    }
}
