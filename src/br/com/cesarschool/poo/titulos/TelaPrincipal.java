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
                TelaPrincipal window = new TelaPrincipal();
                window.tela.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
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
        tela.setSize(450,400);
        tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tela.setLayout(null);
        
        //botão de adição
        JButton botAdicao = new JButton("Navegação Ação");
        botAdicao.setBounds(100,30,200,30);
        botAdicao.addActionListener(e -> abrirNavegacaoAcao());
        tela.getContentPane().add(botAdicao);

        //botão de cadastro
        JButton botCadastro = new JButton("Navegação Ação");
        botCadastro.setBounds(100,30,200,30);
        botCadastro.addActionListener(e -> abrirNavegacaoTituloDivida());
        tela.getContentPane().add(botCadastro);

        //botão de entidade
        JButton botEntidade = new JButton("Navegação Ação");
        botEntidade.setBounds(100,30,200,30);
        botEntidade.addActionListener(e -> abrirEntidadeOpeadora());
        tela.getContentPane().add(botEntidade);

        //botão de operacao
        JButton botOperacao = new JButton("Navegação Ação");
        botOperacao.setBounds(100,30,200,30);
        botOperacao.addActionListener(e -> abrirOperacao());
        tela.getContentPane().add(botOperacao);
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

    private void abrirEntidadeOpeadora(){
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
