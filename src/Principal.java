import br.univali.cc.prog3.banco.Banco;
import br.univali.cc.prog3.visao.CaixaEletronico;
import br.univali.cc.prog3.visao.CaixaEletronicoGUI;

public class Principal {

    public static void main(String[] args) throws Exception {

        System.out.println("Olá, antes de iniciarmos o programa, escolha qual interface deseja utilizar: ");
        System.out.println("1 - Interface gráfica");
        System.out.println("2 - Interface de texto");
        System.out.print("Digite a opção desejada: ");
        int opcao = Integer.parseInt(System.console().readLine());

        if (opcao == 1){
            Banco banco = new Banco(1964, "BankVali");
            CaixaEletronicoGUI caixa = new CaixaEletronicoGUI(banco);
        }
        else if (opcao == 2){
            Banco banco = new Banco(1964, "BankVali");
            CaixaEletronico caixa = new CaixaEletronico(banco);
            caixa.menu();
        }
        else{
            System.out.println("Opção inválida!");
        }

        /*

        Banco banco = new Banco(1964, "BankVali");
        CaixaEletronico caixa = new CaixaEletronico(banco);
        caixa.menu();

        CaixaEletronicoGUI caixa = new CaixaEletronicoGUI(banco);

        Banco banco = new Banco(1, "Banco do Brasil");
        banco.criarConta(1000);
        banco.criarConta(1000, 1000);

        banco.depositar(1, 500);
        banco.sacar(2, 250);
        banco.transferir(2, 1, 800);
        banco.sacar(2, 500);

        banco.transferir(1, 2, 1200);

        System.out.println(banco.emitirExtrato(1));
        System.out.println(banco.emitirExtrato(2));

        System.out.println("deu");
        */
    }

}
