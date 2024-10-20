package br.com.cesarschool.poo.titulos.repositorios;

import br.com.cesarschool.poo.titulos.entidades.EntidadeOperadora;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/*
 * Deve gravar em e ler de um arquivo texto chamado EntidadeOperadora.txt os dados dos objetos do tipo
 * EntidadeOperadora. Seguem abaixo exemplos de linhas:
 *
    1;PETROBRAS;true;1000.0;500.0
    2;BANCO DO BRASIL;false;1500.0;800.0
 *
 * A inclusão deve adicionar uma nova linha ao arquivo. Não é permitido incluir
 * identificador repetido. Neste caso, o método deve retornar false. Inclusão com
 * sucesso, retorno true.
 *
 * A alteração deve substituir a linha atual por uma nova linha. A linha deve ser
 * localizada por identificador que, quando não encontrado, enseja retorno false.
 * Alteração com sucesso, retorno true.
 *
 * A exclusão deve apagar a linha atual do arquivo. A linha deve ser
 * localizada por identificador que, quando não encontrado, enseja retorno false.
 * Exclusão com sucesso, retorno true.
 *
 * A busca deve localizar uma linha por identificador, materializar e retornar um
 * objeto. Caso o identificador não seja encontrado no arquivo, retornar null.
 */

public class RepositorioEntidadeOperadora {
    
    private static final String FILE_NAME = "EntidadeOperadora.txt";

    // Método para incluir uma nova EntidadeOperadora
    public boolean incluir(EntidadeOperadora entidadeOperadora) {
        if (procurarId(entidadeOperadora.getIdentificador())) {
            return false; // Identificador já existe
        }

        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            String linha = entidadeOperadora.getIdentificador() + ";" + entidadeOperadora.getNome() + ";" +
                    entidadeOperadora.getAutorizacaoAcao() + ";" + entidadeOperadora.getSaldoAcao() + ";" +
                    entidadeOperadora.getSaldoTituloDivida();
            escritor.write(linha);
            escritor.newLine();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Método para alterar uma EntidadeOperadora existente
    public boolean alterar(EntidadeOperadora entidadeOperadora) {
        List<String> linhas = lerLinhas();
        boolean alterado = false;

        for (int i = 0; i < linhas.size(); i++) {
            String[] divisao = linhas.get(i).split(";");
            if (Long.parseLong(divisao[0]) == entidadeOperadora.getIdentificador()) {
                // Substitui a linha correspondente
                String novaLinha = entidadeOperadora.getIdentificador() + ";" + entidadeOperadora.getNome() + ";" +
                        entidadeOperadora.getAutorizacaoAcao() + ";" + entidadeOperadora.getSaldoAcao() + ";" +
                        entidadeOperadora.getSaldoTituloDivida();
                linhas.set(i, novaLinha);
                alterado = true;
                break;
            }
        }

        if (alterado) {
            return gravarLinhas(linhas);
        }
        return false;
    }

    // Método para excluir uma EntidadeOperadora pelo identificador
    public boolean excluir(long identificador) {
        List<String> linhas = lerLinhas();
        boolean deletado = false;

        for (int i = 0; i < linhas.size(); i++) {
            String[] divisao = linhas.get(i).split(";");
            if (Long.parseLong(divisao[0]) == identificador) {
                linhas.remove(i);
                deletado = true;
                break;
            }
        }

        if (deletado) {
            return gravarLinhas(linhas);
        }
        return false;
    }

    // Método para buscar uma EntidadeOperadora pelo identificador
    public EntidadeOperadora buscar(long identificador) {
        List<String> linhas = lerLinhas();

        for (String linha : linhas) {
            String[] divisao = linha.split(";");
            if (Long.parseLong(divisao[0]) == identificador) {
                return new EntidadeOperadora(
                        Long.parseLong(divisao[0]),
                        divisao[1],
                        Boolean.parseBoolean(divisao[2]),
                        Double.parseDouble(divisao[3]),
                        Double.parseDouble(divisao[4])
                );
            }
        }
        return null;
    }

    // Método auxiliar para procurar um identificador no arquivo
    private boolean procurarId(long identificador) {
        return buscar(identificador) != null;
    }

    // Método auxiliar para ler todas as linhas do arquivo
    private List<String> lerLinhas() {
        List<String> linhas = new ArrayList<>();
        try (BufferedReader leitor = new BufferedReader(new FileReader(FILE_NAME))) {
            String linha;
            while ((linha = leitor.readLine()) != null) {
                linhas.add(linha);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return linhas;
    }

    // Método auxiliar para gravar todas as linhas no arquivo
    private boolean gravarLinhas(List<String> linhas) {
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (String linha : linhas) {
                escritor.write(linha);
                escritor.newLine();
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
