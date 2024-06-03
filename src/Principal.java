import br.univali.cc.prog3.banco.Banco;
import br.univali.cc.prog3.visao.CaixaEletronico;
import br.univali.cc.prog3.visao.CaixaEletronicoGUI;

import java.util.Scanner;

public class Principal {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Olá, antes de iniciarmos o programa, escolha qual interface deseja utilizar: ");
        System.out.println("1 - Interface gráfica");
        System.out.println("2 - Interface de texto");
        System.out.print("Digite a opção desejada: ");
        int opcao = scanner.nextInt();

        Banco banco = new Banco(1964, "BankVali");

        if (opcao == 1) {
            CaixaEletronicoGUI caixa = new CaixaEletronicoGUI(banco);
        } else if (opcao == 2) {
            CaixaEletronico caixa = new CaixaEletronico(banco);
            caixa.menu();
        } else {
            System.out.println("Opção inválida!");
        }

        scanner.close();
    }
}
