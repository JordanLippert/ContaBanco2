
package br.univali.cc.prog3.visao;

import br.univali.cc.prog3.banco.Banco;
import exception.ExceptionBanco;
import exception.ExceptionCaixaEletronico;


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
            opcoes += "\n6. Ver Saldo";
            opcoes += "\ns. Sair";

            opcao = this.lerValor(opcoes).charAt(0);

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


    private void criarConta() throws ExceptionCaixaEletronico {
        boolean especial = lerValor("Que tipo de conta deseja criar ? [e]special / [n]ormal")
                .toLowerCase()
                .charAt(0) == 'e';

        double saldoInicial = Double.parseDouble(lerValor("Digite o saldo inicial da conta: "));

        if (especial) {
            double limite = Double.parseDouble(lerValor("Digite o limite da conta: "));
            try {
                this.banco.criarConta(saldoInicial, limite);
            } catch (ExceptionBanco e) {
                throw new ExceptionCaixaEletronico("Erro ao criar conta especial: " + e.getMessage());
            }
        } else {
            try {
                this.banco.criarConta(saldoInicial);
            } catch (ExceptionBanco e) {
                throw new ExceptionCaixaEletronico("Erro ao criar conta normal: " + e.getMessage());
            }
        }
    }

    private void depositar() throws ExceptionCaixaEletronico {
        try {
            int numero = Integer.parseInt(lerValor("Digite o numero da conta"));
            double valor = Double.parseDouble(lerValor("Digite o valor a ser depositado: "));
            this.banco.depositar(numero, valor);
        } catch (ExceptionBanco e) {
            throw new ExceptionCaixaEletronico("Erro ao realizar depósito: " + e.getMessage());
        } catch (ExceptionConta e) {
            throw new ExceptionCaixaEletronico("Erro ao realizar depósito: " + e.getMessage());
        }
    }

    private void sacar() throws ExceptionCaixaEletronico {
        try {
            int numero = Integer.parseInt(lerValor("Digite o numero da conta"));
            double valor = Double.parseDouble(lerValor("Digite o valor a ser sacado: "));
            this.banco.sacar(numero, valor);
        } catch (ExceptionBanco e) {
            throw new ExceptionCaixaEletronico("Erro ao realizar saque: " + e.getMessage());
        } catch (ExceptionConta e) {
            throw new ExceptionCaixaEletronico("Erro ao realizar saque: " + e.getMessage());
        }
    }

    private void transferencia() throws ExceptionCaixaEletronico {
        try {
            int origem = Integer.parseInt(lerValor("Digite o numero da conta de origem"));
            int destino = Integer.parseInt(lerValor("Digite o numero da conta de destino"));
            double valor = Double.parseDouble(lerValor("Digite o valor a ser transferido: "));
            this.banco.transferir(origem, destino, valor);
        } catch (ExceptionBanco e) {
            throw new ExceptionCaixaEletronico("Erro ao realizar transferência: " + e.getMessage());
        } catch (ExceptionConta e) {
            throw new ExceptionCaixaEletronico("Erro ao realizar transferência: " + e.getMessage());
        }
    }

    private void emitirExtrato() throws ExceptionCaixaEletronico {
        try {
            int numero = Integer.parseInt(lerValor("Digite o numero da conta: "));
            this.escreverValor(this.banco.emitirExtrato(numero));
        } catch (ExceptionBanco e) {
            throw new ExceptionCaixaEletronico("Erro ao emitir extrato: " + e.getMessage());
        }
    }

    private void verSaldo() throws ExceptionCaixaEletronico {
        try {
            int numero = Integer.parseInt(lerValor("Digite o numero da conta: "));
            this.escreverValor(this.banco.verSaldo(numero));
        } catch (ExceptionBanco e) {
            throw new ExceptionCaixaEletronico("Erro ao verificar saldo: " + e.getMessage());
        }
    }
}
