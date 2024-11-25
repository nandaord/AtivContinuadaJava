package br.com.cesarschool.poo.titulos.entidades;
import java.time.LocalDate;

public class TituloDivida extends Ativo {

    private double taxaJuros;

    public TituloDivida(int identificador, String nome, LocalDate dataDeValidade, double taxaJuros){

        super(identificador,nome,dataDeValidade);
        this.taxaJuros = taxaJuros;

    }

    //SET E GET
    public void setTaxaJuros(double taxaJuros){
        this.taxaJuros = taxaJuros;
    }

    public double getTaxaJuros(){
        return taxaJuros;
    }


    //MÉTODOS ADICIONAIS
    public double calcularPrecoTransacao(double montante){
        return montante*(1 - taxaJuros/100.0);
    }

    public String toString() {
        return "Taxa Juros: " + taxaJuros + "%";
    }

}