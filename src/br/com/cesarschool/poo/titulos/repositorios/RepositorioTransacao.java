package br.com.cesarschool.poo.titulos.repositorios;

import br.com.cesarschool.poo.titulos.daogenerico.DAOSerializadorObjetos;
import br.com.cesarschool.poo.titulos.entidades.Transacao;
import br.com.cesarschool.poo.titulos.entidades.EntidadeOperadora;
import br.com.cesarschool.poo.titulos.entidades.Acao;
import br.com.cesarschool.poo.titulos.entidades.TituloDivida;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.io.*;

public class RepositorioTransacao implements RepositorioGeral {
    public void incluir(Transacao transacao) {
        File arquivo = new File("Transacao.txt");

        EntidadeOperadora entidadeCredito = transacao.getEntidadeCredito();
        EntidadeOperadora entidadeDebito = transacao.getEntidadeDebito();
        Acao acao = transacao.getAcao();
        TituloDivida tituloDivida = transacao.getTituloDivida();
        double valorOperacao = transacao.getValorOperacao();
        LocalDateTime dataHoraOperacao = transacao.getDataHoraOperacao();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(arquivo, true))) {
            // entidadeCredito
            bw.write(entidadeCredito.getIdentificador() + ";" + entidadeCredito.getNome() + ";" +
                    entidadeCredito.getAutorizadoAcao() + ";" + entidadeCredito.getSaldoAcao() + ";" +
                    entidadeCredito.getSaldoTituloDivida() + ";");

            // entidadeDebito
            bw.write(entidadeDebito.getIdentificador() + ";" + entidadeDebito.getNome() + ";" +
                    entidadeDebito.getAutorizadoAcao() + ";" + entidadeDebito.getSaldoAcao() + ";" +
                    entidadeDebito.getSaldoTituloDivida() + ";");

            // acao
            if (acao != null) {
                bw.write(acao.getIdentificador() + ";" + acao.getNome() + ";" +
                        acao.getDataDeValidade() + ";" + acao.getValorUnitario() + ";");
            } else {
                bw.write("null;");
            }

            // tituloDivida
            if (tituloDivida != null) {
                bw.write(tituloDivida.getIdentificador() + ";" + tituloDivida.getNome() + ";" +
                        tituloDivida.getDataDeValidade() + ";" + tituloDivida.getTaxaJuros() + ";");
            } else {
                bw.write("null;");
            }

            // valor e data da operação
            bw.write(valorOperacao + ";" + dataHoraOperacao);

            // nova linha
            bw.newLine();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Transacao[] buscarPorEntidadeCredora(int identificadorEntidadeCredito) {
        File arquivo = new File("Transacao.txt");
        List<Transacao> transacoesEncontradas = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;

            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");

                // Verifique se o identificador da entidade de crédito é o primeiro
                int idExistente = Integer.parseInt(dados[0]);

                if (idExistente == identificadorEntidadeCredito) {
                    EntidadeOperadora entidadeCredito = new EntidadeOperadora(
                            Integer.parseInt(dados[0]), // Identificador
                            dados[1], // Nome
                            Boolean.parseBoolean(dados[2]) // AutorizadoAção
                    );

                    EntidadeOperadora entidadeDebito = new EntidadeOperadora(
                            Integer.parseInt(dados[5]), // Identificador
                            dados[6], // Nome
                            Boolean.parseBoolean(dados[7]) // AutorizadoAção
                    );

                    Acao acao = null;

                    if (!dados[10].equals("null")) {
                        acao = new Acao(
                                Integer.parseInt(dados[10]), // Identificador
                                dados[11], // Nome
                                LocalDate.parse(dados[12]), // Data de Validade
                                Double.parseDouble(dados[13]) // Valor Unitário
                        );
                    }

                    TituloDivida tituloDivida = null;

                    if (!dados[14].equals("null")) {
                        tituloDivida = new TituloDivida(
                                Integer.parseInt(dados[14]), // Identificador
                                dados[15], // Nome
                                LocalDate.parse(dados[16]), // Data de Validade
                                Double.parseDouble(dados[17]) // Taxa de Juros
                        );
                    }

                    double valorOperacao = Double.parseDouble(dados[18]);
                    LocalDateTime dataHoraOperacao = LocalDateTime.parse(dados[19]);

                    Transacao transacao = new Transacao(entidadeCredito, entidadeDebito, acao, tituloDivida, valorOperacao, dataHoraOperacao);
                    transacoesEncontradas.add(transacao);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return new Transacao[0]; // Retornar um array vazio em caso de erro
        }
        return transacoesEncontradas.toArray(new Transacao[0]);
    }

    public Transacao[] buscarPorEntidadeDebito(int identificadorEntidadeDebito) {
        File arquivo = new File("Transacao.txt");
        List<Transacao> transacoesEncontradas = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;

            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");

                int idDebitoExistente = Integer.parseInt(dados[5]);

                if (idDebitoExistente == identificadorEntidadeDebito) {
                    EntidadeOperadora entidadeCredito = new EntidadeOperadora(
                            Integer.parseInt(dados[0]),
                            dados[1],
                            Boolean.parseBoolean(dados[2])
                    );

                    EntidadeOperadora entidadeDebito = new EntidadeOperadora(
                            Integer.parseInt(dados[5]),
                            dados[6],
                            Boolean.parseBoolean(dados[7])
                    );

                    Acao acao = null;

                    if (!dados[10].equals("null")) {
                        acao = new Acao(
                                Integer.parseInt(dados[10]),
                                dados[11],
                                LocalDate.parse(dados[12]),
                                Double.parseDouble(dados[13])
                        );
                    }

                    TituloDivida tituloDivida = null;

                    if (!dados[14].equals("null")) {
                        tituloDivida = new TituloDivida(
                                Integer.parseInt(dados[14]),
                                dados[15],
                                LocalDate.parse(dados[16]),
                                Double.parseDouble(dados[17])
                        );
                    }

                    double valorOperacao = Double.parseDouble(dados[18]);
                    LocalDateTime dataHoraOperacao = LocalDateTime.parse(dados[19]);

                    Transacao transacao = new Transacao(entidadeCredito, entidadeDebito, acao, tituloDivida, valorOperacao, dataHoraOperacao);
                    transacoesEncontradas.add(transacao);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return new Transacao[0];
        }
        return transacoesEncontradas.toArray(new Transacao[0]);
    }

    public DAOSerializadorObjetos getDao() {
        return new DAOSerializadorObjetos(Transacao.class);
    }

    @Override
    public Class<?> getClasseEntidade() {
        return Transacao.class;
    }


}
