package br.com.cesarschool.poo.titulos.repositorios;

import br.com.cesarschool.poo.titulos.entidades.Acao;
import br.com.cesarschool.poo.titulos.entidades.TituloDivida;
import java.time.LocalDate;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.File;

/*
 * Deve gravar em e ler de um arquivo texto chamado TituloDivida.txt os dados dos objetos do tipo
 * TituloDivida. Seguem abaixo exemplos de linhas (identificador, nome, dataValidade, taxaJuros).
 *
    1;BRASIL;2024-12-12;10.5
    2;EUA;2026-01-01;1.5
    3;FRANCA;2027-11-11;2.5
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
public class RepositorioTituloDivida extends TituloDivida {

    public RepositorioTituloDivida(int identificador, String nome, LocalDate dataDeValidade, double taxaJuros){
        super(identificador,nome,dataDeValidade,taxaJuros);
    }

    public boolean incluir(TituloDivida tituloDivida) {

        File arquivo = new File("TituloDivida.txt");

        // try-with-resources faz com que o arquivo seja automaticamente fechado ao sair do escopo
        // bufferedReader: lê texto a partir de um input de forma eficiente, guardando informações num buffer temporário (ideal para leitura de grandes quantidades de texto)
        try(BufferedReader br = new BufferedReader(new FileReader(arquivo))){
            String linha;

            // percorre cada linha do arquivo
            while((linha = br.readLine())!=null){

                // divide a linha usando o split()
                String[] dados = linha.split(";");
                //o primeiro campo do arquivo é o id, ou seja, dados[0]
                int idExistente = Integer.parseInt(dados[0]);

                // verifica se o id ja existe, se sim, retorna falso
                if(idExistente == tituloDivida.getIdentificador()) {
                    return false;
                }
            }
         // em caso de erro, printa o erro e retorna false
        } catch (IOException e){
            e.printStackTrace();
            return false;
        }

        // parte do código que roda se o id ainda nao existir
        // BufferedWriter: escreve no arquivo
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(arquivo,true))) {

            // escreve os dados separados por ;
            bw.write(tituloDivida.getIdentificador() + ";" + tituloDivida.getNome() + ";" + tituloDivida.getDataDeValidade() + ";" + tituloDivida.getTaxaJuros());

            //adiciona nova linha no final do arquivo
            bw.newLine();
            return true;
          // em caso de erro, printa o erro e retona falso
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean alterar(TituloDivida tituloDivida) {

        File arquivo = new File("TituloDivida.txt");
        File arquivoTemp = new File("ArqTemp.txt");

        boolean encontrado = false;

        // abre o arquivo pra ler (br) e o arquivoTemp pra escrever (bw)
        try(BufferedReader br = new BufferedReader(new FileReader(arquivo)); BufferedWriter bw = new BufferedWriter(new FileWriter(arquivoTemp))){

            String linha;

            // lê de linha por linha
            while((linha = br.readLine())!=null){
                // adiciona os ngc da linha na array de string dados[]
                String[] dados = linha.split(";");

                // transforma o dados[0] em int
                int idExistente = Integer.parseInt(dados[0]);

                // verifica se esse int é igual ao novo identificador
                if(idExistente == tituloDivida.getIdentificador()){

                    // escreve no arquivo temporário a informação da linha nova
                    bw.write(tituloDivida.getIdentificador() + ";" + tituloDivida.getNome() + ";" + tituloDivida.getDataDeValidade() + ";" + tituloDivida.getTaxaJuros());
                    encontrado = true;

                }else {
                    // caso não seja igual, ele só repete a mesma linha
                    bw.write(linha);
                }
                bw.newLine();

            }

        } catch(IOException e){
            e.printStackTrace();
            return false;
        }

        //sobrescreve o arquivo original pelo temporário
        if(!arquivo.delete()||!arquivoTemp.renameTo(arquivo)){
            return false;
        }

            return encontrado;
    }

    public boolean excluir(int identificador) {

        File arquivo = new File("TituloDivida.txt");
        File arquivoTemp = new File("ArqTemp.txt");

        boolean encontrado = false;

        try(BufferedReader br = new BufferedReader(new FileReader(arquivo));BufferedWriter bw = new BufferedWriter(new FileWriter(arquivoTemp))){

            String linha;

            while((linha = br.readLine())!=null){

                String[] dados = linha.split(";");

                int idEncontrado = Integer.parseInt(dados[0]);

                if(idEncontrado == identificador){
                    encontrado = true;
                    continue;
                }

                bw.write(linha);
                bw.newLine();

            }

        } catch(IOException e){
            e.printStackTrace();
            return false;
        }

        if(encontrado) {

            if (!arquivo.delete() || !arquivoTemp.renameTo(arquivo)) {
                return false;
            }
            return true;
        } else {
            arquivoTemp.delete();
            return false;
        }
    }

    public Acao buscar(int identificador) {

        File arquivo = new File("TituloDivida.txt");

        try(BufferedReader br = new BufferedReader(new FileReader(arquivo))){

            String linha;

            while((linha = br.readLine())!=null){
                String[] dados = linha.split(";");
                int idExistente = Integer.parseInt(dados[0]);

                if(idExistente==identificador){
                    String nome = dados[1];
                    LocalDate dataValidade = LocalDate.parse(dados[2]);
                    double taxaJuros = Double.parseDouble(dados[3]);

                    return new Acao (idExistente,nome,dataValidade,taxaJuros);

                }
            }

        }catch(IOException e){
            e.printStackTrace();
        }

        return null;
    }
}