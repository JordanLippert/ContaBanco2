package br.univali.cc.prog3.banco;

import exception.ExceptionBanco;
import exception.ExceptionConta;

import java.util.ArrayList;

public class Banco {

    private int numero;
    private String nome;
    private ArrayList<ContaCorrente> contas;

    public Banco(int numero, String nome) {
        this.numero = numero;
        this.nome = nome;
        this.contas = new ArrayList<>();
    }

    public void criarConta(double saldoInicial){
        ContaCorrente conta = new ContaCorrente(contas.size() + 1, saldoInicial);
        contas.add(conta);
    }

    public void criarConta(double saldoInicial, double limite) {
        ContaCorrente conta = new ContaCorrente(contas.size() + 1, saldoInicial, limite);
        contas.add(conta);
    }

    public void depositar(int conta, double valor) throws ExceptionBanco, ExceptionConta {
        ContaCorrente contaCorrente = localizarConta(conta);
        if (contaCorrente != null) {
            contaCorrente.depositar(valor);
        } else {
            throw new ExceptionBanco(ExceptionBanco.erroDeposito());
        }
    }

    public void sacar(int conta, double valor) throws ExceptionBanco, ExceptionConta {
        ContaCorrente contaCorrente = localizarConta(conta);
        if (contaCorrente != null) {
            contaCorrente.sacar(valor);
        } else {
            throw new ExceptionBanco(ExceptionBanco.erroSaque());
        }
    }

    public String emitirExtrato(int conta) throws ExceptionBanco {
        ContaCorrente contaCorrente = localizarConta(conta);
        if (contaCorrente != null) {
            return contaCorrente.emitirExtrato();
        } else {
            throw new ExceptionBanco(ExceptionBanco.erroExtrato());
        }
    }

    public void transferir(int contaOrigem, int contaDestino, double valor) throws ExceptionBanco, ExceptionConta {
        ContaCorrente contaCorrenteOrigem = localizarConta(contaOrigem);
        ContaCorrente contaCorrenteDestino = localizarConta(contaDestino);
        if (contaCorrenteOrigem != null && contaCorrenteDestino != null){
            if (contaCorrenteOrigem.getSaldo() >= valor) {
                contaCorrenteOrigem.sacar(valor);
                contaCorrenteDestino.depositar(valor);
            } else {
                throw new ExceptionBanco(ExceptionBanco.erroSaldoInsuficiente());
            }
        } else {
            throw new ExceptionBanco(ExceptionBanco.erroTransferencia());
        }
    }

    public Double verSaldo(int conta) throws ExceptionBanco {
        ContaCorrente contaCorrente = localizarConta(conta);
        if (contaCorrente != null) {
            return contaCorrente.getSaldo();
        } else {
            throw new ExceptionBanco(ExceptionBanco.erroSaldo());
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
