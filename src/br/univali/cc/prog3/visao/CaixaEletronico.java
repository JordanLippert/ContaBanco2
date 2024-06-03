package br.univali.cc.prog3.visao;

import br.univali.cc.prog3.banco.Banco;
import exception.ExceptionBanco;
import exception.ExceptionCaixaEletronico;
import exception.ExceptionConta;

import java.util.Scanner;

public class CaixaEletronico {

    private Banco banco;

    public CaixaEletronico(Banco banco) {
        this.banco = banco;
    }

    private String lerValor(String rotulo) {
        System.out.print(rotulo + ": ");
        Scanner leitor = new Scanner(System.in);
        return leitor.nextLine();
    }

    public void menu() throws Exception {
        char opcao;
        do {
            System.out.println("Caixa Eletronico do banco " + banco.getNome());
            System.out.println("\tAgencia: " + banco.getNumero());
            System.out.println("Menu de opções");
            System.out.println("1. Criar Conta corrente");
            System.out.println("2. Depositar");
            System.out.println("3. Sacar");
            System.out.println("4. Transferência");
            System.out.println("5. Emitir extrato");
            System.out.println("6. Ver Saldo");
            System.out.println("s. Sair");
            opcao = this.lerValor("Digite uma opção do menu: ").charAt(0);

            switch(opcao) {
                case '1': this.criarConta(); break;
                case '2': this.depositar(); break;
                case '3': this.sacar(); break;
                case '4': this.transferencia();break;
                case '5': this.emitirExtrato();break;
                case '6': this.verSaldo();break;
            }

        } while (opcao != 's');
    }

    private void criarConta() throws ExceptionCaixaEletronico, ExceptionBanco {
        boolean especial = lerValor("Que tipo de conta deseja criar? [e]special / [n]ormal: ")
                .toLowerCase()
                .charAt(0) == 'e';

        double saldoInicial = Double.parseDouble(lerValor("Digite o saldo inicial da conta: "));

        if (especial) {
            double limite = Double.parseDouble(lerValor("Digite o limite da conta: "));
            banco.criarConta(saldoInicial, limite);
        } else {
            banco.criarConta(saldoInicial);
        }
    }

    private void depositar() throws ExceptionCaixaEletronico {
        try {
            int numero = Integer.parseInt(lerValor("Digite o numero da conta: "));
            double valor = Double.parseDouble(lerValor("Digite o valor a ser depositado: "));
            banco.depositar(numero, valor);
        } catch (ExceptionBanco | ExceptionConta e) {
            throw new ExceptionCaixaEletronico("Erro ao depositar: " + e.getMessage());
        }
    }

    private void sacar() throws ExceptionCaixaEletronico {
        try {
            int numero = Integer.parseInt(lerValor("Digite o numero da conta: "));
            double valor = Double.parseDouble(lerValor("Digite o valor a ser sacado: "));
            banco.sacar(numero, valor);
        } catch (ExceptionBanco | ExceptionConta e) {
            throw new ExceptionCaixaEletronico("Erro ao sacar: " + e.getMessage());
        }
    }

    private void transferencia() throws ExceptionCaixaEletronico {
        try {
            int numeroOrigem = Integer.parseInt(lerValor("Digite o numero da conta origem: "));
            int numeroDestino = Integer.parseInt(lerValor("Digite o numero da conta destino: "));
            double valor = Double.parseDouble(lerValor("Digite o valor a ser transferido: "));
            banco.transferir(numeroOrigem, numeroDestino, valor);
        } catch (ExceptionBanco | ExceptionConta e) {
            throw new ExceptionCaixaEletronico("Erro ao transferir: " + e.getMessage());
        }
    }

    private void emitirExtrato() throws ExceptionCaixaEletronico {
        try {
            int numero = Integer.parseInt(lerValor("Digite o numero da conta: "));
            String extrato = banco.emitirExtrato(numero);
            System.out.println(extrato);
        } catch (ExceptionBanco e) {
            throw new ExceptionCaixaEletronico("Erro ao emitir extrato: " + e.getMessage());
        }
    }

    private void verSaldo() throws ExceptionCaixaEletronico {
        try {
            int numero = Integer.parseInt(lerValor("Digite o numero da conta: "));
            Double saldo = banco.verSaldo(numero);
            System.out.println("Saldo da conta " + numero + ": " + saldo);
        } catch (ExceptionBanco e) {
            throw new ExceptionCaixaEletronico("Erro ao ver saldo: " + e.getMessage());
        }
    }
}
