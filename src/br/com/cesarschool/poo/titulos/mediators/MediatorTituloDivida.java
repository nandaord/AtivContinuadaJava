package br.com.cesarschool.poo.titulos.mediators;

import br.com.cesarschool.poo.titulos.entidades.TituloDivida;
import br.com.cesarschool.poo.titulos.repositorios.RepositorioTituloDivida;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/*
 * Deve ser um singleton.
 *
 * Deve ter um atributo repositorioTituloDivida, do tipo RepositorioTituloDivida, que deve
 * ser inicializado na sua declaração. Este atributo será usado exclusivamente
 * pela classe, não tendo, portanto, métodos set e get.
 *
 * Métodos:
 *
 * pivate String validar(TituloDivida titulo): deve validar os dados do objeto recebido nos seguintes termos:
 * identificador: deve ser maior que zero e menor que 100000 (1)
 * nome: deve ser preenchido, diferente de branco e de null (2). deve ter entre 10 e 100 caracteres (3).
 * data de validade: deve ser maior do que a data atual mais 180 dias (4).
 * valorUnitario: deve ser maior que zero (5).
 * O método validar deve retornar null se o objeto estiver válido, e uma mensagem pertinente (ver abaixo)
 * se algum valor de atributo estiver inválido.
 *
 * (1) - Identificador deve estar entre 1 e 99999.
 * (2) - Nome deve ser preenchido.
 * (3) - Nome deve ter entre 10 e 100 caracteres.
 * (4) - Data de validade deve ter pelo menos 180 dias na frente da data atual.
 * (5) - Taxa de juros deve ser maior ou igual a zero.
 *
 * public String incluir(TituloDivida titulo): deve chamar o método validar. Se ele
 * retornar null, deve incluir titulo no repositório. Retornos possíveis:
 * (1) null, se o retorno do validar for null e o retorno do incluir do
 * repositório for true.
 * (2) a mensagem retornada pelo validar, se o retorno deste for diferente
 * de null.
 * (3) A mensagem "Título já existente", se o retorno do validar for null
 * e o retorno do repositório for false.
 *
 * public String alterar(TituloDivida titulo): deve chamar o método validar. Se ele
 * retornar null, deve alterar titulo no repositório. Retornos possíveis:
 * (1) null, se o retorno do validar for null e o retorno do alterar do
 * repositório for true.
 * (2) a mensagem retornada pelo validar, se o retorno deste for diferente
 * de null.
 * (3) A mensagem "Título inexistente", se o retorno do validar for null
 * e o retorno do repositório for false.
 *
 * public String excluir(int identificador): deve validar o identificador.
 * Se este for válido, deve chamar o excluir do repositório. Retornos possíveis:
 * (1) null, se o retorno do excluir do repositório for true.
 * (2) A mensagem "Título inexistente", se o retorno do repositório for false
 * ou se o identificador for inválido.
 *
 * public TituloDivida buscar(int identificador): deve validar o identificador.
 * Se este for válido, deve chamar o buscar do repositório, retornando o
 * que ele retornar. Se o identificador for inválido, retornar null.
 */
public class MediatorTituloDivida {
    private static MediatorTituloDivida instance;

    RepositorioTituloDivida repositorioTituloDivida = new RepositorioTituloDivida();

    private MediatorTituloDivida() {
    }

    public static MediatorTituloDivida getInstance() {
        if (instance == null) {
            instance = new MediatorTituloDivida();
        }
        return instance;
    }

    private String validar(TituloDivida titulo) {
        String nome = titulo.getNome();
        long diasDiferenca = ChronoUnit.DAYS.between(LocalDate.now(), titulo.getDataDeValidade());

        if (titulo.getIdentificador() <= 0 || titulo.getIdentificador() >= 100000) {
            return "Identificador deve estar entre 1 e 99999.";
        }
        if (nome == null) {
            return "Nome deve estar preenchido.";
        }
        if (nome.trim().length() < 10 || nome.trim().length() > 100) {
            return "Nome deve ter entre 10 e 100 caracteres.";
        }
        if (diasDiferenca < 180) {
            return "Data de validade deve ter pelo menos 180 dias na frente da data atual.";
        }
        if (titulo.getTaxaJuros() < 0) {
            return "Taxa de juros deve ser maior ou igual a zero.";
        }

        return null;
    }

    public String incluir(TituloDivida titulo) {

        String validacao = validar(titulo);

        if (validacao == null) {
            if (repositorioTituloDivida.incluir(titulo)) {
                return null;
            } else {
                return "Título já existente";
            }
        } else {
            return validacao;
        }

    }

    public String alterar(TituloDivida titulo) {

        String validacao = validar(titulo);

        if (validacao == null) {
            if (repositorioTituloDivida.alterar(titulo)) {
                return null;
            } else {
                return "Título inexistente";
            }
        } else {
            return validacao;
        }
    }

    public String excluir(int identificador) {

        if (identificador <= 0 || identificador > 100000) {
            return "Título inexistente";
        }

        TituloDivida busca = repositorioTituloDivida.buscar(identificador);

        if (busca != null) {
            if (repositorioTituloDivida.excluir(identificador)) {
                return null;
            } else {
                return "Título inexistente";
            }
        } else {
            return "Título inexistente";
        }
    }

    public TituloDivida buscar(int identificador){

        if (identificador <= 0 || identificador > 100000) {
            return null;
        } else {
            return repositorioTituloDivida.buscar(identificador);
        }

    }

}