package br.com.cesarschool.poo.titulos.repositorios;

import br.com.cesarschool.poo.titulos.entidades.Acao;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.time.format.DateTimeFormatter;	
/*
 * Deve gravar em e ler de um arquivo texto chamado Acao.txt os dados dos objetos do tipo
 * Acao. Seguem abaixo exemplos de linhas (identificador, nome, dataValidade, valorUnitario)
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
	    try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO_ACOES, true))) {
	        String linha = acao.getIdentificador() + ";" + acao.getNome() + ";" + acao.getDataDeValidade() + ";" + acao.getValorUnitario();
	        writer.write(linha);
	        writer.newLine();
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
                int id = Integer.parseInt(partes[0]);
                if (id == acao.getIdentificador()) {
                    linha = acao.getIdentificador() + ";" + acao.getNome() + ";" + acao.getDataDeValidade() + ";" + acao.getValorUnitario();
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
                int id = Integer.parseInt(partes[0]);
                if (id == identificador) {
                    String nome = partes[1];
                    LocalDate dataDeValidade = LocalDate.parse(partes[2]);
                    double valorUnitario = Double.parseDouble(partes[3]);
                    return new Acao(id, nome, dataDeValidade, valorUnitario);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null; 
    }
    public List<Acao> listar() {
        List<Acao> acoes = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try(BufferedReader reader = new BufferedReader(new FileReader(path.toFile()))){
            String line;
            while((line = reader.readLine()) != null){
                String[] parts = line.split(";");
                int id = Integer.parseInt(parts[0]);
                String nome = parts[1];
                LocalDate dataValidade = LocalDate.parse(parts[2], formatter);
                double valorUnitario = Double.parseDouble(parts[3]);
                acoes.add(new Acao(id, nome, dataValidade, valorUnitario));
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return acoes;
    }
}
