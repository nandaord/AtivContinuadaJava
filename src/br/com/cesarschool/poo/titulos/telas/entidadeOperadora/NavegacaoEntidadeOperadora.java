package br.com.cesarschool.poo.titulos.telas.entidadeOperadora;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import br.com.cesarschool.poo.titulos.TelaPrincipal;

public class NavegacaoEntidadeOperadora {
    
    private JFrame tela;
    private static final int LARGURA_JANELA = 450;
    private static final int ALTURA_JANELA = 450;
    private static final int LARGURA_BOTAO = 200;
    private static final int ALTURA_BOTAO = 30;

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
                NavegacaoEntidadeOperadora window = new NavegacaoEntidadeOperadora();
                window.tela.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public NavegacaoEntidadeOperadora() {
        initialize();
    }

    public void setVisible(boolean visibilidade) {
        tela.setVisible(visibilidade);
    }

    private void initialize() {
        tela = new JFrame("Navegação Entidade Operadora");
        tela.setSize(LARGURA_JANELA, ALTURA_JANELA);
        tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tela.setLayout(null);
        tela.getContentPane().setBackground(new java.awt.Color(190, 190, 190));

        // Botão para incluir
        JButton botIncluir = new JButton("Incluir");
        botIncluir.setBounds(100, 80, LARGURA_BOTAO, ALTURA_BOTAO);
        botIncluir.addActionListener(e -> abrirTelaIncluirEntidadeOperadora());
        tela.getContentPane().add(botIncluir);

        // Botão para alterar
        JButton botAlterar = new JButton("Alterar");
        botAlterar.setBounds(100, 30, LARGURA_BOTAO, ALTURA_BOTAO);
        botAlterar.addActionListener(e -> abrirTelaAlterarEntidadeOperadora());
        tela.getContentPane().add(botAlterar);

        // Botão para buscar
        JButton botBuscar = new JButton("Buscar");
        botBuscar.setBounds(100, 130, LARGURA_BOTAO, ALTURA_BOTAO);
        botBuscar.addActionListener(e -> abrirTelaBuscarEntidadeOperadora());
        tela.getContentPane().add(botBuscar);

        // Botão para excluir
        JButton botExcluir = new JButton("Excluir");
        botExcluir.setBounds(100, 180, LARGURA_BOTAO, ALTURA_BOTAO);
        botExcluir.addActionListener(e -> abrirTelaExcluirEntidadeOperadora());
        tela.getContentPane().add(botExcluir);

        // Botão para voltar
        JButton botVoltar = new JButton("Voltar");
        botVoltar.setBounds(150, 230, 100, ALTURA_BOTAO);
        botVoltar.addActionListener(e -> {
            TelaPrincipal telaInicio = new TelaPrincipal();
            telaInicio.setVisible(true);
            tela.dispose(); // Fecha a tela atual
        });
        tela.getContentPane().add(botVoltar);
    }

    private void abrirTelaIncluirEntidadeOperadora() {
        tela = new JFrame("Incluir Entidade Operadora");
        TelaIncluirEntidadeOperadora tela = new TelaIncluirEntidadeOperadora();
        tela.setVisible(true);
        this.tela.setVisible(false);
    }

    private void abrirTelaAlterarEntidadeOperadora() {
        tela = new JFrame("Alterar Entidade Operadora");
        TelaAlterarEntidadeOperadora tela = new TelaAlterarEntidadeOperadora();
        tela.setVisible(true);
        this.tela.setVisible(false);
    }

    private void abrirTelaBuscarEntidadeOperadora() {
        tela = new JFrame("Buscar Entidade Operadora");
        TelaBuscarEntidadeOperadora tela = new TelaBuscarEntidadeOperadora();
        tela.setVisible(true);
        this.tela.setVisible(false);
    }

    private void abrirTelaExcluirEntidadeOperadora() {
        tela = new JFrame("Excluir Entidade Operadora");
        TelaExcluirEntidadeOperadora tela = new TelaExcluirEntidadeOperadora();
        tela.setVisible(true);
        this.tela.setVisible(false);
    }
}
