package br.univali.cc.prog3.banco;

import exception.ExceptionConta;

import java.util.ArrayList;
import java.util.List;

public class ContaCorrente {

    private boolean especial;
    private double limite;
    private int numero;
    private double saldo;
    private List<Movimentacao> movimentacoes;

    public ContaCorrente(int numero, double saldoInicial){
        this.numero = numero;
        this.saldo = saldoInicial;
        this.especial = false;
        this.limite = 0;
        this.movimentacoes = new ArrayList<>();
        criarMovimentacao("Saldo inicial", 'C', this.saldo);
//        this.movimentacoes.add(new Movimentacao("Saldo inicial", 'C', this.saldo));
    }

    public ContaCorrente(int numero, double saldoInicial, double limite){
        this.numero = numero;
        this.saldo = saldoInicial;
        this.limite = limite;
        this.especial = true;
        this.movimentacoes = new ArrayList<>();
        criarMovimentacao("Saldo inicial", 'C', this.saldo);
//        this.movimentacoes.add(new Movimentacao("Saldo inicial", 'C', this.saldo));
    }

    public int getNumeroConta(){
        return numero;
    }

    public double getSaldo(){
        return saldo;
    }

    protected boolean depositar(double valor){
        if (valor > 0){
            this.saldo += valor;

            criarMovimentacao("Depósito", 'D', valor);
            return true;
        }
        else{
            throw new ExceptionConta("Valor de depósito não é válido!");
            return false;
        }
    }

    protected boolean sacar(double valor){
        double saldoDisponivel = this.saldo;
        if (this.especial){
            saldoDisponivel += this.limite;
        }
        if (saldoDisponivel >= valor){
            this.saldo -= valor;
            criarMovimentacao("Saque", 'S', valor);
            return true;
        }
        else{
            throw new ExceptionConta("Saldo insuficiente para saque!");
            return false;
        }
    }

    public String emitirExtrato(){
        String extrato = "Extrato da conta " + this.numero + "\n";
        for (Movimentacao movimentacao : this.movimentacoes){
            extrato += movimentacao.getMovimentacao() + "\n";
        }
        return extrato;
    }

    private void criarMovimentacao(String descricao, char tipo, double valor){
        Movimentacao movimentacao = new Movimentacao(descricao, tipo, valor);
        this.movimentacoes.add(movimentacao);
        movimentacao.getMovimentacao();
    }
}