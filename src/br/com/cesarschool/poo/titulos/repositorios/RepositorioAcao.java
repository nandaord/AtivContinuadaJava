package br.com.cesarschool.poo.titulos.repositorios;

import br.com.cesarschool.poo.titulos.daogenerico.DAOSerializadorObjetos;
import br.com.cesarschool.poo.titulos.entidades.Acao;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.time.format.DateTimeFormatter;

public class RepositorioAcao implements RepositorioGeral {
    private static final String ARQUIVO_ACOES = "Acao.txt";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private Path path;

    public RepositorioAcao(){
        this.path = Paths.get(ARQUIVO_ACOES).toAbsolutePath();
    }

    public boolean incluir(Acao acao) {
        if (buscar(acao.getIdentificador()) != null) {
            return false; // Identificador já existe
        }
        acao.setDataHoraInclusao(LocalDateTime.now()); // Definindo a data e hora da inclusão
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO_ACOES, true))) {
            String linha = acao.getIdentificador() + ";" + acao.getNome() + ";" + acao.getDataDeValidade() + ";" + acao.getValorUnitario() + ";" + acao.getDataHoraInclusao();
            writer.write(linha);
            writer.newLine();
            writer.flush();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean alterar(Acao acao) {
        List<String> linhas = new ArrayList<>();
        boolean encontrado = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO_ACOES))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(";");
                if (partes.length != 5) {
                    System.out.println("Linha mal formatada: " + linha);
                    continue; // Ignorar linhas mal formatadas
                }
                int id = Integer.parseInt(partes[0]);
                if (id == acao.getIdentificador()) {
                    linha = acao.getIdentificador() + ";" + acao.getNome() + ";" + acao.getDataDeValidade() + ";" + acao.getValorUnitario() + ";" + acao.getDataHoraInclusao();
                    encontrado = true;
                }
                linhas.add(linha);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        if (encontrado) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO_ACOES))) {
                for (String linha : linhas) {
                    writer.write(linha);
                    writer.newLine();
                }
                writer.flush();
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

        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO_ACOES))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(";");
                if (partes.length != 5) {
                    System.out.println("Linha mal formatada: " + linha);
                    continue; // Ignorar linhas mal formatadas
                }
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
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO_ACOES))) {
                for (String linha : linhas) {
                    writer.write(linha);
                    writer.newLine();
                }
                writer.flush();
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        } else {
            return false;
        }
    }

    public Acao buscar(int identificador) {
        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO_ACOES))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(";");
                if (partes.length != 5) {
                    System.out.println("Linha mal formatada: " + linha);
                    continue; // Ignorar linhas mal formatadas
                }
                int id = Integer.parseInt(partes[0]);
                if (id == identificador) {
                    String nome = partes[1];
                    LocalDate dataDeValidade = LocalDate.parse(partes[2], formatter);
                    double valorUnitario = Double.parseDouble(partes[3]);
                    LocalDateTime dataHoraInclusao = null;

                    if (partes[4] != null && !partes[4].isEmpty()) {
                        try {
                            dataHoraInclusao = LocalDateTime.parse(partes[4], DateTimeFormatter.ISO_DATE_TIME);
                        } catch (DateTimeParseException e) {
                            System.out.println("Erro ao parsear a data de inclusão: " + partes[4]);
                        }
                    }

                    Acao acao = new Acao(id, nome, dataDeValidade, valorUnitario);
                    if (dataHoraInclusao != null) {
                        acao.setDataHoraInclusao(dataHoraInclusao);
                    }
                    return acao;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public List<Acao> listar() {
        List<Acao> acoes = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path.toFile()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length != 5) {
                    System.out.println("Linha mal formatada: " + line);
                    continue; // Ignorar linhas mal formatadas
                }
                int id = Integer.parseInt(parts[0]);
                String nome = parts[1];
                LocalDate dataValidade = LocalDate.parse(parts[2], formatter);
                double valorUnitario = Double.parseDouble(parts[3]);
                acoes.add(new Acao(id, nome, dataValidade, valorUnitario));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return acoes;
    }

    public DAOSerializadorObjetos getDao() {
        return new DAOSerializadorObjetos(Acao.class);
    }

    @Override
    public Class<?> getClasseEntidade() {
        return Acao.class;
    }

}
