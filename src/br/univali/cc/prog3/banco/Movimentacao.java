package br.univali.cc.prog3.banco;


public class Movimentacao {
    private String descricao;
    private char tipo;
    private double valor;

    public Movimentacao(String descricao, char tipo, double valor) {
        this.descricao = descricao;
        this.tipo = tipo;
        this.valor = valor;
    }

    public String getMovimentacao(){
        return this.descricao+" ("+this.tipo+") R$ "+this.valor;
    }

}
