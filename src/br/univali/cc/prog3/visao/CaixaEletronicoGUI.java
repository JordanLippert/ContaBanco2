
package br.univali.cc.prog3.visao;

import br.univali.cc.prog3.banco.Banco;
import java.util.Scanner;
import javax.swing.JOptionPane;


public class CaixaEletronicoGUI {
    Banco banco;

    public CaixaEletronicoGUI(Banco banco) {
        this.banco = banco;
    }

    private String lerValor(String rotulo) {
        return JOptionPane.showInputDialog(null, rotulo);
    }

    private void escreverValor(String valor) {
        JOptionPane.showMessageDialog(null, valor);
    }

    public void menu() throws Exception {
        char opcao;
        do {
            String opcoes = "\nCaixa Eletronico do banco "+banco.getNome();
            opcoes += "\nAgencia: "+banco.getNumero();
            opcoes += "\nMenu de opções";
            opcoes += "\n1. Criar Conta corrente";
            opcoes += "\n2. Depositar";
            opcoes += "\n3. Sacar";
            opcoes += "\n4. Transferir";
            opcoes += "\n5. Emitir extrato";
            opcoes += "\ns. Sair";

            opcao = this.lerValor(opcoes).charAt(0);

            switch(opcao) {
                case '1': this.criarConta(); break;
                case '2': this.depositar(); break;
                case '3': this.sacar(); break;
                case '4': this.transferencia();break;
                case '5': this.emitirExtrato();break;
            }

        } while (opcao != 's');
    }


    private void criarConta() throws Exception {
        boolean especial = lerValor("Que tipo de conta deseja criar ? [e]special / [n]ormal")
                .toLowerCase()
                .charAt(0) == 'e';

        double saldoInicial = Double.parseDouble(lerValor("Digite o saldo inicial da conta: "));

        if (especial){
            double limite = Double.parseDouble(lerValor("Digite o limite da conta: "));
            this.banco.criarConta(saldoInicial, limite);
        } else {
            this.banco.criarConta(saldoInicial);
        }
    }

    private void depositar() {
        int numero = Integer.parseInt(lerValor("Digite o numero da conta"));
        double valor = Double.parseDouble(lerValor("Digite o valor a ser depositado: "));
        this.banco.depositar(numero, valor);
    }

    private void sacar() {
        int numero = Integer.parseInt(lerValor("Digite o numero da conta"));
        double valor = Double.parseDouble(lerValor("Digite o valor a ser sacado: "));
        this.banco.sacar(numero, valor);
    }

    private void transferencia() throws Exception {
        int origem = Integer.parseInt(lerValor("Digite o numero da conta de origem"));
        int destino = Integer.parseInt(lerValor("Digite o numero da conta de destino"));
        double valor = Double.parseDouble(lerValor("Digite o valor a ser transferido: "));
        this.banco.transferir(origem, destino, valor);
    }

    private void emitirExtrato() {
        int numero = Integer.parseInt(lerValor("Digite o numero da conta: "));
        this.escreverValor(this.banco.emitirExtrato(numero));
    }
}
