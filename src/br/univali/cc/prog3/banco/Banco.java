package br.univali.cc.prog3.banco;

import exception.ExceptionBanco;

import java.util.ArrayList;

public class Banco {

    int numero;
    String nome;
    ArrayList<ContaCorrente> contas;

    public Banco(int numero, String nome) {
        this.numero = numero;
        this.nome = nome;
        contas = new ArrayList<>();
    }

    public void criarConta(double saldoInicial){
        ContaCorrente conta = new ContaCorrente(contas.size()+1, saldoInicial);
        contas.add(conta);
    }

    public void criarConta(double saldoInicial, double limite) {
        ContaCorrente conta = new ContaCorrente(contas.size()+1, saldoInicial, limite);
        contas.add(conta);
    }

    public void depositar(int conta, double valor) throws ExceptionBanco {
        ContaCorrente contaCorrente = this.localizarConta(conta);
        if (contaCorrente != null) {
            contaCorrente.depositar(valor);
        }
    }

    public void sacar(int conta, double valor) throws ExceptionBanco {
        ContaCorrente contaCorrente = this.localizarConta(conta);
        if (contaCorrente != null) {
            contaCorrente.sacar(valor);
        }
    }

    public String emitirExtrato(int conta) throws ExceptionBanco {
        ContaCorrente contaCorrente = this.localizarConta(conta);
        if (contaCorrente != null) {
            return contaCorrente.emitirExtrato();
        }
        return "";
    }

    public void transferir(int contaOrigem, int contaDestino, double valor) throws ExceptionBanco {
        ContaCorrente contaCorrenteOrigem = this.localizarConta(contaOrigem);
        ContaCorrente contaCorrenteDestino = this.localizarConta(contaDestino);
        if (contaCorrenteOrigem != null && contaCorrenteDestino != null){
            if (contaCorrenteOrigem.sacar(valor)) {
                contaCorrenteDestino.depositar(valor);
            }
        }
    }

    private ContaCorrente localizarConta(int numeroConta) {
        for (ContaCorrente conta : contas) {
            if (conta.getNumeroConta() == numeroConta) {
                return conta;
            }
        }
        return null;
    }

    public String getNome() {
        return nome;
    }

    public ArrayList<ContaCorrente> getContas() {
        return contas;
    }

    public int getNumero() {
        return numero;
    }
}
