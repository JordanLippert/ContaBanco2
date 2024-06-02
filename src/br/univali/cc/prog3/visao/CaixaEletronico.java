package br.univali.cc.prog3.visao;

import br.univali.cc.prog3.banco.Banco;
import exception.ExceptionBanco;

import java.util.Scanner;


public class CaixaEletronico {

    Banco banco;

    public CaixaEletronico(Banco banco) {
        this.banco = banco;
    }

    private String lerValor(String rotulo) {
        System.out.print(rotulo+": ");
        Scanner leitor = new Scanner(System.in);
        return leitor.nextLine();
    }

    public void menu() throws Exception {
        char opcao;
        do {
            System.out.println(" Caixa Eletronico do banco "+banco.getNome());
            System.out.println("\tAgencia: "+banco.getNumero());
            System.out.println(" Menu de opções");
            System.out.println(" 1. Criar Conta corrente");
            System.out.println(" 2. Depositar");
            System.out.println(" 3. Sacar");
            System.out.println(" 4. Transferência");
            System.out.println(" 5. Emitir extrato");
            System.out.println(" 6. Ver Saldo");
            System.out.println(" s. Sair");
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

    private void criarConta() {
        boolean especial = lerValor("Que tipo de conta deseja criar ? [e]special / [n]ormal: ")
                .toLowerCase()
                .charAt(0) == 'e';

        double saldoInicial = Double.parseDouble(lerValor("Digite o saldo inicial da conta: "));

        if (especial){
            double limite = Double.parseDouble(lerValor("Digite o limite da conta: "));
            try {
                this.banco.criarConta(saldoInicial, limite);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            try {
                this.banco.criarConta(saldoInicial);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void depositar() throws ExceptionBanco {
        int numero = Integer.parseInt(lerValor("Digite o numero da conta: "));
        double valor = Double.parseDouble(lerValor("Digite o valor a ser depositado: "));
        this.banco.depositar(numero, valor);
    }

    private void sacar() throws ExceptionBanco {
        int numero = Integer.parseInt(lerValor("Digite o numero da conta: "));
        double valor = Double.parseDouble(lerValor("Digite o valor a ser sacado: "));
        this.banco.sacar(numero, valor);
    }

    private void transferencia() throws Exception {
        int origem = Integer.parseInt(lerValor("Digite o numero da conta de origem: "));
        int destino = Integer.parseInt(lerValor("Digite o numero da conta de destino: "));
        double valor = Double.parseDouble(lerValor("Digite o valor a ser transferido: "));
        this.banco.transferir(origem, destino, valor);
    }

    private void emitirExtrato() throws ExceptionBanco {
        int numero = Integer.parseInt(lerValor("Digite o numero da conta: "));
        System.out.println(this.banco.emitirExtrato(numero));
    }

    private void verSaldo() throws ExceptionBanco {
        int numero = Integer.parseInt(lerValor("Digite o numero da conta: "));
        System.out.println(this.banco.verSaldo(numero));
    }
}
