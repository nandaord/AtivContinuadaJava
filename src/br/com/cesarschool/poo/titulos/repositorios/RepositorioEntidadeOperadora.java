package br.com.cesarschool.poo.titulos.repositorios;

import br.com.cesarschool.poo.titulos.entidades.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RepositorioEntidadeOperadora {
    
    private static final String FILE_NAME = "EntidadeOperadora.txt";

    public boolean incluir(EntidadeOperadora entidadeOperadora) {
        if (procurarId(entidadeOperadora.getIdentificador())) {
            return false; // Identificador já existe
        }

        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            String linha = entidadeOperadora.getIdentificador() + ";" + entidadeOperadora.getNome() + ";" +
                    entidadeOperadora.getAutorizadoAcao() + ";" + entidadeOperadora.getSaldoAcao() + ";" +
                    entidadeOperadora.getSaldoTituloDivida();
            escritor.write(linha);
            escritor.newLine();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean alterar(EntidadeOperadora entidadeOperadora) {
        List<String> linhas = lerLinhas();
        boolean alterado = false;

        for (int i = 0; i < linhas.size(); i++) {
            String[] divisao = linhas.get(i).split(";");
            if (Long.parseLong(divisao[0]) == entidadeOperadora.getIdentificador()) {
                // Substitui a linha correspondente
                String novaLinha = entidadeOperadora.getIdentificador() + ";" + entidadeOperadora.getNome() + ";" +
                        entidadeOperadora.getAutorizadoAcao() + ";" + entidadeOperadora.getSaldoAcao() + ";" +
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

    public EntidadeOperadora buscar(long identificador) {
        List<String> linhas = lerLinhas();

        for (String linha : linhas) {
            String[] divisao = linha.split(";");
            if (Long.parseLong(divisao[0]) == identificador) {
                return new EntidadeOperadora(
                        Long.parseLong(divisao[0]),
                        divisao[1],
                        Boolean.parseBoolean(divisao[2])
                );
            }
        }
        return null;
    }

    private boolean procurarId(long identificador) {
        return buscar(identificador) != null;
    }

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
    public List<EntidadeOperadora> listar() {
        List<EntidadeOperadora> entidades = new ArrayList<>();
        List<String> linhas = lerLinhas(); // Usando o método já existente para ler as linhas do arquivo

        for (String linha : linhas) {
            String[] partes = linha.split(";");


            if (partes.length == 5) {
                long id = Long.parseLong(partes[0]);
                String nome = partes[1];
                boolean autorizadoAcao = Boolean.parseBoolean(partes[2]);
                double saldoAcao = Double.parseDouble(partes[3]);
                double saldoTituloDivida = Double.parseDouble(partes[4]);

                EntidadeOperadora entidade = new EntidadeOperadora(id, nome, autorizadoAcao);
                entidades.add(entidade);
            }
        }
        return entidades;
    }

}
