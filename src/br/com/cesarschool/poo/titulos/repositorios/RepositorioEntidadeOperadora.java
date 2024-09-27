package br.com.cesarschool.poo.titulos.repositorios;

import br.com.cesarschool.poo.titulos.entidades.Acao;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/*
 * Deve gravar em e ler de um arquivo texto chamado Acao.txt os dados dos objetos do tipo
 * Acao. Seguem abaixo exemplos de linhas.
 *
    1;PETROBRAS;2024-12-12;30.33
    2;BANCO DO BRASIL;2026-01-01;21.21
    3;CORREIOS;2027-11-11;6.12
 *
 * A inclus�o deve adicionar uma nova linha ao arquivo. N�o � permitido incluir
 * identificador repetido. Neste caso, o m�todo deve retornar false. Inclus�o com
 * sucesso, retorno true.
 *
 * A altera��o deve substituir a linha atual por uma nova linha. A linha deve ser
 * localizada por identificador que, quando n�o encontrado, enseja retorno false.
 * Altera��o com sucesso, retorno true.
 *
 * A exclus�o deve apagar a linha atual do arquivo. A linha deve ser
 * localizada por identificador que, quando n�o encontrado, enseja retorno false.
 * Exclus�o com sucesso, retorno true.
 *
 * A busca deve localizar uma linha por identificador, materializar e retornar um
 * objeto. Caso o identificador n�o seja encontrado no arquivo, retornar null.
 */

public class RepositorioAcao {

    private static final String FILE_NAME = "Acao.txt";
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public boolean incluir(Acao acao) {
        List<Acao> acoes = lerAcoes();
        for (Acao a : acoes) {
            if (a.getIdentificador() == acao.getIdentificador()) {
                return false;
            }
        }
        acoes.add(acao);
        return gravarAcoes(acoes);
    }

    public boolean alterar(Acao acao) {
        List<Acao> acoes = lerAcoes();
        for (int i = 0; i < acoes.size(); i++) {
            if (acoes.get(i).getIdentificador() == acao.getIdentificador()) {
                acoes.set(i, acao);
                return gravarAcoes(acoes);
            }
        }
        return false;
    }


    public boolean excluir(int identificador) {
        List<Acao> acoes = lerAcoes();
        for (int i = 0; i < acoes.size(); i++) {
            if (acoes.get(i).getIdentificador() == identificador) {
                acoes.remove(i);
                return gravarAcoes(acoes);
            }
        }
        return false;
    }

    
    public Acao buscar(int identificador) {
        List<Acao> acoes = lerAcoes();
        for (Acao acao : acoes) {
            if (acao.getIdentificador() == identificador) {
                return acao;
            }
        }
        return null;
    }

    // Método auxiliar para ler todas as ações do arquivo
    private List<Acao> lerAcoes() {
        List<Acao> acoes = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] campos = linha.split(";");
                int identificador = Integer.parseInt(campos[0]);
                String nome = campos[1];
                LocalDate dataDeValidade = LocalDate.parse(campos[2], DATE_FORMAT);
                double valorUnitario = Double.parseDouble(campos[3]);
                acoes.add(new Acao(identificador, nome, dataDeValidade, valorUnitario));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return acoes;
    }

    // Método auxiliar para gravar todas as ações no arquivo
    private boolean gravarAcoes(List<Acao> acoes) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Acao acao : acoes) {
                String linha = acao.getIdentificador() + ";" + acao.getNome() + ";" +
                        acao.getDataDeValidade().format(DATE_FORMAT) + ";" + acao.getValorUnitario();
                bw.write(linha);
                bw.newLine();
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
