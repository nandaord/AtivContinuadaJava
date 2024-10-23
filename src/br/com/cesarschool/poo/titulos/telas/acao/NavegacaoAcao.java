package br.com.cesarschool.poo.titulos.telas.acao;

import javax.swing.*;

import br.com.cesarschool.poo.titulos.TelaPrincipal;

public class NavegacaoAcao {
    private JFrame tela; 

    private static final int LARGURA_JANELA = 450;
    private static final int ALTURA_JANELA = 450;
    private static final int LARGURA_BOTAO = 200;
    private static final int ALTURA_BOTAO = 30;

    public static void main(String[] args) {
        try {
            // Define o look and feel "Nimbus" ou um outro disponível no sistema
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
                NavegacaoAcao window = new NavegacaoAcao();
                window.tela.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public NavegacaoAcao() {
        initialize();
    }

    public void setVisible(boolean visibilidade) {
        tela.setVisible(visibilidade);
    }

    private void initialize() {
        tela = new JFrame("Navegação Ação");
        tela.setSize(LARGURA_JANELA, ALTURA_JANELA);
        tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tela.setLayout(null);
        tela.setLocationRelativeTo(null);

        tela.getContentPane().setBackground(new java.awt.Color(190, 190, 190));

        JButton botIncluir = new JButton("Incluir");
        botIncluir.setBounds(100, 30, LARGURA_BOTAO, ALTURA_BOTAO);
        botIncluir.addActionListener(e -> abrirTelaIncluir());
        tela.getContentPane().add(botIncluir);

        JButton botAlterar = new JButton("Alterar");
        botAlterar.setBounds(100, 80, LARGURA_BOTAO, ALTURA_BOTAO);
        botAlterar.addActionListener(e -> abrirTelaAlterarAcao());
        tela.getContentPane().add(botAlterar);

        JButton botBuscar = new JButton("Buscar"); 
        botBuscar.setBounds(100, 130, LARGURA_BOTAO, ALTURA_BOTAO);
        botBuscar.addActionListener(e -> abrirTelaBuscarAcao());
        tela.getContentPane().add(botBuscar);
        
        JButton botExcluir = new JButton("Excluir");
        botExcluir.setBounds(100, 180, LARGURA_BOTAO, ALTURA_BOTAO);
        botExcluir.addActionListener(e -> abrirTelaExcluirAcao());
        tela.getContentPane().add(botExcluir);

        JButton botVoltar = new JButton("Voltar");
        botVoltar.setBounds(150, 230, 100, ALTURA_BOTAO);
        botVoltar.addActionListener(e -> voltarTelaPrincipal());
        tela.getContentPane().add(botVoltar);
    }

    private void abrirTelaAlterarAcao() {
        TelaAlterarAcao telaAlterarAcao = new TelaAlterarAcao();
        telaAlterarAcao.setVisible(true);
        tela.dispose();
    }

    private void abrirTelaIncluir() {
        TelaIncluirAcao telaIncluirAcao = new TelaIncluirAcao();
        telaIncluirAcao.setVisible(true);
        tela.dispose();
    }

    private void abrirTelaBuscarAcao() {
        TelaBuscarAcao telaBuscarAcao = new TelaBuscarAcao();
        telaBuscarAcao.setVisible(true);
        tela.dispose();
    }

    private void abrirTelaExcluirAcao() {
        TelaExcluirAcao telaExcluirAcao = new TelaExcluirAcao();
        telaExcluirAcao.setVisible(true);
        tela.dispose();
    }

    private void voltarTelaPrincipal() {
        TelaPrincipal telaPrincipal = new TelaPrincipal();
        telaPrincipal.setVisible(true);
        tela.dispose();
    }
}
