package br.com.cesarschool.poo.titulos.telas.acao;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import br.com.cesarschool.poo.titulos.TelaPrincipal;

public class NavegacaoAcao {
    private JFrame frame;

    private static final int LARGURA_JANELA = 450;
    private static final int ALTURA_JANELA = 450;
    private static final int LARGURA_BOTAO = 200;
    private static final int ALTURA_BOTAO = 30;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                NavegacaoAcao window = new NavegacaoAcao();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public NavegacaoAcao() {
        initialize();
    }

    public void setVisible(boolean visibilidade) {
        frame.setVisible(visibilidade);
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

        JButton botEntidade = new JButton("Navegação Entidade Operadora");
        botEntidade.setBounds(100, 130, 200, 30);
        botEntidade.addActionListener(e -> abrirEntidadeOperadora());
        tela.getContentPane().add(botEntidade);

        JButton botOperacao = new JButton("Navegação Operação");
        botOperacao.setBounds(100, 180, 200, 30);
        botOperacao.addActionListener(e -> abrirOperacao());
        tela.getContentPane().add(botOperacao);
     
        JButton botSair = new JButton("Sair");
        botSair.setBounds(100, 230, 200, 30);
        botSair.addActionListener(e -> System.exit(0));
        tela.getContentPane().add(botSair);
    }


    private void abrirTelaAlterarAcao() {
        TelaAlterarAcao telaAlterarAcao = new TelaAlterarAcao();
        telaAlterarAcao.setVisible(true);
        frame.dispose(); // Fecha NavegacaoAcao
    }

    private void abrirTelaIncluir() {
        TelaIncluirAcao telaIncluirAcao = new TelaIncluirAcao();
        telaIncluirAcao.setVisible(true);
        frame.dispose(); // Fecha NavegacaoAcao
    }

    private void abrirTelaBuscarAcao() {
        TelaBuscarAcao telaBuscarAcao = new TelaBuscarAcao();
        telaBuscarAcao.setVisible(true);
        frame.dispose(); // Fecha NavegacaoAcao
    }

    private void abrirTelaExcluirAcao() {
        TelaExcluirAcao telaExcluirAcao = new TelaExcluirAcao();
        telaExcluirAcao.setVisible(true);
        frame.dispose(); // Fecha NavegacaoAcao
    }

    private void voltarTelaPrincipal() {
        TelaPrincipal telaPrincipal = new TelaPrincipal();
        telaPrincipal.setVisible(true);
        frame.dispose(); // Fecha NavegacaoAcao e volta para TelaPrincipal
    }
}
