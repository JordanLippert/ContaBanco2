package exception;

public class ExceptionBanco extends Exception{

    public ExceptionBanco(String message){
        super(message);
    }

    public static String erroDeposito(){
        return "Conta não encontrada";
    }

    public static String erroSaque(){
        return "Conta não encontrada";
    }

    public static String erroExtrato(){
        return "Conta não encontrada";
    }

    public static String erroTransferencia(){
        return "Conta não encontrada";
    }

    public static String erroSaldoInsuficiente(){
        return "Saldo insuficiente para transferência";
    }

    public static String erroSaldo(){
        return "Conta não encontrada";
    }

}
