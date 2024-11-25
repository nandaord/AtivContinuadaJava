package br.com.cesarschool.poo.titulos.entidades;
import br.com.cesarschool.poo.titulos.daogenerico.Entidade;

import java.time.LocalDate;

public class Ativo extends Entidade {

    private final int identificador;
    private String nome;
    private LocalDate dataDeValidade = LocalDate.now();

    public Ativo(int identificador, String nome, LocalDate dataDeValidade){
        this.identificador = identificador;
        this.nome = nome;
        this.dataDeValidade = dataDeValidade;
    }

    // apenas getIdentificador pq ele é read only externamente
    public int getIdentificador() {
        return identificador;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataDeValidade() {
        return dataDeValidade;
    }

    public void setDataDeValidade(LocalDate dataDeValidade) {
        this.dataDeValidade = dataDeValidade;
    }

    @Override
    public String getIdUnico() {
        return String.valueOf(identificador);
    }

}