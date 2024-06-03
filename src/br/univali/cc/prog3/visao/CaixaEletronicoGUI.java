package br.univali.cc.prog3.visao;

import br.univali.cc.prog3.banco.Banco;
import exception.ExceptionBanco;
import exception.ExceptionCaixaEletronico;
import exception.ExceptionConta;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CaixaEletronicoGUI {

    private Banco banco;

    public CaixaEletronicoGUI(Banco banco) {
        this.banco = banco;
        initialize();
    }

    private void initialize() {
        JFrame frame = new JFrame("Caixa Eletrônico");
        frame.setSize(400, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JLabel lblNome = new JLabel(banco.getNome());
        lblNome.setBounds(100, 0, 200, 30);
        frame.add(lblNome);
        JLabel lblAgencia = new JLabel("Agência: " + banco.getNumero());
        lblAgencia.setBounds(100, 10, 200, 30);
        frame.add(lblAgencia);

        JButton btnCriarConta = new JButton("Criar Conta");
        btnCriarConta.setBounds(100, 50, 200, 30);
        JButton btnDepositar = new JButton("Depositar");
        btnDepositar.setBounds(100, 90, 200, 30);
        JButton btnSacar = new JButton("Sacar");
        btnSacar.setBounds(100, 130, 200, 30);
        JButton btnTransferir = new JButton("Transferir");
        btnTransferir.setBounds(100, 170, 200, 30);
        JButton btnEmitirExtrato = new JButton("Emitir Extrato");
        btnEmitirExtrato.setBounds(100, 210, 200, 30);
        JButton btnVerSaldo = new JButton("Ver Saldo");
        btnVerSaldo.setBounds(100, 250, 200, 30);
        JButton btnSair = new JButton("Sair");
        btnSair.setBounds(100, 290, 200, 30);

        btnCriarConta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    criarConta();
                } catch (ExceptionCaixaEletronico | ExceptionBanco ex) {
                    JOptionPane.showMessageDialog(frame, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnDepositar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    depositar();
                } catch (ExceptionCaixaEletronico ex) {
                    JOptionPane.showMessageDialog(frame, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnSacar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    sacar();
                } catch (ExceptionCaixaEletronico ex) {
                    JOptionPane.showMessageDialog(frame, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnTransferir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    transferencia();
                } catch (ExceptionCaixaEletronico ex) {
                    JOptionPane.showMessageDialog(frame, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnEmitirExtrato.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    emitirExtrato();
                } catch (ExceptionCaixaEletronico ex) {
                    JOptionPane.showMessageDialog(frame, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnVerSaldo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    verSaldo();
                } catch (ExceptionCaixaEletronico ex) {
                    JOptionPane.showMessageDialog(frame, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        frame.add(btnCriarConta);
        frame.add(btnDepositar);
        frame.add(btnSacar);
        frame.add(btnTransferir);
        frame.add(btnEmitirExtrato);
        frame.add(btnVerSaldo);
        frame.add(btnSair);
        frame.setVisible(true);
    }

    private void criarConta() throws ExceptionCaixaEletronico, ExceptionBanco {
        String tipoConta = JOptionPane.showInputDialog("Que tipo de conta deseja criar? [e]special / [n]ormal: ");
        boolean especial = tipoConta != null && tipoConta.toLowerCase().charAt(0) == 'e';

        String saldoInicialStr = JOptionPane.showInputDialog("Digite o saldo inicial da conta: ");
        double saldoInicial = Double.parseDouble(saldoInicialStr);

        if (especial) {
            String limiteStr = JOptionPane.showInputDialog("Digite o limite da conta: ");
            double limite = Double.parseDouble(limiteStr);
            banco.criarConta(saldoInicial, limite);
        } else {
            banco.criarConta(saldoInicial);
        }
    }

    private void depositar() throws ExceptionCaixaEletronico {
        try {
            int numero = Integer.parseInt(JOptionPane.showInputDialog("Digite o numero da conta: "));
            double valor = Double.parseDouble(JOptionPane.showInputDialog("Digite o valor a ser depositado: "));
            banco.depositar(numero, valor);
        } catch (ExceptionBanco | ExceptionConta e) {
            throw new ExceptionCaixaEletronico("Erro ao depositar: " + e.getMessage());
        }
    }

    private void sacar() throws ExceptionCaixaEletronico {
        try {
            int numero = Integer.parseInt(JOptionPane.showInputDialog("Digite o numero da conta: "));
            double valor = Double.parseDouble(JOptionPane.showInputDialog("Digite o valor a ser sacado: "));
            banco.sacar(numero, valor);
        } catch (ExceptionBanco | ExceptionConta e) {
            throw new ExceptionCaixaEletronico("Erro ao sacar: " + e.getMessage());
        }
    }

    private void transferencia() throws ExceptionCaixaEletronico {
        try {
            int numeroOrigem = Integer.parseInt(JOptionPane.showInputDialog("Digite o numero da conta origem: "));
            int numeroDestino = Integer.parseInt(JOptionPane.showInputDialog("Digite o numero da conta destino: "));
            double valor = Double.parseDouble(JOptionPane.showInputDialog("Digite o valor a ser transferido: "));
            banco.transferir(numeroOrigem, numeroDestino, valor);
        } catch (ExceptionBanco | ExceptionConta e) {
            throw new ExceptionCaixaEletronico("Erro ao transferir: " + e.getMessage());
        }
    }

    private void emitirExtrato() throws ExceptionCaixaEletronico {
        try {
            int numero = Integer.parseInt(JOptionPane.showInputDialog("Digite o numero da conta: "));
            String extrato = banco.emitirExtrato(numero);
            JOptionPane.showMessageDialog(null, extrato);
        } catch (ExceptionBanco e) {
            throw new ExceptionCaixaEletronico("Erro ao emitir extrato: " + e.getMessage());
        }
    }

    private void verSaldo() throws ExceptionCaixaEletronico {
        try {
            int numero = Integer.parseInt(JOptionPane.showInputDialog("Digite o numero da conta: "));
            Double saldo = banco.verSaldo(numero);
            JOptionPane.showMessageDialog(null, "Saldo da conta " + numero + ": " + saldo);
        } catch (ExceptionBanco e) {
            throw new ExceptionCaixaEletronico("Erro ao ver saldo: " + e.getMessage());
        }
    }
}
