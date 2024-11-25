package br.com.cesarschool.poo.titulos.repositorios;

import br.com.cesarschool.poo.titulos.daogenerico.DAOSerializadorObjetos;
import br.com.cesarschool.poo.titulos.entidades.Acao;
import br.com.cesarschool.poo.titulos.entidades.TituloDivida;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.io.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class RepositorioTituloDivida implements RepositorioGeral{

    private static final String ARQUIVO_TITULOS = "TituloDivida.txt";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private Path path;

    public RepositorioTituloDivida(){
        this.path = Paths.get(ARQUIVO_TITULOS).toAbsolutePath();
    }

    public boolean incluir(TituloDivida tituloDivida) {
       if(buscar(tituloDivida.getIdentificador())!=null){
           return false;
       }
       try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO_TITULOS,true))){
           String linha = tituloDivida.getIdentificador() + ";" + tituloDivida.getNome() + ";" + tituloDivida.getDataDeValidade() + ";" + tituloDivida.getTaxaJuros();
           writer.write(linha);
           writer.newLine();
           return true;
       } catch (IOException e){
           e.printStackTrace();
           return false;
       }
    }

    public boolean alterar(TituloDivida tituloDivida) {

        List<String> linhas = new ArrayList<>();
        boolean encontrado = false;

        try (BufferedReader br = new BufferedReader(new FileReader(ARQUIVO_TITULOS))) {

            String linha;

            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");

                int id = Integer.parseInt(dados[0]);

                if (id == tituloDivida.getIdentificador()) {
                    linha = tituloDivida.getIdentificador() + ";" + tituloDivida.getNome() + ";" + tituloDivida.getDataDeValidade() + ";" + tituloDivida.getTaxaJuros();
                    encontrado = true;
                }
                linhas.add(linha);
            }

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        if (encontrado) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO_TITULOS))) {
                for (String linha : linhas) {
                    bw.write(linha);
                    bw.newLine();
                }
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean excluir(int identificador) {
        List<String> linhas = new ArrayList<>();
        boolean encontrado = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO_TITULOS))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(";");
                int id = Integer.parseInt(partes[0]);
                if (id == identificador) {
                    encontrado = true; // Não adicionamos esta linha
                } else {
                    linhas.add(linha); // Adicionamos as outras linhas
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        if (encontrado) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO_TITULOS))) {
                for (String linha : linhas) {
                    writer.write(linha);
                    writer.newLine();
                }
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        } else {
            return false;
        }
    }

    public TituloDivida buscar(int identificador) {
        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO_TITULOS))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(";");
                int id = Integer.parseInt(partes[0]);
                if (id == identificador) {
                    String nome = partes[1];
                    LocalDate dataDeValidade = LocalDate.parse(partes[2]);
                    double valorUnitario = Double.parseDouble(partes[3]);
                    return new TituloDivida(id, nome, dataDeValidade, valorUnitario);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<TituloDivida> listar() {
        List<TituloDivida> titulosDividas = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try(BufferedReader reader = new BufferedReader(new FileReader(path.toFile()))){
            String line;
            while((line = reader.readLine()) != null){
                String[] parts = line.split(";");
                int id = Integer.parseInt(parts[0]);
                String nome = parts[1];
                LocalDate dataValidade = LocalDate.parse(parts[2], formatter);
                double valorUnitario = Double.parseDouble(parts[3]);
                titulosDividas.add(new TituloDivida(id, nome, dataValidade, valorUnitario));
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return titulosDividas;
    }
    public DAOSerializadorObjetos getDao() {
        return new DAOSerializadorObjetos(TituloDivida.class);
    }
}
