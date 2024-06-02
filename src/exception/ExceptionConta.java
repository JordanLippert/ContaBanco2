package exception;

public class ExceptionConta extends Exception {

    public ExceptionConta(String message){
        super(message);
    }

    public static String erroDeposito(){
        return "Valor de depósito não é válido!";
    }

    public static String erroSaque(){
        return "Saldo insuficiente para saque!";
    }
}
