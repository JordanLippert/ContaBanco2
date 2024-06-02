import br.univali.cc.prog3.banco.Banco;
import br.univali.cc.prog3.visao.CaixaEletronico;
import br.univali.cc.prog3.visao.CaixaEletronicoGUI;

public class Principal {

    public static void main(String[] args) throws Exception {
        Banco banco = new Banco(1964, "BankVali");
        CaixaEletronico caixa = new CaixaEletronico(banco);
        caixa.menu();

        /*
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
