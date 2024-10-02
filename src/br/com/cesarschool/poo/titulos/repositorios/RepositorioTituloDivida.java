package br.com.cesarschool.poo.titulos.repositorios;

import br.com.cesarschool.poo.titulos.entidades.TituloDivida;
import java.time.LocalDate;
import java.io.*;

public class RepositorioTituloDivida {

    public boolean incluir(TituloDivida tituloDivida) {
        File arquivo = new File("TituloDivida.txt");

        // Verifica se o identificador já existe
        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;

            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                int idExistente = Integer.parseInt(dados[0]);

                if (idExistente == tituloDivida.getIdentificador()) {
                    return false; // Identificador já existe
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        // Se o identificador não existe, adiciona o novo título
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(arquivo, true))) {
            bw.write(tituloDivida.getIdentificador() + ";" + tituloDivida.getNome() + ";" +
                    tituloDivida.getDataDeValidade() + ";" + tituloDivida.getTaxaJuros());
            bw.newLine();
            return true; // Inclusão bem-sucedida
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean alterar(TituloDivida tituloDivida) {
        File arquivo = new File("TituloDivida.txt");
        File arquivoTemp = new File("ArqTemp.txt");

        boolean encontrado = false;

        try (BufferedReader br = new BufferedReader(new FileReader(arquivo));
             BufferedWriter bw = new BufferedWriter(new FileWriter(arquivoTemp))) {

            String linha;

            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");

                int idExistente = Integer.parseInt(dados[0]);

                if (idExistente == tituloDivida.getIdentificador()) {
                    bw.write(tituloDivida.getIdentificador() + ";" + tituloDivida.getNome() + ";" +
                            tituloDivida.getDataDeValidade() + ";" + tituloDivida.getTaxaJuros());
                    encontrado = true;
                } else {
                    bw.write(linha);
                }
                bw.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        if (!arquivo.delete() || !arquivoTemp.renameTo(arquivo)) {
            return false; // Falha ao substituir o arquivo
        }

        return encontrado; // Retorna se encontrou e alterou
    }

    public boolean excluir(int identificador) {
        File arquivo = new File("TituloDivida.txt");
        File arquivoTemp = new File("ArqTemp.txt");

        boolean encontrado = false;

        try (BufferedReader br = new BufferedReader(new FileReader(arquivo));
             BufferedWriter bw = new BufferedWriter(new FileWriter(arquivoTemp))) {

            String linha;

            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");

                int idEncontrado = Integer.parseInt(dados[0]);

                if (idEncontrado == identificador) {
                    encontrado = true;
                    continue; // Ignora a linha a ser excluída
                }

                bw.write(linha);
                bw.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        if (encontrado) {
            if (!arquivo.delete() || !arquivoTemp.renameTo(arquivo)) {
                return false; // Falha ao substituir o arquivo
            }
            return true; // Exclusão bem-sucedida
        } else {
            arquivoTemp.delete(); // Deleta o arquivo temporário se não encontrou
            return false;
        }
    }

    public TituloDivida buscar(int identificador) {
        File arquivo = new File("TituloDivida.txt");

        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;

            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                int idExistente = Integer.parseInt(dados[0]);

                if (idExistente == identificador) {
                    String nome = dados[1];
                    LocalDate dataValidade = LocalDate.parse(dados[2]);
                    double taxaJuros = Double.parseDouble(dados[3]);

                    return new TituloDivida(idExistente, nome, dataValidade, taxaJuros);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null; // Retorna null se não encontrar
    }
}
