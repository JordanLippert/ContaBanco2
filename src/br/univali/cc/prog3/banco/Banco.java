package br.univali.cc.prog3.banco;

import exception.ExceptionBanco;
import exception.ExceptionConta;

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

    public String depositar(int conta, double valor) throws ExceptionBanco, ExceptionConta {
        ContaCorrente contaCorrente = this.localizarConta(conta);
        if (contaCorrente != null) {
            contaCorrente.depositar(valor);
        }
        else {
            throw new ExceptionBanco("Erro ao realizar depósito!");
        }
        return ExceptionBanco.erroDeposito();
    }

    public String sacar(int conta, double valor) throws ExceptionBanco {
        ContaCorrente contaCorrente = this.localizarConta(conta);
        if (contaCorrente != null) {
            contaCorrente.sacar(valor);
        }
        else {
            throw new ExceptionBanco("Erro ao realizar saque!");
        }
        return ExceptionBanco.erroSaque();
    }

    public String emitirExtrato(int conta) throws ExceptionBanco {
        ContaCorrente contaCorrente = this.localizarConta(conta);
        if (contaCorrente != null) {
            return contaCorrente.emitirExtrato();
        }
        else {
            throw new ExceptionBanco("Erro ao emitir extrato!");
        }
        return ExceptionBanco.erroExtrato();
    }

    public String transferir(int contaOrigem, int contaDestino, double valor) throws ExceptionBanco, ExceptionConta {
        ContaCorrente contaCorrenteOrigem = this.localizarConta(contaOrigem);
        ContaCorrente contaCorrenteDestino = this.localizarConta(contaDestino);
        if (contaCorrenteOrigem != null && contaCorrenteDestino != null){
            if (contaCorrenteOrigem.getSaldo() >= valor) {
                contaCorrenteOrigem.sacar(valor);
                contaCorrenteDestino.depositar(valor);
            }
            else {
                throw new ExceptionBanco("Erro ao realizar transferência!");
            }
            return ExceptionBanco.erroSaldoInsuficiente();
        }
        else {
            throw new ExceptionBanco("Erro ao realizar transferência!");
        }
        return ExceptionBanco.erroTransferencia();
    }

    public String verSaldo(int conta) throws ExceptionBanco {
        ContaCorrente contaCorrente = this.localizarConta(conta);
        if (contaCorrente != null) {
            System.out.println("Saldo da conta " + conta + ": " + contaCorrente.getSaldo());
        }
        else {
            throw new ExceptionBanco("Erro ao verificar saldo!");
        }
        return ExceptionBanco.erroSaldo();
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
