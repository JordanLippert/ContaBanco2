package br.univali.cc.prog3.visao;

import br.univali.cc.prog3.banco.Banco;
import exception.ExceptionBanco;
import exception.ExceptionCaixaEletronico;

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
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JButton btnCriarConta = new JButton("Criar Conta");
        btnCriarConta.setBounds(100, 30, 200, 30);
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

        frame.add(btnCriarConta);
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
}
