package br.com.cesarschool.poo.titulos.entidades;

import br.com.cesarschool.poo.titulos.daogenerico.Entidade;

public class EntidadeOperadora extends Entidade {

    private long identificador;
    private String nome;
    private boolean autorizadoAcao;
    private double saldoAcao;
    private double saldoTituloDivida;

    public EntidadeOperadora(long identificador, String nome, boolean autorizadoAcao) {
        this.identificador = identificador;
        this.nome = nome;
        this.autorizadoAcao = autorizadoAcao;
    }

    // apenas getIdentificador() pois identificador é um atributo apenas read-only externamente
    public long getIdentificador(){
        return identificador;
    }


    // SETS E GETS
    public void setNome(String nome){
        this.nome = nome;
    }

    public String getNome(){
        return nome;
    }

    public void setAutorizadoAcao(boolean autorizadoAcao){
        this.autorizadoAcao = autorizadoAcao;
    }

    public boolean getAutorizadoAcao(){
        return autorizadoAcao;
    }

    public double getSaldoAcao(){
        return saldoAcao;
    }

    public double getSaldoTituloDivida(){
        return saldoTituloDivida;
    }


    //OUTROS MÉTODOS
    public void creditarSaldoAcao(double valor){
        saldoAcao += valor;
    }

    public void debitarSaldoAcao(double valor){
        saldoAcao -= valor;
    }

    public void creditarSaldoTituloDivida(double valor){
        saldoTituloDivida += valor;
    }

    public void debitarSaldoTituloDivida(double valor){
        saldoTituloDivida -= valor;
    }

    public String toString() {
        return identificador +": " + nome + " - R$" + saldoAcao + " - R$" + saldoTituloDivida;
    }

    
    public void ajustarSaldoAcao(double saldoAcao) {
        this.saldoAcao = saldoAcao;
    }

    public void ajustarSaldoTituloDivida(double saldoTituloDivida) {
        this.saldoTituloDivida = saldoTituloDivida;
    }

    @Override
    public String getIdUnico() {
        return String.valueOf(identificador);
    }

}


