package br.gov.cesarschool.poo.daogenerico;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
/*
 * Esta classe representa um DAO genérico, que inclui, exclui, altera, busca por identificador
 * único e busca todos, qualquer objeto(s) cujo tipo é subtipo de Entidade.
 *
 * Sugerimos o uso da API de serialização do JAVA, que grava e lê objetos em arquvos.
 * Lembrar sempre de fechar (em qualquer circunstância) streams JAVA abertas.
 *
 * As nuances mais detalhadas do funcionamento desta classe está especificada na classe de testes
 * automatizados br.gov.cesarschool.poo.testes.TestesDAOSerializador.
 *
 * A classe deve ter a estrutura (métodos e construtores) dada abaixo.
 */

public class DAOSerializadorObjetos {
    private final String nomeDiretorio;

    public DAOSerializadorObjetos(Class<?> tipoEntidade) {
        this.nomeDiretorio = "." + File.separator + tipoEntidade.getSimpleName();
        File diretorio = new File(nomeDiretorio);
        if (!diretorio.exists()) {
            diretorio.mkdir();
        }
    }

    public boolean incluir(Entidade entidade) {
        String caminhoArquivo = obterCaminhoArquivo(entidade.getIdUnico());
        File arquivo = new File(caminhoArquivo);
        if (arquivo.exists()) {
            return false;
        }
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(caminhoArquivo))) {
            oos.writeObject(entidade);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public boolean alterar(Entidade entidade) {
        String caminhoArquivo = obterCaminhoArquivo(entidade.getIdUnico());
        File arquivo = new File(caminhoArquivo);
        if (!arquivo.exists()) {
            return false;
        }
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(caminhoArquivo))) {
            oos.writeObject(entidade);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public boolean excluir(String idUnico) {
        String caminhoArquivo = obterCaminhoArquivo(idUnico);
        File arquivo = new File(caminhoArquivo);
        return arquivo.exists() && arquivo.delete();
    }

    public Entidade buscar(String idUnico) {
        String caminhoArquivo = obterCaminhoArquivo(idUnico);
        File arquivo = new File(caminhoArquivo);
        if (!arquivo.exists()) {
            return null;
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(caminhoArquivo))) {
            return (Entidade) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return null;
        }
    }

    public Entidade[] buscarTodos() {
        File diretorio = new File(nomeDiretorio);
        File[] arquivos = diretorio.listFiles();
        if (arquivos == null || arquivos.length == 0) {
            return new Entidade[0];
        }
        Entidade[] entidades = new Entidade[arquivos.length];
        for (int i = 0; i < arquivos.length; i++) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivos[i]))) {
                entidades[i] = (Entidade) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                return new Entidade[0];
            }
        }
        return entidades;
    }

    private String obterCaminhoArquivo(String idUnico) {
        return nomeDiretorio + File.separator + idUnico;
    }
}
